package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.text.TextAlignment;
import javafx.util.converter.DoubleStringConverter;
import sample.Calculator.Data;
import sample.Functions.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Optional;

public class ElementCreator {
    private XYChart.Series series,graph;
    private LineChart lineChart,lineChartAstana;
    private Function function,astanaFunction;
    private ObservableList<Data>astanaData,astanaData2;
    private TableView<Data>astanaTableView,astanaTableView2;
    private VBox vBox,vBox2;
    private StackPane chartPane;
    private ComboBox graphComboBox;
    private Alert inputAlert;
    private Button rangeBTN;
    private Alert powerAlert,rangeDialog;
    private GridPane dialogHBX, dialogHBY;
    private VBox dialogVB;
    private double[]axis;
    private Optional<ButtonType> result;
    private ButtonType okBTN, cancelBTN;
    private TextField fromTFX, toTFX, fromTFY, toTFY;
    private Label taskLabelX, taskLabelY, taskLabel;
    private String functionName;

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

    public void defineExampleButton(TabPane tabPane,Button exampleBTN){
        exampleBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                Tab astanaTab = new Tab();
                astanaTab.setText("Prices in Astana");
                astanaTab.setClosable(true);
                try{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Main.class.getResource("filesFXML/astana.fxml"));
                    HBox astanaBox = loader.load();
                    findElements(astanaBox);
                    ObservableList<Data>list = fillList();
                    astanaTableView = createTable(astanaTableView,vBox,list);
                    astanaTableView2 = createTable(astanaTableView2,vBox2,list);
                    lineChartAstana = createLineChart("Default",astanaFunction,astanaTableView,null);
                    chartPane.getChildren().add(lineChartAstana);
                    astanaTableView.setPrefSize(260,195);
                    astanaTableView2.setPrefSize(260,195);
                    astanaTab.setContent(astanaBox);
                    createComboBox(graphComboBox);
                    graphComboBox.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            astanaData = astanaTableView.getItems();
                            functionName = (String)graphComboBox.getValue();
                            switch (functionName){
                                case "Linear function":{
                                    if(astanaData!=null){
                                        astanaFunction = new LinearFunction(astanaData);
                                    }else {
                                        astanaFunction = new LinearFunction();
                                    }
                                }break;
                                case "Inverse ratio function":{
                                    if(astanaData!=null)astanaFunction = new InverseRatioFunction(astanaData);
                                    else astanaFunction = new InverseRatioFunction();
                                }break;
                                case "Quadratic function":{
                                    if(astanaData!=null)astanaFunction = new QuadraticFunction(astanaData);
                                    else astanaFunction = new QuadraticFunction();
                                }break;
                                case "Logarithmic function":{
                                    if(astanaData!=null)astanaFunction = new LogarithmicFunction(astanaData);
                                    else astanaFunction = new LogarithmicFunction();
                                }break;
                                case "Exponential function":{
                                    if(astanaData!=null)astanaFunction = new ExponentialFunction(astanaData);
                                    else astanaFunction = new ExponentialFunction();
                                }break;
                                case "Polynom":{
                                    if(list!=null)function = new Polynom(list,3);
                                    else function = new Polynom(3);
                                }break;
                                default:{
                                    astanaFunction=new LinearFunction(astanaData);
                                }
                            }

                            chartPane.getChildren().remove(lineChartAstana);
                            lineChartAstana = creator.createLineChart(functionName,astanaFunction,astanaTableView,null);
                            astanaTableView.setItems(astanaFunction.getDataList());
                            chartPane.getChildren().add(lineChartAstana);
                        }
                    });
                }catch (IOException e){

                }
                tabPane.getTabs().add(astanaTab);
            }
        });
    }

    public LineChart createLineChart(String functionName,Function function,TableView tableView,double[]axis){
        ObservableList<Data> dataList;
        if(function==null&&tableView!=null){
            dataList = tableView.getItems();
        }else if(function==null&&tableView==null){
            dataList = FXCollections.observableArrayList();
            for(int i=0;i<10;i++){
                dataList.add(new Data(0,0));
            }
        }else{
            this.function = function;
            dataList = function.getDataList();
        }
        ArrayList<Data> dataListCopy = new ArrayList<>();
        dataListCopy.addAll(dataList);
        series = addSeries(dataListCopy);
        series.setName(functionName);
        createAxis(dataListCopy,axis);
        if(xAxis!=null&&yAxis!=null){
        lineChart = new LineChart(xAxis,yAxis);
            if(function!=null){
                graph = addGraph(function,dataListCopy,xAxis,yAxis);
                graph.setName("Approximation of "+functionName);
                lineChart.getData().addAll(series,graph);
            }else {
                lineChart.getData().add(series);
            }
        }

        return lineChart;
    }

    private XYChart.Series addSeries(ArrayList<Data>dataList){
        series = new XYChart.Series();
        for(int i=0;i<dataList.size();i++){
            series.getData().add(new XYChart.Data(dataList.get(i).getX(),dataList.get(i).getY()));
        }

        return series;
    }

    private XYChart.Series addGraph(Function function,ArrayList<Data>list, NumberAxis xAxis, NumberAxis yAxis){
        graph = new XYChart.Series();
        double x1;
        double x2 = xAxis.getUpperBound();
        double dX = xAxis.getTickUnit();

        for(x1=xAxis.getLowerBound();x1<=x2;x1+=dX){
            if (x1 == 0 && (function instanceof InverseRatioFunction||function instanceof LogarithmicFunction)) {
            x1=1;
            }
            graph.getData().add(new XYChart.Data(x1,function.getResult(x1)));
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
                graphComboBox.getItems().add("Polynom");
        }
    }

    private void createAxis(ArrayList<Data>list, double[]axis){
        if(axis==null) {
            double maxX = list.get(0).getX();
            double minX = list.get(0).getX();
            double maxY = list.get(0).getY();
            double minY = list.get(0).getY();
            int n = list.size();
            for (int i = 0; i < n; i++) {
                if (maxX < list.get(i).getX()) {
                    maxX = list.get(i).getX();
                }
            }
            for (int i = 0; i < n; i++) {
                if (minX > list.get(i).getX()) {
                    minX = list.get(i).getX();
                }
            }
            for (int i = 0; i < n; i++) {
                if (maxY < list.get(i).getY()) {
                    maxY = list.get(i).getY();
                }
            }
            for (int i = 0; i < n; i++) {
                if (minY > list.get(i).getY()) {
                    minY = list.get(i).getY();
                }
            }
            xAxis = new NumberAxis(minX, maxX, (maxX - minX) / n);
            yAxis = new NumberAxis(minY, maxY, (maxY - minY) / n);
        }else{
            xAxis = new NumberAxis(axis[0],axis[1],(axis[1]-axis[0])/list.size());
            yAxis = new NumberAxis(axis[2],axis[3],(axis[3]-axis[2])/list.size());
        }
    }

    public TableView<Data> createTable(TableView<Data> dataTableView, VBox box, ObservableList<Data>dataList){
        ObservableList<Data>list=dataList;

        if(dataTableView==null){
        dataTableView = new TableView<Data>();
        }
        dataTableView.setEditable(true);
        createColumns(dataTableView);
        dataTableView.setItems(list);
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
        xColumn.setCellFactory(e->new TableCell<Data,Double>(){
            private TextField xTF;

            @Override
            public void startEdit() {
                super.startEdit();
                if(this.xTF==null){
                    createTextField();
                }
                this.xTF.setText(String.valueOf(getValue()));
                super.setGraphic(this.xTF);
                super.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                Platform.runLater(this.xTF::requestFocus);
            }

            @Override
            public void cancelEdit() {
                super.cancelEdit();
                super.setText(String.valueOf(getValue()));
                super.setContentDisplay(ContentDisplay.TEXT_ONLY);
            }

            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if(empty||item==null){
                    super.setText(null);
                    super.setGraphic(null);
                }else {
                    super.setText(String.valueOf(this.getValue()));
                    super.setContentDisplay(ContentDisplay.TEXT_ONLY);
                }
            }

            private Double getValue(){
                return Double.valueOf(super.getItem());
            }
            private void createTextField(){
                this.xTF = new TextField(String.valueOf(getValue()));
                this.xTF.setMinWidth(super.getMinWidth()-super.getGraphicTextGap()*2);
                this.xTF.setOnKeyPressed(t->{
                    if(t.getCode()== KeyCode.ENTER||t.getCode()==KeyCode.TAB){
                        Double d=0.0;
                        try {
                           d = Double.parseDouble(xTF.getText());
                        }catch (NumberFormatException e){
                            inputAlert = new Alert(Alert.AlertType.ERROR);
                            inputAlert.setTitle("Error");
                            inputAlert.setHeaderText(null);
                            inputAlert.setContentText("Please enter a correct number!");
                            inputAlert.showAndWait();
                            System.out.println("Exception");
                        }
                        super.commitEdit(d);
                    }else if(t.getCode()==KeyCode.ESCAPE){
                        super.cancelEdit();
                    }
                });
                this.xTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if(!newValue&&xTF!=null){
                        Double d=0.0;
                        try {
                            d = Double.parseDouble(xTF.getText());
                        }catch (NumberFormatException e){
                            inputAlert = new Alert(Alert.AlertType.ERROR);
                            inputAlert.setTitle("Error");
                            inputAlert.setHeaderText(null);
                            inputAlert.setContentText("Please enter a correct number!");
                            inputAlert.showAndWait();
                            System.out.println("Exception");
                        }
                        super.commitEdit(d);
                    }
                });
            }
        });
        /*xColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter(){
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
        }));*/
        xColumn.setOnEditCommit((TableColumn.CellEditEvent<Data,Double>event)->{
            int selectedPos = event.getTablePosition().getRow();
            double newValue=0;
            try{
            newValue = event.getNewValue();
            }catch (Exception e){
                inputAlert = new Alert(Alert.AlertType.ERROR);
                inputAlert.setTitle("Error");
                inputAlert.setHeaderText(null);
                inputAlert.setContentText("Please enter a correct number!");
                inputAlert.showAndWait();
            }
            Data data = event.getTableView().getItems().get(selectedPos);
            data.setX(newValue);
        });

        TableColumn<Data,Double> yColumn = new TableColumn<Data,Double>("y");
        yColumn.setMaxWidth(130);
        yColumn.setEditable(true);
        yColumn.setPrefWidth(130);
        yColumn.setSortable(false);
        /*yColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter(){
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

        }));*/
        yColumn.setCellFactory(e->new TableCell<Data,Double>(){
            private TextField xTF;

            @Override
            public void startEdit() {
                super.startEdit();
                if(this.xTF==null){
                    createTextField();
                }
                this.xTF.setText(String.valueOf(getValue()));
                super.setGraphic(this.xTF);
                super.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                Platform.runLater(this.xTF::requestFocus);
            }

            @Override
            public void cancelEdit() {
                super.cancelEdit();
                super.setText(String.valueOf(getValue()));
                super.setContentDisplay(ContentDisplay.TEXT_ONLY);
            }

            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if(empty||item==null){
                    super.setText(null);
                    super.setGraphic(null);
                }else {
                    super.setText(String.valueOf(this.getValue()));
                    super.setContentDisplay(ContentDisplay.TEXT_ONLY);
                }
            }

            private Double getValue(){
                return Double.valueOf(super.getItem());
            }
            private void createTextField(){
                this.xTF = new TextField(String.valueOf(getValue()));
                this.xTF.setMinWidth(super.getMinWidth()-super.getGraphicTextGap()*2);
                this.xTF.setOnKeyPressed(t->{
                    if(t.getCode()== KeyCode.ENTER||t.getCode()==KeyCode.TAB){
                        Double d=0.0;
                        try {
                            d = Double.parseDouble(xTF.getText());
                        }catch (NumberFormatException e){
                            inputAlert = new Alert(Alert.AlertType.ERROR);
                            inputAlert.setTitle("Error");
                            inputAlert.setHeaderText(null);
                            inputAlert.setContentText("Please enter a correct number!");
                            inputAlert.showAndWait();
                            System.out.println("Exception");
                        }
                        super.commitEdit(d);
                    }else if(t.getCode()==KeyCode.ESCAPE){
                        super.cancelEdit();
                    }
                });
                this.xTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if(!newValue&&xTF!=null){
                        Double d=0.0;
                        try {
                            d = Double.parseDouble(xTF.getText());
                        }catch (NumberFormatException e){
                            inputAlert = new Alert(Alert.AlertType.ERROR);
                            inputAlert.setTitle("Error");
                            inputAlert.setHeaderText(null);
                            inputAlert.setContentText("Please enter a correct number!");
                            inputAlert.showAndWait();
                            System.out.println("Exception");
                        }
                        super.commitEdit(d);
                    }
                });

            }
        });

        yColumn.setCellValueFactory(cellData->cellData.getValue().yPropertyProperty().asObject());
        yColumn.setOnEditCommit((TableColumn.CellEditEvent<Data,Double>event)->{
            int selectedPos = event.getTablePosition().getRow();
            double newValue=0;
            try{
                newValue = event.getNewValue();
            }catch (Exception e){
                inputAlert = new Alert(Alert.AlertType.ERROR);
                inputAlert.setTitle("Error");
                inputAlert.setHeaderText(null);
                inputAlert.setContentText("Please enter a correct number!");
                inputAlert.showAndWait();
            }
            Data data = event.getTableView().getItems().get(selectedPos);
            data.setY(newValue);
        });

        dataTableView.getColumns().addAll(xColumn,yColumn);
    }

    private ObservableList<Data>fillList(){
        ObservableList<Data>list = FXCollections.observableArrayList();
        list.add(new Data(19.90,9.55));//may 2013
        list.add(new Data(19.98,9.34));
        list.add(new Data(20.25,8.59));
        list.add(new Data(20.17,8.82));
        list.add(new Data(20.62,7.48));
        list.add(new Data(20.50,7.86));
        list.add(new Data(20.71,7.20));//november 2013
        list.add(new Data(20.95,6.40));
        list.add(new Data(21.23,6.66));
        list.add(new Data(21.35,7.72));//february 2014
        list.add(new Data(21.24,5.54));
        list.add(new Data(21.01,8.44));
        list.add(new Data(20.84,6.11));
        list.add(new Data(20.47,6.51));
        list.add(new Data(20.28,9.09));
        list.add(new Data(20.11,7.63));
        list.add(new Data(20.17,7.83));
        list.add(new Data(20.17,8.44));
        list.add(new Data(20.18,8.52));
        list.add(new Data(19.88,10.1));
        list.add(new Data(19.71,9.75));
        list.add(new Data(19.51,9.24));
        list.add(new Data(19.17,10.92));
        list.add(new Data(18.89,16.11));
        list.add(new Data(18.65,12.84));
        list.add(new Data(18.53,14.20));
        list.add(new Data(18.18,13.03));
        list.add(new Data(17.89,13.85));
        list.add(new Data(17.11,13.76));
        list.add(new Data(16.04,14.81));
        list.add(new Data(15.27,12.33));//november 2015
        return list;
    }

    private ObservableList<Data>fillList2(){
        ObservableList<Data>list = FXCollections.observableArrayList();
        list.add(new Data(19.90,34.45));//may 2013
        list.add(new Data(19.98,36.48));
        list.add(new Data(20.25,41.62));
        list.add(new Data(20.17,42.30));
        list.add(new Data(20.62,33.78));
        list.add(new Data(20.50,33.43));
        list.add(new Data(20.71,32.40));//november 2013
        list.add(new Data(20.95,24.90));
        list.add(new Data(21.23,30.38));
        list.add(new Data(21.35,29.88));//february 2014
        list.add(new Data(21.24,30.49));
        list.add(new Data(21.01,30.17));
        list.add(new Data(20.84,31.168));
        list.add(new Data(20.47,31.32));
        list.add(new Data(20.28,31.57));//july 2014
        list.add(new Data(20.11,35.33));
        list.add(new Data(20.17,19.53));
        list.add(new Data(20.17,29.75));
        list.add(new Data(20.18,31.67));
        list.add(new Data(19.88,30.70));
        list.add(new Data(19.71,34.89));
        list.add(new Data(19.51,34.84));
        list.add(new Data(19.17,38.25));
        list.add(new Data(18.89,40.31));
        list.add(new Data(18.65,38.25));
        list.add(new Data(18.53,37.72));
        list.add(new Data(18.18,39.74));
        list.add(new Data(17.89,44.36));
        list.add(new Data(17.11,43.82));
        list.add(new Data(16.04,55.04));
        list.add(new Data(15.27,57.57));//november 2015
        return list;
    }

    private void findElements(HBox astanaBox){
        vBox = (VBox) astanaBox.lookup("#tableVB");
        vBox2 = (VBox) astanaBox.lookup("#tableVB2");
        chartPane = (StackPane)astanaBox.lookup("#chartPane");
        graphComboBox = (ComboBox)astanaBox.lookup("#graphCB");
        rangeBTN = (Button)astanaBox.lookup("#rangeBTN");
        rangeBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showDialog();
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
                chartPane.getChildren().remove(lineChartAstana);
                lineChartAstana = creator.createLineChart(functionName,astanaFunction,astanaTableView,axis);
                chartPane.getChildren().add(lineChartAstana);
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
