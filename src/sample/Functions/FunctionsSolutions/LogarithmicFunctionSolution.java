package sample.Functions.FunctionsSolutions;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import sample.Calculator.Answer;
import sample.Calculator.Data;
import sample.Calculator.DetCalculator;
import sample.Functions.LogarithmicFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class LogarithmicFunctionSolution implements Solution {

    private LogarithmicFunction function;
    private String solutionText;
    private TeXIcon icon;
    private TeXFormula formula;
    private JLabel functionLabel;
    private BufferedImage image;

    private double[][]matrix,matrix1,matrix2;
    private double[]additionalMatrix;
    private double det1,det2,det3;
    private Answer answer;
    private ArrayList<Data> dataList;
    private ArrayList<String> logarithmicFunctionSolution;
    private ArrayList<DetCalculator.Minor>minors1,minors2,minors3;

    public LogarithmicFunctionSolution() {
    }

    public LogarithmicFunctionSolution(LogarithmicFunction function) {
        System.out.println("Solution of LogF");
        this.function = function;
        this.matrix = function.getMatrix();
        this.matrix1=function.getMatrix1();
        this.matrix2=function.getMatrix2();
        this.additionalMatrix = function.getAdditionalMatrix();
        this.dataList = new ArrayList<>();
        for(Data d:function.getDataList()){
            this.dataList.add(d);
        }
        this.answer = function.getAnswer();
        this.logarithmicFunctionSolution = new ArrayList<>();
        this.minors1 = new ArrayList<>();
        this.det1 = new BigDecimal(DetCalculator.det(matrix,minors1)).setScale(2,RoundingMode.CEILING).doubleValue();
        this.minors2 = new ArrayList<>();
        this.det2 = new BigDecimal(DetCalculator.det(matrix1,minors2)).setScale(2,RoundingMode.CEILING).doubleValue();
        this.minors3 = new ArrayList<>();
        this.det3 = new BigDecimal(DetCalculator.det(matrix2,minors3)).setScale(2,RoundingMode.CEILING).doubleValue();
    }

    private String createSolutionText(){
        StringBuilder sb = new StringBuilder();
        sb.append("\\hline\n"+"x & y\\\\\n"+"\\hline\n");
        solutionText="\\begin{array}{l}";
        solutionText += "Solution:\\\\";
        solutionText+="Firstly\\ let\\ represent\\ statistical\\ data\\ as\\ a\\ table:\\\\";
        for(Data d:this.dataList){
            sb.append(String.valueOf(d.getX())+" & "+String.valueOf(d.getY())+"\\\\\n");
            sb.append("\\hline\n");
        }
        solutionText+="\\begin{tabular}{||r|l||}\n" +
                sb.toString() +
                "\\end{tabular}\\\\";
        solutionText+="The\\ least\\ squares\\ method\\ is\\ for\\ finding\\ the\\ best-fitting\\ logarithmic\\ curve\\ to\\ a\\\\";
        solutionText+="given\\ set\\ of\\ points\\ by\\ minimizing\\ the\\ sum\\ of\\ the\\ squares\\ of\\ the\\ offsets\\ (so-\\\\";
        solutionText+="called\\ {}''the\\ residuals{}'')\\ of\\ the\\ points\\ from\\ the\\ curve.\\\\";
        solutionText+="Least\\ squares\\ fitting\\ in\\ the\\ case\\ of\\ logarithmic\\ function\\ proceeds\\ by\\ finding\\ the\\\\";
        solutionText+="sum\\ of\\ the\\ squares\\ of\\ the\\ vertical\\ deviations\\ R^{2}\\ of\\ a\\ set\\ of\\  n\\ data\\ points\\\\";
        solutionText+="R^{2}\\equiv \\sum_{i=1}^{n}(y_{i}-(a\\cdot \\ln x_{i}+b))^{2}\\\\";
        solutionText+="from\\ the\\ function\\ f\\\\";

        solutionText+="The\\ condition\\ for\\ R^{2}\\ to\\ be\\ a\\ minimum\\ in\\ case\\ of\\ logarithmic\\ function\\ is\\ that\\\\";
        solutionText+="\\frac{\\partial \\left ( R ^{2}\\right )}{\\partial a}=0\\ \\ \\frac{\\partial \\left ( R^{2} \\right )}{\\partial b}=0\\\\";
        solutionText+="For\\ logarithmic\\ fit:\\\\";
        solutionText+="f=a\\cdot \\ln x+b\\\\";
        solutionText+="so\\\\";
        solutionText+="\\frac{\\partial \\left ( R ^{2}\\right )}{\\partial a}=-2\\sum_{i=1}^{n}(y_{i}-(a\\cdot \\ln x_{i}+b))\\cdot \\ln x_{i}=0\\\\";
        solutionText+="\\frac{\\partial \\left ( R ^{2}\\right )}{\\partial b}=-2\\sum_{i=1}^{n}(y_{i}-(a\\cdot \\ln x_{i}+b))=0\\\\";
        solutionText+="By\\ simplifying\\ these\\ lead\\ to\\ the\\ equations\\\\";
        solutionText+="\\left\\{\\begin{matrix}\n" +
                "a\\cdot \\sum_{i=1}^{n}\\left (\\ln x_{i}\\right )^{2}+b\\cdot \\sum_{i=1}^{n}\\ln x_{i}=\\sum_{i=1}^{n}y_{i}\\cdot \\ln x_{i} \\\\ \n" +
                "a\\cdot \\sum_{i=1}^{n}\\ln x_{i}+b\\cdot n=\\sum_{i=1}^{n}y_{i}\\\\ \n" +
                "\\end{matrix}\\right.\\\\";
        solutionText+="To\\ solve\\ this\\ let\\ present\\ calculating\\ of\\ the\\ sums\\ of\\ statistic\\ data:\\\\";
        sb = new StringBuilder();
        sb.append("\\hline\n"+"\\ln x_{i} & y_{i} & (\\ln x_{i})^{2} & y_{i} \\cdot \\ln x_{i}\\\\\n"+"\\hline\n");
        for(Data d:this.dataList){
            double x2 = new BigDecimal(Math.log(Math.pow(d.getX(),2))).setScale(2, RoundingMode.CEILING).doubleValue();
            double xy = new BigDecimal(d.getY()*Math.log(d.getY())).setScale(2,RoundingMode.CEILING).doubleValue();
            sb.append(String.valueOf(new BigDecimal(Math.log(d.getX())).setScale(2,RoundingMode.CEILING).doubleValue())+
                    " & "+String.valueOf(d.getY())+
                    " & "+String.valueOf(x2)+
                    " & "+String.valueOf(xy)+"\\\\\n");
            sb.append("\\hline\n");
        }
        sb.append("\\sum_{i=1}^{n}\\ln x_{i} " +
                "& \\sum_{i=1}^{n}y_{i} " +
                "& \\sum_{i=1}^{n}\\ \\left ( \\ln x_{i} \\right )^{2}" +
                "& \\sum_{i=1}^{n} y_{i} \\cdot \\ln x_{i}"+
                "\\hline\n");
        sb.append("\\hline\n");
        sb.append(matrix[0][1]+" & "+additionalMatrix[1]+" & "+matrix[0][0]+" & "+additionalMatrix[0]);
        sb.append("\\hline\n");
        solutionText+="\\begin{tabular}{||r|l|2|3||}\n" +
                sb.toString() +"\\hline\n"+
                "\\end{tabular}\\\\";
        solutionText+="Thus\\ we\\ get\\ the\\ following\\ equation\\ system:\\\\";
        sb=new StringBuilder();
        sb.append(matrix[0][0]+"\\cdot a+"+matrix[1][0]+"\\cdot b="+additionalMatrix[0]);
        solutionText+="\\left\\{\\begin{matrix}"+sb.toString()+"\n" +
                "\\\\ ";
        sb=new StringBuilder();
        sb.append(matrix[1][0]+"\\cdot a+"+matrix[1][1]+"\\cdot b="+additionalMatrix[1]);
        solutionText+=sb.toString();
        solutionText+="\n" +
                "\\end{matrix}\\right.\\\\";
        solutionText+="Let\\ solve\\ this\\ by\\ Kramer\'s\\ method.\\\\";
        solutionText+="1.\\ Main\\ matrix\\ of\\ the\\ linear\\ equations\\ system\\ is:\\\\";
        solutionText+="\\begin{pmatrix}\n" + matrix[0][0]+
                " & "+matrix[1][0]+"\\\\ \n" + matrix[1][0]+
                " & "+matrix[1][1]+"\n" +
                "\\end{pmatrix}\\\\";
        solutionText+="And\\ the\\ suplementary\\ column\\ is:\\\\";
        solutionText+="\\begin{pmatrix}\n" +additionalMatrix[0]+
                "\\\\"+additionalMatrix[1]+" \n" +
                "\n" +
                "\\end{pmatrix}\\\\";
        solutionText+="As\\ main\\ matrix\\ is\\ square\\ and\\ it's\\ of\\ size\\ 2\\times2,\\ determinant\\ of\\ the\\ matrix\\ is\\\\";
        solutionText+="equal\\ to:\\\\";
        solutionText+="det_{main}="+matrix[0][0]+"\\cdot "+matrix[1][1]+"\\ -\\ "+matrix[1][0]+"\\cdot "+matrix[1][0]+"\\ =\\ "+det1+"\\\\";
        solutionText+="2.\\ Matrix\\ for\\ the\\ first\\ variable\\ a\\ is\\ (by\\ replacing\\ the\\ 1^{st}\\ column\\ of\\ the\\\\";
        solutionText+="main\\ matrix\\ by\\ the\\ suplementary\\ column):\\\\";
        solutionText+="\\begin{pmatrix}\n" + additionalMatrix[0]+
                " & "+matrix[1][0]+"\\\\ \n" +additionalMatrix[1]+
                " & "+matrix[1][1]+"\n" +
                "\\end{pmatrix}\\\\";
        solutionText+="As\\ matrix\\ is\\ also\\ square\\ and\\ it's\\ of\\ size\\ 2\\times2,\\ determinant\\ of\\ the\\ matrix\\ is\\\\";
        solutionText+="equal\\ to:\\\\";
        solutionText+="det_{a}="+additionalMatrix[0]+"\\cdot "+matrix[1][1]+"\\ -\\ "+matrix[1][0]+"\\cdot "+additionalMatrix[1]+"\\ =\\ "+det2+"\\\\";
        solutionText+="3.\\ Matrix\\ for\\ the\\ first\\ variable\\ a\\ is\\ (by\\ replacing\\ the\\ 1^{st}\\ column\\ of\\ the\\\\";
        solutionText+="main\\ matrix\\ by\\ the\\ suplementary\\ column):\\\\";
        solutionText+="\\begin{pmatrix}\n" + matrix[0][0]+
                " & "+matrix[1][0]+"\\\\ \n" +additionalMatrix[0]+
                " & "+additionalMatrix[1]+"\n" +
                "\\end{pmatrix}\\\\";
        solutionText+="And\\ it\'s denominator\\ is\\ equal\\ to:\\\\";
        solutionText+="det_{b}="+matrix[0][0]+"\\cdot "+additionalMatrix[1]+"\\ -\\ "+additionalMatrix[0]+"\\cdot "+matrix[1][0]+"\\ =\\ "+det3+"\\\\";
        solutionText+="Thus\\ we\\ get\\ following\\ solutions:\\\\";
        solutionText+="a\\ = \\frac{det_{a}}{det_{main}}=\\frac{"+det2+"}{"+det1+"}="+answer.getA()+"\\\\";
        solutionText+="b\\ = \\frac{det_{b}}{det_{main}}=\\frac{"+det3+"}{"+det1+"}="+answer.getB()+"\\\\";
        solutionText+="So\\ the\\ logarithmic\\ approximation\\ of\\ statistic\\ data\\ is\\ an\\ expression:\\\\";
        solutionText+="y\\ =\\ "+answer.getA()+"\\cdot \\ln x\\ +\\ "+answer.getB()+"\\\\";
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
        //image=null;


        return functionLabel;
    }
    @Override
    public BufferedImage getImage() {
        return image;
    }
}
