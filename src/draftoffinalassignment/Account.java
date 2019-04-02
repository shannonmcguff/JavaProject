
package draftoffinalassignment;

/**
 *
 * @author shann
 */
public class Account extends User {
   private double balance; // should balanace be a class? 
   private Expenses expenses; 
   private Income income; 

   public Account(double balance, Expenses expenses, Income income, 
     String name, Goal goal, String password) {
      super(name, goal, password);
      this.balance = balance;
      this.expenses = expenses;
      this.income = income;
   }

   
   public String toString(Object object) {
      return "Account balance: " + balance + "\nExpenses: " + 
        expenses + "\nIncome: " + income;  
   }


   public double getBalance() {
      return balance;
   }

   public void setBalance(double balance) {
      this.balance = balance;
   }

   public Expenses getExpenses() {
      return expenses;
   }

   public void setExpenses(Expenses expenses) {
      this.expenses = expenses;
   }

   public Income getIncome() {
      return income;
   }

   public void setIncome(Income income) {
      this.income = income;
   }

}
 