package draftoffinalassignment;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author shann
 */
public class DraftOfFinalAssignment extends Application {

   //First Stage - login

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
