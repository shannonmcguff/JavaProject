//
///* @authors Artur Hrytsenko */
//
//package Stages;
////This class will be used to create a graph of the expenses made by the user 
//
//import javafx.scene.Scene;
//import javafx.scene.chart.LineChart;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//import javafx.stage.Stage;
//
//
//public class MathCalculations extends Stage{
//
//  MathCalculations() {
//        final NumberAxis xAxis = new NumberAxis();
//        final NumberAxis yAxis = new NumberAxis();
//        xAxis.setLabel("Date of transaction");
//        yAxis.setLabel("Amount spent");
//        
//        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
//        lineChart.setTitle("Spending graph");
//        
////Currently cannot access the arrayList of Expenses from the Expenses class
//        XYChart.Series series = new XYChart.Series();
//        for(int i = 0; i < ExpensesArrayList.size(); i++){
//            String axisX = ExpensesArrayList.get(i).getTimeOfPurchase();
//            String axisY = ExpensesArrayList.get(i).getAddedExpenses();
//            
//            series.getData().add(new XYChart.Data(axisX, axisY));
//        }
//        Scene scene = new Scene(lineChart, 500, 500);
//        this.setScene(scene);
//        this.setTitle("Expenses Chart");
//        this.show();
//    }
//}