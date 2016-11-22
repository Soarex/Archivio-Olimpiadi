package main.archive;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ShortIndex extends Index<Short>{
    public ShortIndex() {

    }

    public ShortIndex(Short name, short pointer) {
        this.key = name;
        this.pointer = pointer;
    }

    public int getSize() {
        return 4;
    }

    public void write(RandomAccessFile file) throws IOException {
        file.writeShort(key);
        file.writeShort(pointer);
    }


    public ShortIndex read(RandomAccessFile file) throws IOException {
        key = file.readShort();
        pointer = file.readShort();
        return this;
    }


}
