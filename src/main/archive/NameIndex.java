package main.archive;

import java.io.IOException;
import java.io.RandomAccessFile;

public class NameIndex extends Index<String>{
    public static final int NAME_LENGHT = 50;
    public static final int NAME_SIZE = NAME_LENGHT * 2;
    public static final int SIZE = NAME_SIZE + 2;

    public NameIndex() {

    }

    public NameIndex(String name, short pointer) {
        this.key = name;
        this.pointer = pointer;
    }

    public int getSize() {
        return SIZE;
    }

    public void write(RandomAccessFile file) throws IOException {
        writeName(file, key);
        file.writeShort(pointer);
    }

    private void writeName(RandomAccessFile file, String name) throws IOException {
        StringBuilder buffer = new StringBuilder(NAME_LENGHT);
        buffer.insert(0, name);
        buffer.setLength(NAME_LENGHT);
        file.writeChars(buffer.toString());
    }

    public NameIndex read(RandomAccessFile file) throws IOException {
        key = readName(file);
        pointer = file.readShort();
        return this;
    }

    private String readName(RandomAccessFile file) throws IOException {
        char[] buffer = new char[NAME_LENGHT];
        for(int i = 0; i < NAME_LENGHT; i++)
            buffer[i] = file.readChar();

        String res = new String(buffer);
        res = res.replaceAll("\0", "");
        return res;
    }
}
