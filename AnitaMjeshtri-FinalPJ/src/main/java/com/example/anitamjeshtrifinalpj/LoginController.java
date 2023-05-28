package com.example.anitamjeshtrifinalpj;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
    private final Stage primaryStage;
    private final View nextView;



    private User currentUser;
    public LoginController(LoginView view, View nextView, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.nextView = nextView;
        addListener(view);
    }

    private void addListener(LoginView view) {
        final int[] cnt = {0};
        view.getLoginBtn().setOnAction(e -> {
            String password = view.getPasswordField().getText();
            String username = view.getUsernameField().getText();
            User potentialUser = new User(username, password);
            if ((currentUser = User.getIfExists(potentialUser)) != null) {
                nextView.setCurrentUser(currentUser);
                primaryStage.setScene(new Scene(nextView.getView()));
            }
            else{
                if(cnt[0] ==0){
                    view.vBox.getChildren().add(view.getError());
                }
                cnt[0]++;
            }

        });
    }
}
