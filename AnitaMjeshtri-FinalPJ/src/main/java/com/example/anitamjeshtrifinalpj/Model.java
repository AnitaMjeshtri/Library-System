package com.example.anitamjeshtrifinalpj;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public abstract class Model {
    public abstract boolean saveInFile();
    public abstract void updateFile();

    public boolean save(File dataFile) {
        if (!isValid())
            return false;
        try {
            ObjectOutputStream outputStream;
            FileOutputStream fileOutputStream = new FileOutputStream(dataFile, true);
            if (dataFile.length() == 0)
                outputStream = new ObjectOutputStream(fileOutputStream);
            else
                outputStream = new HeaderlessObjectOutputStream(fileOutputStream);
            outputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public abstract boolean isValid();
    public abstract boolean deleteFromFile();
}
