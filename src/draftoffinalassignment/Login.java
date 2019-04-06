package draftoffinalassignment;

import java.util.ArrayList;
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
public class Login extends Stage {

   ArrayList<User> userList = new ArrayList<>();
   User newUser;

   Button submitButton = new Button("Login");
   TextField username;
   TextField password;
   GridPane loginpane = new GridPane();
   Button createUser = new Button("Create New User");
   User user;

   Login() {
      // this takes the user created on this page and returns it to the arraylist which is compared to the input in the login and this actually will return the account stage 
    
      userList.add(newUser);

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

   private void loggingIn() {
      username.getText();
      password.getText();

      if (username.getText().trim().length() == 0
        && password.getText().trim().length() == 0) {

         Alert alert = new Alert(Alert.AlertType.ERROR);

         alert.setTitle("Error");
         alert.setHeaderText("Please fix the error");
         alert.setContentText("Both field are required!");

         alert.showAndWait();

      } else if (newUser.getName().equals(username.getText()) &&
        (newUser.getPassword().equals(password.getText()))) {
         AccountStage accountstage = new AccountStage();
      }
   }

//   public boolean userExists(ArrayList userList, TextField Username) {
//      boolean exists = false;
//      userList = user.getUserList();
//
//      for (Object userList1 : userList) {
//         if (userList1.toString().equalsIgnoreCase(username.toString())
//           && userList1.toString().equalsIgnoreCase(password.toString())) {
//            
//         }
//         {
//            exists = true;
//         }
//      }
//      return exists;
//   }
//
//   
//
   private void creatingUser() {
      CreateUserStage createUserStage = new CreateUserStage();
   }

//   
//      } else if (userExists(user.getUserList(), username) == false) {
//         System.out.println("Please create an account!");
//         CreateUserStage createUserStage = new CreateUserStage();
//      } else if (userExists(user.getUserList(), username) == true){
//         AccountStage accountstage = new AccountStage();
//      }
}
