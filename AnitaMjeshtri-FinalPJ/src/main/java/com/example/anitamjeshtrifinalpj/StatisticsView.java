package com.example.anitamjeshtrifinalpj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class StatisticsView extends View{
    private final VBox vBox = new VBox();
    public StatisticsView() {

        new StatisticsController(this);
    }


    public Parent getView2() {
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        ArrayList<Order> book_orders = Order.getOrders();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for(Order order : book_orders) {
            boolean flag = true;
            for (PieChart.Data data: pieChartData) {
                if (order.getClientName().matches(data.getName())) {
                    data.setPieValue(data.getPieValue()+order.getTotal());
                    flag = false;
                    break;
                }
            }
            if (flag) {
                pieChartData.add(new PieChart.Data(order.getClientName(), order.getTotal()));
            }

        }
        System.out.println("SIZEE "+pieChartData.size());
        final PieChart chart = new PieChart(pieChartData);
        System.out.println("The sales for each client");
        float sum = 0;
        for(PieChart.Data d: pieChartData){
            System.out.println(d.getName()+" "+d.getPieValue());
            sum+=d.getPieValue();
        }
        System.out.println(sum);
        chart.setTitle("Clients who ordered books");
        chart.setStyle("-fx-font-weight: bold;");
        return chart;
    }



    public Parent getView() {
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        Order.getOrders().clear();
        ArrayList<Order> orders = Order.getOrders();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for(Order order : orders) {
            for (BookOrder bookOrder: order.getBooksOrdered()){
                boolean flag = true;
                for (PieChart.Data d: pieChartData) {
                    if (bookOrder.getTitle().matches(d.getName())) {
                        d.setPieValue(d.getPieValue() + bookOrder.getQuantity());
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    pieChartData.add(new PieChart.Data(bookOrder.getTitle(), bookOrder.getQuantity()));
                }
            }
        }

        //PRINTTTTTTTT
        int sum = 0;
        System.out.println("The ordered books: ");
        for (PieChart.Data d: pieChartData){
            System.out.println(d.getName()+" "+d.getPieValue());
            sum+=d.getPieValue();
        }
        System.out.println("Total books sold "+sum);
        final PieChart chart = new PieChart(pieChartData);

        chart.setTitle("The ordered books");
        chart.setStyle("-fx-font-weight: bold;");

        return chart;
    }

    public Parent getView3(){
        HBox hBox = new HBox(getView(),getView2());
        hBox.setAlignment(Pos.CENTER);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hBox);
        return borderPane;
    }
}
