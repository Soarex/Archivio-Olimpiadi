package main.archive;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Athlete {
    public String name, surname;
    public LocalDate date;
    public short id;
    public Nation nation;

    public Athlete() {
        nation = Nation.NULL;
        date = LocalDate.parse("1999-01-01");
    }

    public Athlete(String name, String surname, Nation nation, LocalDate date, int id) {
        this.name = name;
        this.surname = surname;
        this.nation = nation;
        this.date = date;
        this.id = (short)id;
    }

    public String toString() {
        return name + " " + surname + " " + nation.getName() + " " + date.getDayOfMonth() + "/" + date.getMonth() + "/" + date.getYear() + " " + id;
    }

    public String getDate() {
        return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
    }

    public static final int NAME_LENGHT = 25;
    public static final int NAME_SIZE = NAME_LENGHT * 2;
    public static final int SIZE = NAME_SIZE * 2 + 2 + 4 + 4 + 4 + 4;

    public static void write(RandomAccessFile file, Athlete athlete) throws IOException {
        writeName(file, athlete.name);
        writeName(file, athlete.surname);
        writeDate(file, athlete.date);
        file.writeShort(athlete.id);
        Nation.write(file, athlete.nation);
    }

    private static void writeName(RandomAccessFile file, String name) throws IOException {
        StringBuffer buffer = new StringBuffer(NAME_LENGHT);
        buffer.insert(0, name);
        buffer.setLength(NAME_LENGHT);
        file.writeChars(buffer.toString());
    }

    private static void writeDate(RandomAccessFile file, LocalDate date) throws IOException {
        file.writeInt(date.getYear());
        file.writeInt(date.getMonthValue());
        file.writeInt(date.getDayOfMonth());
    }

    public static Athlete read(RandomAccessFile file) throws IOException {
        Athlete res = new Athlete();
        res.name = readName(file);
        res.surname = readName(file);
        res.date = readDate(file);
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

    private static LocalDate readDate(RandomAccessFile file) throws IOException {
        String res = "";
        res = res +  String.format("%04d", file.readInt()) + "-";
        res = res +  String.format("%02d", file.readInt()) + "-";
        res = res +  String.format("%02d", file.readInt());

        return LocalDate.parse(res);
    }
}
