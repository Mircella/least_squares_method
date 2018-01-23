
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
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
import Calculator.Analyse;
import Calculator.Data;

import Functions.*;

import javax.swing.*;
import java.io.IOException;

public class Controller{
    Stage formulasStage = new Stage();
    Stage solutionStage = new Stage();
    private Stage analyseStage;
    private Scene analyseScene;
    private JLabel label;
    private SwingNode swingNode;
    private ScrollPane analyseScrollPane;
    private StackPane analyseStackPane;
    private FormulasFrame formulasFrame;
    private SolutionFrame solutionFrame;
    private Function function;
    private Analyse linAnalyse,logAnalyse,expAnalyse,quadAnalyse,invAnalyse,polAnalyse;

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
        analyseStackPane = FXMLLoader.load(getClass().getResource("analyse.fxml"));
        if(analyseScene==null){
        analyseScene = new Scene(analyseStackPane,600,400);
        }
        if(swingNode==null){
            swingNode = new SwingNode();
        }
        dataTableView = Main.getDataTableView();
        if(dataTableView!=null){
            ObservableList<Data>list = dataTableView.getItems();
            defineElements(analyseScene,analyseScrollPane,swingNode, list);
        }
        else {
            System.out.println("Null");
        }
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

    private void defineElements(Scene analyseScene, ScrollPane analyseScrollPane, SwingNode swingNode, ObservableList<Data>list){
        analyseScrollPane = (ScrollPane) analyseScene.lookup("#analyseSP");
        analyseScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        analyseScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        createSwingContent(swingNode,list);
        analyseScrollPane.setContent(swingNode);
    }

    private void createSwingContent(SwingNode swingNode,ObservableList<Data>list){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(list!=null){
                    linAnalyse = new Analyse(new LinearFunction(list));
                    logAnalyse = new Analyse(new LogarithmicFunction(list));
                    expAnalyse = new Analyse(new ExponentialFunction(list));
                    quadAnalyse = new Analyse(new QuadraticFunction(list));
                    //polAnalyse = new Analyse(new Polynom(list,3));
                    invAnalyse = new Analyse(new InverseRatioFunction(list));
                    label = new FunctionAnalyse(linAnalyse,logAnalyse,expAnalyse,quadAnalyse,invAnalyse).createSolutionLabel();
                }else{
                    label = new JLabel("No analyse");
                }
                swingNode.setContent(label);
            }
        });

    }
}
