package main.archive;

import java.io.IOException;
import java.io.RandomAccessFile;

public enum Discipline implements Comparable<Discipline> {
    NULL                (-1, "NULL"),
    METRI_100           (0, "100 Metri"),
    METRI_200           (1, "200 Metri"),
    METRI_400           (2, "400 Metri"),
    METRI_800           (3, "800 Metri"),
    SALTO_IN_LUNGO      (4, "Salto in lungo"),
    GETTO_DEL_PESO      (5, "Getto del peso"),
    LANCIO_DEL_DISCO    (6, "Lancio del disco"),
    NUOTO_100_LIBERO    (7, "Nuoto 100 metri Libero"),
    NUOTO_200_LIBERO    (8, "Nuoto 200 metri Libero"),
    NUOTO_100_FARFALLA  (9, "Nuoto 100 metri Farfalla"),
    NUOTO_800_LIBERO    (10, "Nuoto 800 metri Libero");

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

    public static Discipline get(int id) {
        Discipline[] v = values();
        return v[id + 1];
    }

    public static void write(RandomAccessFile file, Discipline discipline) throws IOException {
        file.writeInt(discipline.getId());
    }

    public static Discipline read(RandomAccessFile file) throws IOException {
        Discipline[] v = values();
        return v[file.readInt() + 1];
    }
}
