package com.example.anitamjeshtrifinalpj;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class CreateButton extends Button {
    public CreateButton() {
        super.setText("Save");
        super.setTextFill(Color.WHITE);
        super.setStyle("-fx-background-color: blue");
    }
}
