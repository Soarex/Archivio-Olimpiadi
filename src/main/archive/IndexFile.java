package main.archive;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class IndexFile {
    protected RandomAccessFile file;
    protected int entryCount;
    protected Index type;

    public IndexFile(String path, Index type) throws IOException {
        if(new File(path).isFile()) {
            file = new RandomAccessFile(path, "rw");
            entryCount = file.readInt();
        }

        file = new RandomAccessFile(path, "rw");
        file.writeInt(entryCount);
        this.type = type;
    }

    public void add(Index index) throws IOException, IllegalAccessException, InstantiationException {
        if(entryCount == 0) {
            file.seek(4);
            index.write(file);
            entryCount++;
            file.seek(0);
            file.writeInt(entryCount);
            return;
        }

        file.seek(4);
        int position = findPosition(index);
        if(position == -1) return;

        shift(index, position);
        entryCount++;
        file.seek(0);
        file.writeInt(entryCount);
    }

    private <T extends Comparable<T>> int findPosition(Index<T> index) throws IOException, IllegalAccessException, InstantiationException {
        int res = 0;
        Index<T> aus = type.getClass().newInstance();
        aus.read(file);
        if(index.compareTo(aus) == 0) return -1;
        while(res < entryCount && index.compareTo(aus) > 0) {
            res++;
            if(res < entryCount)
                aus.read(file);
        }
        if(index.compareTo(aus) == 0) return -1;

        return res;
    }

    private void shift(Index index, int position) throws IOException, IllegalAccessException, InstantiationException {
        if(position == entryCount) {
            index.write(file);
            return;
        }

        file.seek(position * index.getSize() + 4);

        Index aus = type.getClass().newInstance();
        aus.read(file);
        file.seek(position * index.getSize() + 4);
        index.write(file);

        shift(aus, ++position);
    }

    public void remove(short pointer) throws IOException, IllegalAccessException, InstantiationException {
        file.seek(4);
        int position = findPosition(pointer);
        if(position == -1) return;

        if(position == entryCount - 1) {
            entryCount--;
            return;
        }

        Index temp = type.getClass().newInstance();
        file.seek((entryCount - 1) * temp.getSize() + 4);
        temp.read(file);
        shiftBack(temp, entryCount - 2, position);
        entryCount--;
        file.seek(0);
        file.writeInt(entryCount);
    }

    private int findPosition(short pointer) throws IOException, IllegalAccessException, InstantiationException {
        int res = 0;
        Index aus = type.getClass().newInstance();
        while(res < entryCount && aus.read(file).pointer != pointer)
            res++;

        if(res == entryCount) return -1;
        return res;
    }

    private void shiftBack(Index index, int position, int limit) throws IOException, IllegalAccessException, InstantiationException {
        if(position == limit){
            file.seek(position * index.getSize() + 4);
            index.write(file);
            return;
        }

        Index aus = type.getClass().newInstance();
        file.seek((position) * aus.getSize() + 4);

        aus.read(file);
        file.seek(position * aus.getSize() + 4);
        index.write(file);

        shiftBack(aus, --position, limit);
    }

    public <T extends Comparable<T>> Short[] search(T key) throws IOException, IllegalAccessException, InstantiationException  {
        ArrayList<Short> res = new ArrayList<>();

        search(key, res, 0, entryCount - 1);

        return res.toArray(new Short[0]);
    }

    private <T extends Comparable<T>> void search(T key, ArrayList<Short> res,  int l, int r) throws IOException, IllegalAccessException, InstantiationException {
        if(l > r) return;
        int position = (l + r) / 2;

        file.seek(position * type.getSize() + 4);
        Index<T> aus = type.getClass().newInstance();
        aus.read(file);

        if(aus.key.equals(key)) {
            while (aus.key.equals(key) && position > 0) {
                position--;
                file.seek(position * type.getSize() + 4);
                aus.read(file);
            }

            if(position == 0 && aus.key.equals(key)) {
                file.seek(position * type.getSize() + 4);
                aus.read(file);
                res.add(aus.pointer);
            }

            do {
                if(position < r) {
                    position++;
                    file.seek(position * type.getSize() + 4);
                    aus.read(file);
                    if (aus.key.equals(key))
                        res.add(aus.pointer);
                }
            } while (aus.key.equals(key) && position < r);
            return;
        }

        if(aus.key.compareTo(key) > 0) search(key, res, l, position - 1);

        if(aus.key.compareTo(key) < 0) search(key, res, position + 1, r);
    }

    public Short[] get(int l, int r) throws IOException, IllegalAccessException, InstantiationException {
        ArrayList<Short> list = new ArrayList<>();

        while(l < entryCount && l < r) {
            file.seek(l * type.getSize() + 4);
            Index<?> aus = type.getClass().newInstance();
            aus.read(file);
            list.add(aus.pointer);
            l++;
        }

        return list.toArray(new Short[0]);
    }

}
