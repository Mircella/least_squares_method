package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;

import javafx.util.converter.DoubleStringConverter;
import sample.Calculator.Data;
import sample.Functions.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.IllegalFormatException;

public class ElementCreator {
    private XYChart.Series series,graph;
    private LineChart lineChart;
    private Function function;

    private NumberAxis xAxis;
    private NumberAxis yAxis;

    public Function getFunction() {
        return function;
    }

    private static ElementCreator creator;
    private ElementCreator(){}
    public static ElementCreator getInstance(){
        if(creator==null){
            creator = new ElementCreator();
        }
        return creator;
    }

    public LineChart createLineChart(String functionName,Function function){
        this.function = function;
        ObservableList<Data> dataList = function.getDataList();
        ArrayList<Data> dataListCopy = new ArrayList<>();
        dataListCopy.addAll(dataList);
        series = addSeries(dataListCopy);
        series.setName(functionName);
        graph = addGraph(function,dataListCopy);
        graph.setName("Approximation of "+functionName);
        createAxis(dataListCopy);
        if(xAxis!=null&&yAxis!=null){
        lineChart = new LineChart(xAxis,yAxis);
        }

        lineChart.getData().addAll(series,graph);
        return lineChart;
    }

    private XYChart.Series addSeries(ArrayList<Data>dataList){
        series = new XYChart.Series();
        for(int i=0;i<dataList.size();i++){
            series.getData().add(new XYChart.Data(dataList.get(i).getX(),dataList.get(i).getY()));
        }

        return series;
    }

    private XYChart.Series addGraph(Function function,ArrayList<Data>list){
        graph = new XYChart.Series();
        if(function instanceof InverseRatioFunction){
            for(int i=0;i<list.size();i++){
                if(list.get(i).getX()==0){
                    list.get(i).setX(1);
                }
            }
        }
        for(int i=0;i<list.size();i++){
            double x = list.get(i).getX();
            graph.getData().add(new XYChart.Data(x,function.getResult(x)));
        }

        return graph;
    }

    public void createComboBox(ComboBox graphComboBox){
        if (graphComboBox != null) {
                graphComboBox.getItems().add("Linear function");
                graphComboBox.getItems().add("Inverse ratio function");
                graphComboBox.getItems().add("Quadratic function");
                graphComboBox.getItems().add("Exponential function");
                graphComboBox.getItems().add("Logarithmic function");
        }
    }

    private void createAxis(ArrayList<Data>list){
        double maxX = list.get(0).getX();
        double minX = list.get(0).getX();
        double maxY = list.get(0).getY();
        double minY = list.get(0).getY();
        int n = list.size();
        for(int i=0;i<n;i++){
            if(maxX<list.get(i).getX()){
                maxX = list.get(i).getX();
            }
        }
        for(int i=0;i<n;i++){
            if(minX>list.get(i).getX()){
                minX = list.get(i).getX();
            }
        }
        for(int i=0;i<n;i++){
            if(maxY<list.get(i).getY()){
                maxY = list.get(i).getY();
            }
        }
        for(int i=0;i<n;i++){
            if(minY>list.get(i).getY()){
                minY = list.get(i).getY();
            }
        }
        xAxis = new NumberAxis(minX,maxX,(maxX-minX)/n);
        yAxis = new NumberAxis(minY,maxY,(maxY-minY)/n);
        //System.out.println(""+maxX+" "+minX+" "+maxY+" "+minY);
    }

    public TableView<Data> createTable(TableView<Data> dataTableView, VBox box, Function function){
        ObservableList<Data>list = function.getDataList();
        if(dataTableView==null){
        dataTableView = new TableView<Data>();
        }
        dataTableView.setEditable(true);
        dataTableView.setMinSize(260,250);
        dataTableView.setPrefSize(260,250);
        dataTableView.setMaxSize(260,250);
        createColumns(dataTableView);
        dataTableView.setItems(list);
        /*dataTableView.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            if(oldSelection!=null){
                Data d = oldSelection;
                //Data d2 = newSelection;
                System.out.println("Old: "+String.valueOf(d.getX()));
            }

        });*/

        box.getChildren().add(dataTableView);
        box.setAlignment(Pos.CENTER);
        return dataTableView;
    }

    private void createColumns(TableView<Data>dataTableView){
        TableColumn<Data,Double> xColumn = new TableColumn<Data,Double>("x");
        xColumn.setMaxWidth(130);
        xColumn.setEditable(true);
        xColumn.setPrefWidth(130);
        xColumn.setSortable(false);
        xColumn.setCellValueFactory(cellData->cellData.getValue().xPropertyProperty().asObject());
        xColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter(){
            @Override
            public Double fromString(String value) {
                double result=0;
                try {
                    result = Double.parseDouble(value);
                }catch (Exception e){
                    System.out.println("Error input");
                }
                return result;
            }
        }));
        xColumn.setOnEditCommit((TableColumn.CellEditEvent<Data,Double>event)->{
            int selectedPos = event.getTablePosition().getRow();
            double newValue=0;
            try{
            newValue = event.getNewValue();
            }catch (Exception e){
            }
            Data data = event.getTableView().getItems().get(selectedPos);
            data.setX(newValue);
        });

        TableColumn<Data,Double> yColumn = new TableColumn<Data,Double>("y");
        yColumn.setMaxWidth(130);
        yColumn.setEditable(true);
        yColumn.setPrefWidth(130);
        yColumn.setSortable(false);
        yColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter(){
            @Override
            public Double fromString(String value) {
                double result=0;
                try {
                    result = Double.parseDouble(value);
                }catch (Exception e){
                    System.out.println("Error input");
                }
                return result;
            }

        }));

        yColumn.setCellValueFactory(cellData->cellData.getValue().yPropertyProperty().asObject());
        yColumn.setOnEditCommit((TableColumn.CellEditEvent<Data,Double>event)->{
            int selectedPos = event.getTablePosition().getRow();
            double newValue=0;
            try{
                newValue = event.getNewValue();
            }catch (Exception e){
            }
            Data data = event.getTableView().getItems().get(selectedPos);
            data.setY(newValue);
        });

        dataTableView.getColumns().addAll(xColumn,yColumn);
    }
}
