/* This class will hold mock session data for user logins - instead of the cookies held in an HTTP login*/

package model;

public class Session {
   String username;
   int lineNumber; 

   @Override
  public String toString () {
     return username + "," + lineNumber; 
  }


   
   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public int getLineNumber() {
      return lineNumber;
   }

   public void setLineNumber(int lineNumber) {
      this.lineNumber = lineNumber;
   }
   
   
}