/* 
 /* @authors Shannon McGuff
 */
package model;

import java.util.ArrayList;
import java.util.UUID;
import javafx.scene.control.ListView;

/**
 *
 * @author shann
 */
public class User {

   //Two Lists: ListView and ArrayList which hold values of expenses
//    ListView<String> expenseTypes = new ListView<>();
   ArrayList<String> expenseArray = new ArrayList<>();

   public String name;
   public double income;  // user prompted for income when they sign up. need an edit user account info to be able to change income wit setIncome()
   public double amountToSave;
   public String password;
   //create id for user 
   public String expenseArrayLocation;

   public User(String name, double income, double amountToSave, String password) {
      this.name = name;
      this.income = income;
      this.amountToSave = amountToSave;
      this.password = password;

   }


//Override Object to String method 
   @Override
   public String toString() {
      return "\nUser name: " + name + "\nUser Savings Goal: " + amountToSave 
        + "\nUser Income: " + income + "\nUser Password: " + "\nUser Expenses: " + expenseArrayLocation; 
   }
  
   //income getter/setter
   public double getIncome() {
      return income;
   }

   public void setIncome(double income) {
      this.income = income;
   }

      //weekly income getter/setter 
   public double getWeeklyIncome() {
      return income;
   }

   public void setWeeklyincome(double weeklyIncome) {
      this.income = weeklyIncome;
   }

      //amount to save (goal) getter/setter
   public double getAmountToSave() {
      return amountToSave;
   }

   public void setAmountToSave(double amountToSave) {
      this.amountToSave = amountToSave;
   }

   
      //password getter/setter
   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }


   // name getter/setter
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   } 
   
}
