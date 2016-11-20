package main.archive;

import java.io.IOException;
import java.io.RandomAccessFile;

public class NationIndex extends Index<Nation> {

    public NationIndex() {

    }

    public NationIndex(Nation name, short pointer) {
        this.key = name;
        this.pointer = pointer;
    }

    public int getSize() {
        return 2 + 4;
    }

    public void write(RandomAccessFile file) throws IOException {
        Nation.write(file, key);
        file.writeShort(pointer);
    }

    public NationIndex read(RandomAccessFile file) throws IOException {
        key = Nation.read(file);
        pointer = file.readShort();
        return this;
    }
}
