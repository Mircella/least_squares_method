package main.java;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import main.java.Functions.*;
import main.java.Functions.FunctionsSolutions.*;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SolutionFrame extends Application {
    private Function function;
    private Solution solution;
    private JLabel label;
    private SwingNode swingNode;
    private Scene scene;
    private ScrollPane scrollPane;
    private Button saveBTN;
    private BufferedImage image;

    private DirectoryChooser directoryChooser;

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
        if(swingNode==null){
            swingNode = new SwingNode();
        }
        defineElements(primaryStage,scene,swingNode, solution);
        scene.getStylesheets().add(0, "main/res/style.css");
        primaryStage.setTitle("Solution");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setX(Main.primaryStage.getX()+500);
        primaryStage.setY(Main.primaryStage.getY()+250);
        primaryStage.show();
    }

    private void createSwingContent(SwingNode swingNode){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(solution!=null){
                    label = solution.createSolutionLabel();
                }else{
                    label = new JLabel("You didn't choose a function");
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
        }else if(function instanceof Polynom){
            solution = new PolynomSolution((Polynom) function);
        }
        else {
            solution=null;
        }
        return solution;
    }

    private void defineElements(Stage stage, Scene scene, SwingNode swingNode, Solution solution){
        directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a directory");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        scrollPane = (ScrollPane)scene.lookup("#solutionScroll");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        createSwingContent(swingNode);
        scrollPane.setContent(swingNode);
        saveBTN = (Button)scene.lookup("#saveBTN");
        saveBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File directory = directoryChooser.showDialog(stage);
                image = solution.getImage();
                if(image!=null){
                try {
                    File file = new File(directory.getAbsolutePath()+"\\Example2.png");
                    ImageIO.write(image, "png", file.getAbsoluteFile());
                } catch (IOException ex) {
                    ex.printStackTrace();}
                    catch (NullPointerException e){e.printStackTrace();}
                catch (Exception e){e.printStackTrace();}
            }
            }
        });
    }
}
