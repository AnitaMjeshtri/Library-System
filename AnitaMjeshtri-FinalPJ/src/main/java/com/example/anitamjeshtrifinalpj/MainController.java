package com.example.anitamjeshtrifinalpj;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

public class MainController {
    private final MainView mainView;
    private final Stage mainStage;

    public MainController(MainView mainView, Stage mainStage) {
        this.mainView = mainView;
        this.mainStage = mainStage;
        setListener();
    }
    private void openTab(Tab tab){
        for(Tab t:mainView.getTabPane().getTabs()){
            if(t.getText().equals(tab.getText())){
                return;
            }
        }
        mainView.getTabPane().getTabs().add(tab);
    }

    private void setListener() {

        mainView.getViewBooks().setOnAction((e)-> {
            Tab booksTab = new Tab("Books");
            booksTab.setContent(new BookView().getView());
            openTab(booksTab);
        });

        mainView.getViewAuthors().setOnAction((e)-> {
            Tab authorTab = new Tab("Authors");
            authorTab.setContent(new AuthorView().getView());
            openTab(authorTab);
        });

        mainView.getSalesStatistics().setOnAction(e->{
            Tab sales = new Tab("Sales Graphics");
            sales.setContent(new StatisticsView().getView3());
            openTab(sales);
        });

        mainView.getManageUsers().setOnAction(e->{
            Tab users = new Tab("Users");
            users.setContent(new UsersView().getView());
            openTab(users);
        });

        mainView.getNewBill().setOnAction(e->{
            Tab order = new Tab("New Order");
            order.setContent(new GeneratingBillView(mainView, order).getView());
            openTab(order);
        });

        mainView.getLogoutMenuLabel().setOnMouseClicked((e)->{
            LoginView loginView = new LoginView();
            new LoginController(loginView, new MainView(mainStage), mainStage);
            Scene scene = new Scene(loginView.getView(), 900, 520);
            mainStage.setScene(scene);
        });
    }
}
