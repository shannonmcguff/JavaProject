package draftoffinalassignment;

import java.util.ArrayList;
import javafx.application.Application;
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

/**
 *
 * @author shann
 */
public class CreateUserStage extends Stage {
   GridPane root = new GridPane();
   Button addUser = new Button("Add User");
   TextField nameText = new TextField();
   TextField incomeAmount = new TextField();
   TextField goalAmount = new TextField();
   TextField userpassword = new TextField();
   User user;

   CreateUserStage() {
      root.setPadding(new Insets(10));
      root.setVgap(10);

 Label userNameLabel = new Label("Name (This will be used as your login): ");
      Label incomeLabel = new Label("What is your approximate annual income? ");
      Label goalamountlabel = new Label("How much do you want to save? ");
      Label passwordLabel = new Label ("Create a password: ");

      root.add(userNameLabel, 0, 0);
      root.add(nameText, 1, 0);
      
      root.add(incomeLabel, 0,2);
      root.add(incomeAmount, 1, 2);

      root.add(goalamountlabel, 0, 3);
      root.add(goalAmount, 1, 3);
      
      root.add(passwordLabel, 0,4);
      root.add(userpassword, 1,4);

//      GridPane.setHalignment(addUser, HPos.RIGHT);
//      root.add(addUser, 1, 3);
      addUser.setOnAction((ActionEvent t) -> submitUserInformation());
      root.add(addUser, 0, 4); // column 1, row 2

      Scene scene = new Scene(root, 400, 350);
    
    
      
     this.user = new User(nameText.getText(), Double.parseDouble(incomeAmount.getText()), Double.parseDouble(goalAmount.getText()), userpassword.getText());

      this.setTitle("Cash Track");
      this.setScene(scene);
      this.show();


   }
   
   public ArrayList getUserList(ArrayList userList) {
      return userList;
   }

   private void submitUserInformation() {

      if (nameText.getText().trim().length() == 0
        && incomeAmount.getText().trim().length()==0
        && goalAmount.getText().trim().length() == 0 &&
        userpassword.getText().trim().length() == 0) {

         Alert alert = new Alert(Alert.AlertType.ERROR);

         alert.setTitle("Error");
         alert.setHeaderText("Please fix the error");
         alert.setContentText("All fields are required!");

         alert.showAndWait();

      } else {
         String userName = nameText.getText();
         
         double income = Double.parseDouble(incomeAmount.toString());
//
         double goal = Double.parseDouble(goalAmount.toString());
         
         String passwordToStore = userpassword.getText();

         user = new User(userName, income, goal, passwordToStore);
         
         ArrayList <User> userList = user.getUserList();

         userList.add(user);
         
         Login login = new Login();
         
      }
   }

}
