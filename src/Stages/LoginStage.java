/**
 *
 * Project Name: CASH TRACK This project is a modified banking application.
 * Users can create an account, enter their expenses, and see their expenses
 * over a period of time. The idea is that users can input their own expenses to
 * be able to track and see how their spending habits look.
 *
 *
 * @authors Shannon McGuff
 */
package Stages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Session;
import model.SessionInformationSingleton;
import model.User;

/*First Stage - login
 Users are prompted at the login stage to either login or create new userif they have not signed up
 After creating a new user they will be prompted back to the login page to login */
public class LoginStage extends Stage {

   ArrayList<Session> sessioninformation = new ArrayList();

   Button submitButton = new Button("Login");
   TextField username;
   TextField password;
   GridPane loginpane = new GridPane();
   Button createUser = new Button("Create New User");

   String name = null;
   String userpassword = null;

   LoginStage() {
// this takes the user created on this page and returns it to the arraylist which is compared to the input in the login and this actually will return the account stage 

      loginpane.setPadding(new Insets(10));
      loginpane.setVgap(5);
      loginpane.setHgap(5);

      Label lblLogin = new Label("User:");
      lblLogin.setStyle("-fx-text-fill: white;");
      GridPane.setHalignment(lblLogin, HPos.RIGHT);
      loginpane.add(lblLogin, 0, 0); // column 0, row 0

      Label lblPassword = new Label("Password:");
      lblPassword.setStyle("-fx-text-fill: white;");
      loginpane.add(lblPassword, 0, 1); // column 0, row 1

      username = new TextField();
      loginpane.add(username, 1, 0, 2, 1); // column 1, row 0, colSpan 2, rowSpan 1 
      password = new TextField();
      loginpane.add(password, 1, 1, 2, 1); // column 1, row 1, colSpan 2, rowSpan 1

      submitButton.setOnAction((ActionEvent t) -> {
         try {
            loggingIn();
         } catch (InputMismatchException ex) {
            System.out.println("ERROR. Login Process.");
         } catch (IllegalArgumentException ex) {
            Logger.getLogger(LoginStage.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
            Logger.getLogger(LoginStage.class.getName()).log(Level.SEVERE, null, ex);
         }
      });
      loginpane.add(submitButton, 1, 2); // column 1, row 2
      submitButton.setStyle("-fx-border-color:white; -fx-background-color:lightgreen;");

      Button btnClear = new Button("Clear");
      GridPane.setHalignment(btnClear, HPos.RIGHT);
      loginpane.add(btnClear, 2, 2); // column 2, row 2
      btnClear.setStyle("-fx-border-color:white; -fx-background-color:lightgreen;");

      GridPane.setHalignment(createUser, HPos.LEFT);
      createUser.setOnAction((ActionEvent t) -> creatingUser());
      loginpane.add(createUser, 1, 3); // column 1, row 3
      createUser.setStyle("-fx-border-color:white; -fx-background-color:lightgreen;");

      this.setScene(new Scene(loginpane, 300, 300));
      this.show();
      this.setTitle("Cash Track!");
      loginpane.setStyle("-fx-background-color:green");

   }

//Method runs the login - checks to see if user exists in the arraylist and continues to login if they do, or they are told to create an account
   private void loggingIn() throws InputMismatchException, IllegalArgumentException, FileNotFoundException, IOException {
      name = username.getText();
      userpassword = password.getText();

//throw to method to see if user already exists in file
      userDoesNotExist(name);
//Throw to method to find out which line on the file the user exists on
      fileLineWhereuserExists(name);

//ensure datafields are filled out
      if (username.getText().trim().length() == 0
        && password.getText().trim().length() == 0) {

         Alert alert = new Alert(Alert.AlertType.ERROR);

         alert.setTitle("Error");
         alert.setHeaderText("Please fix the error");
         alert.setContentText("Both field are required!");

         alert.showAndWait();

      } else if (username.getText().trim().length() != 0
        && password.getText().trim().length() != 0) {

//         //throw to method to see if user already exists in file
//         userDoesNotExist(name);
         //Throw to method to find out which line on the file the user exists on
         fileLineWhereuserExists(name);
//         //check user password  
//         passwordExists(userpassword);

//If data fields are filled out properly, check to see if the user exists 
         if (userDoesNotExist(name) == false) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No account registered!");
            alert.setContentText("Please create an account!");
            alert.showAndWait();

         } else if (passwordExists(userpassword) == false) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Sign-in Error");
            alert.setContentText("Wrong User or Password.");
            alert.showAndWait();

         } else if ((userDoesNotExist(name) == true) && (passwordExists(userpassword) == true)) {

            //This will create a session for the user, using username and the linenumber from the textfile that the user is stored on
            Session session = new Session();
            session.setUsername(name);
            //gets the line number where the information is stored on the text file
            session.setLineNumber(fileLineWhereuserExists(name));
            
            //NAME IS PASSED BEFORE THIS POINT BELOW ** 

//          The session is then stored in singleton from sessioninformation
//         SessionInformationSingleton.getInstance();
            SessionInformationSingleton.getInstance().getList();
            sessioninformation.add(session);
            System.out.print(sessioninformation.toString());
            SessionInformationSingleton.getInstance().setArrayList(sessioninformation);
            ArrayList<Session> sessionList = SessionInformationSingleton.getInstance().getList();
            
            //String created to accept the information from method whichUserIsLoggedIn
            //method returns " [String name, String lineNumber] "
            
            
          
            String userInformationForAccountInformationDisplay = whichUserIsLoggedOn(sessionList);
            
            //.split to get the name of the user only, build file with theirName.txt
            String nameOfUserForFileCreation = nameOfUserWhereuserExists(userInformationForAccountInformationDisplay);
            createFileWithUserName(nameOfUserForFileCreation);
            
//String  userInformationForAccountInformationDisplay is passed to method fileLineWhereuserExists to get the
            //line number on the text file where the user is stored, passed to next stage
            int accountToPull = lineOfUserWhereUserExists(userInformationForAccountInformationDisplay);
            
            

//            AccountStage accountstage = new AccountStage(sessioninformation);
            AccountStage accountstage = new AccountStage(accountToPull);
            accountstage.show();
         }
      }
   }

//Method to see if user already Exists in the text file where users are stored
   public boolean userDoesNotExist(String name) throws FileNotFoundException {
      File file;
      boolean exists = false;
      Scanner input = new Scanner(new File("C:\\Users\\shann\\Documents\\user.txt"));
      int lineNumber = 0;
      while (input.hasNextLine()) {
         String line = input.nextLine();
         if (line.contains(name)) {
            exists = true;
         } else {
            lineNumber++;
            exists = false;
         }
      }
      return exists;
   }

   public boolean passwordExists(String password) throws FileNotFoundException {
       File file;
      boolean exists = false;
      Scanner input = new Scanner(new File("C:\\Users\\shann\\Documents\\user.txt"));
      int lineNumber = 0;
      while (input.hasNextLine()) {
         String line = input.nextLine();
         if (line.contains(password)) {
            exists = true;
         } else {
            lineNumber++;
            exists = false;
         }
      }
      return exists;
   }

//Method returns the line number in the text file that users are saved on. This will be used later to display user information 
   public int fileLineWhereuserExists(String name) throws FileNotFoundException {
      Scanner input = new Scanner(new File("C:\\Users\\shann\\Documents\\user.txt"));
      int lineNumber = 1;
      while (input.hasNextLine()) {
         String line = input.nextLine();
         if (line.contains(name)) {
            return lineNumber;
         } else {
          lineNumber++;
         }
      }
      return lineNumber;
   }

//Throws to the stage which allows users to create a user - is called on by a button above
   private void creatingUser() {
      CreateUserStage createUserStage = new CreateUserStage();
      createUserStage.show();
   }

   
      //Method is used to pull the login session information by accessing last instance of Session from
   //the arraylist of sessions  
   //Method passes back a string which includes [username,int(line in the fle where user is stored)]

   public String whichUserIsLoggedOn(ArrayList sessionList) {
      String userString = "";
//      SessionInformationSingleton instance = sessioninformation.getInstance();
      
      for (int i = 0; i <= sessionList.size(); i++) {
         if (i == sessionList.size()) {
            userString = sessionList.get(i-1).toString();
            System.out.print(userString);
         } else {
//            i++;
         }
      }
      return userString;
   }
  
   //NULL
      public String nameOfUserWhereuserExists(String userInformationForAccountInformationDisplay) throws FileNotFoundException, IOException {
      String name;
      String[] userInfoSplit = userInformationForAccountInformationDisplay.split(",");
      System.out.println(userInformationForAccountInformationDisplay);
      name = userInfoSplit[0];
      String newString = name.replace("[", "");
      System.out.println(newString + "banaan");
      return newString;
      
   }
      public int lineOfUserWhereUserExists(String userInformationForAccountInformationDisplay) throws FileNotFoundException, IOException {
      String lineNumber;
      String[] userInfoSplit = userInformationForAccountInformationDisplay.split(",");
      System.out.println(userInformationForAccountInformationDisplay);
      lineNumber = userInfoSplit[1];
      String newString = lineNumber.replace("]", "");
      int toReturn = Integer.parseInt(newString);
      
      return toReturn;
      
   }
   
   /**
    *
    * @param nameOfUserForFileCreation
    * @throws IOException
    */
   public void createFileWithUserName (String nameOfUserForFileCreation) throws IOException {
      System.out.println(nameOfUserForFileCreation);
      File file = new File ("C:\\Users\\shann\\Documents\\" + nameOfUserForFileCreation+ ".txt");

//      File file = new File(newFileName);
      FileWriter createFile = new FileWriter(file);
      if (file.exists()) {
      } else {
         createFile.append("");
      }
      createFile.close();

   }
}
//      public int fileLineNumber() throws FileNotFoundException, IOException {
//      int lines;
//      try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\shann\\Documents\\user.txt"))) {
//         lines = 0;
//         while (reader.readLine() != null) {
//            lines++;
//            System.out.print(lines);
//         }
//      }
//      return lines;
//   }


   //Method takes the string of information about the current user from the previous method
   //Using this information it returns the second part of the return statement which is the int 
   //value of the line in which the user is stored within the text file
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
// 
