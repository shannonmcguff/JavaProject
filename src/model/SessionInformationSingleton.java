package model;

import java.util.ArrayList;
/**
 *
 * @author shann
 */
public class SessionInformationSingleton {
  
   private static SessionInformationSingleton sessioninformationuniq;

   public ArrayList <Session> sessioninformation = new ArrayList();
   
   private SessionInformationSingleton() {
      
   }
   
   public static SessionInformationSingleton getInstance() {
      if (sessioninformationuniq == null) {
            sessioninformationuniq = new SessionInformationSingleton();
      } else {
      return sessioninformationuniq;
   }
      return sessioninformationuniq;
  }
   
   
  public void setArrayList(ArrayList<Session> sessioninformationlist) {
       this.sessioninformation = sessioninformationlist;
    }
   public ArrayList<Session> getList() {
      return this.sessioninformation;
   }
   
   @Override
   public String toString() {
     return sessioninformationuniq.toString();
   }
}
