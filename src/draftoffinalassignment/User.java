/* 
// 2019-04-05 
Got rid of goal class --> user now takes a goal amount 
getting rid of income, transaction, expenses classes 
income--> gone, user inputs now as variable
Transaction --> gone
Expenses --> class now takes the expenses, transactions because they are the same 
Math class will now determine the users balance 
(how much money they have remaining based on their income and their expenses) 

need to organize packages 
   - package for stages, package for classes, package (model) for main 

Added in the call to stages in the AccountStage class
- these clases still need 


For date in the expenses stage: 
use regex (tell user to enter date YYYY/MM/DD, and use the 
slashes to separate the information so that the first 4
digits are always year, second 2 are always month, and last 2 are always day



will need to update the transactions accepted by the user 
 */
package draftoffinalassignment;

import java.util.ArrayList;

/**
 *
 * @author shann
 */
public class User {
    private String name; 
    private double income;  // user prompted for income when they sign up. need an edit user account info to be able to change income wit setIncome()
    private double amountToSave;
    private String password; 
    private Expenses expense;

   public double getIncome() {
      return income;
   }

   public void setIncome(double income) {
      this.income = income;
   }

   public Expenses getExpense() {
      return expense;
   }

   public void setExpense(Expenses expense) {
      this.expense = expense;
   }

    
   public double getWeeklyIncome() {
      return income;
   }

   public void setincome(double weeklyIncome) {
      this.income = weeklyIncome;
   }

   public double getAmountToSave() {
      return amountToSave;
   }

   public void setAmountToSave(double amountToSave) {
      this.amountToSave = amountToSave;
   }

    
    public User(String name, double income, double amountToSave, String password) {
      this.name = name;
      this.income = income; 
      this.amountToSave = amountToSave; 
      this.password = password;

    }
   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public String toString() {
      return "User name: " + name + "User goal: " + amountToSave; 
   }

   public double getGoal() {
      return amountToSave;
   }

   public void setGoal(double amountToSave) {
     this.amountToSave = amountToSave;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   } 
   
}
