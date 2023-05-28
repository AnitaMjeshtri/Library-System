package com.example.anitamjeshtrifinalpj;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class AuthorView extends View {
    private final BorderPane borderPane = new BorderPane();
    private final TableView<Author> tableView = new TableView<>();
    private final HBox formPane = new HBox();
    private final TextField firstNameField = new TextField();
    private final TextField lastNameField = new TextField();
    private final Button saveBtn = new CreateButton();
    private final Button deleteBtn = new DeleteButton();
    private final TableColumn<Author, String> firstNameCol = new TableColumn<>("Name");
    private final TableColumn<Author, String> lastNameCol = new TableColumn<>("Surname");
    private final Label resultLabel = new Label("");

    public TableColumn<Author, String> getFirstNameCol() {
        return firstNameCol;
    }

    public TableColumn<Author, String> getLastNameCol() {
        return lastNameCol;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public Label getResultLabel() {
        return resultLabel;
    }

    public TableView<Author> getTableView() {
        return tableView;
    }

    public TextField getFirstNameField() {
        return firstNameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }
    public AuthorView() {
        setTableView();
        setForm();
        new AuthorController(this);
    }
    @Override
    public Parent getView() {
        borderPane.setCenter(tableView);
        if ((super.getCurrentUser().getRole() == Role.ADMIN) || (super.getCurrentUser().getRole() == Role.MANAGER)) {
            formPane.getChildren().add(resultLabel);
            borderPane.setBottom(formPane);
        }
        Image img = new Image("file:back-image.png");
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        borderPane.setBackground(bGround);
        return borderPane;
    }

    private void setForm() {
        formPane.setPadding(new Insets(20));
        formPane.setSpacing(20);
        formPane.setAlignment(Pos.CENTER);

        Label firstNameLabel = new Label("First Name ", firstNameField);
        firstNameLabel.setContentDisplay(ContentDisplay.TOP);

        Label lastNameLabel = new Label("Last Name ", lastNameField);
        lastNameLabel.setContentDisplay(ContentDisplay.TOP);
        formPane.getChildren().addAll(firstNameLabel, lastNameLabel, saveBtn, deleteBtn);
    }

    private void setTableView() {

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setEditable(true);
        tableView.setItems(FXCollections.observableArrayList(Author.getAuthors()));
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.getColumns().addAll(firstNameCol, lastNameCol);
    }
}
