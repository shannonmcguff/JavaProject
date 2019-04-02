package draftoffinalassignment;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ExpensesStage extends Stage{

    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setVgap(10);
        
//        Button btnIncome = new Button("Add income");
        Button btnExpense = new Button("Add expense");
        
        Label lbl1 = new Label("Amount spent");
        TextField amount = new TextField();
        
        Label lbl2 = new Label("Time of purchase    ");
        TextField timeOfPurchase = new TextField();
        
        Label lbl3 = new Label("Type of expense");
        ListView<String> expenseTypes = new ListView<>();
        ArrayList<String> expenseArray = new ArrayList<>();
       
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
        
        root.add(lbl3, 0, 2);
        root.add(expenseTypes, 1, 2);
        
        GridPane.setHalignment(btnExpense, HPos.RIGHT);
        root.add(btnExpense, 1, 3);
        
        Scene scene = new Scene(root, 400, 350);
        
        primaryStage.setTitle("Cash Track");
        primaryStage.setScene(scene);
        primaryStage.show();
    
}
}