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

    private Description description;
    private JLabel label;
    private Stage stage;
    private SwingNode swingNode;
    private Scene scene;
    private ScrollPane scrollPane;
    private String functionName;

    public FormulasFrame(){}
    public FormulasFrame(String functionName){
        if(functionName==null){
            this.functionName = "Inverse ratio function";
        }else {
            this.functionName = functionName;
        }

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        description = defineDescription();
        FXMLLoader loader = new FXMLLoader();
        String path = "D:\\IdeaProjects\\Test\\src\\sample\\filesFXML\\formulas.fxml";
        FileInputStream fis = new FileInputStream(path);
        VBox vBox = (VBox) loader.load(fis);
        if(scene==null){
        scene = new Scene(vBox,400,180);
        }
        scrollPane = (ScrollPane)scene.lookup("#myScrollPane");
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        if(swingNode==null){
        swingNode = new SwingNode();
        }
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

    private Description defineDescription(){
        switch (functionName){
            case "Linear function":{
                description = LinearFunctionDescription.getInstance();
            }break;
            case "Quadratic function":{}break;
            case "Logarithmic function":{}break;
            case "Exponential function":{}break;
            case "Inverse ratio function":{
                description = InverseRatioFunctionDescription.getInstance();

            }break;
        }
        return description;
    }
}
