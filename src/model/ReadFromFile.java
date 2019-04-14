//package model;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import javafx.scene.text.Text;
//
//
///**
// *
// * @author shann
// */
//public class ReadFromFile {
//
//   ArrayList<User> userList = new ArrayList<>();
//   BufferedReader userFileReadIn;
//   String read;
//   Text userinfo = new Text();
//
//   //load file and add user information to output  - change to User 
//   //File path will have to change depending what computer is in use
//
//  public void loadUser() {
//      try {
//         userFileReadIn = new BufferedReader(new FileReader("C:\\Users\\shann\\Documents\\user.txt"));
//         read = userFileReadIn.readLine();
//         String split = read.replace(",", "\n");
//         userinfo.setText(split);
//         userFileReadIn.close();
//      } catch (IOException e) {
//         userinfo.setText("Error Loading. Are you sure you filled out your user information?");
//         System.out.println("There was a problem:" + e);
//      }
//   }
//}
//
//
//      
