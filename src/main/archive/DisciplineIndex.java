package main.archive;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DisciplineIndex extends Index<Discipline>{

    public DisciplineIndex() {

    }

    public DisciplineIndex(Discipline name, short pointer) {
        this.key = name;
        this.pointer = pointer;
    }

    public int getSize() {
        return 2 + 4;
    }

    public void write(RandomAccessFile file) throws IOException {
        Discipline.write(file, key);
        file.writeShort(pointer);
    }

    public DisciplineIndex read(RandomAccessFile file) throws IOException {
        key = Discipline.read(file);
        pointer = file.readShort();
        return this;
    }
}
