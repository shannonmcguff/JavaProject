/**
 * 
 * Project Name: CASH TRACK 
 * This project is a modified banking application. Users can create an account,
 * enter their expenses, and see their expenses over a period of time.
 * The idea is that users can input their own expenses to be able to 
 * track and see how their spending habits look.
 * 
 * The program currently uses several classes: 
 * User --> Shannon
 * Expenses --> Artur
 * CSV Parser --> Artur 
 * There are several stages: 
 * Account stage - overview of user account --> Shannon
 * CashTrackMain - which is the main stage which runs to the Login page --> Shannon
 * LoginStage - which allows users to login --> Shannon
 * CreateUserStage - which allows users to create an account --> Shannon
 * ExpensesStage - which allows users to add their expenses --> Artur
 * MathCalculationsStage - which shows a graphical representation of a user's expenses--> Artur
 
 * 
 * Declared Problems: 
 * 1) Currently we are having some difficulties creating the User 
 * Array List while trying to add in both the User and Expenses Objects.
 * We are trying to figure out what is the best way to do this: Either using PrintWriter and 
 * Writing to File on the local laptop of the user; Using an ArrayList<Objects> which holds 
 * both the User arraylist and expenses arraylist; create an arraylist which can hold the 
 * User input and Expenses as an object; create the items on a database
 * 2) Need to add try/catch blocks to catch some additional errors 
 * 3) Until we can add in the expenses objects, the MathCalculations class will not run 
 * 4) Verify User input 
 * 
 *
 * @authors Shannon McGuff/Artur Hrytsenko 2019-04-12 Assignment 3
 * - Object Oriented Programming - Java 2
 */

package Stages;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;


public class CashTrackMain extends Application {

/*First Stage - login   
   This is the main stage which brings up the login stage or the ability to sign up  
   */
   @Override
   public void start(Stage primaryStage) throws Exception {
      LoginStage login = new LoginStage();
   }

   public static void main(String[] args) {
      launch(args);
   }
   
   
}
