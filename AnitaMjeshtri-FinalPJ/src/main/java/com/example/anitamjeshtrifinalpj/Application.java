package com.example.anitamjeshtrifinalpj;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class Application extends javafx.application.Application {
    private static void getData() {
        User admin = new User("Administrator01","Anita","Mjeshtri","25/03/2003","amjeshtri21@epoka.edu.al","0685153241", Role.ADMIN,"admin01");
        User manager = new User("Manager02","Sara","Mjeshtri","25/01/2003","smjeshtri21@epoka.edu.al","0685353241",Role.MANAGER,"manager02");
        User librarian = new User("Librarian03","Eva","Mjeshtri","15/03/2003","emjeshtri21@epoka.edu.al","0685153241",Role.LIBRARIAN,"librarian03");
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(User.FILE_PATH));
            outputStream.writeObject(admin);
            outputStream.writeObject(manager);
            outputStream.writeObject(librarian);
            System.out.println("All users are written into the file");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Author.FILE_PATH))) {
            outputStream.writeObject(new Author("Danielle", "Steel"));
            outputStream.writeObject(new Author("William", "Shakespeare"));
            outputStream.writeObject(new Author("William", "Faulkner"));
            outputStream.writeObject(new Author("James", "Patterson"));
            outputStream.writeObject(new Author("Dritero","Agolli"));
            outputStream.writeObject(new Author("Ismail","Kadare"));
            outputStream.writeObject(new Author("Viktor","Canosinaj"));
            outputStream.writeObject(new Author("Charles", "Dickens"));
            outputStream.writeObject(new Author("Fyodor", "Dostoevsky"));
            outputStream.writeObject(new Author("Franz", "Kafka"));
            outputStream.writeObject(new Author("Henry", "James"));

            System.out.println("All authors are written into the file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage){

        if(1==1)
        {
            System.out.println("empty");
        }else{

        }
        LoginView loginView = new LoginView();
        new LoginController(loginView, new MainView(stage), stage);
        Image icon = new Image("file:book-icon-1.png");
        stage.getIcons().add(icon);
        stage.setTitle("Epoka's Bookstore");
        Scene scene = new Scene(loginView.getView(), 900, 520);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        //getData();
        launch();
    }
}