package main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class AthleteNameIndexFile {
    private RandomAccessFile file;
    private int entryCount;

    public AthleteNameIndexFile() throws IOException {
        file = new RandomAccessFile("data/athletes_name.ndx", "rw");
        if(new File("data/athletes.dat").isFile())
            entryCount = file.readInt();
    }

    public void add(AthleteNameIndex index) throws IOException {
        if(entryCount == 0) {
            file.seek(4);
            AthleteNameIndex.write(file, index);
            entryCount++;
            file.seek(0);
            file.writeInt(entryCount);
            return;
        }

        file.seek(4);
        int position = findPosition(index);
        shift(index, position);
        entryCount++;
    }

    public Short[] search(String key) throws IOException {
        ArrayList<Short> res = new ArrayList<>();

        search(key, res, 0, entryCount);

        return res.toArray(new Short[0]);
    }

    private void search(String key, ArrayList<Short> res,  int l, int r) throws IOException {
        int position = (l + r) / 2;
        if(position < l || position > r) return;

        file.seek(position * AthleteNameIndex.SIZE + 4);
        AthleteNameIndex aus = AthleteNameIndex.read(file);

        if(aus.key.equals(key)) {
            while (aus.key.equals(key) && position > 0) {
                position--;
                file.seek(position * AthleteNameIndex.SIZE + 4);
                aus = AthleteNameIndex.read(file);
            }

            if(position == 0) {
                file.seek(position * AthleteNameIndex.SIZE + 4);
                aus = AthleteNameIndex.read(file);
                res.add(aus.pointer);
            }

            do {
                position++;
                file.seek(position * AthleteNameIndex.SIZE + 4);
                aus = AthleteNameIndex.read(file);
                if(aus.key.equals(key))
                    res.add(aus.pointer);
            } while (aus.key.equals(key) && position < r - 1);
            return;
        }

        if(aus.key.compareTo(key) > 0) search(key, res, l, position);

        if(aus.key.compareTo(key) < 0) search(key, res, position, r);
    }

    private int findPosition(AthleteNameIndex index) throws IOException {
        int res = 0;
        while(res < entryCount && index.compareTo(AthleteNameIndex.read(file)) > 0)
            res++;

        return res;
    }

    private void shift(AthleteNameIndex index, int position) throws IOException {
        if(position == entryCount) {
            AthleteNameIndex.write(file, index);
            return;
        }

        file.seek(position * AthleteNameIndex.SIZE + 4);
        AthleteNameIndex aus = AthleteNameIndex.read(file);
        file.seek(position * AthleteNameIndex.SIZE + 4);
        AthleteNameIndex.write(file, index);

        shift(aus, ++position);
    }
}
