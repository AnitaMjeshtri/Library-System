package com.example.anitamjeshtrifinalpj;

import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

public class GeneratingBillController {
    private final GeneratingBillView orderView;

    public GeneratingBillController(GeneratingBillView bookView) {
        this.orderView = bookView;
        Order.getOrders();
        setEditListener();
        setChooseBookListener();
        setRemoveBookListener();
        setCreateListener();
    }

    private void setChooseBookListener(){

        Alert alert = new Alert(Alert.AlertType.NONE);
        orderView.getBooks_tableView().setOnMousePressed(e->{
            if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
                BookOrder bookOrder = new BookOrder(1, orderView.getBooks_tableView().getSelectionModel().getSelectedItem());
                if(orderView.getBooks_tableView().getSelectionModel().getSelectedItem().getStock()<5){
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setContentText("Stock of the book less than five");
                    alert.show();
                }
                if(orderView.getBooks_tableView().getSelectionModel().getSelectedItem().getStock()==0){
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("This book is no more in stock!");
                    alert.show();
                }
                else{
                    orderView.getOrder().getBooksOrdered().add(bookOrder);
                    orderView.getBooks_tableView().getItems().remove(orderView.getBooks_tableView().getSelectionModel().getSelectedItem());
                    orderView.getOrderTableView().getItems().add(bookOrder);
                    orderView.getTotalValueLabel().setText(((Float)orderView.getOrder().getTotal()).toString());
                }
            }
        });
    }

    private void setRemoveBookListener(){
        orderView.getOrderTableView().setOnMousePressed(e->{
            if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
                BookOrder bookOrder = orderView.getOrderTableView().getSelectionModel().getSelectedItem();
                orderView.getOrder().getBooksOrdered().remove(bookOrder);
                orderView.getBooks_tableView().getItems().add(bookOrder.getBook());
                orderView.getOrderTableView().getItems().remove(bookOrder);
                orderView.getTotalValueLabel().setText(((Float)orderView.getOrder().getTotal()).toString());
            }
        });
    }

    private void setEditListener() {
        orderView.getNoCol().setOnEditCommit(event -> {
            BookOrder editBookOrder = event.getRowValue();
            int oldBookOrder = editBookOrder.getQuantity();
            if(event.getNewValue() <= editBookOrder.getStock()) {
                editBookOrder.setQuantity(event.getNewValue());
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("There are not enough book in the stock");
                alert.show();
            }

            if (editBookOrder.getQuantity() > 0){
                System.out.println(orderView.getOrder().getBooksOrdered());
                orderView.getTotalPriceCol().setText(((Float)orderView.getOrder().getTotal()).toString());
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                editBookOrder.setQuantity(oldBookOrder);
                orderView.getOrderTableView().getItems().set(orderView.getOrderTableView().getItems().indexOf(editBookOrder), editBookOrder);
                alert.setContentText("Try entering a new value different from the current one!");
                alert.show();
            }
        });

    }

    private void setCreateListener() {
        orderView.getSaveBtn().setOnMousePressed(e -> {
            orderView.getOrder().completeOrder(orderView.getNameField().getText());
            if (orderView.getOrder().saveInFile()) {
                orderView.getOrder().print();
                orderView.getResultLabel().setText("Your order is created!");
                orderView.getResultLabel().setTextFill(Color.GREEN);

                for(BookOrder b: orderView.getOrder().getBooksOrdered()){
                    b.getBook().setStock(b.getBook().getStock() - b.getQuantity());
                    orderView.getBooks_tableView().getItems().remove(b.getBook());
                    orderView.getBooks_tableView().getItems().add(b.getBook());
                    b.getBook().updateFile();

                }
                resetFields();
            }
            else {
                orderView.getResultLabel().setText("Please enter a valid client name!");
                orderView.getResultLabel().setTextFill(Color.YELLOW);
            }
        });
    }
    private void resetFields() {
        orderView.getNameField().clear();
    }
}
