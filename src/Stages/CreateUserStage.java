/* @authors Shannon McGuff */
package Stages;

//Create User Stage adds a user to the arraylist of users 
//Can throw InputMismatchExceptions and IllegalArgumentExceptions 
// Users can enter their name, approximate income, the goal amount to save and create a password 
//Their name will be checked against the arrayList to see if they already exist as a user 
//If the user already exists they will be told to go back to the login page 
//If they do not exist in the system, they will be added then reverted back to the login page
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//import model.UserFile;

public class CreateUserStage extends Stage {

   //Variables declared 
   GridPane root = new GridPane();
   Button addUser = new Button("Add User");
   TextField nameText = new TextField();
   TextField incomeAmount = new TextField();
   TextField goalAmount = new TextField();
   TextField userpassword = new TextField();
   ArrayList<User> list = new ArrayList();
   BufferedReader userFileReadIn;
   String read;

   CreateUserStage() {
      root.setPadding(new Insets(10));
      root.setVgap(10);

      Label userNameLabel = new Label("Name (This will be used as your login): ");
      Label incomeLabel = new Label("What is your approximate annual income? ");
      Label goalamountlabel = new Label("How much do you want to save? ");
      Label passwordLabel = new Label("Create a password: ");

      root.add(userNameLabel, 0, 0);
      root.add(nameText, 1, 0);

      root.add(incomeLabel, 0, 2);
      root.add(incomeAmount, 1, 2);

      root.add(goalamountlabel, 0, 3);
      root.add(goalAmount, 1, 3);

      root.add(passwordLabel, 0, 4);
      root.add(userpassword, 1, 4);

      root.add(addUser, 0, 5);

      addUser.setOnAction((ActionEvent t) -> submitUserInformation());

      Scene scene = new Scene(root, 400, 350);

      this.setTitle("Cash Track");
      this.setScene(scene);
      this.show();

   }

   //Method will check user submition for exceptions, against the arraylist to see if they exist in the system 
   private void submitUserInformation() throws InputMismatchException, IllegalArgumentException {

      //Check to ensure all fields have an input
      if (nameText.getText().trim().length() == 0
        && incomeAmount.getText().trim().length() == 0
        && goalAmount.getText().trim().length() == 0
        && userpassword.getText().trim().length() == 0) { 

         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error");
         alert.setHeaderText("Please fix the error");
         alert.setContentText("All fields are required!");
         alert.showAndWait();

         // Create a list of conditions to check datafields if user inputs values
      } else if (incomeAmount.getText().matches("^[a-zA-Z]*$")) {
         
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Entry");
            alert.setContentText("Income amount must contain only numbers!");
            alert.showAndWait();
            
     //Check to make sure the amount to save is is numbers
     } else if (goalAmount.getText().matches("^[a-zA-Z]*$")) {
        
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Entry");
            alert.setContentText("Goal amount must contain only numbers!");
            alert.showAndWait();

 //If all above conditions passed, user's inputs will be added 
         } else {
        
            User user = new User((nameText.getText()), (Double.valueOf(incomeAmount.getText())),
              (Double.valueOf(goalAmount.getText())), (userpassword.getText()));
            list.add(user);
            
            //Write User input to local file 
            File file = new File("C:\\Users\\shann\\Documents\\user.txt");
            
            try {
               try (FileWriter filewriter = new FileWriter(file, true)) {
                  filewriter.flush();
                  filewriter.write("\n");
                  filewriter.write(user.getid());
                  filewriter.write(",");
                  filewriter.write(user.name);
                  filewriter.write(",");
                  String income = Double.toString(user.getIncome());
                  filewriter.write(income);
                  filewriter.write(",");
                  String save = Double.toString(user.getAmountToSave());
                  filewriter.write(save);
                  filewriter.write(",");
                  filewriter.write(user.password);
                  System.out.print(list);
                  String path = file.getAbsolutePath();
                  System.out.print(path);
               } 

 //Throw to login in stage so user can sign in 
               LoginStage login = new LoginStage();
               login.show();

            } catch (IOException ex) {
               System.out.print(ex);
            }

         }
   }
//method returns a boolean value if the user exists or does not exist in the system  
   public boolean userDoesNotExist(TextField Username) {
      boolean exists = false;
      Scanner input = new Scanner("C:\\Users\\shann\\Documents\\user.txt");
      int lineNumber = 0;
      while (input.hasNextLine()) {
         String line = input.nextLine();
         if (line.contains(Username.toString())) {
            exists = true;
         } else {
            lineNumber++;
         }

      }
      return exists;
   }

}
