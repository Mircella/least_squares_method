package sample;

import javafx.application.Application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Calculator.Data;
import sample.Functions.*;

import java.io.IOException;

public class Main extends Application {
    private ElementCreator creator;

    private LineChart lineChart;
    private static Function function;
    private static ComboBox graphComboBox;
    private Scene scene;
    private HBox hBox;
    private TabPane tabPane;
    private Button exampleBTN;
    private StackPane chartPane;
    private TextField numbersTF;

    private VBox tableBox;
    private TableView<Data> dataTableView;

    private static FormulasFrame formulasFrame;
    private static SolutionFrame solutionFrame;

    private int pointNumber;

    public static ComboBox getGraphComboBox() {
        return graphComboBox;
    }

    public static Function getFunction() {
        return function;
    }

    public static void setFunction(Function function) {
        Main.function = function;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("filesFXML/sample.fxml"));
        Pane root = loader.load();
        scene = new Scene(root,880,500);
        findElements(scene);

        creator = ElementCreator.getInstance();
        creator.createComboBox(graphComboBox);
        creator.defineExampleButton(tabPane,exampleBTN);
        ObservableList<Data>list = FXCollections.observableArrayList();
        for(int i=0;i<10;i++){
            Data data = new Data(0,0);
            list.add(data);
        }
        function = new LinearFunction();
        lineChart = creator.createLineChart("Default",function);
        chartPane.getChildren().add(lineChart);
        dataTableView = creator.createTable(dataTableView,tableBox,function);
        dataTableView.setEditable(true);

        if(numbersTF!=null){
            numbersTF.setOnKeyPressed(t->{
                if(t.getCode()== KeyCode.ENTER){
                    tableBox.getChildren().remove(dataTableView);
                    dataTableView = null;
                    list.clear();
                    try {
                        pointNumber = Integer.parseInt(numbersTF.getText());
                    }catch (Exception e){
                        System.out.println("Smth wrong");
                    }
                    for(int i=0;i<pointNumber;i++){
                        Data data = new Data(0,0);
                        list.add(data);
                    }
                    function = new LinearFunction(list);
                    dataTableView = creator.createTable(dataTableView,tableBox,function);
                    dataTableView.refresh();
                }
            });
        }

        graphComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<Data> list = dataTableView.getItems();
                String functionName = (String)graphComboBox.getValue();
                switch (functionName){
                    case "Linear function":{
                        if(list!=null){
                        function = new LinearFunction(list);
                        }else {
                            function = new LinearFunction();
                        }
                    }break;
                    case "Inverse ratio function":{
                        if(list!=null)function = new InverseRatioFunction(list);
                        else function = new InverseRatioFunction();
                    }break;
                    case "Quadratic function":{
                        if(list!=null)function = new QuadraticFunction(list);
                        else function = new QuadraticFunction();
                    }break;
                    case "Logarithmic function":{
                        if(list!=null)function = new LogarithmicFunction(list);
                        else function = new LogarithmicFunction();
                    }break;
                    case "Exponential function":{
                        if(list!=null)function = new ExponentialFunction(list);
                        else function = new ExponentialFunction();
                    }break;
                    default:{
                        function=new LinearFunction(list);
                    }
                }

                chartPane.getChildren().remove(lineChart);
                lineChart = creator.createLineChart(functionName,function);
                dataTableView.setItems(function.getDataList());
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
        /*QuadraticFunction testFunc = new QuadraticFunction();
        double[][]matrix = testFunc.getMatrix();
        ArrayList<String> solution  =new ArrayList<>();
        double res = DetCalculator2.det(matrix,solution);
        System.out.println(res);
        for(String s:solution){
            System.out.println(s);
        }*/
    }

    private void findElements(Scene scene){
        chartPane = (StackPane)scene.lookup("#chartPane");
        graphComboBox = (ComboBox)scene.lookup("#graphCB");
        tableBox = (VBox)scene.lookup("#tableVB");
        numbersTF = (TextField)scene.lookup("#numbersTF");
        tabPane = (TabPane)scene.lookup("#tabPane");
        exampleBTN = (Button)scene.lookup("#exampleBTN");
        hBox = (HBox)scene.lookup("#tabBox");

    }

    private void prepareStage(Stage primaryStage,Scene scene){
        primaryStage.setTitle("Least Square Method");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setX(10);
        primaryStage.setY(10);
    }
}
