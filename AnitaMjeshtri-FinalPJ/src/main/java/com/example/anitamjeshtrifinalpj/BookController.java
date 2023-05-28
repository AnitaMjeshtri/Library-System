package com.example.anitamjeshtrifinalpj;

import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

import java.util.List;

public class BookController {
    private final BookView bookView;
    public BookController(BookView bookView) {
        this.bookView = bookView;
        setSaveListener();
        setDeleteListener();
        setEditListener();
        setComboBoxListener();
    }
    private void setComboBoxListener(){
        bookView.getAuthorsComboBox().setOnMouseClicked(e->{
            bookView.getAuthorsComboBox().getItems().setAll(Author.getAuthors());
            if (!Author.getAuthors().isEmpty())
                bookView.getAuthorsComboBox().setValue(Author.getAuthors().get(0));
        });
    }
    private void setSaveListener() {
        bookView.getSaveBtn().setOnAction(e -> {
            try{
                String isbn = bookView.getIsbnField().getText();
                String title = bookView.getTitleField().getText();
                String bookCategory = bookView.getField_bookCategory().getText();
                int stock = Integer.parseInt(bookView.getStockField().getText());
                float purchasedPrice = Float.parseFloat(bookView.getPurchasedPriceField().getText());
                float sellingPrice = Float.parseFloat(bookView.getSellingPriceField().getText());
                Author author = bookView.getAuthorsComboBox().getValue();
                Book book = new Book(stock,isbn, title,bookCategory, purchasedPrice, sellingPrice, author);
                if (!book.exists() && book.isValid()){
                    if (book.saveInFile()) {
                        bookView.getTableView().getItems().add(book);
                        resetFields();
                    } else {
                        System.out.println("Book creation failed!");
                    }
                } else {
                    System.out.println("Book creation failed");
                }
            }catch(NumberFormatException d){
                System.out.println("Number format Exception occurred");
            }
        });
    }
    private void setDeleteListener(){
        bookView.getDeleteBtn().setOnAction(e->{
            List<Book> itemsToDelete = List.copyOf(bookView.getTableView().getSelectionModel().getSelectedItems());
            for (Book b: itemsToDelete) {
                if (b.deleteFromFile()) {
                    bookView.getTableView().getItems().remove(b);
                    System.out.println("The book with this ISBN: "+b.getIsbn()+" is deleted.");
                } else {
                    System.out.println("Book deletion failed ISBN: "+b.getIsbn());
                    break;
                }
            }
        });
    }

    private void setEditListener() {

        bookView.getIsbnCol().setOnEditCommit(e -> {
            Book editIsbn = e.getRowValue();
            String oldIsbn=editIsbn.getIsbn();
            editIsbn.setIsbn(e.getNewValue());
            if (editIsbn.isValid()){
                editIsbn.updateFile();
            }
            else {
                System.out.println("Edit process cannot happen "+e.getNewValue());
                editIsbn.setIsbn(oldIsbn);
                bookView.getTableView().getItems().set(bookView.getTableView().getItems().indexOf(editIsbn), editIsbn);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid isbn");
                alert.setContentText("Please enter a valid isbn!");
                alert.show();
            }
        });

        bookView.getTitleCol().setOnEditCommit(e -> {
            Book editTitle = e.getRowValue();
            String oldTitle=editTitle.getTitle();
            editTitle.setTitle(e.getNewValue());
            if (editTitle.isValid()){
                editTitle.updateFile();
            }
            else {
                System.out.println("Edit process cannot happen "+e.getNewValue());
                editTitle.setTitle(oldTitle);
                bookView.getTableView().getItems().set(bookView.getTableView().getItems().indexOf(editTitle), editTitle);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid title");
                alert.setContentText("Please enter a valid title!");
                alert.show();
            }
        });

        bookView.getPurchasedPriceCol().setOnEditCommit(e -> {
            Book editPrice = e.getRowValue();
            float oldPrice=editPrice.getPurchasedPrice();
            editPrice.setPurchasedPrice(e.getNewValue());
            if (editPrice.isValid()){
                editPrice.updateFile();
            }
            else {
                System.out.println("Edit process cannot happen "+e.getNewValue());
                editPrice.setPurchasedPrice(oldPrice);
                bookView.getTableView().getItems().set(bookView.getTableView().getItems().indexOf(editPrice), editPrice);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid price");
                alert.setContentText("Please enter a valid price!");
                alert.show();
            }
        });

        bookView.getSellingPriceCol().setOnEditCommit(e -> {
            Book editSellingPrice = e.getRowValue();
            float oldVal=editSellingPrice.getSellingPrice();
            editSellingPrice.setSellingPrice(e.getNewValue());
            if (editSellingPrice.isValid()){
                editSellingPrice.updateFile();
            }
            else {
                System.out.println("Edit process cannot happen "+e.getNewValue());
                editSellingPrice.setSellingPrice(oldVal);
                bookView.getTableView().getItems().set(bookView.getTableView().getItems().indexOf(editSellingPrice), editSellingPrice);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid selling price");
                alert.setContentText("Please enter a valid price!");
                alert.show();
            }
        });


    }

    private void resetFields() {
        bookView.getStockField().clear();
        bookView.getIsbnField().clear();
        bookView.getTitleField().clear();
        bookView.getField_bookCategory().clear();
        bookView.getPurchasedPriceField().clear();
        bookView.getSellingPriceField().clear();
    }
}
