import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Functions.FunctionDescriptions.DefaultDescription;
import Functions.FunctionDescriptions.Description;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;

public class FormulasFrame extends Application {

    private Description description;
    private JLabel label;
    private String descriptionText;
    private TeXIcon icon;
    private Stage stage;
    private SwingNode swingNode;
    private Scene scene;
    private ScrollPane scrollPane;
    private String functionName;

    public FormulasFrame(){}
    public FormulasFrame(String functionName){
        this.functionName = functionName;
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
        final VBox load = (VBox) loader.load(getClass().getResource("formulas.fxml"));
        VBox vBox = load;
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

        scene.getStylesheets().add(0, "style.css");
        primaryStage.setTitle("Formulas");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setX(Main.primaryStage.getX()+700);
        primaryStage.setY(Main.primaryStage.getY()+10);
        primaryStage.show();


    }

    private void createSwingContent(SwingNode swingNode){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(description!=null){
                descriptionText = description.createDiscription();
                icon = description.createIcon(descriptionText);
                label = description.createLabel(icon);
                }
                swingNode.setContent(label);
            }
        });
    }

    private Description defineDescription(){
        description = DefaultDescription.getInstance();
        return description;
    }
}
