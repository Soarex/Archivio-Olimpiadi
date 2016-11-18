package main;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Competition {
    public Discipline discipline;
    public float score;
    public short athleteId;
    public int id;

    public Competition() {

    }

    public Competition(Discipline discipline, short athleteId, float score) {
        this.athleteId = athleteId;
        this.discipline = discipline;
        this.score = score;
    }

    public String toString() {
        return discipline.getName() + " " + score + " " + athleteId;
    }

    public static final int SIZE = 4 + 4 + 2 + 4;

    public static void write(RandomAccessFile file, Competition competition) throws IOException {
        Discipline.write(file, competition.discipline);
        file.writeFloat(competition.score);
        file.writeShort(competition.athleteId);
        file.writeInt(competition.id);
    }

    public static Competition read(RandomAccessFile file) throws IOException {
        Competition res = new Competition();
        res.discipline = Discipline.read(file);
        res.score = file.readShort();
        res.athleteId = file.readShort();
        res.id = file.readInt();
        return res;
    }
}
