/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draftoffinalassignment;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author shann
 */
public class ExampleStage extends Stage {
   Label x = new Label("Second stage");
   VBox y = new VBox();

ExampleStage(){
    y.getChildren().add(x);
    this.setScene(new Scene(y, 300, 300));
    this.show();
   }    
}

