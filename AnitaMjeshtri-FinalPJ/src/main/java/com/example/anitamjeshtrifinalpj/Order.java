package com.example.anitamjeshtrifinalpj;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Order extends Model implements Serializable {
    @Serial
    private static final long serialVersionUID = -6672248350784369808L;
    //Printing bills
    private String clientName;
    private String username;
    private String orderID;
    private ArrayList<BookOrder> booksOrdered;
    private String date;

    private static final ArrayList<Order> book_orders = new ArrayList<>();
    public static final String FILE_PATH = "orders.ser";
    public static final File dataFile = new File(FILE_PATH);

    private static final DateTimeFormatter idFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");

    private static final DateTimeFormatter datatf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public Order(String username) {
        booksOrdered = new ArrayList<>();
        setUsername(username);

    }
    public void completeOrder(String clientName){
        setClientName(clientName);
        LocalDateTime now = LocalDateTime.now();
        setDate(datatf.format(now));
        setOrderID("Order_"+idFormatter.format(now));
    }

    @Override
    public String toString() {
        StringBuilder s= new StringBuilder("Order: " + orderID + "\nDate: " + date + "\nClient: " + clientName + "\nBooks Ordered: \n");
        for (BookOrder b: booksOrdered)
            s.append(b).append("\n");
        s.append(String.format("\nTotal: %.2f", getTotal()));
        return s.toString();
    }

    public float getTotal(){
        float sum=0;
        for(BookOrder b : booksOrdered)
            sum+=b.getTotalPrice();
        return sum;
    }
    public void print(){
        try {
            PrintWriter p = new PrintWriter("BillNo.txt");
            p.println(this);
            p.close();
        }catch (Exception ex){
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    public ArrayList<BookOrder> getBooksOrdered() {
        return booksOrdered;
    }


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setBooksOrdered(ArrayList<BookOrder> booksOrdered) {
        this.booksOrdered = booksOrdered;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean saveInFile() {
        if (super.save(Order.dataFile))
            book_orders.add(this);
        return super.save(Order.dataFile);
    }

    @Override
    public void updateFile() {
        try {
            FileHandler.overwriteCurrentListToFile(dataFile, book_orders);
        }
        catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public boolean isValid() {
        return clientName.length() > 0;
    }

    @Override
    public boolean deleteFromFile() {
        book_orders.remove(this);
        try {
            FileHandler.overwriteCurrentListToFile(dataFile, book_orders);
        }
        catch (Exception e){
            book_orders.add(this);
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
        return true;
    }

    public static ArrayList<Order> getOrders() {
        if (book_orders.size() == 0) {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
                while (true) {
                    Order temp = (Order) inputStream.readObject();
                    if (temp == null)
                        break;
                    book_orders.add(temp);
                }
                inputStream.close();
            } catch (EOFException eofException) {
                System.out.println("End of orders file");
            }
            catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return book_orders;
    }
}
