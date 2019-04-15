package Stages;

import model.Expenses;
import model.SortByExpenseAmount;
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Artur
 */
public class MathCalculations extends Stage {

    GridPane root = new GridPane();

    StackPane rootYearly = new StackPane();
    StackPane rootMonthly = new StackPane();
    StackPane rootBar = new StackPane();

    Button btnMonthChart = new Button("Last month's expenses");
    Button btnYearChart = new Button("Last year's expenses");
    Button btnBarChart = new Button("Expenses per category");

    ArrayList<String> expenseArray = new ArrayList<>();

    MathCalculations() {

        root.add(btnMonthChart, 0, 0);
        root.add(btnYearChart, 1, 0);
        root.add(btnBarChart, 0, 1);
        Scene scene = new Scene(root, 300, 100);
        this.setScene(scene);
        this.setTitle("Expenses Chart");
        this.show();

        ArrayList<Expenses> mathTest = new ArrayList<>();
        
        mathTest.add(new Expenses(93.12, "Food", "2019/01/2"));
        mathTest.add(new Expenses(105.00, "Leisure", "2019/03/4"));
        mathTest.add(new Expenses(189.03, "Transportation", "2019/05/12"));
        mathTest.add(new Expenses(124.52, "Subscriptions", "2019/07/22"));

        SortByExpenseAmount sortByAmount = new SortByExpenseAmount();
        Collections.sort(mathTest, sortByAmount);

        for (int i = 0; i < mathTest.size(); i++) {
            System.out.println(mathTest.get(i).getExpense());
        }
        btnMonthChart.setOnAction(event -> createMonthlyLineChart(mathTest));
        btnYearChart.setOnAction(event -> createYearlyLineChart(mathTest));
        btnBarChart.setOnAction(event -> BarGraphExpenseType(mathTest));

    }

    private void createMonthlyLineChart(ArrayList<Expenses> list) {
        final NumberAxis xAxis = new NumberAxis(1, 31, 1);
        final NumberAxis yAxis = new NumberAxis(0, (list.get(list.size() - 1).getExpense())*1.1, 20);
        final LineChart monthlyChart = new LineChart<>(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();

        xAxis.setLabel("Date of transaction");
        yAxis.setLabel("Amount spent");
        monthlyChart.setTitle("Expenses last month");

        for (int i = 0; i < list.size(); i++) {
            //make instance of expense each loop to grab amount and date from arrayList
            Expenses expense = list.get(i);

            double amount = expense.getExpense();
            String date = expense.getTimeOfPurchase();
            int day = 0;
            //format date 
            if (date.contains("/")) {
                date = replaceDateElements(date, 0);
            }
            //date is returned as a string but the chart uses ints/doubles
            day = Integer.parseInt(date);

            series.getData().add(new XYChart.Data(day, amount));
        }

        monthlyChart.getData().add(series);
        rootMonthly.getChildren().add(monthlyChart);
        Scene scene = new Scene(rootMonthly, 500, 500);

        this.setScene(scene);
        this.setTitle("Expenses Chart");
        this.show();
    }

    private void createYearlyLineChart(ArrayList<Expenses> list) {

        XYChart.Series series = new XYChart.Series();

        //initialize arraylist with 12 values, all 0.0
        ArrayList<Double> averagePerMonth = new ArrayList<>(Collections.nCopies(12, 0.0));

        //read expenses array, calculate avg per month, copy to perMonth array
        calculateAverageMonthlyExpense(list, averagePerMonth);

        //x axis is i (month 1-12), y axis is the amount from the above array per month
        for (int i = 0; i < averagePerMonth.size(); i++) {
            series.getData().add(new XYChart.Data(i + 1, averagePerMonth.get(i)));
        }
        double yAxisMax = calculateHighestMonthYearly(averagePerMonth);
        //add all data to graph

        final NumberAxis xAxis = new NumberAxis(1, 12, 1);
        final NumberAxis yAxis = new NumberAxis(0, yAxisMax + (yAxisMax * 0.1), 20);
        final LineChart yearlyChart = new LineChart<>(xAxis, yAxis);

        xAxis.setLabel("Month");
        yAxis.setLabel("Amount spent");
        yearlyChart.setTitle("Expenses per month");

        yearlyChart.getData().add(series);
        rootYearly.getChildren().add(yearlyChart);
        Scene scene = new Scene(rootYearly, 500, 500);

        this.setScene(scene);
        this.setTitle("Expenses Chart");
        this.show();
    }

    private void BarGraphExpenseType(ArrayList<Expenses> list) {
        //fill arraylist with same categories as the expenses stage to match to
        expenseArray.add("Food");
        expenseArray.add("Transportation");
        expenseArray.add("Mortgage");
        expenseArray.add("Leisure");
        expenseArray.add("Phone");
        expenseArray.add("Subscriptions");
        
        XYChart.Series series = new XYChart.Series();
        
        ArrayList<Double> expensesResults = new ArrayList<>(Collections.nCopies(6, 0.0));
        //fill array with expenses per type
        calculateExpenseByCategory(list, expenseArray, expensesResults);
        
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(expenseArray));
        
        //first array holds strings of categories, second holds corresponding amount in dollars
        for(int i = 0; i < expensesResults.size(); i++){
            series.getData().add(new XYChart.Data<>(expenseArray.get(i), expensesResults.get(i)));
        }
        
        final NumberAxis yAxis = new NumberAxis(0, 300, 20);

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.getData().add(series);
        barChart.setTitle("What you spent your money on");
        
        rootBar.getChildren().add(barChart);
        Scene scene = new Scene(rootBar, 500, 500);

        this.setScene(scene);
        this.setTitle("Category Graph");
        this.show();
    }

    //0 for sending the date back and 1 for sending the month back formatted
    private String replaceDateElements(String date, int type) {
        String[] parts = date.split("/");
        String year = parts[0];
        String month = parts[1];
        String day = parts[2];

        if (type == 0) {
            String formattedDate = formatDate(day);
            return formattedDate;
        } else if (type == 1) {
            String formattedDate = formatMonth(month);
            return formattedDate;
        }
        return null;
    }

    private String formatDate(String date) {
        String tempDate = null;
        if (date.startsWith("0")) {
            tempDate = date.replace("0", "");
        } else {
            tempDate = date;
        }
        return tempDate;
    }

    private String formatMonth(String month) {
        String tempMonth = null;
        if (month.startsWith("0")) {
            tempMonth = month.replace("0", "");
        } else {
            tempMonth = month;
        }
        return tempMonth;
    }

    //takes in an arraylist of the same month and sums the expenses
    private void calculateAverageMonthlyExpense(ArrayList<Expenses> expenses, ArrayList<Double> averages) {
        for (int i = 0; i < expenses.size(); i++) {

            //split the date and loop array checking the month of each purchase
            String[] dateSplit = (expenses.get(i).getTimeOfPurchase()).split("/");
            String month = dateSplit[1];
            //match the month to the averages arraylist holding doubles, += expenses to it, and replace it.
            //HUGE switch statement, couldnt think of a cleaner way but it works
            double temp = 0.0;
            switch (month) {
                case "01":
                    temp = averages.get(0);
                    temp += expenses.get(i).getExpense();
                    averages.remove(0);
                    averages.add(0, temp);
                    break;
                case "02":
                    temp = averages.get(1);
                    temp += expenses.get(i).getExpense();
                    averages.remove(1);
                    averages.add(1, temp);
                    break;
                case "03":
                    temp = averages.get(2);
                    temp += expenses.get(i).getExpense();
                    averages.remove(2);
                    averages.add(2, temp);
                    break;
                case "04":
                    temp = averages.get(3);
                    temp += expenses.get(i).getExpense();
                    averages.remove(3);
                    averages.add(3, temp);
                    break;
                case "05":
                    temp = averages.get(4);
                    temp += expenses.get(i).getExpense();
                    averages.remove(4);
                    averages.add(4, temp);
                    break;
                case "06":
                    temp = averages.get(5);
                    temp += expenses.get(i).getExpense();
                    averages.remove(5);
                    averages.add(5, temp);
                    break;
                case "07":
                    temp = averages.get(6);
                    temp += expenses.get(i).getExpense();
                    averages.remove(6);
                    averages.add(6, temp);
                    break;
                case "08":
                    temp = averages.get(7);
                    temp += expenses.get(i).getExpense();
                    averages.remove(7);
                    averages.add(7, temp);
                    break;
                case "09":
                    temp = averages.get(8);
                    temp += expenses.get(i).getExpense();
                    averages.remove(8);
                    averages.add(8, temp);
                    break;
                case "10":
                    temp = averages.get(9);
                    temp += expenses.get(i).getExpense();
                    averages.remove(9);
                    averages.add(9, temp);
                    break;
                case "11":
                    temp = averages.get(10);
                    temp += expenses.get(i).getExpense();
                    averages.remove(10);
                    averages.add(10, temp);
                    break;
                case "12":
                    temp = averages.get(11);
                    temp += expenses.get(i).getExpense();
                    averages.remove(11);
                    averages.add(11, temp);
                    break;
            }
        }
    }
    
    private void calculateExpenseByCategory(ArrayList<Expenses> allExpenses, ArrayList<String> categories, 
            ArrayList<Double> expenseResults){
        
        /*
        The outer loop iterates over the allExpenses array holding all the data. It saves the amount and category
        temporarily and then iterates the next loop, through the categories array to match values. When they match, 
        the index they match at corresponds to the index of the third array the initial amount is added to, so one array
        holds 6 categories and the other holds 6 doubles corresponding to the total spent per category. 
        */
        for(int i = 0; i < allExpenses.size (); i++){
                Expenses expense = allExpenses.get(i);
                Double amount = expense.getExpense();
                String tempCategory = expense.getExpenseCategory();
                for(int j = 0; j < categories.size(); j++){
                    if(tempCategory.equals(categories.get(j))){
                        double temp = expenseResults.get(j);
                        temp += amount;
                        expenseResults.remove(j);
                        expenseResults.add(j, temp);                    
                    }
                }
            }
    }
        
    

    private double calculateTotalYearly(ArrayList<Double> list) {
        double total = 0.0;
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i);
        }
        return total;
    }

    private double calculateHighestMonthYearly(ArrayList<Double> list) {
        double largest = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (largest < list.get(i)) {
                largest = list.get(i);
            }
        }
        return largest;
    }

}