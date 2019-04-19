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

import model.SessionInformationSingleton;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Session;
import model.User;

public final class AccountStage extends Stage {

   public ArrayList<User> list = new ArrayList();
   public BufferedReader userFileReadIn;
   public String read;
//   String userInformationForAccountInformationDisplay;
   //CHANGED THE () TO ACCOUNT TO PULL FROM SESSIONINFORMATIONSINGLETON -- 4:48
   AccountStage(int accountToPull) throws FileNotFoundException, IOException {

      Label welcome = new Label("Welcome");
      welcome.setStyle("-fx-text-fill: white; -fx-font: 24 arial;");

      //Main gridpane where buttons are added
      GridPane accountGrid = new GridPane();

      //Clicking on this button allows users to see their expenses
      Button viewExpenses = new Button("Add expenses");
      viewExpenses.setStyle("-fx-border-color:white;-fx-background-color: lightgreen; -fx-pref-width: 200px");

//Clicking takes the viewers to the math page
      Button viewExpenseHistory = new Button("View expense history");
      viewExpenseHistory.setStyle("-fx-border-color:white; -fx-background-color: lightgreen;  -fx-pref-width: 200px");

      //clicking allows the user to see account info 
      Button viewAccountInfo = new Button("View account information");
      viewAccountInfo.setStyle("-fx-border-color:white; -fx-background-color: lightgreen; -fx-pref-width: 200px");

      Button importCSV = new Button("import CSV file");
      importCSV.setStyle("-fx-border-color:white; -fx-background-color: lightgreen; -fx-pref-width: 200px");

      Button viewMathGraphs = new Button("View as graph");
      viewMathGraphs.setStyle("-fx-border-color:white; -fx-background-color: lightgreen; -fx-pref-width: 200px");

      accountGrid.setPadding(new Insets(20));

      accountGrid.add(welcome, 0, 0);

      accountGrid.add(viewExpenses, 0, 1);

      accountGrid.add(viewExpenseHistory, 0, 2);

      accountGrid.add(viewAccountInfo, 1, 2);

      accountGrid.add(importCSV, 0, 3);

      accountGrid.add(viewMathGraphs, 1, 3);

      viewExpenses.setOnAction(event -> {
         try {
            showExpensesStage();
         } catch (IOException ex) {
            System.out.println("Error Loading Page.");
         }
      });

      viewMathGraphs.setOnAction(event -> showMathCalculationsStage());

      //String created to accept the information from method whichUserIsLoggedIn
      String userInformationForAccountInformationDisplay = whichUserIsLoggedOn(sessioninformation);

//String  userInformationForAccountInformationDisplay is passed to method fileLineWhereuserExists to get the
      //line number on the text file where the user is stored 
      int accountToPull = fileLineWhereuserExists(userInformationForAccountInformationDisplay);

//Action Event for when user clicks on account information
      viewAccountInfo.setOnAction((ActionEvent event) -> {
         try {
            showAccountInfo(accountToPull,userInformationForAccountInformationDisplay);
         } catch (IOException ex) {
            Logger.getLogger(AccountStage.class.getName()).log(Level.SEVERE, null, ex);
         }
      });

      importCSV.setOnAction(event -> showCSVParserStage());
      
      String nameOfUserForFileCreation = nameOfUserWhereuserExists(userInformationForAccountInformationDisplay);
      createFileWithUserName(nameOfUserForFileCreation);

      Scene scene = new Scene(accountGrid, 500, 500);
      accountGrid.setStyle("-fx-border-color:white; -fx-background-color: green;");
      this.setScene(scene);
      this.setTitle("Account");
      this.show();

   }

   public void showMathCalculationsStage() {
      MathCalculations mathStage = new MathCalculations();
   }

   public void showCSVParserStage() {
      CSVParser parser = new CSVParser();
   }

//   //Method is used to pull the login session information by accessing last instance of Session from
//   //the arraylist of sessions  
//   //Method passes back a string which includes [username,int(line in the fle where user is stored)]
//
//   public String whichUserIsLoggedOn(SessionInformationSingleton sessioninformation) {
//      String userString = "";
//      SessionInformationSingleton instance = sessioninformation.getInstance();
//      instance.getList();
//      for (int i = 0; i < instance.getList().size(); i++) {
//         if (i == instance.getList().size()) {
//            userString = instance.getInstance().getList().get(i).toString();
//            System.out.print(userString);
//         } else {
//            i++;
//         }
//      }
//      return userString;
//   }
//
//   //Method takes the string of information about the current user from the previous method
//   //Using this information it returns the second part of the return statement which is the int 
//   //value of the line in which the user is stored within the text file
//   public int fileLineWhereuserExists(String userInformationForAccountInformationDisplay) throws FileNotFoundException, IOException {
//      int lines;
//      Scanner input = new Scanner(new File("C:\\Users\\shann\\Documents\\user.txt"));
//      lines = 0;
//      while (input.hasNext()) {
//         lines++;
//         if (input.toString().contains((userInformationForAccountInformationDisplay))) {
//            return lines;
//         }
//      }
//      return lines;
//   }

   //Method to show account information
   //Method accepts the line number inwhich the user information is stored then loops through the text file until it gets to that line
   // On that line, the user information is stored and then can be pulled and displayed via the method below
   public void showAccountInfo(int accountToPull, String userInformationForAccountInformationDisplay) throws IOException {
      System.out.println(accountToPull);
      System.out.println(userInformationForAccountInformationDisplay);
      Label userinfo = new Label();
      Label labels = new Label("Username: \nIncome:  \nGoal to save:  \nPassword:");
      userinfo.setStyle("-fx-text-fill: white; -fx-font: 24 arial;");
      labels.setStyle("-fx-text-fill: white; -fx-font: 24 arial;");

      try {
         userFileReadIn = new BufferedReader(new FileReader("C:\\Users\\shann\\Documents\\user.txt"));
         read = userFileReadIn.readLine();
         int currentLineNumber = 0;
         while (read != null) {
            currentLineNumber++;

            if (currentLineNumber != accountToPull) {
               currentLineNumber++;

            } else {
               String split = read.replace(",", "\n");
               userinfo.setText(split);
               userinfo.toString();
               userFileReadIn.close();
               break;
            }
         }
      } catch (IOException e) {
         userinfo.setText("Error Loading. Are you sure you filled out your user information?");
         System.out.println("There was a problem:" + e);
      }

        
      GridPane displayinfo = new GridPane();

      displayinfo.add(labels, 0, 0);
      displayinfo.add(userinfo, 1, 0);

      Scene scene = new Scene(displayinfo, 300,300);
      
      displayinfo.setStyle("-fx-border-color:white; -fx-background-color: green;");
      Stage stageAccountInfo = new Stage();
      stageAccountInfo.setScene(scene);
      stageAccountInfo.setTitle("Account Information");
      stageAccountInfo.show();
   }

   //Throws to a new instance of expenses stage 
   public void showExpensesStage() throws IOException {
      ExpensesStage expensesStage = new ExpensesStage();
      expensesStage.show();
   }

//   public String nameOfUserWhereuserExists(String userInformationForAccountInformationDisplay) throws FileNotFoundException, IOException {
//      String name;
//      String[] userInfoSplit = userInformationForAccountInformationDisplay.split(",");
//      System.out.println(userInformationForAccountInformationDisplay);
//      name = userInfoSplit[0];
//      String newString = name.replace("[", "");
//      System.out.println(newString + "banaan");
//      return newString;
//      
//   }
//   
//   public void createFileWithUserName (String nameOfUserForFileCreation) throws IOException {
//      System.out.println(nameOfUserForFileCreation);
//      File file = new File ("C:\\Users\\shann\\Documents\\" + nameOfUserForFileCreation+ ".txt");
//
////      File file = new File(newFileName);
//      FileWriter createFile = new FileWriter(file);
//      if (file.exists()) {
//      } else {
//         createFile.append("");
//      }
//
//   }
//}
