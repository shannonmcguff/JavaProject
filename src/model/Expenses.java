package model;


public class Expenses {
   private String expenseCategory;
   private String timeOfPurchase;
   private double expenseAmount;

   public Expenses(double expenseAmount, String expenseCategory, String timeOfPurchase) {
      this.expenseCategory = expenseCategory;
      this.expenseAmount = expenseAmount;
      this.timeOfPurchase = timeOfPurchase;
   }

    public double getExpense() {
        return expenseAmount;
    }
    
    public void setExpense(double expenseAmount){
        this.expenseAmount = expenseAmount;
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
   
   @Override
   public String toString() {
      return expenseCategory + "," + expenseAmount + "," + timeOfPurchase;  
   }
  
}