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


import model.User;
import model.SessionInformation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Session;

/*First Stage - login
 Users are prompted at the login stage to either login or create new userif they have not signed up
 After creating a new user they will be prompted back to the login page to login */
public class LoginStage extends Stage {

//For Testing Purposes: 
   ArrayList<User> list = new ArrayList<>();
   ArrayList<Session> sessioninformation;

   Button submitButton = new Button("Login");
   TextField username;
   TextField password;
   GridPane loginpane = new GridPane();
   Button createUser = new Button("Create New User");
   String name = null;

   LoginStage() {
// this takes the user created on this page and returns it to the arraylist which is compared to the input in the login and this actually will return the account stage 

      loginpane.setPadding(new Insets(10));
      loginpane.setVgap(5);
      loginpane.setHgap(5);

      Label lblLogin = new Label("User:");
      GridPane.setHalignment(lblLogin, HPos.RIGHT);
      loginpane.add(lblLogin, 0, 0); // column 0, row 0

      Label lblPassword = new Label("Password:");
      loginpane.add(lblPassword, 0, 1); // column 0, row 1

      username = new TextField();
      loginpane.add(username, 1, 0, 2, 1); // column 1, row 0, colSpan 2, rowSpan 1 
      password = new TextField();
      loginpane.add(password, 1, 1, 2, 1); // column 1, row 1, colSpan 2, rowSpan 1

      submitButton.setOnAction((ActionEvent t) -> loggingIn());
      loginpane.add(submitButton, 1, 2); // column 1, row 2

      Button btnClear = new Button("Clear");
      GridPane.setHalignment(btnClear, HPos.RIGHT);
      loginpane.add(btnClear, 2, 2); // column 2, row 2

      GridPane.setHalignment(createUser, HPos.LEFT);
      createUser.setOnAction((ActionEvent t) -> creatingUser());
      loginpane.add(createUser, 1, 3); // column 1, row 3

      this.setScene(new Scene(loginpane, 300, 300));
      this.show();
   }

//Method runs the login - checks to see if user exists in the arraylist and continues to login if they do, or they are told to create an account
   private void loggingIn() throws InputMismatchException, IllegalArgumentException {
      name = username.getText();
      

//throw to method to see if user already exists in file
      userExists(name);
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

//If data fields are filled out properly, check to see if the user exists 
      } else if (userExists(name)) {

         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error");
         alert.setHeaderText("No account registered!");
         alert.setContentText("Please create an account!");
         alert.showAndWait();

      } else if (!userExists(name)) {
         
         //This will create a session for the user, using username and the linenumber from the textfile that the user is stored on
         Session session = new Session(); 
         session.setUsername(name);
         session.setLineNumber(fileLineWhereuserExists(name));
         SessionInformation sessioninfo = new SessionInformation();
         // The session is then stored on an arraylist
         sessioninformation = sessioninfo.getList();
         sessioninformation.add(session);
         System.out.print(sessioninformation.toString());
    
         AccountStage accountstage = new AccountStage();
         accountstage.show();
      }
   }

//Method to see if user already Exists in the text file where users are stored
   public boolean userExists(String name) {
      boolean exists = false;
      Scanner input = new Scanner("C:\\Users\\shann\\Documents\\user.txt");

      int lineNumber = 0;
      while (input.hasNextLine()) {
         String line = input.nextLine();
         if (line.equalsIgnoreCase(name)) {
            exists = true;
         } else {
            lineNumber++;
            exists = false;
         }
      }
      return exists;
   }

//Method returns the line number in the text file that users are saved on. This will be used later to display user information 
    public int fileLineWhereuserExists(String name) {  
      Scanner input = new Scanner("C:\\Users\\shann\\Documents\\user.txt");
      int lineNumber = 0;
      while (input.hasNextLine()) {
         String line = input.nextLine();
         if (line.equalsIgnoreCase(name)) {
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

}