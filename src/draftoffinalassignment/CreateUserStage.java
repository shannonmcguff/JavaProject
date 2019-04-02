package draftoffinalassignment;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 *
 * @author shann
 */
public class CreateUserStage extends Stage {

   Button addUser = new Button("Add User");
   GridPane root = new GridPane();
   TextField nameText = new TextField();
   TextField goalText = new TextField();
   TextField goalAmount = new TextField();
   TextField userpassword = new TextField();
   User user;

   CreateUserStage() {
      root.setPadding(new Insets(10));
      root.setVgap(10);

//        Button btnIncome = new Button("Add income");
      Label userNameLabel = new Label("User Name: ");

      Label goalLabel = new Label("What is your goal? ");

      Label goalamountlabel = new Label("How much do you want to save? ");
      
      Label passwordLabel = new Label ("Create a password: ");

      root.add(userNameLabel, 0, 0);
      root.add(nameText, 1, 0);

      root.add(goalLabel, 0, 1);
      root.add(goalText, 1, 1);

      root.add(goalamountlabel, 0, 2);
      root.add(goalAmount, 1, 2);
      
      root.add(passwordLabel, 0,3);
      root.add(userpassword, 1,3);

//      GridPane.setHalignment(addUser, HPos.RIGHT);
//      root.add(addUser, 1, 3);
      addUser.setOnAction((ActionEvent t) -> submitUserInformation());
      root.add(addUser, 0, 4); // column 1, row 2
      Login login = new Login();
      

      Scene scene = new Scene(root, 400, 350);

      this.setTitle("Cash Track");
      this.setScene(scene);
      this.show();
   }
   
  
   
   public ArrayList getUserList(ArrayList userList) {
      return userList;
   }

   private void submitUserInformation() {

      if (nameText.getText().trim().length() == 0
        && goalText.getText().trim().length() == 0 && 
        goalAmount.getText().trim().length() == 0 &&
        userpassword.getText().trim().length() == 0) {

         Alert alert = new Alert(Alert.AlertType.ERROR);

         alert.setTitle("Error");
         alert.setHeaderText("Please fix the error");
         alert.setContentText("All fields are required!");

         alert.showAndWait();

      } else {
         String userName = nameText.getText();

         String stringGoal = goalText.getText();

         double goalTotal = Double.valueOf(goalAmount.getText());

         Goal userGoal = new Goal(stringGoal, goalTotal);

         user = new User(userName, userGoal, userpassword.toString());
         
         ArrayList <User> userList = user.getUserList();

         userList.add(user);
         
         
         
      }
   }
}
