package sample.Functions.FunctionsSolutions;

import javafx.collections.ObservableList;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import sample.Calculator.Data;
import sample.Functions.QuadraticFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class QuadraticFunctionSolution implements Solution {
    private QuadraticFunction function;
    private String solutionText;
    private TeXIcon icon;
    private TeXFormula formula;
    private JLabel functionLabel;
    private BufferedImage image;

    private double[][]matrix,matrix1,matrix2,matrix3;
    private double[]additionalMatrix;
    private ArrayList<Data>dataList;
    private ArrayList<String>quadraticFunctionSolution;

    public QuadraticFunctionSolution() {
    }

    public QuadraticFunctionSolution(QuadraticFunction function) {
        System.out.println("Solution of QF");
        //this.quadraticFunctionSolution = new ArrayList<>();
        this.function = function;
        this.matrix = function.getMatrix();
        this.matrix1 = function.getMatrix1();
        this.matrix2 = function.getMatrix2();
        this.matrix3 = function.getMatrix3();
        this.additionalMatrix = function.getAdditionalMatrix();
        ObservableList<Data>list = function.getDataList();
        dataList = new ArrayList<>();
        for(Data d:list){
            dataList.add(d);
        }

        /*for(Data d:dataList){
            System.out.println(d.getX()+" "+d.getY());
        }
        System.out.println("Constructed matrix");
        for(int i=0;i<matrix.length;i++)
        {
            for (int j=0;j<matrix.length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("Additional column");
        for(int i=0;i<additionalMatrix.length;i++){
            System.out.println(additionalMatrix[i]);
        }
        System.out.println("Constructed matrix1");
        for(int i=0;i<matrix1.length;i++)
        {
            for (int j=0;j<matrix1.length;j++){
                System.out.print(matrix1[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("Constructed matrix2");
        for(int i=0;i<matrix2.length;i++)
        {
            for (int j=0;j<matrix2.length;j++){
                System.out.print(matrix2[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("Constructed matrix3");
        for(int i=0;i<matrix3.length;i++)
        {
            for (int j=0;j<matrix3.length;j++){
                System.out.print(matrix3[i][j]+" ");
            }
            System.out.println();
        }*/
    }

    private String createSolutionText(){
        solutionText = "Solution\\ of\\ Quadratic\\ Function";
        return solutionText;
    }


    @Override
    public TeXIcon createSolutionIcon() {
        createSolutionText();
        if(solutionText.equals("")){
            formula = new TeXFormula(true,"No solution");
        }else{
            formula = new TeXFormula(true, solutionText);}
        icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 15);
        icon.setInsets(new Insets(0, 0, 0, 0));
        icon.setIconHeight(100,TeXConstants.ALIGN_CENTER);
        icon.setIconWidth(380,TeXConstants.ALIGN_LEFT);
        return icon;
    }

    @Override
    public JLabel createSolutionLabel() {
        createSolutionIcon();
        image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0,0,icon.getIconWidth(),icon.getIconHeight());
        if(icon==null){
            icon = createSolutionIcon();
        }
        functionLabel = new JLabel("Solution",icon,JLabel.CENTER);
        functionLabel.setForeground(new Color(0, 0, 0));
        icon.paintIcon(functionLabel, g2, 0, 0);

        return functionLabel;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }
}
