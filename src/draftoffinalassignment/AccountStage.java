package draftoffinalassignment;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 *
 * @author shann
 */
public class AccountStage extends Stage {

   Label accountStageLabel = new Label("Account");

   GridPane accountGrid = new GridPane();

   Text accountOutput = new Text();

   Button viewExpenses = new Button("View expenses");

   Button viewTransactions = new Button("View transactions");

   Button viewIncome = new Button("View income");

   Button viewGoals = new Button("View goals");

   AccountStage() {
      accountOutput.setText("Account Information:");
      
      accountGrid.setPadding(new Insets(10));

      accountGrid.add(accountStageLabel, 0, 0);

      accountGrid.add(accountOutput, 1, 0);

      accountGrid.add(viewExpenses, 0, 1);

      accountGrid.add(viewTransactions, 1, 1);

      accountGrid.add(viewIncome, 0, 2);

      accountGrid.add(viewGoals, 1, 2);

      Scene scene = new Scene(accountGrid, 300, 300);

      this.setScene(scene);

      this.show();

   }

}
