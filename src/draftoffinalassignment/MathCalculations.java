
package draftoffinalassignment;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author shann
 */

   
public class MathCalculations extends Stage{

    public void start(Stage primaryStage) throws Exception {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date of transaction");
        yAxis.setLabel("Amount spent");
        
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Spending graph");
        
        XYChart.Series series = new XYChart.Series();
        for(int i = 0; i < ExpensesArrayList.size(); i++){
            String axisX = ExpensesArrayList.get(i).getTimeOfPurchase();
            String axisY = ExpensesArrayList.get(i).getAddedExpenses();
            
            series.getData().add(new XYChart.Data(axisX, axisY));
        }
        Scene scene = new Scene(lineChart, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Expenses Chart");
        primaryStage.show();
    }
}