package main.archive;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class AthleteArchive {
    private RandomAccessFile file;
    private IndexFile nameIndex;
    private IndexFile nationIndex;
    private int capacity;

    public AthleteArchive(int capacity) throws IOException {
        this.capacity = capacity;
        nameIndex = new IndexFile("data/athletes_name.ndx", new NameIndex());
        nationIndex = new IndexFile("data/athletes_nation.ndx", new NationIndex());

        if(new File("data/athletes.dat").isFile()) {
            file = new RandomAccessFile("data/athletes.dat", "rw");
            return;
        }

        file = new RandomAccessFile("data/athletes.dat", "rw");
        Athlete a = new Athlete();
        for(int i = 0; i < capacity; i++) {
            file.writeBoolean(false);
            Athlete.write(file, a);
        }
    }

    public void write(Athlete athlete) throws IOException, IllegalAccessException, InstantiationException {
        if(athlete.id >= capacity)
            throw new IOException();


        long offset = athlete.id * (Athlete.SIZE + 1);
        file.seek(offset);
        file.writeBoolean(true);
        Athlete.write(file, athlete);

        nameIndex.add(new NameIndex(athlete.name + athlete.surname, athlete.id));
        nationIndex.add(new NationIndex(athlete.nation, athlete.id));

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

    public Athlete[] search(String fullname) throws IOException, IllegalAccessException, InstantiationException {
        Short[] arr = nameIndex.search(fullname);
        ArrayList<Athlete> list = new ArrayList<>();
        for(Short s : arr)
            list.add(read(s));

        return list.toArray(new Athlete[0]);
    }

    public Athlete[] search(Nation nation) throws IOException, IllegalAccessException, InstantiationException {
        Short[] arr = nationIndex.search(nation);
        ArrayList<Athlete> list = new ArrayList<>();
        for(Short s : arr)
            list.add(read(s));

        return list.toArray(new Athlete[0]);
    }

    public Athlete[] get(int l, int r) throws IOException, IllegalAccessException, InstantiationException {
        Short[] arr = nationIndex.get(l, r);
        ArrayList<Athlete> list = new ArrayList<>();
        for(Short s : arr)
            list.add(read(s));

        return list.toArray(new Athlete[0]);
    }

}
