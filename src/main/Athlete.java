package main;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Athlete {
    public String name, surname;
    public short id;
    public Nation nation;

    public Athlete() {
        nation = Nation.NULL;
    }

    public Athlete(String name, String surname, Nation nation) {
        this.name = name;
        this.surname = surname;
        this.nation = nation;
    }

    public String toString() {
        return name + " " + surname + " " + nation + " " + id;
    }

    public static final int NAME_LENGHT = 25;
    public static final int NAME_SIZE = NAME_LENGHT * 2;
    public static final int SIZE = NAME_SIZE * 2 + 2 + 4;

    public static void write(RandomAccessFile file, Athlete athlete) throws IOException {
        writeName(file, athlete.name);
        writeName(file, athlete.surname);
        file.writeShort(athlete.id);
        Nation.write(file, athlete.nation);
    }

    private static void writeName(RandomAccessFile file, String name) throws IOException {
        StringBuffer buffer = new StringBuffer(NAME_LENGHT);
        buffer.insert(0, name);
        buffer.setLength(NAME_LENGHT);
        file.writeChars(buffer.toString());
    }

    public static Athlete read(RandomAccessFile file) throws IOException {
        Athlete res = new Athlete();
        res.name = readName(file);
        res.surname = readName(file);
        res.id = file.readShort();
        res.nation = Nation.read(file);
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
