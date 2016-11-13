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
        if(new File(filepath).isFile())
            return;

        Athlete a = new Athlete();
        for(int i = 0; i < capacity; i++) {
            file.writeBoolean(false);
            Athlete.write(file, a);
        }
    }

    public void write(Athlete athlete) throws IOException {
        file.writeBoolean(true);
        Athlete.write(file, athlete);
    }

    public void write(Athlete athlete, int position) throws IOException {
        if(position >= capacity)
            throw new IOException();

        long offset = position * (Athlete.SIZE + 1);
        file.seek(offset);
        file.writeBoolean(true);
        Athlete.write(file, athlete);
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

}
