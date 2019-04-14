/* Project Name: CASH TRACK 
 * This project is a modified banking application. Users can create an account,
 * enter their expenses, and see their expenses over a period of time.
 * The idea is that users can input their own expenses to be able to 
 * track and see how their spending habits look.
 *
 *
 * @authors Shannon McGuff

 //Account class displays account information and main menu 
 //Account class has buttons that allow users to view their expenses, 
 //the math class which shows a graph of their expenses, and their account information 
 */
package Stages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import model.User;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Session;
import model.SessionInformation;

public final class AccountStage extends Stage {

   ArrayList<User> list = new ArrayList();
   BufferedReader userFileReadIn;
   String read;
   Text userinfo = new Text();

   AccountStage() {

      Label welcome = new Label("Welcome");

      //Main gridpane where buttons are added
      GridPane accountGrid = new GridPane();

      //Clicking on this button allows users to see their expenses
      Button viewExpenses = new Button("View expenses");

//Clicking takes the viewers to the math page
      Button viewExpenseHistory = new Button("View expense history");

      //clicking allows the user to see account info 
      Button viewAccountInfo = new Button("View account information");

      accountGrid.setPadding(new Insets(10));

      accountGrid.add(welcome, 0, 0);

      accountGrid.add(viewExpenses, 0, 1);

      accountGrid.add(viewExpenseHistory, 0, 2);

      accountGrid.add(viewAccountInfo, 1, 2);

      accountGrid.add(userinfo, 1, 3);

      viewExpenses.setOnAction(event -> showExpensesStage());

      //Math stage will output expense details in math for
//     viewChart.setOnAction(event -> showMathStage());
 //String created to accept the information from method whichUserIsLoggedIn
      String userInformationForAccountInformationDisplay = whichUserIsLoggedOn();

//String  userInformationForAccountInformationDisplay is passed to method fileLineWhereuserExists to get the
      //line number on the text file where the user is stored 
      int accountToPull = fileLineWhereuserExists(userInformationForAccountInformationDisplay);

//Action Event for when user clicks on account information
      viewAccountInfo.setOnAction((ActionEvent event) -> {
         showAccountInformation(accountToPull);
      });

      Scene scene = new Scene(accountGrid, 300, 300);
      this.setScene(scene);
      this.setTitle("Account");
      this.show();

   }

 //Method is used to pull the login session information by accessing last instance of Session from
   //the arraylist of sessions  
   //Method passes back a string which includes [username,int(line in the fle where user is stored)]
   public String whichUserIsLoggedOn() {
      String userString;
      SessionInformation sessioninfo = new SessionInformation();
      ArrayList<Session> sessioninformation = sessioninfo.getList();
      if(sessioninformation.isEmpty()) {
      userString = sessioninformation.get(sessioninformation.size()).toString();
      System.out.print(userString);
      } else {
      userString = sessioninformation.get(sessioninformation.size() -1).toString();
      System.out.print(userString);
      }
      return userString;
   }

   //Method takes the string of information about the current user from the previous method
   //Using this information it returns the second part of the return statement which is the int 
   //value of the line in which the user is stored within the text file
   public int fileLineWhereuserExists(String userInformationForAccountInformationDisplay) {
      Scanner input = new Scanner("C:\\Users\\shann\\Documents\\user.txt");
      String number;
      int userLineNumber = 0;
      while (input.hasNextLine()) {
         String line = input.nextLine();
         number = line.split(",")[1];
         userLineNumber = Integer.parseInt(number);
      }
      return userLineNumber;
   }

   //Method to show account information
   //Method accepts the line number inwhich the user information is stored then loops through the text file until it gets to that line
   // On that line, the user information is stored and then can be pulled and displayed via the method below
   public String showAccountInformation(int accountToPull) {
      try {
         userFileReadIn = new BufferedReader(new FileReader("C:\\Users\\shann\\Documents\\user.txt"));
         read = userFileReadIn.readLine();
         int currentLineNumber = 0;
         while (read != null) {
            currentLineNumber++;
            if (currentLineNumber == accountToPull) {
               String split = read.replace(",", "\n");
               userinfo.setText(split);
               userFileReadIn.close();
            } else {
               currentLineNumber++;
            }
         }
      } catch (IOException e) {
         userinfo.setText("Error Loading. Are you sure you filled out your user information?");
         System.out.println("There was a problem:" + e);
      }

      return userinfo.toString();

   }

   //Throws to a new instance of expenses stage 
   public void showExpensesStage() {
      ExpensesStage expensesStage = new ExpensesStage();
      expensesStage.show();
   }
}

// public int fileLineWhereuserExists(String userInformationForAccountInformationDisplay) {  
//      Scanner input = new Scanner("C:\\Users\\shann\\Documents\\user.txt");
//      int lineNumber = 1;
//      while (input.hasNextLine()) {
//         String line = input.nextLine();
//         if (line.equalsIgnoreCase(name)) {
//            return lineNumber;
//         } else {
//            lineNumber++;
//         }
//      }
//      return lineNumber;
//   }

