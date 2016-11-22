package main.archive;

import java.io.IOException;
import java.io.RandomAccessFile;

public enum Nation implements Comparable<Nation> {
    NULL        (-1, "NULL"),
    ITALIA      (0, "Italia"),
    FRANCIA     (1, "Francia"),
    SPAGNA      (2, "Spagna"),
    REGNO_UNITO (3, "Regno Unito"),
    USA         (4, "U.S.A."),
    BRASILE     (5, "Brasile"),
    GERMANIA    (6, "Germania");

    private int id;
    private String name;

    Nation(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Nation get(int id) {
        Nation[] v = values();
        return v[id + 1];
    }

    public boolean equals(Nation n) {
        return n.id == id;
    }

    public static void write(RandomAccessFile file, Nation nation) throws IOException {
        file.writeInt(nation.getId());
    }

    public static Nation read(RandomAccessFile file) throws IOException {
        Nation[] v = values();
        return v[file.readInt() + 1];
    }
}

