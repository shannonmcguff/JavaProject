package draftoffinalassignment;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 *
 * @author shann
 */
public class AccountStage extends Stage {


   GridPane accountGrid = new GridPane();

   Text accountOutput = new Text();

   Button viewExpenses = new Button("View expenses");

   Button viewTransactions = new Button("View transactions");

    Button viewExpenseHistory = new Button("View expense history");
   
   Button viewAccountInfo = new Button("View account information");
   
  
   

   AccountStage() {

      accountGrid.setPadding(new Insets(10));
//
//      accountGrid.add(accountOutput, 1, 0);

      accountGrid.add(viewExpenses, 0, 0 );

      accountGrid.add(viewTransactions, 2,0);

      accountGrid.add(viewExpenseHistory, 0, 1);
//
//     accountGrid.add(viewGoals, 1, 2);
      
      accountGrid.add(viewAccountInfo, 2,1);

      viewExpenses.setOnAction(event -> showExpensesStage());

//     viewTransactions.setOnAction(event -> showTransactionsStage());

//     viewChart.setOnAction(event -> showMathStage());
      
     viewAccountInfo.setOnAction(event -> showAccountInformation());

      Scene scene = new Scene(accountGrid, 300, 300);

      this.setScene(scene);
      this.setTitle("Account");
      this.show();

   }

   public void showExpensesStage() {
      ExpensesStage expensesStage = new ExpensesStage();
   }

//   public void showTransactionsStage() {
//     TransactionStage transactionsStage = new TransactionStage();
////   }
//
//   public void showMathStage() {
//      MathStage mathStage = new  MathStage();
//   }

   public String showAccountInformation() {
      //this class has to take this user's accoutn 
      //infromation and put it to string on a labels?
      
      String userInformation = ""; 
      
      return userInformation; 
        
   }

}
