
package draftoffinalassignment;

/**
 *
 * @author shann
 */
public class Goal {
   private String goal;
   private double amountToSave; 

   public Goal(String goal, double amountToSave) {
      this.goal = goal;
      this.amountToSave = amountToSave;
   }

   public String getGoal() {
      return goal;
   }

   public void setGoal(String goal) {
      this.goal = goal;
   }

   public double getAmountToSave() {
      return amountToSave;
   }

   public void setAmountToSave(double amountToSave) {
      this.amountToSave = amountToSave;
   }
   
}
