package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.Calculator.Data;
import sample.Functions.Function;
import sample.Functions.LinearFunction;

import java.io.IOException;

public class Controller{
    Stage formulasStage = new Stage();
    Stage solutionStage = new Stage();
    private Stage analyseStage;
    private Scene analyseScene;
    private ScrollPane analyseScrollPane;
    private StackPane analyseStackPane;
    private FormulasFrame formulasFrame;
    private SolutionFrame solutionFrame;
    private Function function;

    @FXML
    private TableView<Data> dataTableView;
    private TableColumn<Data,Double>xColumn;
    private TableColumn<Data,Double>yColumn;

    public Controller() {
    }

    private ComboBox comboBox;

    public void analyseAction(ActionEvent actionEvent) throws IOException{
        if(analyseStage==null){
        analyseStage = new Stage();
        }
        analyseStage.setTitle("Analyse");
        analyseStackPane = FXMLLoader.load(getClass().getResource("filesFXML/analyse.fxml"));
        if(analyseScene==null){
        analyseScene = new Scene(analyseStackPane,600,400);
        }
        analyseScrollPane = (ScrollPane) analyseScene.lookup("#analyseSP");
        analyseScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        analyseScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        analyseStage.setScene(analyseScene);
        analyseStage.setX(Main.primaryStage.getX()-200);
        analyseStage.setY(Main.primaryStage.getY()+100);
        analyseStage.show();
    }


    public void formulasAction(ActionEvent actionEvent) {
        comboBox = Main.getGraphComboBox();
        try{
            String functionName = (String)comboBox.getValue();
            formulasFrame = new FormulasFrame(functionName);
            formulasFrame.start(formulasStage);
        }catch (Exception e){e.printStackTrace();}
    }

    public void solAction(ActionEvent actionEvent){

        try{
            function = Main.getFunction();
            solutionFrame = new SolutionFrame(function);
            solutionFrame.start(solutionStage);
    }catch (Exception e){e.printStackTrace();}
    }
}
