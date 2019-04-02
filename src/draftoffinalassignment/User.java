/* 
added a to string method 
 */
package draftoffinalassignment;

import java.util.ArrayList;

/**
 *
 * @author shann
 */
public class User {
   private String name; 
    private Goal goal; 
    private String password; 
    private ArrayList<User> userList; 
    
    public User(String name, Goal goal, String password) {
      this.name = name;
      this.goal = goal; 
      this.password = password;
      userList = new ArrayList<>();
   }
    
   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public String toString() {
      return "User name: " + name + "User goal: " + goal; 
   }

   public Goal getGoal() {
      return goal;
   }

   public void setGoal(Goal goal) {
      this.goal = goal;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   } 
   
   public ArrayList <User> getUserList() {
      return userList; 
   }
}
