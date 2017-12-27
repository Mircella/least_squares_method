package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Functions.FunctionDescriptions.Description;
import sample.Functions.FunctionDescriptions.InverseRatioFunctionDescription;
import sample.Functions.FunctionDescriptions.LinearFunctionDescription;

import javax.swing.*;

import java.io.FileInputStream;

public class FormulasFrame extends Application {

    private static FormulasFrame formulasFrame;
    private Description description;
    private JLabel label;

    private FormulasFrame(){}
    private FormulasFrame(String functionName){

    }
    public static FormulasFrame getInstance(String functionName){
        if(formulasFrame==null){
            formulasFrame = new FormulasFrame(functionName);
        }
        if(functionName==null){
            functionName = "Inverse ratio function";
        }
        switch (functionName){
            case "Linear function":{
                formulasFrame.description = LinearFunctionDescription.getInstance();
            }break;
            case "Quadratic function":{}break;
            case "Logarithmic function":{}break;
            case "Exponential function":{}break;
            case "Inverse ratio function":{
                formulasFrame.description = InverseRatioFunctionDescription.getInstance();
            }break;
        }

        return formulasFrame;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        String path = "D:\\IdeaProjects\\Test\\src\\sample\\filesFXML\\formulas.fxml";
        FileInputStream fis = new FileInputStream(path);
        VBox vBox = (VBox) loader.load(fis);
        Scene scene = new Scene(vBox,400,180);
        ScrollPane scrollPane = (ScrollPane)scene.lookup("#myScrollPane");
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);
        scrollPane.setContent(swingNode);

        scene.getStylesheets().add(0,"styles/style.css");
        primaryStage.setTitle("Formulas");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.setX(880);
        primaryStage.setY(10);
        primaryStage.show();
    }

    private void createSwingContent(SwingNode swingNode){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(description!=null){
                label = description.createLabel();
                }
                swingNode.setContent(label);

            }
        });

    }
}
