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
import javafx.util.converter.FloatStringConverter;

public class BookView extends View {
    private final BorderPane borderPane = new BorderPane();
    private final TableView<Book> tableView = new TableView<>();
    private final HBox formPane = new HBox();
    private final TextField isbnField = new TextField();
    private final TextField titleField = new TextField();
    private final TextField purchasedField = new TextField();
    private final TextField sellingField = new TextField();
    private final TextField stockField = new TextField();
    private final TextField bookCategoryField = new TextField();
    private final ComboBox<Author> authorsComboBox = new ComboBox<>();
    private final Button saveBtn = new CreateButton();
    private final Button deleteBtn = new DeleteButton();
    private final TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
    private final TableColumn<Book, String> titleCol = new TableColumn<>("Title");
    private final TableColumn<Book, Float> purchasedCol = new TableColumn<>("Purchased Price");
    private final TableColumn<Book, Float> sellingCol = new TableColumn<>("Selling Price");
    private final TableColumn<Book, String> authorCol = new TableColumn<>("Author");
    private final TableColumn<Book, Integer> stockCol = new TableColumn<>("Stock");
    private final TableColumn<Book,String>bookCategoryCol = new TableColumn<>("Book Category");
    private final Label resultLabel = new Label("");
    public TableView<Book> getTableView() {
        return tableView;
    }

    public TextField getIsbnField() {
        return isbnField;
    }

    public TextField getTitleField() {
        return titleField;
    }
    public TextField getField_bookCategory(){
        return bookCategoryField;
    }

    public TextField getPurchasedPriceField() {
        return purchasedField;
    }

    public TextField getSellingPriceField() {
        return sellingField;
    }
    public TextField getStockField() {
        return stockField;
    }

    public ComboBox<Author> getAuthorsComboBox() {
        return authorsComboBox;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public TableColumn<Book, String> getIsbnCol() {
        return isbnCol;
    }

    public TableColumn<Book, String> getTitleCol() {
        return titleCol;
    }

    public TableColumn<Book, Float> getPurchasedPriceCol() {
        return purchasedCol;
    }

    public TableColumn<Book, Float> getSellingPriceCol() {
        return sellingCol;
    }

    public TableColumn<Book, Integer> getStockCol() {
        return stockCol;
    }

    public TableColumn<Book, String>bookCategoryCol(){
        return bookCategoryCol;
    }

    public Label getResultLabel() {
        return resultLabel;
    }


    public BorderPane getBorderPane(){
        return borderPane;
    }
    public BookView() {
        setTableView();
        setForm();
        new BookController(this);
    }
    private void setForm() {

        formPane.setPadding(new Insets(20));
        formPane.setSpacing(20);
        formPane.setAlignment(Pos.CENTER);

        Label isbnLabel = new Label("ISBN", isbnField);
        isbnLabel.setContentDisplay(ContentDisplay.TOP);

        Label titleLabel = new Label("Title", titleField);
        titleLabel.setContentDisplay(ContentDisplay.TOP);

        Label purchasedPriceLabel = new Label("Purchased price", purchasedField);
        purchasedPriceLabel.setContentDisplay(ContentDisplay.TOP);

        Label sellingPriceLabel = new Label("Selling price", sellingField);
        sellingPriceLabel.setContentDisplay(ContentDisplay.TOP);

        Label stockLabel = new Label("Stock",stockField);
        stockLabel.setContentDisplay(ContentDisplay.TOP);

        Label bookCategoryLabel = new Label("Book Category",bookCategoryField);
        bookCategoryLabel.setContentDisplay(ContentDisplay.TOP);

        Label authorLabel = new Label("Author", authorsComboBox);

        authorsComboBox.getItems().setAll(Author.getAuthors());

        if (!Author.getAuthors().isEmpty())
            authorsComboBox.setValue(Author.getAuthors().get(0));
        authorLabel.setContentDisplay(ContentDisplay.TOP);
        formPane.getChildren().addAll(stockLabel,isbnLabel, titleLabel,bookCategoryLabel, purchasedPriceLabel, sellingPriceLabel,
                authorLabel, saveBtn, deleteBtn);
    }

    private void setTableView() {
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setEditable(true);
        tableView.setItems(FXCollections.observableArrayList(Book.getBooks()));

        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        isbnCol.setCellFactory(TextFieldTableCell.forTableColumn());

        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());

        purchasedCol.setCellValueFactory(new PropertyValueFactory<>("purchasedPrice"));
        purchasedCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        sellingCol.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        sellingCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        bookCategoryCol.setCellValueFactory(new PropertyValueFactory<>("bookCategory"));
        bookCategoryCol.setCellFactory(TextFieldTableCell.forTableColumn());

        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        tableView.getColumns().addAll(stockCol,isbnCol, titleCol,bookCategoryCol, purchasedCol, sellingCol, authorCol);
    }


    @Override
    public Parent getView() {
        borderPane.setCenter(tableView);

        if ((super.getCurrentUser().getRole() == Role.ADMIN) || (super.getCurrentUser().getRole() == Role.MANAGER)) {
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(formPane, resultLabel);
            borderPane.setBottom(vBox);
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
}
