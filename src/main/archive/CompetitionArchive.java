package main.archive;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CompetitionArchive {
    private RandomAccessFile file;
    private int capacity;

    public CompetitionArchive(String filepath, int capacity) throws IOException {
        this.capacity = capacity;
        file = new RandomAccessFile(filepath, "rw");
        if(new File(filepath).isFile())
            return;

        Competition a = new Competition();
        for(int i = 0; i < capacity; i++) {
            file.writeBoolean(false);
            Competition.write(file, a);
        }
    }

    public void write(Competition competition) throws IOException {
        file.writeBoolean(true);
        Competition.write(file, competition);
    }

    public void write(Competition competition, int position) throws IOException {
        if(position >= capacity)
            throw new IOException();

        long offset = position * (Competition.SIZE + 1);
        file.seek(offset);
        file.writeBoolean(true);
        Competition.write(file, competition);
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
}
