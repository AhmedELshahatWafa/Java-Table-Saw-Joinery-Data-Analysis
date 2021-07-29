package UsingTableSaw;


import org.knowm.xchart.*;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler;
import tech.tablesaw.api.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainClass {
    public static void main(String[] args) throws  IOException{
        // Dealing with titanic Data   Loadiing Data
        Table titanic = Table.read().csv("src/main/resources/data/titanic.csv");

        //
//        //using Structure
//        String structure = titanic.structure().toString();
//        //System.out.println(structure);
//        System.out.println();
//        System.out.println();
//        //using summary
//        String summary = titanic.summary().toString() ;
//        //System.out.println(summary);
//        System.out.println();
//        System.out.println();
//
//        // inserting column
//        //titanic.insertColumn(Providing a list of column values);

        // mapping text data to numeric values on the sex field female=1,male=0 and adding a column named gender

        StringColumn strSexColumn = titanic.stringColumn("sex") ;
        List<Number> lstSexNumerical = new ArrayList<>() ;
        for (String val:strSexColumn) {
            if (val.equals("male"))
                lstSexNumerical.add(0);
            else if (val.equals("female"))
                lstSexNumerical.add(1);
        }



        /////////////////////////////////////////// Inserting a column into the Data
        NumberColumn numMappedCol =null;
        numMappedCol = DoubleColumn.create("Gendre New Column",lstSexNumerical);
        titanic.insertColumn(4,numMappedCol);
        //printing the head of the data set
        System.out.println(titanic.first(5).toString());



        //////////////////////////////////////////// Printing summary data
        String summaryAboutData = titanic.summary().toString() ;
        System.out.println(summaryAboutData);
        //Another Data Set
        Table anotherTitanic = Table.read().csv("src/main/resources/data/titanic.csv");
        System.out.println(anotherTitanic.first(20));



        //////////////////////////////////////////// adding column from one to another
        StringColumn strCol = StringColumn.create("New Sex",((StringColumn)titanic.column(3)).asList().stream());
        anotherTitanic.insertColumn(4,strCol);
        System.out.println(anotherTitanic.first(5));

        //merging two columns
        System.out.println(anotherTitanic.row(0).toString());

//        System.out.println(titanic.columnArray().toString());
//        Object[] arr1 =  titanic.column(0).asList().toArray();
//        int [] xValues = new int[arr1.length];
//        for (int i = 0; i < arr1.length; i++) {
//            xValues[i] = (int) arr1[i];
//        }
//
//        Object[] arr2 =  titanic.column(5).asList().toArray();
//        double [] yValues = new double[arr2.length];
//
//        for (int i = 0; i < arr1.length; i++) {
//            yValues[i] = (double) arr2[i];
//        }

//        for (Integer x:xValues) {
//            System.out.println(x);
//        }

        /////////////////////////////////////////// using XChart to plot the data

        List xValues =  titanic.column(9).asList();
        List yValues =  titanic.column(5).asList();

        //System.out.println(arr1.getClass());
//        XYChart chart = QuickChart.getChart("t","x","y","series",xValues,yValues);
//        new SwingWrapper<>(chart).displayChart();


        XYChart chart = new XYChartBuilder().width(600).height(500).title("Titanic").xAxisTitle("X").yAxisTitle("Y").build();

        chart.addSeries("Tit",xValues,yValues);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        //chart.getStyler().setChartTitleVisible(false);
        //chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(8);

        new SwingWrapper<>(chart).displayChart();

    }

}
