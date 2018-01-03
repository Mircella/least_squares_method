package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Functions.*;
import sample.Functions.FunctionDescriptions.Description;
import sample.Functions.FunctionsSolutions.*;

import javax.swing.*;

public class SolutionFrame extends Application {

    private Function function;
    private String solutionText;
    private Solution solution;
    private JLabel label;
    private Stage stage;
    private SwingNode swingNode;
    private Scene scene;
    private ScrollPane scrollPane;

    public SolutionFrame() {
    }

    public SolutionFrame(Function function){
        this.function = function;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        solution = defineSolution(function);
        Pane root = FXMLLoader.load(getClass().getResource("filesFXML/solution.fxml"));
        if(scene==null){
        scene = new Scene(root,600,400);
        }
        scrollPane = (ScrollPane)scene.lookup("#solutionScroll");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        if(swingNode==null){
            swingNode = new SwingNode();
        }
        createSwingContent(swingNode);
        scrollPane.setContent(swingNode);
        scene.getStylesheets().add(0,"styles/style.css");
        primaryStage.setTitle("Solution");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setX(780);
        primaryStage.setY(250);
        primaryStage.show();
    }

    private void createSwingContent(SwingNode swingNode){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(solution!=null){
                    label = solution.createSolutionLabel();
                }
                swingNode.setContent(label);
            }
        });

    }

    private Solution defineSolution(Function function){
        if(function instanceof LinearFunction){
            solution = new LinearFunctionSolution((LinearFunction) function);
        }else if(function instanceof InverseRatioFunction){
            solution = new InverseRatioFunctionSolution((InverseRatioFunction) function);
        }else if(function instanceof LogarithmicFunction){
            solution = new LogarithmicFunctionSolution((LogarithmicFunction) function);
        }else if(function instanceof ExponentialFunction){
            solution = new ExponentialFunctionSolution((ExponentialFunction) function);
        }else if(function instanceof QuadraticFunction){
            solution = new QuadraticFunctionSolution((QuadraticFunction) function);
        }else {
            solution=null;
        }
        return solution;
    }
}
