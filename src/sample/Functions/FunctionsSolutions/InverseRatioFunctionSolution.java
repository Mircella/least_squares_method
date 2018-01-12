package sample.Functions.FunctionsSolutions;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import sample.Calculator.Answer;
import sample.Calculator.Data;
import sample.Functions.InverseRatioFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class InverseRatioFunctionSolution implements Solution {
    private InverseRatioFunction function;
    private String solutionText;
    private TeXIcon icon;
    private TeXFormula formula;
    private JLabel functionLabel;
    private BufferedImage image;

    private double[][]matrix,matrix1,matrix2;
    private double[]additionalMatrix;
    private Answer answer;
    private ArrayList<Data> dataList;
    private ArrayList<String> inverseRatioFunctionSolution;

    public InverseRatioFunctionSolution() {
    }

    public InverseRatioFunctionSolution(InverseRatioFunction function) {
        System.out.println("Solution of IRF");
        this.function = function;
        this.answer=function.getAnswer();
        this.matrix = function.getMatrix();
        this.matrix1=function.getMatrix1();
        this.matrix2=function.getMatrix2();
        this.additionalMatrix = function.getAdditionalMatrix();
        this.dataList = new ArrayList<>();
        for(Data d:function.getDataList()){
            this.dataList.add(d);
        }
        this.inverseRatioFunctionSolution = new ArrayList<>();
    }

    private String createSolutionText(){
        StringBuilder sb = new StringBuilder();
        sb.append("\\hline\n"+"x & y\\\\\n"+"\\hline\n");
        solutionText="\\begin{array}{l}";
        solutionText += "Solution:\\\\";
        solutionText+="The\\ method\\ of\\ least\\ squares\\ calculates\\ the\\ line\\ of\\ the\\ best\\ fit\\ by\\ minimising\\\\";
        solutionText+="the\\ sum\\ of\\ the\\ squares\\ of\\ the\\ vertical\\ distances\\ of\\ the\\ points\\ to\\ the\\ line.\\\\";
        solutionText+="Firstly\\ let\\ represent\\ statistical\\ data\\ as\\ a\\ table:\\\\";
        for(Data d:this.dataList){
            sb.append(String.valueOf(d.getX())+" & "+String.valueOf(d.getY())+"\\\\\n");
            sb.append("\\hline\n");
        }
        solutionText+="\\begin{tabular}{||r|l||}\n" +
                sb.toString() +
                "\\end{tabular}\\\\";
        solutionText+="According\\ to\\ the\\ formulas\\ of\\ linear\\ approximation,\\ parameters\\ a\\ and\\ b\\ of\\ optimal\\\\";
        solutionText+="linear\\ regression\\ model\\ y=a\\cdot x+b\\ should\\ be\\ calculated\\ as\\ a\\ solution\\ of\\ the\\\\";
        solutionText+="equation\\ system:\\\\";
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
