package com.example.anitamjeshtrifinalpj;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Book extends Model implements Serializable {

    @Serial
    private static final long serialVersionUID = -5414982383319219020L;
    private String isbn;
    private String title;
    private float purchasedPrice;
    private float sellingPrice;
    private Author author;
    private int stock;
    private static final String FILE_PATH = "books.ser";
    private static final File dataFile = new File(FILE_PATH);
    private static final ArrayList<Book> books = new ArrayList<>();

    private String bookCategory;
    public Book(int stock,String isbn, String title,String bookCategory, float purchasedPrice, float sellingPrice, Author author) {
        this.stock=stock;
        this.isbn = isbn;
        this.title = title;
        this.bookCategory = bookCategory;
        this.purchasedPrice = purchasedPrice;
        this.sellingPrice = sellingPrice;
        this.author = author;
    }

    public Book(){}

    public String getBookCategory(){
        return bookCategory;
    }
    public float getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(float purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock=stock;
    }

    @Override
    public boolean saveInFile() {
        boolean savedInFile = super.save(dataFile);
        if (savedInFile)
            books.add(this);
        return savedInFile;
    }

    public boolean isValid() {
        if (!isbn.matches("[A-Z]\\d{8}[A-Z]"))
            return false;
        if (sellingPrice<0)
            return false;
        if (purchasedPrice<0)
            return false;
        if (title.length()==0)
            return false;
        if(bookCategory.length()==0){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteFromFile(){
        books.remove(this);
        try {
            FileHandler.overwriteCurrentListToFile(dataFile, books);
        }
        catch (Exception e){
            books.add(this);
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
        return true;
    }

    @Override
    public void updateFile(){
        try {
            FileHandler.overwriteCurrentListToFile(dataFile, books);
        }
        catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public static ArrayList<Book> getBooks() {
        if (books.size() == 0) {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
                while (true) {
                    Book temp = (Book) inputStream.readObject();
                    if (temp == null)
                        break;
                    books.add(temp);
                }
                inputStream.close();
            } catch (EOFException eofException) {
                System.out.println("The end of file is reached.");
            }
            catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return books;
    }
    public boolean exists(){
        for (Book b: books) {
            if (b.getIsbn().equals(this.getIsbn()))
                return true;
        }
        return false;
    }
}
