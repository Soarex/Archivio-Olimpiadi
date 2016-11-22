package main.archive;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class CompetitionArchive {
    private RandomAccessFile file;
    private IndexFile athleteIndex;
    private IndexFile disciplineIndex;
    private int capacity;

    public CompetitionArchive(int capacity) throws IOException {
        this.capacity = capacity;
        athleteIndex = new IndexFile("data/competion_atlete.ndx", new ShortIndex());
        disciplineIndex = new IndexFile("data/competion_discipline.ndx", new DisciplineIndex());

        if(new File("data/competitions.dat").isFile()) {
            file = new RandomAccessFile("data/competitions.dat", "rw");
            return;
        }

        file = new RandomAccessFile("data/competitions.dat", "rw");

        Competition a = new Competition();
        for(int i = 0; i < capacity; i++) {
            file.writeBoolean(false);
            Competition.write(file, a);
        }
    }

    public void write(Competition competition) throws IOException, IllegalAccessException, InstantiationException  {
        if(competition.id >= capacity)
            throw new IOException();

        long offset = competition.id * (Competition.SIZE + 1);
        file.seek(offset);
        file.writeBoolean(true);
        Competition.write(file, competition);

        athleteIndex.add(new ShortIndex(competition.athleteId, competition.id));
        disciplineIndex.add(new DisciplineIndex(competition.discipline, competition.id));
    }

    public Competition read() throws IOException {
        if(file.readBoolean())
            return Competition.read(file);
        else
            return null;
    }

    public Competition read(int position) throws IOException {
        if(position >= capacity)
            throw new IOException();

        long offset = position * (Competition.SIZE + 1);
        file.seek(offset);

        if(file.readBoolean())
            return Competition.read(file);
        else
            return null;
    }

    public void delete() throws IOException {
        file.writeBoolean(false);
    }

    public void delete(int position) throws IOException {
        if(position >= capacity)
            throw new IOException();

        long offset = position * (Competition.SIZE + 1);
        file.seek(offset);
        file.writeBoolean(false);
    }

    public void restore() throws IOException {
        file.writeBoolean(true);
    }

    public void restore(int position) throws IOException {
        if (position >= capacity)
            throw new IOException();

        long offset = position * (Competition.SIZE + 1);
        file.seek(offset);
        file.writeBoolean(true);
    }

    public Competition[] search(Short athleteId) throws IOException, IllegalAccessException, InstantiationException {
        Short[] arr = athleteIndex.search(athleteId);
        ArrayList<Competition> list = new ArrayList<>();
        for(Short s : arr)
            list.add(read(s));

        return list.toArray(new Competition[0]);
    }

    public Competition[] search(Discipline discipline) throws IOException, IllegalAccessException, InstantiationException {
        Short[] arr = disciplineIndex.search(discipline);
        ArrayList<Competition> list = new ArrayList<>();
        for(Short s : arr)
            list.add(read(s));

        return list.toArray(new Competition[0]);
    }
}
