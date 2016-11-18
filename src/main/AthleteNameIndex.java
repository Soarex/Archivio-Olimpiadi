package main;

import java.io.IOException;
import java.io.RandomAccessFile;

public class AthleteNameIndex implements Comparable<AthleteNameIndex>{
    public String key;
    public short pointer;

    public AthleteNameIndex() {

    }

    public AthleteNameIndex(String name, short pointer) {
        this.key = name;
        this.pointer = pointer;
    }

    public String toString() {
        return key + " " + pointer;
    }

    public int compareTo(AthleteNameIndex nameIndex) {
        return key.compareTo(nameIndex.key);
    }

    public static final int NAME_LENGHT = 50;
    public static final int NAME_SIZE = NAME_LENGHT * 2;
    public static final int SIZE = NAME_SIZE + 2;

    public static void write(RandomAccessFile file, AthleteNameIndex nameIndex) throws IOException {
        writeName(file, nameIndex.key);
        file.writeShort(nameIndex.pointer);
    }

    private static void writeName(RandomAccessFile file, String name) throws IOException {
        StringBuffer buffer = new StringBuffer(NAME_LENGHT);
        buffer.insert(0, name);
        buffer.setLength(NAME_LENGHT);
        file.writeChars(buffer.toString());
    }

    public static AthleteNameIndex read(RandomAccessFile file) throws IOException {
        AthleteNameIndex res = new AthleteNameIndex();
        res.key = readName(file);
        res.pointer = file.readShort();
        return res;
    }

    private static String readName(RandomAccessFile file) throws IOException {
        char[] buffer = new char[NAME_LENGHT];
        for(int i = 0; i < NAME_LENGHT; i++)
            buffer[i] = file.readChar();

        String res = new String(buffer);
        res = res.replaceAll("\0", "");
        return res;
    }
}
