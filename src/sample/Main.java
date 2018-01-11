package sample;

import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import sample.Calculator.Analyse;
import sample.Calculator.Answer;
import sample.Calculator.Data;
import sample.Functions.*;

import java.util.Optional;

public class Main extends Application {
    private ElementCreator creator;

    private LineChart lineChart;
    private static Function function;
    private static ComboBox graphComboBox;
    public static Stage primaryStage;
    private Scene scene;
    private HBox hBox;
    private GridPane dialogHBX, dialogHBY;
    private VBox dialogVB;
    private TabPane tabPane;
    private Button exampleBTN,rangeBTN;
    private StackPane chartPane;
    private TextField numbersTF, powerTF;
    private VBox tableBox;
    private TableView<Data> dataTableView;
    private Alert powerAlert,numberAlert,rangeDialog;
    private TextField fromTFX, toTFX, fromTFY, toTFY;
    private Label taskLabelX, taskLabelY, taskLabel;
    private Optional<ButtonType> result;
    private ButtonType okBTN, cancelBTN;

    private int pointNumber;
    private Integer power;
    private String functionName;
    private double[]axis;

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
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("filesFXML/sample.fxml"));
        Pane root = loader.load();
        scene = new Scene(root, 880, 500);
        findElements(scene);
        creator = ElementCreator.getInstance();
        creator.createComboBox(graphComboBox);
        creator.defineExampleButton(tabPane, exampleBTN);
        ObservableList<Data> list = Data.createDefaultList();
        dataTableView = creator.createTable(dataTableView, tableBox, list);
        dataTableView.setPrefSize(260, 250);
        lineChart = creator.createLineChart("Default", function, dataTableView,null);
        chartPane.getChildren().add(lineChart);
        defineListeners(primaryStage,list);
        scene.getStylesheets().add(0, "styles/style.css");
        prepareStage(primaryStage, scene);
        primaryStage.show();
        }


        public static void main (String[]args){
        launch(args);
       /*double[][]matrix2 = {
                {1,4,5,6,8},
                {3,6,7,4,3},
                {1,2,3,9,8},
                {1,1,-3,-2,4},
                {9,-4,3,5,6},
        };
       ObservableList<Data>list = FXCollections.observableArrayList();
       list.add(new Data(4,2));
       list.add(new Data(2,2));
       list.add(new Data(3,1));
       list.add(new Data(2,3));
       list.add(new Data(1,1));
       Function function = new LogarithmicFunction(list);
       Analyse analyse = new Analyse(function);
       System.out.println("Result of analyse "+analyse.toString());*/

       }

        private void findElements (Scene scene){
            chartPane = (StackPane) scene.lookup("#chartPane");
            graphComboBox = (ComboBox) scene.lookup("#graphCB");
            tableBox = (VBox) scene.lookup("#tableVB");
            numbersTF = (TextField) scene.lookup("#numbersTF");
            powerTF = (TextField) scene.lookup("#powerTF");
            tabPane = (TabPane) scene.lookup("#tabPane");
            exampleBTN = (Button) scene.lookup("#exampleBTN");
            hBox = (HBox) scene.lookup("#tabBox");
            rangeBTN = (Button)scene.lookup("#rangeBTN");
            rangeBTN.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    showDialog();
                }
            });

        }

        private void prepareStage (Stage primaryStage, Scene scene){
            primaryStage.setTitle("Least Square Method");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
        }

        private void defineListeners(Stage primaryStage, ObservableList<Data> list){
            if (numbersTF != null) {
                numbersTF.setOnKeyPressed(t -> {
                    if (t.getCode() == KeyCode.ENTER) {
                        tableBox.getChildren().remove(dataTableView);
                        dataTableView = null;
                        list.clear();
                        try {
                            pointNumber = Integer.parseInt(numbersTF.getText());
                            if(pointNumber<3&&pointNumber>0){
                                numberAlert = new Alert(Alert.AlertType.ERROR);
                                numberAlert.setTitle("Error");
                                numberAlert.setHeaderText(null);
                                numberAlert.setContentText("Function cannot be constructed with 3 or less points!");
                                numberAlert.showAndWait();
                                numbersTF.setPromptText(String.valueOf(10));
                                pointNumber=10;
                            }else if(pointNumber<=0){
                                numberAlert = new Alert(Alert.AlertType.ERROR);
                                numberAlert.setTitle("Error");
                                numberAlert.setHeaderText(null);
                                numberAlert.setContentText("Please enter a correct number!");
                                numberAlert.showAndWait();
                                numbersTF.setPromptText(String.valueOf(10));
                                pointNumber=10;
                            }
                        } catch (Exception e) {
                            numberAlert = new Alert(Alert.AlertType.ERROR);
                            numberAlert.setTitle("Error");
                            numberAlert.setHeaderText(null);
                            numberAlert.setContentText("Please enter a correct number!");
                            numberAlert.showAndWait();
                            numbersTF.setPromptText(String.valueOf(10));
                            pointNumber=10;
                            System.out.println("Smth wrong");
                        }
                        for (int i = 0; i < pointNumber; i++) {
                            Data data = new Data(0, 0);
                            list.add(data);
                        }
                        dataTableView = creator.createTable(dataTableView, tableBox, list);
                        dataTableView.refresh();
                    }
                });
            }
            if (powerTF != null) {
                powerTF.setOnKeyPressed(t -> {
                    if (t.getCode() == KeyCode.ENTER) {
                        try {
                            power = Integer.parseInt(powerTF.getText());
                            if(power<3&&power>0){
                                powerAlert = new Alert(Alert.AlertType.ERROR);
                                powerAlert.setTitle("Error");
                                powerAlert.setHeaderText(null);
                                powerAlert.setContentText("To construct polynom of power 1 or 2,use option of the linear or quadratic function");
                                powerAlert.showAndWait();
                                powerTF.setPromptText(String.valueOf(3));
                                power=3;
                            }else if(power<=0){
                                powerAlert = new Alert(Alert.AlertType.ERROR);
                                powerAlert.setTitle("Error");
                                powerAlert.setHeaderText(null);
                                powerAlert.setContentText("Please enter the correct power!");
                                powerAlert.showAndWait();
                                powerTF.setPromptText(String.valueOf(3));
                                power=3;
                            }
                        } catch (Exception e) {
                            powerAlert = new Alert(Alert.AlertType.ERROR);
                            powerAlert.setTitle("Error");
                            powerAlert.setHeaderText(null);
                            powerAlert.setContentText("Please enter the correct power!");
                            powerAlert.showAndWait();
                            powerTF.setPromptText(String.valueOf(3));
                            power=3;
                            System.out.println("Smth wrong");
                        }
                    }else {
                        power=3;
                    }
                });
            }
            graphComboBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ObservableList<Data> list = dataTableView.getItems();
                    functionName = (String) graphComboBox.getValue();
                    switch (functionName) {
                        case "Linear function": {
                            if (list != null) {
                                function = new LinearFunction(list);
                            } else {
                                function = new LinearFunction();
                            }
                        }
                        break;
                        case "Inverse ratio function": {
                            if (list != null) function = new InverseRatioFunction(list);
                            else function = new InverseRatioFunction();
                        }
                        break;
                        case "Quadratic function": {
                            if (list != null) function = new QuadraticFunction(list);
                            else function = new QuadraticFunction();
                        }
                        break;
                        case "Logarithmic function": {
                            if (list != null) function = new LogarithmicFunction(list);
                            else function = new LogarithmicFunction();
                        }
                        break;
                        case "Exponential function": {
                            if (list != null) function = new ExponentialFunction(list);
                            else function = new ExponentialFunction();
                        }
                        break;
                        case "Polynom": {
                            if (list != null) {
                                if(power==null){
                                    power=3;
                                }
                                function = new Polynom(list, power);
                            }
                            else {function = new Polynom(power);}
                        }
                        break;
                        default: {
                            function = new LinearFunction(list);
                        }
                    }

                    chartPane.getChildren().remove(lineChart);
                    lineChart = creator.createLineChart(functionName, function, dataTableView,axis);
                    dataTableView.setItems(function.getDataList());
                    chartPane.getChildren().add(lineChart);
                    //prepareStage(primaryStage, scene);
                    //primaryStage.show();
                }

            });
        }

        private void showDialog(){
        rangeDialog = new Alert(Alert.AlertType.NONE);
        rangeDialog.setTitle("Range");
        rangeDialog.setHeaderText(null);
        dialogVB = createDialogContent();
        rangeDialog.getDialogPane().setContent(dialogVB);
        rangeDialog.getButtonTypes().clear();
        okBTN = new ButtonType("OK");
        cancelBTN = new ButtonType("Cancel");
        rangeDialog.getButtonTypes().addAll(okBTN,cancelBTN);
        result = rangeDialog.showAndWait();
        if(result.get()==cancelBTN){
            System.out.println("No selection");
        }else if(result.get()==okBTN){
            try {
                String X1 = fromTFX.getText();
                String X2 = toTFX.getText();
                String Y1 = fromTFY.getText();
                String Y2 = toTFY.getText();
                double x1 = Double.parseDouble(X1);
                double x2 = Double.parseDouble(X2);
                double y1 = Double.parseDouble(Y1);
                double y2 = Double.parseDouble(Y2);
                if(x1>=x2||y1>=y2){
                    powerAlert = new Alert(Alert.AlertType.ERROR);
                    powerAlert.setTitle("Error");
                    powerAlert.setHeaderText(null);
                    powerAlert.setContentText("Incorrect ranges!");
                    powerAlert.showAndWait();
                    showDialog();
                }
                axis = new double[4];
                axis[0]=x1;
                axis[1]=x2;
                axis[2]=y1;
                axis[3]=y2;
                chartPane.getChildren().remove(lineChart);
                lineChart = creator.createLineChart(functionName, function, dataTableView, axis);
                chartPane.getChildren().add(lineChart);
            }catch (Exception e){
                powerAlert = new Alert(Alert.AlertType.ERROR);
                powerAlert.setTitle("Error");
                powerAlert.setHeaderText(null);
                powerAlert.setContentText("Please enter the correct numbers!");
                powerAlert.showAndWait();
                showDialog();
            }
        }

        }

        private VBox createDialogContent(){
            VBox dialogVB = new VBox();

            taskLabel = new Label("Enter new coordinates:");
            taskLabel.setTextAlignment(TextAlignment.CENTER);

            dialogHBX = new GridPane();
            dialogHBX.setAlignment(Pos.CENTER);
            dialogHBX.setHgap(5);
            dialogHBX.setPadding(new Insets(10));
            taskLabelX = new Label("X (from/to):");
            taskLabelX.setTextAlignment(TextAlignment.CENTER);
            fromTFX = new TextField();
            fromTFX.setPromptText("0");
            toTFX = new TextField();
            toTFX.setPromptText("10");
            dialogHBX.add(taskLabelX,0,0);
            dialogHBX.add(fromTFX,1,0);
            dialogHBX.add(toTFX,2,0);

            dialogHBY = new GridPane();
            dialogHBY.setAlignment(Pos.CENTER);
            dialogHBY.setHgap(5);
            dialogHBY.setPadding(new Insets(10));
            taskLabelY = new Label("Y (from/to):");
            taskLabelY.setTextAlignment(TextAlignment.CENTER);
            fromTFY = new TextField();
            fromTFY.setPromptText("0");
            toTFY = new TextField();
            toTFY.setPromptText("10");
            dialogHBY.add(taskLabelY,0,0);
            dialogHBY.add(fromTFY,1,0);
            dialogHBY.add(toTFY,2,0);

            dialogVB.getChildren().addAll(taskLabel,dialogHBX,dialogHBY);
            return dialogVB;
        }
    }
