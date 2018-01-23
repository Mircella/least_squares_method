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
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Calculator.Data;
import Functions.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.odftoolkit.simple.TextDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

public class Main extends Application {
    private ElementCreator creator;

    private GridPane dialogHBX, dialogHBY;
    private VBox dialogVB;
    private TextField fromTFX, toTFX, fromTFY, toTFY;
    private Label taskLabelX, taskLabelY, taskLabel;
    private Optional<ButtonType> result;
    private Alert powerAlert,numberAlert,rangeDialog;
    private ButtonType okBTN, cancelBTN;

    private Button exampleBTN,rangeBTN;
    private TabPane tabPane;

    private VBox tableBox;
    private static TableView<Data> dataTableView;

    private StackPane chartPane;
    private LineChart lineChart;
    private static Function function;
    private static ComboBox graphComboBox;
    private String functionName;
    private double[]axis;

    private MenuBar menuBar;
    private Menu mainMenu;
    private MenuItem openMenuItem1, openMenuItem2, closeMenuItem;

    public static Stage primaryStage;
    private Scene scene;
    private HBox hBox;
    private TextField numbersTF, powerTF;
    private int pointNumber;
    private Integer power;

    public static ComboBox getGraphComboBox() {
        return graphComboBox;
    }

    public static Function getFunction() {
        return function;
    }

    public static void setFunction(Function function) {
        Main.function = function;
    }

    public static TableView<Data> getDataTableView() {
        return Main.dataTableView;
    }

    public static void setDataTableView(TableView<Data> dataTableView) {
        Main.dataTableView = dataTableView;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample.fxml"));
        Pane root = (Pane)(loader.load());
        scene = new Scene(root, 910, 530);
        findElements(scene,primaryStage);
        creator = ElementCreator.getInstance();
        creator.createComboBox(graphComboBox);
        creator.defineExampleButton(tabPane, exampleBTN);
        ObservableList<Data> list = Data.createDefaultList();
        dataTableView = creator.createTable(dataTableView, tableBox, list);
        dataTableView.setPrefSize(260, 250);
        lineChart = creator.createLineChart("Default", function, dataTableView,null);
        chartPane.getChildren().add(lineChart);
        defineListeners(primaryStage,list);
        scene.getStylesheets().add(0, "style.css");
        prepareStage(primaryStage, scene);
        primaryStage.show();
        }


        public static void main (String[]args){
        launch(args);
        /*ObservableList<Data>list=ElementCreator.getInstance().fillList();
        ObservableList<Data>list2=ElementCreator.getInstance().fillList2();
        Function function = new QuadraticFunction(list);
        Function function2 = new ExponentialFunction(list2);
        Analyse analyse = new Analyse(function);
        Analyse analyse2 = new Analyse(function2);
        System.out.println("First analyse:"+analyse.toString());
        System.out.println("Second analyse:"+analyse2.toString());*/

       }

        private void findElements (Scene scene,Stage stage){
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

            menuBar = (MenuBar)scene.lookup("#menuBar");
            mainMenu = menuBar.getMenus().get(0);
            openMenuItem1 = mainMenu.getItems().get(0);
            openMenuItem2 = mainMenu.getItems().get(1);
            closeMenuItem = mainMenu.getItems().get(2);
            defineMenuItems(stage,openMenuItem1,openMenuItem2,closeMenuItem);


        }

        private void prepareStage (Stage primaryStage, Scene scene){
            primaryStage.setTitle("Least Square Method");
            primaryStage.setScene(scene);
            //primaryStage.setResizable(false);
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
                                powerAlert = new Alert(Alert.AlertType.ERROR);
                                powerAlert.setTitle("Error");
                                powerAlert.setHeaderText(null);
                                powerAlert.setContentText("Function cannot be constructed with 3 or less points!");
                                powerAlert.showAndWait();
                                numbersTF.setPromptText(String.valueOf(10));
                                pointNumber=10;
                            }else if(pointNumber<=0){
                                powerAlert = new Alert(Alert.AlertType.ERROR);
                                powerAlert.setTitle("Error");
                                powerAlert.setHeaderText(null);
                                powerAlert.setContentText("Please enter a correct number!");
                                powerAlert.showAndWait();
                                numbersTF.setPromptText(String.valueOf(10));
                                pointNumber=10;
                            }
                        } catch (Exception e) {
                            powerAlert = new Alert(Alert.AlertType.ERROR);
                            powerAlert.setTitle("Error");
                            powerAlert.setHeaderText(null);
                            powerAlert.setContentText("Please enter a correct number!");
                            powerAlert.showAndWait();
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

        private void defineMenuItems(Stage stage,MenuItem...items){
            FileChooser.ExtensionFilter docFilter = new FileChooser.ExtensionFilter("DOC","*.doc");
            FileChooser.ExtensionFilter odtFilter = new FileChooser.ExtensionFilter("ODT","*.odt");
            MenuItem item1 = items[0];
            MenuItem item2 =items[1];
            MenuItem item3 = items[2];

            item1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FileChooser chooser = new FileChooser();
                    chooser.setInitialDirectory(new File(System.getProperty("user.home")+"/Desktop"));
                    chooser.getExtensionFilters().add(docFilter);
                    File file = chooser.showOpenDialog(stage);
                    if(file!=null){
                        openFile(file);
                    }
                }
            });

            item2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FileChooser chooser = new FileChooser();
                    chooser.setInitialDirectory(new File(System.getProperty("user.home")+"/Desktop"));
                    chooser.getExtensionFilters().add(odtFilter);
                    File file = chooser.showOpenDialog(stage);
                    if(file!=null){
                        openFile(file);
                    }
                }
            });

            item3.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
            item3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.exit(0);
                }
            });
    }

        private void openFile(File file){
            WordExtractor extractor;

            String path = file.getAbsolutePath();
            if(path.endsWith(".doc")){
                try {
                FileInputStream fis = new FileInputStream(file.getAbsoluteFile());
                ObservableList<Data> list = FXCollections.observableArrayList();
                HWPFDocument document = new HWPFDocument(fis);
                extractor = new WordExtractor(document);
                String text = extractor.getText().trim();
                String[]text2 = text.split("\n| ");
                for(String s:text2){
                    String[]data = new String[2];
                    data = s.split(";| |,");

                    Double x = Double.parseDouble(data[0]);
                    Double y = Double.parseDouble(data[1]);
                    list.add(new Data(x,y));
                }
                dataTableView = null;
                dataTableView = creator.createTable(dataTableView,tableBox,list);
            }catch (IOException e){
                    powerAlert = new Alert(Alert.AlertType.ERROR);
                    powerAlert.setTitle("Error");
                    powerAlert.setHeaderText("Failed to open the file");
                    powerAlert.setContentText("Check if the file exists or is not defective");
                    powerAlert.showAndWait();
                }catch (NumberFormatException e){
                    powerAlert = new Alert(Alert.AlertType.ERROR);
                    powerAlert.setTitle("Error");
                    powerAlert.setHeaderText("Illegal format of data");
                    powerAlert.setContentText("Check the accuracy of data");
                    powerAlert.showAndWait();
                }catch (IndexOutOfBoundsException e){
                    powerAlert = new Alert(Alert.AlertType.ERROR);
                    powerAlert.setTitle("Error");
                    powerAlert.setHeaderText("Only 2 numbers might be at the same line");
                    powerAlert.setContentText("Check the accuracy of data");
                    powerAlert.showAndWait();
                }
            catch (Exception e){
                    powerAlert = new Alert(Alert.AlertType.ERROR);
                powerAlert.setTitle("Error");
                powerAlert.setHeaderText("Failed to read the file");
                powerAlert.setContentText("Check if the data format is correct");
                powerAlert.showAndWait();
                }
            }else if(path.endsWith(".odt")){
                try {
                    ObservableList<Data> list = FXCollections.observableArrayList();
                    TextDocument document = TextDocument.loadDocument(file);
                    String text = document.getContentRoot().getTextContent().trim();
                    String[]text2=text.split(" |\n");
                    for(String s:text2){
                        String[]data = new String[2];
                        data = s.split(";| |,");
                        Double x = Double.parseDouble(data[0]);
                        Double y = Double.parseDouble(data[1]);
                        list.add(new Data(x,y));
                    }
                }catch (IOException e){
                    powerAlert = new Alert(Alert.AlertType.ERROR);
                    powerAlert.setTitle("Error");
                    powerAlert.setHeaderText("Failed to open the file");
                    powerAlert.setContentText("Check if the file exists or is not defective");
                    powerAlert.showAndWait();
                }catch (NumberFormatException e){
                    powerAlert = new Alert(Alert.AlertType.ERROR);
                    powerAlert.setTitle("Error");
                    powerAlert.setHeaderText("Illegal format of data");
                    powerAlert.setContentText("Check the accuracy of data");
                    powerAlert.showAndWait();
                }catch (IndexOutOfBoundsException e){
                    powerAlert = new Alert(Alert.AlertType.ERROR);
                    powerAlert.setTitle("Error");
                    powerAlert.setHeaderText("Only 2 numbers might be at the same line");
                    powerAlert.setContentText("Check the accuracy of data");
                    powerAlert.showAndWait();
                }
                catch (Exception e){
                    powerAlert = new Alert(Alert.AlertType.ERROR);
                    powerAlert.setTitle("Error");
                    powerAlert.setHeaderText("Failed to read the file");
                    powerAlert.setContentText("Check if the data format is correct");
                    powerAlert.showAndWait();
                }
            }else{
                powerAlert = new Alert(Alert.AlertType.ERROR);
                powerAlert.setTitle("Error");
                powerAlert.setHeaderText("Illegal extension");
                powerAlert.setContentText("Choose another file");
                powerAlert.showAndWait();
            }

        }

}
