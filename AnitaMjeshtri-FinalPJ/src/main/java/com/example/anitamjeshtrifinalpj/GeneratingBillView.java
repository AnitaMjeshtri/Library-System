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
import javafx.util.converter.IntegerStringConverter;

public class GeneratingBillView extends View {
    private final BorderPane borderPane = new BorderPane();
    private final TableView<BookOrder> orderTableView = new TableView<>();
    HBox formPane = new HBox();

    private final TextField nameField = new TextField();
    private final Button saveBtn = new CreateButton();
    private final Label totalValueLabel = new Label("0.0");
    private final Label totalLabel = new Label("Total: ", totalValueLabel);
    private final Order order;
    private final Tab tab;
    private final MainView mainView;
    private final TableColumn<BookOrder, Integer> stockCol= new TableColumn<>("Stock");
    private final TableColumn<BookOrder, Integer> noCol = new TableColumn<>("Quantity");
    private final TableColumn<BookOrder, String> titleCol = new TableColumn<>("Title");
    private final TableColumn<BookOrder, String>bookCategoryCol = new TableColumn<>("Book Category");
    private final TableColumn<BookOrder, Float> priceCol = new TableColumn<>("Unit Price");
    private final TableColumn<BookOrder, Float> totalPriceCol = new TableColumn<>("Total Price");
    private final TableColumn<BookOrder, String> authorCol = new TableColumn<>("Author");

    private final TableView<Book> books_tableView = new TableView<>();
    private final TableColumn<Book,Integer> books_stockCol = new TableColumn<>("Stock");
    private final TableColumn<Book, String> books_isbnCol = new TableColumn<>("ISBN");
    private final TableColumn<Book, String> books_titleCol = new TableColumn<>("Title");
    private final TableColumn<Book, String>books_bookCategoryCol = new TableColumn<>("Book Category");
    private final TableColumn<Book, Float> books_purchasedPriceCol = new TableColumn<>("Purchased Price");
    private final TableColumn<Book, Float> books_sellingPriceCol = new TableColumn<>("Selling Price");
    private final TableColumn<Book, String> books_authorCol = new TableColumn<>("Author");

    private final Label resultLabel = new Label("");
    public GeneratingBillView(MainView mainView, Tab tab) {

        this.mainView=mainView;
        this.tab=tab;
        order = new Order(getCurrentUser().getUsername());
        System.out.println("books ordered "+order.getBooksOrdered().size());
        setForm();
        setTableView();
        setBooks_TableView();
        new GeneratingBillController(this);
    }


    private void setTableView() {

        orderTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        orderTableView.setEditable(true);
        orderTableView.setItems(FXCollections.observableArrayList(order.getBooksOrdered()));

        stockCol.setEditable(true);
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        stockCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


        noCol.setEditable(true);
        noCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        noCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        titleCol.setEditable(false);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());

        bookCategoryCol.setEditable(false);
        bookCategoryCol.setCellValueFactory(new PropertyValueFactory<>("bookCategory"));
        bookCategoryCol.setCellFactory(TextFieldTableCell.forTableColumn());

        priceCol.setEditable(false);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        totalPriceCol.setEditable(false);
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        totalPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        authorCol.setEditable(false);
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        orderTableView.getColumns().addAll(stockCol,noCol, titleCol,bookCategoryCol, priceCol, totalPriceCol, authorCol);
    }
    private void setBooks_TableView() {

        books_tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        books_tableView.setEditable(false);
        books_tableView.setItems(FXCollections.observableArrayList(Book.getBooks()));

        books_stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        books_stockCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        books_isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        books_isbnCol.setCellFactory(TextFieldTableCell.forTableColumn());

        books_titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        books_titleCol.setCellFactory(TextFieldTableCell.forTableColumn());

        books_purchasedPriceCol.setCellValueFactory(new PropertyValueFactory<>("purchasedPrice"));
        books_purchasedPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        books_sellingPriceCol.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        books_sellingPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        books_bookCategoryCol.setCellValueFactory(new PropertyValueFactory<>("bookCategory"));
        books_bookCategoryCol.setCellFactory(TextFieldTableCell.forTableColumn());

        books_authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        books_tableView.getColumns().addAll(books_stockCol,books_isbnCol, books_titleCol,books_bookCategoryCol, books_purchasedPriceCol, books_sellingPriceCol, books_authorCol);
    }

    public TextField getNameField() {
        return nameField;
    }

    public TableView<BookOrder> getOrderTableView() {
        return orderTableView;
    }

    public TableView<Book> getBooks_tableView() {
        return books_tableView;
    }

    @Override
    public Parent getView() {

        HBox tables = new HBox();
        tables.setAlignment(Pos.CENTER);
        HBox.setHgrow(books_tableView, Priority.ALWAYS);
        HBox.setHgrow(orderTableView, Priority.ALWAYS);
        tables.getChildren().add(books_tableView);
        tables.getChildren().add(orderTableView);
        borderPane.setCenter(tables);
        formPane.getChildren().add(resultLabel);
        borderPane.setBottom(formPane);

        Image img = new Image("C:\\Users\\User\\Downloads\\banner-with-open-book-pages-isolated-white-background-reading-hobby-education-concept-copyspace-high-quality-photo_361816-4634.jpg");
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

        Label nameLabel = new Label("Client Name", nameField);

        nameLabel.setContentDisplay(ContentDisplay.TOP);
        totalLabel.setContentDisplay(ContentDisplay.RIGHT);
        formPane.getChildren().addAll(nameLabel, saveBtn, totalLabel);
    }

    public Label getTotalValueLabel() {
        return totalValueLabel;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public Order getOrder() {
        return order;
    }


    public TableColumn<BookOrder, Integer> getStockCol(){
        return stockCol;
    }

    public TableColumn<BookOrder, Float> getTotalPriceCol() {
        return totalPriceCol;
    }

    public TableColumn<BookOrder, String> getAuthorCol() {
        return authorCol;
    }

    public Label getResultLabel() {
        return resultLabel;
    }

    public TableColumn<BookOrder, Integer> getNoCol() {
        return noCol;
    }

    public TableColumn<BookOrder, String> getTitleCol() {
        return titleCol;
    }

    public TableColumn<BookOrder, Float> getPriceCol() {
        return priceCol;
    }
}


