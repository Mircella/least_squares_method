package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExamplesFrame extends Application {

    private static ExamplesFrame examplesFrame;
    private ExamplesFrame(){}
    public static ExamplesFrame getInstance(){
        if(examplesFrame==null){
            examplesFrame = new ExamplesFrame();
        }
        return examplesFrame;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("filesFXML/examples.fxml"));
        Scene scene = new Scene(root,600,400);
        scene.getStylesheets().add(0,"styles/style.css");
        primaryStage.setTitle("Examples");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setX(10);
        primaryStage.setY(10);
        primaryStage.show();
    }
}
