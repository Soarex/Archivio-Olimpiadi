package main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AthleteArchive {
    private RandomAccessFile file;
    private int capacity;

    public AthleteArchive(String filepath, int capacity) throws IOException {
        this.capacity = capacity;
        file = new RandomAccessFile(filepath, "rw");
        if(new File(filepath).canRead())
            return;

        Athlete a = new Athlete();
        for(int i = 0; i < capacity; i++)
            Athlete.write(file, a);
    }

    public void write(Athlete athlete) throws IOException {
        Athlete.write(file, athlete);
    }

    public void write(Athlete athlete, int position) throws IOException {
        if(position >= capacity)
            throw new IOException();

        long offset = position * Athlete.SIZE;
        file.seek(offset);
        Athlete.write(file, athlete);
    }

    public Athlete read() throws IOException {
        return Athlete.read(file);
    }

    public Athlete read(int position) throws IOException {
        if(position >= capacity)
            throw new IOException();

        long offset = position * Athlete.SIZE;
        file.seek(offset);
        return Athlete.read(file);
    }
}
