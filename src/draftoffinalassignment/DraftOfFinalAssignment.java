package draftoffinalassignment;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author shann
 */
public class DraftOfFinalAssignment extends Application {

   //First Stage - login
   //Users are prompted at the login stage to either login or create new user
   //if they have not signed up
   //After creating a new user they will be prompted back to the login page to login

   @Override
   public void start(Stage primaryStage) throws Exception {
      Login login = new Login();
   }

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      launch(args);
   }
}
