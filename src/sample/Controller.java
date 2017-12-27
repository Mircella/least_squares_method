package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.stage.Stage;
import sample.Calculator.Data;
import sample.Functions.Function;
import sample.Functions.LinearFunction;

public class Controller{
    Stage formulasStage = new Stage();
    Stage solutionStage = new Stage();
    Stage exmplesStage = new Stage();
    private FormulasFrame formulasFrame;
    private SolutionFrame solutionFrame;
    private ExamplesFrame examplesFrame;
    private Function function;

    @FXML
    private TableView<Data> dataTableView;
    private TableColumn<Data,Double>xColumn;
    private TableColumn<Data,Double>yColumn;

    public Controller() {
    }

    private ComboBox comboBox;
    public void graphAction(ActionEvent actionEvent) {

    }

    public void examplesAction(ActionEvent actionEvent){

    }


    public void formulasAction(ActionEvent actionEvent) {
        comboBox = Main.getGraphComboBox();
        try{
            String functionName = (String)comboBox.getValue();
            System.out.println(functionName);
            formulasFrame = FormulasFrame.getInstance(functionName);
            formulasFrame.start(formulasStage);
        }catch (Exception e){e.printStackTrace();}
    }

    public void solAction(ActionEvent actionEvent){
        try{
        solutionFrame = SolutionFrame.getInstance();
        solutionFrame.start(solutionStage);
    }catch (Exception e){e.printStackTrace();}
    }
}
