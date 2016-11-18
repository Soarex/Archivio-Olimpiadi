package main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class AthleteNationIndexFile {
    private RandomAccessFile file;
    private int entryCount;

    public AthleteNationIndexFile() throws IOException {
        file = new RandomAccessFile("data/athletes_nation.ndx", "rw");
        if(new File("data/athletes.dat").isFile())
            entryCount = file.readInt();
    }

    public void add( AthleteNationIndex index) throws IOException {
        if(entryCount == 0) {
            file.seek(4);
            AthleteNationIndex.write(file, index);
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

    public Short[] search(Nation key) throws IOException {
        ArrayList<Short> res = new ArrayList<>();

        search(key, res, 0, entryCount);

        return res.toArray(new Short[0]);
    }

    private void search(Nation key, ArrayList<Short> res,  int l, int r) throws IOException {
        int position = (l + r) / 2;
        if(position < l || position > r) return;

        file.seek(position *  AthleteNationIndex.SIZE + 4);
        AthleteNationIndex aus =  AthleteNationIndex.read(file);

        if(aus.key.equals(key)) {
            while (aus.key.equals(key) && position > 0) {
                position--;
                file.seek(position *  AthleteNationIndex.SIZE + 4);
                aus =  AthleteNationIndex.read(file);
            }

            if(position == 0) {
                file.seek(position *  AthleteNationIndex.SIZE + 4);
                aus =  AthleteNationIndex.read(file);
                res.add(aus.pointer);
            }

            do {
                position++;
                file.seek(position *  AthleteNationIndex.SIZE + 4);
                aus =  AthleteNationIndex.read(file);
                if(aus.key.equals(key))
                    res.add(aus.pointer);
            } while (aus.key.equals(key) && position < r - 1);
            return;
        }

        if(aus.key.getId() < key.getId()) search(key, res, l, position);

        if(aus.key.getId() > key.getId()) search(key, res, position, r);
    }

    private int findPosition( AthleteNationIndex index) throws IOException {
        int res = 0;
        while(res < entryCount && index.compareTo( AthleteNationIndex.read(file)) > 0)
            res++;

        return res;
    }

    private void shift( AthleteNationIndex index, int position) throws IOException {
        if(position == entryCount) {
            AthleteNationIndex.write(file, index);
            return;
        }

        file.seek(position *  AthleteNationIndex.SIZE + 4);
        AthleteNationIndex aus =  AthleteNationIndex.read(file);
        file.seek(position *  AthleteNationIndex.SIZE + 4);
        AthleteNationIndex.write(file, index);

        shift(aus, ++position);
    }
}
