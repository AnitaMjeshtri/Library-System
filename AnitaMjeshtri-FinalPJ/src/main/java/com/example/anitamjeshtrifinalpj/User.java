package com.example.anitamjeshtrifinalpj;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class User extends Model implements Serializable {
    private static final ArrayList<User> users = new ArrayList<>();
    @Serial
    private static final long serialVersionUID = -2093802243392921754L;

    private String username;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private String birthday;
    private String email;
    private String phoneNum;
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public String getBirthday(){
        return birthday;
    }
    public String getPhoneNum(){
        return phoneNum;
    }

    public static final String FILE_PATH = "users.ser";
    private static final File dataFile = new File(FILE_PATH);
    public User() {}

    public User(String username, String password, Role role) {
        this(username, password);
        this.role = role;
    }

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User[" + "username:" + getUsername() + ", password:" + getPassword() + ", role:" + getRole() + ']';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User other) {
            return other.getUsername().equals(getUsername()) && other.getPassword().equals(getPassword());
        }
        return false;
    }

    public boolean usernameExists(){
        for(User u: users){
            if(u.getUsername().equals(this.getUsername()))
                return true;
        }
        return false;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static User getIfExists(User potentialUser) {
        for(User user: getUsers())
            if (user.equals(potentialUser))
                return user;
        return null;
    }

    public static ArrayList<User> getUsers () {
        if (users.size() == 0){
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
                while (true) {
                    User temp = (User) inputStream.readObject();
                    if (temp == null)
                        break;
                    users.add(temp);
                }
                inputStream.close();
            } catch (EOFException eofException) {
                System.out.println("End of file reached!");
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public boolean saveInFile() {
        boolean savedInFile = super.save(User.dataFile);
        if (savedInFile)
            users.add(this);
        return savedInFile;
    }

    @Override
    public void updateFile() {
        try {
            FileHandler.overwriteCurrentListToFile(dataFile, users);
        }
        catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public boolean checkUserNameValidation(){
        return !username.matches("[A-Za-z]+\\d{2}");
    }
    public boolean checkFirstNameValidation(){
        return !firstName.matches("\\w+");
    }
    public boolean checkLastNameValidation(){
        return !lastName.matches("\\w+");
    }
    public boolean checkEmailValidation(){
        return !email.matches("[A-Za-z]+\\d{1,2}@epoka\\.edu\\.al");
    }
    public boolean checkBirthdayValidation(){
        return !birthday.matches("\\d{1,2}/\\d{1,2}/\\d{4}");
    }
    public boolean checkPasswordValidation(){
        return !password.matches("[A-Za-z]+\\d+");
    }
    public boolean checkPhoneNumValidation(){
        return !phoneNum.matches("\\d{10}");
    }
    @Override
    public boolean isValid() {
        if (checkUserNameValidation())
            return false;
        if (checkFirstNameValidation())
            return false;
        if(checkLastNameValidation())
            return false;
        if(checkEmailValidation())
            return false;
        if(checkBirthdayValidation())
            return false;
        if(checkPasswordValidation())
            return false;
        if(checkPhoneNumValidation())
            return false;
        return true;
    }
    public User(String username,String firstName,String lastName, String birthday,String email,String phoneNum,Role role,String password){
        this(username,password,role);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        setEmail(email);
        setPhoneNum(phoneNum);
    }

    @Override
    public boolean deleteFromFile() {
        users.remove(this);
        try {
            FileHandler.overwriteCurrentListToFile(dataFile, users);
        }
        catch (Exception e){
            users.add(this);
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
        return true;
    }
}
