package com.example.anitamjeshtrifinalpj;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class UsersView extends View {
    private final BorderPane borderPane = new BorderPane();
    public GridPane registerGridPane = new GridPane();
    private final TableView<User> tableView = new TableView<>();
    private final HBox formPane = new HBox();
    private final TextField userNameField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final TextField firstNameField = new TextField();
    private final TextField lastNameField = new TextField();
    private final TextField birthdayField = new TextField();
    private final TextField emailField = new TextField();
    private final TextField phoneNumField = new TextField();
    public TextField getPhoneNumField(){
        return phoneNumField;
    }
    public TextField getBirthdayField(){
        return birthdayField;
    }
    public TextField getEmailField(){
        return emailField;
    }
    public TextField getFirstNameField(){
        return firstNameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    private final ComboBox<Role> roleComboBox = new ComboBox<>();
    private final Button saveBtn = new CreateButton();
    private final Button deleteBtn = new DeleteButton();
    private final TableColumn<User, String> firstNameCol = new TableColumn<>("First Name");
    private final TableColumn<User,String>lastNameCol = new TableColumn<>("Last Name");
    private final TableColumn<User, String>emailCol = new TableColumn<>("Email Address");
    private final TableColumn<User, String>phoneNumCol = new TableColumn<>("Phone Number");
    private final TableColumn<User, String>birthdayCol = new TableColumn<>("Birthday");
    private final TableColumn<User, String> usernameCol = new TableColumn<>("Username");
    private final TableColumn<User, String> passwordCol = new TableColumn<>("Password");
    private final TableColumn<User, Role> roleCol = new TableColumn<>("Role");
    private final Label resultLabel = new Label("");


    public ComboBox<Role> getRoleComboBox() {
        return roleComboBox;
    }

    public TextField getUserNameField() {
        return userNameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public TableColumn<User, String> getPasswordCol() {
        return passwordCol;
    }

    public TableColumn<User, Role> getRoleCol() {
        return roleCol;
    }



    public TableColumn<User, String> getUsernameCol() {
        return usernameCol;
    }
    public TableColumn<User, String>getFirstNameCol(){
        return firstNameCol;
    }
    public TableColumn<User, String>getLastNameCol(){
        return lastNameCol;
    }
    public TableColumn<User, String>getEmailCol(){
        return emailCol;
    }
    public TableColumn<User, String>getPhoneNumCol(){
        return phoneNumCol;
    }
    public TableColumn<User, String>getBirthdayCol(){
        return birthdayCol;
    }
    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public Label getResultLabel() {
        return resultLabel;
    }

    public TableView<User> getTableView() {
        return tableView;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public UsersView() {
        setTableView();
        setForm();
        new UsersController(this);
    }

    @Override
    public Parent getView() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        HBox registerBtn = new HBox(saveBtn,deleteBtn);
        registerBtn.setSpacing(20);
        registerBtn.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(registerGridPane,registerBtn,resultLabel);
        HBox hbox = new HBox();
        HBox.setHgrow(tableView,Priority.ALWAYS);
        HBox.setHgrow(vBox,Priority.ALWAYS);
        hbox.getChildren().addAll(tableView,vBox);
        borderPane.setCenter(hbox);

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
        registerGridPane.add(new Label("Username"),0,0);
        registerGridPane.add(new Label("First Name"),0,1);
        registerGridPane.add(new Label("Last Name"),0,2);
        registerGridPane.add(new Label("Birthday"),0,3);
        registerGridPane.add(new Label("Email Address"),0,4);
        registerGridPane.add(new Label("Phone Number"),0,5);
        registerGridPane.add(new Label("Role"),0,6);
        registerGridPane.add(new Label("Password"),0,7);;

        registerGridPane.add(userNameField,1,0);
        registerGridPane.add(firstNameField,1,1);
        registerGridPane.add(lastNameField,1,2);
        registerGridPane.add(birthdayField,1,3);
        registerGridPane.add(emailField,1,4);
        registerGridPane.add(phoneNumField,1,5);
        registerGridPane.add(roleComboBox,1,6);
        registerGridPane.add(passwordField,1,7);
        registerGridPane.setVgap(15);
        registerGridPane.setAlignment(Pos.CENTER);
    }

    private void setTableView() {

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setEditable(true);
        tableView.setItems(FXCollections.observableArrayList(User.getUsers()));

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());

        phoneNumCol.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        phoneNumCol.setCellFactory(TextFieldTableCell.forTableColumn());

        birthdayCol.setCellValueFactory(new PropertyValueFactory<>("Birthday"));
        birthdayCol.setCellFactory(TextFieldTableCell.forTableColumn());

        usernameCol.setCellValueFactory(
                new PropertyValueFactory<>("username")
        );
        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordCol.setCellValueFactory(
                new PropertyValueFactory<>("password")
        );
        passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());

        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleCol.setCellFactory(ComboBoxTableCell.forTableColumn(Role.values()));
        tableView.getColumns().addAll(usernameCol,firstNameCol,lastNameCol,birthdayCol,emailCol,phoneNumCol,roleCol,passwordCol);
    }
}
