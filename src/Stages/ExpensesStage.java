 /* @authors Artur Hyrtsenko */
/**
 * 
 * Project Name: CASH TRACK 
 * This project is a modified banking application. Users can create an account,
 * enter their expenses, and see their expenses over a period of time.
 * The idea is that users can input their own expenses to be able to 
 * track and see how their spending habits look.
 *
 *

*/

package Stages;
import model.Expenses;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class ExpensesStage extends Stage {

    GridPane root = new GridPane();

    Button btnExpense = new Button("Add expense");

    Label lbl1 = new Label("Amount spent");
    TextField amount = new TextField();

    Label lbl2 = new Label("Time of purchase    ");
    TextField timeOfPurchase = new TextField();

    Label lbl3 = new Label("Type of expense");
    ListView<String> expenseTypes = new ListView<>();
    ArrayList<String> expenseArray = new ArrayList<>();

    ArrayList<Expenses> newData = new ArrayList<>();

    ExpensesStage() {

        root.setPadding(new Insets(10));
        root.setVgap(10);

        expenseArray.add("Food");
        expenseArray.add("Transportation");
        expenseArray.add("Mortgage");
        expenseArray.add("Leisure");
        expenseArray.add("Phone");
        expenseArray.add("Subscriptions");

        ObservableList expenseList = FXCollections.observableArrayList(expenseArray);
        expenseTypes.setItems(expenseList);
        expenseTypes.setMaxHeight(150);

        root.add(lbl1, 0, 0);
        root.add(amount, 1, 0);

        root.add(lbl2, 0, 1);
        root.add(timeOfPurchase, 1, 1);
        timeOfPurchase.setPromptText("yyyy/dd/mm");

        root.add(lbl3, 0, 2);
        root.add(expenseTypes, 1, 2);

        GridPane.setHalignment(btnExpense, HPos.RIGHT);
        root.add(btnExpense, 1, 3);

        MyHandler myHandler = new MyHandler();
        btnExpense.setOnAction(myHandler);

        Scene scene = new Scene(root, 300, 300);
        this.setScene(scene);
        this.setTitle("Add Expenses");
        this.show();
    }

    public class MyHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            boolean amountOk = checkAmount();
            boolean timeOk = checkTimeOfPurchase();
            boolean typeOk = checkExpenseType();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fix the error");
            if (!typeOk) {
                alert.setContentText("Please select the type of Expense from the drop-down");
                alert.showAndWait();
            } else if (!amountOk) {
                alert.setContentText("Please input an amount");
                alert.showAndWait();
            } else if (!timeOk) {
                alert.setContentText("Please input the date of the expense");
                alert.showAndWait();
            } else {

                String expenseType = (String) expenseTypes.getSelectionModel().getSelectedItem();
                String expenseAmount = amount.getText();
                String expenseTime = timeOfPurchase.getText();

                //format amount for constructor
                double tempExpenseAmount = formatAmount(expenseAmount);

                Expenses expense = new Expenses(tempExpenseAmount, expenseType, expenseTime);
                newData.add(expense);
                amount.clear();
                timeOfPurchase.clear();

                for (int l = 0; l < newData.size(); l++) {
                    System.out.println(newData.get(l).getExpense());
                    System.out.println(newData.get(l).getTimeOfPurchase());
                    System.out.println(newData.get(l).getExpenseCategory());
                }
            }
        }
    }

    private boolean matchesAmount(String s) {
        return s.matches("(\\d+)(.?)(\\d{0,2})");
    }

    private boolean matchesTimeOfPurchase(String s) {
        return s.matches("(\\d{4})(/{1})(\\d{2})(/{1})(\\d{2})");
    }

    //returns true if amount matches Regex
    private boolean checkAmount() {
        String amountTemp = amount.getText();
        boolean isAmount = matchesAmount(amountTemp);
        return isAmount;
    }

    //returns true if time of purchase matches Regex
    private boolean checkTimeOfPurchase() {
        String timeOfPurchaseTemp = timeOfPurchase.getText();
        boolean isTimeOfPurchase = matchesTimeOfPurchase(timeOfPurchaseTemp);
        return isTimeOfPurchase;
    }

    //returns true if user selected an option from the combobox
    private boolean checkExpenseType() {
        boolean isSelectedExpense = !(expenseTypes.getSelectionModel().isEmpty());
        return isSelectedExpense;
    }

    private double formatAmount(String amount) {
        DecimalFormat decimalFormat = new DecimalFormat("######.00");
        double tempAmount = Double.parseDouble(amount);
        amount = decimalFormat.format(tempAmount);
        return Double.parseDouble(amount);
    }
}