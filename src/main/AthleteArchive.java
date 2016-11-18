package main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class AthleteArchive {
    private RandomAccessFile file;
    private AthleteNameIndexFile nameIndex;
    private AthleteNationIndexFile nationIndex;
    private int capacity;

    public AthleteArchive(int capacity) throws IOException {
        this.capacity = capacity;
        file = new RandomAccessFile("data/athletes1.dat", "rw");
        nameIndex = new AthleteNameIndexFile();
        nationIndex = new AthleteNationIndexFile();

        if(new File("data/athletes.dat").isFile())
            return;

        Athlete a = new Athlete();
        for(int i = 0; i < capacity; i++) {
            file.writeBoolean(false);
            Athlete.write(file, a);
        }
    }

    public void write(Athlete athlete) throws IOException {
        if(athlete.id >= capacity)
            throw new IOException();


        long offset = athlete.id * (Athlete.SIZE + 1);
        file.seek(offset);
        file.writeBoolean(true);
        Athlete.write(file, athlete);

        nameIndex.add(new AthleteNameIndex(athlete.name + athlete.surname, athlete.id));
        nationIndex.add(new AthleteNationIndex(athlete.nation, athlete.id));

    }

    public Athlete read() throws IOException {
        if(file.readBoolean())
            return Athlete.read(file);
        else
            return null;
    }

    public Athlete read(int position) throws IOException {
        if(position >= capacity)
            throw new IOException();

        long offset = position * (Athlete.SIZE + 1);
        file.seek(offset);

        if(file.readBoolean())
            return Athlete.read(file);
        else
            return null;
    }

    public void delete() throws IOException {
        file.writeBoolean(false);
    }

    public void delete(int position) throws IOException {
        if(position >= capacity)
            throw new IOException();

        long offset = position * (Athlete.SIZE + 1);
        file.seek(offset);
        file.writeBoolean(false);
    }

    public void restore() throws IOException {
        file.writeBoolean(true);
    }

    public void restore(int position) throws IOException {
        if(position >= capacity)
            throw new IOException();

        long offset = position * (Athlete.SIZE + 1);
        file.seek(offset);
        file.writeBoolean(true);
    }

    public Athlete[] search(String fullname) throws IOException {
        Short[] arr = nameIndex.search(fullname);
        ArrayList<Athlete> list = new ArrayList<>();
        for(Short s : arr)
            list.add(read(s));

        return list.toArray(new Athlete[0]);
    }

    public Athlete[] search(Nation nation) throws IOException {
        Short[] arr = nationIndex.search(nation);
        ArrayList<Athlete> list = new ArrayList<>();
        for(Short s : arr)
            list.add(read(s));

        return list.toArray(new Athlete[0]);
    }

}
