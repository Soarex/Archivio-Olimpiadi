package main;

import java.io.IOException;
import java.io.RandomAccessFile;

public class AthleteNationIndex implements Comparable<AthleteNationIndex>{
    public Nation key;
    public short pointer;

    public AthleteNationIndex() {

    }

    public AthleteNationIndex(Nation name, short pointer) {
        this.key = name;
        this.pointer = pointer;
    }

    public String toString() {
        return key.getName() + " " + pointer;
    }

    public int compareTo(AthleteNationIndex nationIndex) {
        if(key.getId() > nationIndex.key.getId()) return 1;
        if(key.getId() < nationIndex.key.getId()) return -1;
        if(key.getId() == nationIndex.key.getId()) return 0;
        return 0;
    }

    public static final int SIZE = 4 + 2;

    public static void write(RandomAccessFile file, AthleteNationIndex nationIndex) throws IOException {
        Nation.write(file, nationIndex.key);
        file.writeShort(nationIndex.pointer);
    }

    public static AthleteNationIndex read(RandomAccessFile file) throws IOException {
        AthleteNationIndex res = new AthleteNationIndex();
        res.key = Nation.read(file);
        res.pointer = file.readShort();
        return res;
    }

}
