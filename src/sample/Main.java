package sample;

import javafx.application.Application;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Calculator.Data;
import sample.Functions.*;


public class Main extends Application {
    private ElementCreator creator;

    private LineChart lineChart;
    private Function function;
    private static ComboBox graphComboBox;
    private StackPane chartPane;

    private VBox tableBox;
    private TableView<Data> dataTableView;


    public static ComboBox getGraphComboBox() {
        return graphComboBox;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("filesFXML/sample.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root,880,500);
        findElements(scene);
        creator = ElementCreator.getInstance();
        creator.createComboBox(graphComboBox);
        function = new LinearFunction();
        lineChart = creator.createLineChart("Default",function);
        chartPane.getChildren().add(lineChart);
        dataTableView = creator.createTable(dataTableView,tableBox,function);
        dataTableView.setEditable(true);


        graphComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String functionName = (String)graphComboBox.getValue();
                switch (functionName){
                    case "Linear function":{
                        function = new LinearFunction();
                    }break;
                    case "Inverse ratio function":{
                        function = new InverseRatioFunction();
                    }break;
                    case "Quadratic function":{
                        function = new QuadraticFunction();
                    }break;
                    case "Logarithmic function":{
                        function = new LogarithmicFunction();
                    }break;
                    case "Exponential function":{
                        function = new ExponentialFunction();
                    }break;
                    default:{
                        function=new LinearFunction();
                    }
                }
                chartPane.getChildren().remove(lineChart);
                lineChart = creator.createLineChart(functionName,function);
                chartPane.getChildren().add(lineChart);
                prepareStage(primaryStage,scene);
                primaryStage.show();
            }
        });

        scene.getStylesheets().add(0,"styles/style.css");
        prepareStage(primaryStage,scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void findElements(Scene scene){

        chartPane = (StackPane)scene.lookup("#chartPane");
        graphComboBox = (ComboBox)scene.lookup("#graphCB");
        tableBox = (VBox)scene.lookup("#tableVB");

    }

    private void prepareStage(Stage primaryStage,Scene scene){
        primaryStage.setTitle("Least Square Method");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setX(10);
        primaryStage.setY(10);
    }
}
