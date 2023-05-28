package com.example.anitamjeshtrifinalpj;

import javafx.scene.Parent;

public abstract class View {
    static private User currentUser = null;
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User mightBeUser) {
        currentUser = mightBeUser;
    }

    public abstract Parent getView();
}
