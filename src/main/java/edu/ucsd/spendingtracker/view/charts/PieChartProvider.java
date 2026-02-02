package edu.ucsd.spendingtracker.view.charts;

import java.util.Map;
import edu.ucsd.spendingtracker.model.Category;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


public class PieChartProvider  implements IChartProvider{
    @Override
    public Node createChart(Map<Category, Double> data){
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();

        for(Map.Entry<Category, Double> entry: data.entrySet()){
            Category  category = entry.getKey();
            double total;
            if(entry.getValue() == null){
                total = 0.0;
            }
            else{
                total = entry.getValue();
            }
            pieData.add(new PieChart.Data(category.toString(), total));

        }
        PieChart chart = new PieChart(pieData);

        for(PieChart.Data slice: chart.getData()){
            String color = Category.valueOf(slice.getName()).color;
            Node node = slice.getNode();
            if(node != null){
                node.setStyle("-fx-pie-color: " + color + ";");
            }
        }
        chart.setLegendVisible(false);

        return chart; 
    }

    @Override
    public String getDisplayName(){
        return "Pie Chart";
    }
    
}
