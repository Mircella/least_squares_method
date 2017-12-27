package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SolutionFrame extends Application {

    private static SolutionFrame solutionFrame;

    private SolutionFrame() {
    }

    public static SolutionFrame getInstance(){
        if(solutionFrame==null){
            solutionFrame = new SolutionFrame();
        }
        return solutionFrame;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("filesFXML/solution.fxml"));
        Scene scene = new Scene(root,600,400);

        scene.getStylesheets().add(0,"styles/style.css");
        primaryStage.setTitle("Solution");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setX(780);
        primaryStage.setY(250);
        primaryStage.show();
    }
}
