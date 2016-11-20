package main.archive;

import java.io.IOException;
import java.io.RandomAccessFile;

public abstract class Index<T extends Comparable<T>> implements Comparable<Index<T>>{
    public T key;
    public short pointer;

    public Index() {

    }

    public Index(T name, short pointer) {
        this.key = name;
        this.pointer = pointer;
    }

    public int compareTo(Index<T> index) {
        if(key.compareTo(index.key) == 0 && pointer == index.pointer) return 0 ;
        if(key.compareTo(index.key) > 0) return 1;
        if(key.compareTo(index.key) < 0) return -1;
        if(key.compareTo(index.key) == 0 && pointer > index.pointer) return 1;
        if(key.compareTo(index.key) == 0 && pointer < index.pointer) return -1;
        return -2;
    }

    public String toString() {
        return key + " " + pointer;
    }

    public abstract int getSize();

    public abstract void write(RandomAccessFile file) throws IOException;

    public abstract Index read(RandomAccessFile file) throws IOException;
}
