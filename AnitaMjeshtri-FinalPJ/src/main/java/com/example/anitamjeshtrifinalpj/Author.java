package com.example.anitamjeshtrifinalpj;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Author extends Model implements Serializable {

    @Serial
    private static final long serialVersionUID = 5182212442077149989L;
    private String firstName;
    private String lastName;

    private static final ArrayList<Author> authors = new ArrayList<>();
    public static final String FILE_PATH = "authors.ser";
    public static final File dataFile = new File(FILE_PATH);

    public Author(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    @Override
    public boolean saveInFile() {
        boolean savedInFile = super.save(dataFile);
        if (savedInFile)
            authors.add(this);
        return savedInFile;
    }

    @Override
    public void updateFile() {
        try {
            FileHandler.overwriteCurrentListToFile(dataFile, authors);
        }
        catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public boolean isValid() {
        return getFirstName().matches("[A-Za-z]+") && getLastName().matches("[A-Za-z]+");
    }

    public boolean exists(){
        for (Author a: authors) {
            if (a.getFullName().equals(this.getFullName()))
                return true;
        }
        return false;
    }
    @Override
    public boolean deleteFromFile() {
        authors.remove(this);
        try {
            FileHandler.overwriteCurrentListToFile(dataFile, authors);
        }
        catch (Exception e){
            authors.add(this);
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
        return true;
    }

    public static ArrayList<Author> getAuthors() {
        if (authors.size() == 0) {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
                while (true) {
                    Author temp = (Author) inputStream.readObject();
                    if (temp == null)
                        break;
                    authors.add(temp);
                }
                inputStream.close();
            } catch (EOFException eofException) {
                System.out.println("End of file");
            }
            catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return authors;
    }
}
