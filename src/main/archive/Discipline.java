package main.archive;

import java.io.IOException;
import java.io.RandomAccessFile;

public enum Discipline implements Comparable<Discipline> {
    NULL            (-1, "NULL"),
    METRI_100       (0, "100 Metri"),
    METRI_200       (1, "200 Metri"),
    METRI_400       (2, "400 Metri"),
    METRI_800       (3, "800 Metri"),
    SALTO_IN_LUNGO  (4, "Salto in lungo"),
    GETTO_DEL_PESO  (5, "Getto del peso"),
    LANCIO_DEL_DISCO(6, "Lancio del disco");

    private int id;
    private String name;

    Discipline(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static void write(RandomAccessFile file, Discipline discipline) throws IOException {
        file.writeInt(discipline.getId());
    }

    public static Discipline read(RandomAccessFile file) throws IOException {
        Discipline[] v = values();
        return v[file.readInt()];
    }
}
