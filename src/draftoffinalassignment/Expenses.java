package draftoffinalassignment;

import java.util.Comparator;

public class Expenses {
   private double totalExpenses;
   private double addedExpenses; 
   //delete should be a method 

   public Expenses(double totalExpenses, double addedExpenses, String expenseCategory, String timeOfPurchase) {
      this.totalExpenses = totalExpenses;
      this.expenseCategory = expenseCategory;
   }
   
   private String expenseCategory; 
   private String timeOfPurchase; 

   public double getTotalExpenses() {
      return totalExpenses;
   }

   public void setTotalExpenses(double totalExpenses) {
      this.totalExpenses = totalExpenses;
   }

   public double getAddedExpenses() {
      return addedExpenses;
   }

   public void setAddedExpenses(double addedExpenses) {
      this.addedExpenses = addedExpenses;
   }

   public String getExpenseCategory() {
      return expenseCategory;
   }

   public void setExpenseCategory(String expenseCategory) {
      this.expenseCategory = expenseCategory;
   }

   public String getTimeOfPurchase() {
      return timeOfPurchase;
   }

   public void setTimeOfPurchase(String timeOfPurchase) {
      this.timeOfPurchase = timeOfPurchase;
   }
  
}