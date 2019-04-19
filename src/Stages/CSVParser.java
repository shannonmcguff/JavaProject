/* @authors Artur Hrytsenko */

package Stages;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Expenses;

public class CSVParser extends Stage {

    GridPane root = new GridPane();
    Button btnImport = new Button("Select a CSV file");
    Button btnClear = new Button("Clear all CSV data");
    Label lblCleared = new Label("");
    ArrayList<Expenses> CSVExpensesList = new ArrayList<>();
    ArrayList<String> CSVArrayList = new ArrayList<>();

    CSVParser() {

        root.add(btnImport, 0, 0);
        root.add(btnClear, 1, 0);
        root.add(lblCleared, 0, 1);
        Scene scene = new Scene(root, 250, 75);
        this.setScene(scene);
        this.setTitle("CSV File");
        this.show();

        btnClear.setOnAction((ActionEvent event) -> {
            CSVArrayList.clear();
            lblCleared.setText("Data has been cleared");
        });

        btnImport.setOnAction((ActionEvent event) -> {
            FileChooser chooser = new FileChooser();
            File file = new File(".");

            chooser.setTitle("Find a CSV file");
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV files", "*.csv"));

            chooser.setInitialDirectory(file);

            File selectedFile = chooser.showOpenDialog(root.getScene().getWindow());

            if (selectedFile != null) {
                try {
                    Scanner input = new Scanner(selectedFile);
                    while (input.hasNextLine()) {
                        String lineIn = input.nextLine();
                        //-1 allows parser to not skip empty spaces in CSV seen as ",,". Array field is null
                        String[] csvArray = lineIn.split(",", -1);
                        //copy array to permanent ArrayList
                        CSVArrayList.addAll(Arrays.asList(csvArray));
                    }//end of loop

                    removeBalanceTotal(CSVArrayList);

                    createExpenses(CSVArrayList);

                } catch (FileNotFoundException ex) {
                    System.out.println(ex.toString());
                }
            }
            // test print and size test
            for (int i = 0; i < CSVExpensesList.size(); i++) {
                System.out.println(CSVExpensesList.get(i).getExpense());
                System.out.println(CSVExpensesList.get(i).getExpenseCategory());
                System.out.println(CSVExpensesList.get(i).getTimeOfPurchase());
            }
//
//            int size = CSVArrayList.size();
//            System.out.println(size);

            lblCleared.setText("");
        });
    }

    private void removeBalanceTotal(ArrayList<String> list) {

        //trying to set every 5th element to ffff, then remove all elements of that value
        //since using .remove changes the array length as you iterate
        for (int i = 4; i < list.size() - 5; i += 5) {
            list.set(i, "ffff");
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains("ffff")) {
                list.remove(i);
            }
        }

    }

    private void createExpenses(ArrayList<String> list) {
        //store 4 items at a time, corresponds to one expense object
        ArrayList<String> tempArray = new ArrayList<>(4);

        boolean isExpense = false;

        //loop until arraylist has 4 strings
        int j = 0;
        while (tempArray.isEmpty() && j < list.size() - 4) {
            for (int i = 0; i < 4 && (j + i) < list.size() - 1; i++) {
                tempArray.add(list.get(i + j));
            }
            //call to see if the item is expense or income, 
            //create expense objects if true
            isExpense = checkIfExpense(tempArray);
            if (isExpense) {
                tempArray.remove(3);
                callExpensesConstructor(tempArray);
            }
            //clear the array so it can be filled again
            tempArray.clear();
            j += 4;
        }
    }

    private boolean checkIfExpense(ArrayList<String> list) {
        if (list.size() < 4) {
            return false;
        }
        String expense = list.get(2);
        String income = list.get(3);
        //the third string in the list is the expense, the fourth is income.
        if (income.equals("0") && !(expense.equals("0"))) {
            return true;
        }
        return false;
    }

    private void callExpensesConstructor(ArrayList<String> list) {
        String date = list.get(0);
        String expenseType = list.get(1);
        String amount = list.get(2);
        double formattedAmount = formatAmount(amount);

        Expenses expense = new Expenses(formattedAmount, expenseType, date);
        CSVExpensesList.add(expense);
    }

    private double formatAmount(String amount) {
        DecimalFormat decimalFormat = new DecimalFormat("######.00");
        double tempAmount = Double.parseDouble(amount);
        amount = decimalFormat.format(tempAmount);
        return Double.parseDouble(amount);
    }
}