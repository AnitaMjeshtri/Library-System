package com.example.anitamjeshtrifinalpj;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class LoginView extends View {
    private final BorderPane borderPane = new BorderPane();

    private final TextField usernameField = new TextField();

    private final PasswordField passwordField = new PasswordField();
    private final Button loginBtn = new Button("Login");
    private final Label error = new Label("Please enter the correct username or password!");
    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLoginBtn() {
        return loginBtn;
    }
    public Label getError() {
        return error;
    }
    public LoginView() {
        setView();
    }
    public VBox vBox = new VBox();
    private void setView() {

        Label usernameLabel = new Label("Username", usernameField);
        Label passwordLabel = new Label("Password", passwordField);
        final Label WELCOME = new Label("Hello and Welcome to the Epoka's Bookstore!");
        WELCOME.setStyle("-fx-font-size: 25");
        WELCOME.setTextFill(Color.DEEPSKYBLUE);
        final Label LOGIN = new Label("LOGIN");
        final Text directions = new Text("Please enter your username and password");
        directions.setStyle("-fx-fill:grey");
        directions.setFont(Font.font("Verdana", FontPosture.ITALIC, 10));
        vBox.getChildren().addAll(WELCOME,LOGIN,directions,usernameLabel,passwordLabel,loginBtn);
        Image img = new Image("file:back-image.png");
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        borderPane.setBackground(bGround);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(15);
        borderPane.setCenter(vBox);
    }
    @Override
    public Parent getView() {
        return borderPane;
    }
}
