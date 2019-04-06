package draftoffinalassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CSVParser extends Application {

   @Override
   public void start(Stage primaryStage) throws Exception {

      FileChooser chooser = new FileChooser();
      File file = new File(".");

      chooser.setTitle("Find a CSV file");
      chooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("CSV files", "*.csv"));

      chooser.setInitialDirectory(file);

      File selectedFile = chooser.showOpenDialog(primaryStage);

      ArrayList<String> CSVArrayList = new ArrayList<>();
//        ArrayList<String> CSVExpensesList = new ArrayList<>();
//        ArrayList<String> CSVIncomeList = new ArrayList<>();

      if (selectedFile != null) {
         try {
            Scanner input = new Scanner(selectedFile);
            while (input.hasNextLine()) {
               String lineIn = input.nextLine();
               String[] csvArray = lineIn.trim().split(",");
               //copy array to permanent ArrayList
               for (int i = 0; i < csvArray.length; i++) {
                  if (csvArray[i] == null || csvArray[i] == " ") {
                  } else {
                     CSVArrayList.add(csvArray[i]);
                  }
               }
            }//end of loop
            removeBalanceTotal(CSVArrayList);
            formatDate(CSVArrayList);
            createTransactionObjects(CSVArrayList);

         } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
         }
      }
   }//end of start method

   private void removeBalanceTotal(ArrayList<String> CSVArrayList) {

        //trying to set every 5th element to ffff, then remove all elements of that value
      //since using .remove changes the array length as you iterate
      for (int i = 4; i < CSVArrayList.size() - 5; i += 5) {

         CSVArrayList.set(i, "ffff");
      }
      for (int i = 0; i < CSVArrayList.size(); i++) {
         if (CSVArrayList.get(i).contains("ffff")) {
            CSVArrayList.remove(i);
         }
      }
      for (int i = 0; i < CSVArrayList.size(); i++) {
         System.out.println(CSVArrayList.get(i));
      }
   }

   private void formatDate(ArrayList<String> CSVArrayList) {
      for (int i = 0; i < CSVArrayList.size(); i++) {
         if (CSVArrayList.get(i).contains("\"")) {
            String tempString = CSVArrayList.get(i);
            tempString = tempString.replace("\"", ":");
            CSVArrayList.add(i, tempString);
         }
      }
   }

   private void createTransactionObjects(ArrayList<String> CSVArrayList) {
      Boolean continueFlag = true;

      do {
         //check if each set set of data is income or expenditure
         for (int i = 0; i < 4; i++) {
            //expense
            if (CSVArrayList.get(3).contains("0")) {
               Transaction expense = new Expenses(0.0, Double.parseDouble(CSVArrayList.get(3)),
                 CSVArrayList.get(1), CSVArrayList.get(0));
            }//income 
            if (CSVArrayList.get(i).contains("0")) {
               //call relevant constructor
            }
         }
         //remove those 4 entries and re-enter loop for arrayList check
         CSVArrayList.remove(0 - 4);
         if (CSVArrayList.size() < 4) {
            continueFlag = false;
         }
      } while (continueFlag);
   }// end of method

}//end of class