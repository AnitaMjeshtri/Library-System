package com.example.anitamjeshtrifinalpj;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainView extends View {
    private final MenuBar menuBar = new MenuBar();

    private final Menu booksMenu= new Menu("Books");
    private final MenuItem viewBooks = new MenuItem("Show book list");
    private final MenuItem viewAuthors = new MenuItem("Show authors list");

    private final Menu salesMenu= new Menu("Sales");
    private final MenuItem newBill = new MenuItem("Generate a new bill");
    private final MenuItem salesStatistics = new MenuItem("Sale Statistics");

    private final Label logoutMenuLabel = new Label("Logout");
    private final Menu logoutMenu = new Menu("", logoutMenuLabel);

    private final Menu adminMenu = new Menu("Managing");
    private final MenuItem manageUsers = new MenuItem("Manage Users");

    public MainView(Stage mainStage){
        new MainController(this, mainStage);
    }

    private final TabPane tabPane = new TabPane();
    static Label EpokaLibrary = new Label("Epoka Public Library");
    @Override
    public Parent getView() {
        Image epokaLogo = new Image("file:EpokaLogo.png");
        ImageView EpLogo = new ImageView(epokaLogo);
        EpokaLibrary.setStyle("-fx-text-fill:BLUE;-fx-font-size: 15; -fx-font-style: italic;");
        EpokaLibrary.setPadding(new Insets(17,0,0,0));
        EpLogo.setFitWidth(50);
        EpLogo.setFitHeight(50);
        HBox headBox = new HBox(EpLogo,EpokaLibrary);
        headBox.setPadding(new Insets(25));
        BorderPane borderPane = new BorderPane();

        booksMenu.getItems().addAll(viewBooks, viewAuthors);
        salesMenu.getItems().add(newBill);
        menuBar.getMenus().addAll(booksMenu, salesMenu, logoutMenu);


        Tab defaultTab = new Tab("Books");
        defaultTab.setContent(new BookView().getView());

        Role currentRole;
       if(getCurrentUser()!=null){
           currentRole = getCurrentUser().getRole();
       }
       else
           currentRole = null;

        if (currentRole != null) {
            if (currentRole == Role.ADMIN) {
                menuBar.getMenus().add(adminMenu);
                adminMenu.getItems().addAll(manageUsers);
            }
            if (currentRole == Role.MANAGER || currentRole == Role.ADMIN) {
                salesMenu.getItems().add(salesStatistics);
            }

            tabPane.getTabs().add(defaultTab);
        }

        VBox topPane = new VBox();
        topPane.getChildren().addAll(headBox,menuBar, tabPane);
        borderPane.setTop(topPane);
        return borderPane;
    }

   public MainView(){

   }
    public MenuBar getMenuBar() {
        return menuBar;
    }

    public Menu getBooksMenu() {
        return booksMenu;
    }

    public MenuItem getViewBooks() {
        return viewBooks;
    }

    public MenuItem getViewAuthors() {
        return viewAuthors;
    }

    public Menu getSalesMenu() {
        return salesMenu;
    }

    public MenuItem getNewBill() {
        return newBill;
    }


    public Menu getLogoutMenu() {
        return logoutMenu;
    }

    public Menu getAdminMenu() {
        return adminMenu;
    }

    public MenuItem getManageUsers() {
        return manageUsers;
    }


   public MenuItem getSalesStatistics(){
        return salesStatistics;
   }

    public Label getLogoutMenuLabel() {
        return logoutMenuLabel;
    }

    public TabPane getTabPane() {
        return tabPane;
    }
}
