package com.example.anitamjeshtrifinalpj;

import java.io.Serial;
import java.io.Serializable;

public class BookOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 6790876263787049112L;
    private int quantity;
    private String  isbn;
    private String title;
    private float unitPrice;
    private Author author;
    private String bookCategory;
    private int stock;
    private final transient Book book;
/** Table order books properties*/
    public BookOrder(int quantity, Book book) {
        this.book=book;
        setQuantity(quantity);
        setBookISBN(book.getIsbn());
        setTitle(book.getTitle());
        setBookCategory(book.getBookCategory());
        setUnitPrice(book.getSellingPrice());
        setAuthor(book.getAuthor());
        setStock(book.getStock());
    }

    @Override
    public String toString() {
        return "Book Title: "+title+"\n"+"Quantity: "+quantity+"\n"+"Unit price: "+unitPrice + "---------"+" Total price: "+getTotalPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setBookCategory(String bookCategory){
        this.bookCategory = bookCategory;
    }
    public String getBookCategory(){
        return bookCategory;
    }

    public String getBookISBN() {
        return isbn;
    }

    public void setBookISBN(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getTotalPrice() {
        return quantity*unitPrice;
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

    public Book getBook() {
        return book;
    }
}
