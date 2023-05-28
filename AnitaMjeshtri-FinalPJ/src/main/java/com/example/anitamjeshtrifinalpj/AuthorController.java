package com.example.anitamjeshtrifinalpj;

import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

import java.util.List;

public class AuthorController {
    private final AuthorView authorView;
    public AuthorController(AuthorView authorView) {
        this.authorView = authorView;
        setSaveListener();
        setDeleteListener();
        setEditListener();

    }

    private void setEditListener() {
        authorView.getFirstNameCol().setOnEditCommit(e -> {
            Author editAuthor = e.getRowValue();
            String oldAuthor=editAuthor.getFirstName();
            editAuthor.setFirstName(e.getNewValue());
            if (editAuthor.isValid()){
                editAuthor.updateFile();
            }
            else {
                System.out.println("Author cannot be edited "+e.getNewValue());
                editAuthor.setFirstName(oldAuthor);
                authorView.getTableView().getItems().set(authorView.getTableView().getItems().indexOf(editAuthor), editAuthor);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid edit");
                alert.setContentText("Please enter a valid name");
                alert.show();
            }
        });

        authorView.getLastNameCol().setOnEditCommit(e -> {
            Author editAuthor = e.getRowValue();
            String oldAuthor=editAuthor.getLastName();
            editAuthor.setLastName(e.getNewValue());
            if (editAuthor.isValid()){
                editAuthor.updateFile();
            }
            else {
                System.out.println("Author cannot be edited "+e.getNewValue());
                editAuthor.setLastName(oldAuthor);
                authorView.getTableView().getItems().set(authorView.getTableView().getItems().indexOf(editAuthor), editAuthor);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid edit");
                alert.setContentText("Please enter a valid surname");
                alert.show();
            }
        });
    }


    private void setDeleteListener() {
        authorView.getDeleteBtn().setOnAction(e->{
            List<Author> itemsToDelete = List.copyOf(authorView.getTableView().getSelectionModel().getSelectedItems());
            for (Author author: itemsToDelete) {
                if (author.deleteFromFile()) {
                    authorView.getTableView().getItems().remove(author);
                    authorView.getResultLabel().setText("The author "+author.getFullName()+" is deleted");
                    System.out.println("The author: "+author.getFullName()+" is deleted");
                } else {
                    authorView.getResultLabel().setText("Cannot delete the author");
                    System.out.println("Cannot delete this author: "+author.getFullName());
                    break;
                }
            }
        });
    }

    private void setSaveListener() {
        authorView.getSaveBtn().setOnAction(e -> {
            String firstName = authorView.getFirstNameField().getText();
            String lastName = authorView.getLastNameField().getText();
            Author author = new Author(firstName, lastName);
            if (!author.exists()) {
                if (author.saveInFile()) {
                    authorView.getTableView().getItems().add(author);
                    authorView.getResultLabel().setText("Author successfully created!");
                    authorView.getResultLabel().setTextFill(Color.GREEN);
                    authorView.getFirstNameField().clear();
                    authorView.getLastNameField().clear();
                } else {
                    System.out.println("Cannot add the author");
                    authorView.getResultLabel().setText("Cannot add the author!");
                    authorView.getResultLabel().setTextFill(Color.RED);
                }
            }
            else {
                authorView.getResultLabel().setText("Author name must be unique!");
                authorView.getResultLabel().setTextFill(Color.RED);
            }
        });
    }
}
