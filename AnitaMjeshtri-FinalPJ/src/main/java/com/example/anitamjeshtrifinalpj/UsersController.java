package com.example.anitamjeshtrifinalpj;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class UsersController {
    private final UsersView usersView;
    public UsersController(UsersView usersView) {
        this.usersView = usersView;
        setSaveListener();
        setDeleteListener();
        setEditListener();
        setComboBoxListener();
    }

    private void setEditListener() {
        usersView.getUsernameCol().setOnEditCommit(e -> {
            User editUsername = e.getRowValue();
            String oldUsername=editUsername.getUsername();
            editUsername.setUsername(e.getNewValue());
            if (editUsername.isValid()){
                editUsername.updateFile();
            }
            else {
                System.out.println("Edit value invalid! "+e.getNewValue());
                editUsername.setUsername(oldUsername);
                usersView.getTableView().getItems().set(usersView.getTableView().getItems().indexOf(editUsername), editUsername);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid username");
                alert.setContentText("Please enter a valid username");
                alert.show();
            }
        });

        usersView.getFirstNameCol().setOnEditCommit(e->{
            User editFirstName = e.getRowValue();
            String oldName = editFirstName.getFirstName();
            System.out.println(Arrays.toString(editFirstName.getFirstName().getBytes()));
            System.out.println(Arrays.toString(editFirstName.getBirthday().getBytes()));
            editFirstName.setFirstName(e.getNewValue());
            if(editFirstName.isValid()){
                editFirstName.updateFile();
            }
            else{
                System.out.println("Edit value invalid!"+e.getNewValue());
                editFirstName.setFirstName(oldName);
                usersView.getTableView().getItems().set(usersView.getTableView().getItems().indexOf(editFirstName),editFirstName);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid edit");
                alert.setContentText("Please enter a valid name");
                alert.show();
            }
        });
        usersView.getPasswordCol().setOnEditCommit(e -> {
            User editPassword = e.getRowValue();
            String oldPass=editPassword.getPassword();
            editPassword.setPassword(e.getNewValue());
            if (editPassword.isValid()){
                editPassword.updateFile();
            }
            else {
                System.out.println("Edit value invalid!"+e.getNewValue());
                editPassword.setPassword(oldPass);
                usersView.getTableView().getItems().set(usersView.getTableView().getItems().indexOf(editPassword), editPassword);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid edit");
                alert.setContentText("Please enter a valid password");
                alert.show();
            }
        });
        usersView.getLastNameCol().setOnEditCommit(e -> {
            User editLastName = e.getRowValue();
            String oldSurname=editLastName.getLastName();
            editLastName.setLastName(e.getNewValue());
            if (editLastName.isValid()){
                editLastName.updateFile();
            }
            else {
                System.out.println("Edit value invalid!"+e.getNewValue());
                editLastName.setLastName(oldSurname);
                usersView.getTableView().getItems().set(usersView.getTableView().getItems().indexOf(editLastName), editLastName);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid edit");
                alert.setContentText("Please enter a valid surname");
                alert.show();
            }
        });
        usersView.getBirthdayCol().setOnEditCommit(e->{
            User editBirthday = e.getRowValue();
            String oldBirthday = editBirthday.getBirthday();
            editBirthday.setBirthday(e.getNewValue());
            if(editBirthday.isValid()){
                editBirthday.updateFile();
            }
            else{
                System.out.println("Edit value invalid!"+e.getNewValue());
                editBirthday.setBirthday(oldBirthday);
                usersView.getTableView().getItems().set(usersView.getTableView().getItems().indexOf(editBirthday),editBirthday);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid edit");
                alert.setContentText("Please enter a valid birthday");
                alert.show();
            }
        });
        usersView.getEmailCol().setOnEditCommit(e->{
            User editEmail = e.getRowValue();
            String oldEmail = editEmail.getEmail();
            editEmail.setEmail(e.getNewValue());
            if(editEmail.isValid()){
                editEmail.updateFile();
            }
            else{
                System.out.println("Edit value invalid!"+e.getNewValue());
                editEmail.setEmail(oldEmail);
                usersView.getTableView().getItems().set(usersView.getTableView().getItems().indexOf(editEmail),editEmail);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid edit");
                alert.setContentText("Please enter a valid email");
                alert.show();
            }
        });
        usersView.getPhoneNumCol().setOnEditCommit(e->{
            User editPhoneNum = e.getRowValue();
            String oldPhoneNum = editPhoneNum.getPhoneNum();
            editPhoneNum.setPhoneNum(e.getNewValue());
            if(editPhoneNum.isValid()){
                editPhoneNum.updateFile();
            }
            else{
                System.out.println("Edit value invalid!"+e.getNewValue());
                editPhoneNum.setPhoneNum(oldPhoneNum);
                usersView.getTableView().getItems().set(usersView.getTableView().getItems().indexOf(editPhoneNum),editPhoneNum);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid edit");
                alert.setContentText("Please enter a valid phone number");
                alert.show();
            }
        });

        usersView.getRoleCol().setOnEditCommit(e -> {
            User editRole = e.getRowValue();
            Role oldRole=editRole.getRole();
            editRole.setRole(e.getNewValue());
            if (editRole.isValid()){
                editRole.updateFile();
            }
            else {
                System.out.println("Edit value invalid!"+e.getNewValue());
                editRole.setRole(oldRole);
                usersView.getTableView().getItems().set(usersView.getTableView().getItems().indexOf(editRole), editRole);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid edit");
                alert.setContentText("Please enter a valid role");
                alert.show();
            }
        });
    }

    private void setDeleteListener() {
        usersView.getDeleteBtn().setOnAction(e->{
            List<User> itemsToDelete = List.copyOf(usersView.getTableView().getSelectionModel().getSelectedItems());
            for (User user: itemsToDelete) {
                if (user.deleteFromFile()) {
                    usersView.getTableView().getItems().remove(user);
                    System.out.println("The user:"+user.getUsername()+" is deleted!");
                    usersView.getResultLabel().setText("User is deleted!");
                    usersView.getResultLabel().setTextFill(Color.GREEN);
                } else {
                    usersView.getResultLabel().setText("User is not deleted!");
                    usersView.getResultLabel().setTextFill(Color.RED);
                    break;
                }
            }
        });
    }

    private void setSaveListener() {
        usersView.getSaveBtn().setOnAction(e -> {
            String username = usersView.getUserNameField().getText();
            //System.out.println(usersView.getUserNameField().getText());
            String password = usersView.getPasswordField().getText();
            String firstName = usersView.getFirstNameField().getText();
            String lastName = usersView.getLastNameField().getText();
            String birthday = usersView.getBirthdayField().getText();
            String email = usersView.getEmailField().getText();
            String phoneNum = usersView.getPhoneNumField().getText();
            Role role = usersView.getRoleComboBox().getValue();
            User user = new User(username,firstName,lastName,birthday,email,phoneNum,role,password);
            if (!user.usernameExists()) {
                if (user.saveInFile()) {
                    usersView.getTableView().getItems().add(user);
                    usersView.getResultLabel().setText("User added successfully!");
                    usersView.getResultLabel().setTextFill(Color.GREEN);
                    resetFields();
                } else {
                    if(user.checkUserNameValidation())
                        usersView.registerGridPane.add(new Label("Incorrect username"),4,0);
                    if(user.checkFirstNameValidation())
                        usersView.registerGridPane.add(new Label("Incorrect name"),4,1);
                    if(user.checkLastNameValidation())
                        usersView.registerGridPane.add(new Label("Incorrect surname"),4,2);
                    if(user.checkBirthdayValidation())
                        usersView.registerGridPane.add(new Label("Incorrect birth date"),4,3);
                    if(user.checkEmailValidation())
                        usersView.registerGridPane.add(new Label("Incorrect email"),4,4);
                    if(user.checkPhoneNumValidation())
                        usersView.registerGridPane.add(new Label("Incorrect phone number"),4,5);
                    if(user.checkPasswordValidation())
                        usersView.registerGridPane.add(new Label("Incorrect password"),4,7);
                    usersView.registerGridPane.setHgap(5);
                    usersView.getResultLabel().setText("Could not add the user!");
                    usersView.getResultLabel().setTextFill(Color.RED);
                }
            }
            else {
                usersView.getResultLabel().setText("You canNOT add users with the same username!");
                usersView.getResultLabel().setTextFill(Color.RED);
            }
        });
    }
    private void setComboBoxListener(){
        usersView.getRoleComboBox().setOnMouseClicked(e->{
            usersView.getRoleComboBox().getItems().setAll(Role.ADMIN,Role.MANAGER,Role.LIBRARIAN);
            if (!User.getUsers().isEmpty())
                usersView.getRoleComboBox().setValue(Role.MANAGER);
        });

    }
    public void resetFields(){
        usersView.getFirstNameField().clear();
        usersView.getLastNameField().clear();
        usersView.getUserNameField().clear();
        usersView.getEmailField().clear();
        usersView.getBirthdayField().clear();
        usersView.getPhoneNumField().clear();
        usersView.getPasswordField().clear();
    }

}
