package UsingTablesawWithCitiesDS;

import org.knowm.xchart.*;
import tech.tablesaw.api.Table;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class MainClass {

    public static void main(String[] args) throws IOException {
        //reading the data set
        Table cities = Table.read().csv("src/main/resources/data/cities.csv");
        //printing the head of the data
        System.out.println(cities.first(5).toString());

        //getting lat and lang as list of numeric
        List lat = cities.column("lat").asList();
        List lng = cities.column("lng").asList();
        //List id = cities.column("id").asList();
        List pop = cities.column("population").asList();
        //plotting these values
        XYChart chart = new XYChartBuilder().build();
        chart.addSeries("Lat And Lng",lng,lat);

        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        // new SwingWrapper<>(chart).displayChart();

        for (int i = 0; i < pop.size(); i++) {
            if (pop.get(i) == null )
                pop.set(i,0);
        }

        CategoryChart chart2 = new CategoryChartBuilder().width(800).height(600).title("Score Histogram").xAxisTitle("Mean").yAxisTitle("Count").build();
        Histogram hist = new Histogram(pop,(int)Math.sqrt(pop.size()),20000000,37977000);
        chart2.addSeries("Histooooooooooooo",hist.getxAxisData(),hist.getyAxisData());

        new SwingWrapper<>(chart2).displayChart();


    }


}
