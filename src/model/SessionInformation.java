/*
 *Session Information creates an arrayList of sessions
   it takes in first the username then the line number on the file where the user information is store. 
   This information is separated by a comma. 
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author shann
 */
public class SessionInformation {
   ArrayList <Session> sessioninformation = new ArrayList();

 
   public ArrayList<Session> getList() {
      return sessioninformation;
   }
   
   @Override
   public String toString() {
     return sessioninformation.toString();
   }
}
