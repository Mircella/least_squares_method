package sample.Functions.FunctionsSolutions;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import sample.Calculator.Data;
import sample.Functions.LinearFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LinearFunctionSolution implements Solution {

    private LinearFunction function;
    private String solutionText;
    private TeXIcon icon;
    private TeXFormula formula;
    private JLabel functionLabel;

    private double[][]matrix,matrix1,matrix2;
    private double[]additionalMatrix;
    private ArrayList<Data>dataList;
    private ArrayList<String> linearFunctionSolution;

    public LinearFunctionSolution(LinearFunction function) {
        System.out.println("Solution of LF");
        this.function = function;
        this.matrix = function.getMatrix();
        this.matrix1=function.getMatrix1();
        this.matrix2=function.getMatrix2();
        this.additionalMatrix = function.getAdditionalMatrix();
        this.dataList = new ArrayList<>();
        for(Data d:function.getDataList()){
            this.dataList.add(d);
        }
        this.linearFunctionSolution = new ArrayList<>();

    }

    private String createSolutionText(){
        solutionText = "Solution\\ of\\ Linear\\ Function";
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
        icon.setIconHeight(10,TeXConstants.ALIGN_CENTER);
        icon.setIconWidth(30,TeXConstants.ALIGN_LEFT);
        return icon;
    }

    @Override
    public JLabel createSolutionLabel() {
        createSolutionIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.white);
        g2.fillRect(50,50,icon.getIconWidth(),icon.getIconHeight());
        if(icon==null){
            icon = createSolutionIcon();
        }
        functionLabel = new JLabel("Solution",icon,JLabel.CENTER);
        functionLabel.setForeground(new Color(0, 0, 0));
        icon.paintIcon(functionLabel, g2, 10, 10);

        return functionLabel;
    }
}
