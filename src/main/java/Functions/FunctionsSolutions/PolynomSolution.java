package Functions.FunctionsSolutions;

import Calculator.Answer;
import Calculator.Data;
import Functions.Polynom;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import Calculator.DetCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class PolynomSolution implements Solution{
    private Polynom function;
    private String solutionText;
    private TeXIcon icon;
    private TeXFormula formula;
    private JLabel functionLabel;
    private BufferedImage image;

    private double[][]matrix,matrix2;
    private double[]multiplyMatrix,additionalMatrix;
    private ArrayList<Data> dataList;
    private ArrayList<String> polynomSolution;
    private double det,inverseFactor;
    private Answer answer;
    private int power;
    private ArrayList<DetCalculator.Minor>minors,inverseMatrixMinors;

    public PolynomSolution() {
    }

    public PolynomSolution(Polynom function) {
        System.out.println("Solution of Polynom");
        inverseMatrixMinors = new ArrayList<>();
        minors= new ArrayList<>();
        this.function = function;
        this.power = ((Polynom)function).getPower();
        this.matrix = function.getMatrix();
        this.det = DetCalculator.det(matrix,minors);
        this.matrix2 = DetCalculator.inverseMatrix(matrix,inverseMatrixMinors);
        this.multiplyMatrix = ((Polynom)function).getResultMatrixCopy();
        this.additionalMatrix = function.getAdditionalMatrix();
        this.inverseFactor = function.getInverseFactor();

        this.dataList = new ArrayList<>();
        for(Data d:function.getDataList()){
            this.dataList.add(d);
        }
        this.polynomSolution = new ArrayList<>();

        System.out.println("Constructed matrix");
        for(int i=0;i<matrix.length;i++)
        {
            for (int j=0;j<matrix.length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("Minors of matrix");
        for(DetCalculator.Minor m:minors){
            System.out.println(m.toString());
        }
        System.out.println("Inverse matrix");
        for(int i=0;i<matrix2.length;i++)
        {
            for (int j=0;j<matrix2.length;j++){
                System.out.print(matrix2[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("Inverse factor "+inverseFactor);
        System.out.println("Additional column");
        for(int i=0;i<additionalMatrix.length;i++){
            System.out.println(additionalMatrix[i]);
        }
        System.out.println("After matrix multiplication");
        for(int i=0;i<multiplyMatrix.length;i++){
            System.out.println(multiplyMatrix[i]);
        }
    }

    private String createSolutionText(){
        if(power<=3){
        String ending="^{th}";
        if(power==1){
            ending="^{st}";
        }
        if(power==2){
            ending="^{nd}";
        }
        if(power==3){
            ending="^{rd}";
        }
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
        solutionText+="The\\ least\\ squares\\ method\\ in\\ case\\ of\\ polynom\\ of\\ the\\ "+power+ending+"\\ power\\ is\\ for\\\\";
        solutionText+="finding\\ the\\ best-fitting\\ curve\\ to\\ a\\ given\\ set\\ of\\ points\\ by\\ minimizing\\ the\\ sum\\\\";
        solutionText+="of\\ the\\ squares\\ of\\ the\\ offsets\\ (so-called\\ {}''the\\ residuals{}'')\\ of\\ the\\ points\\ from\\\\";
        solutionText+="the\\ curve.\\ In\\ this\\ case\\ least\\ squares\\ method\\ proceeds\\ by\\ finding\\ the\\ sum\\ of\\\\";
        solutionText+="the\\ squares\\ of\\ the\\ vertical\\ deviations\\ R^{2}\\ of\\ a\\ set\\ of\\ n\\ data\\ points\\\\";
        solutionText+="R^{2}\\equiv \\\\ sum_{i=1}^{n}\\left ( y_{i}-\\left ( a_{0}+a_{1}\\cdot x+a_{2}\\cdot x^{2}+...+a_{n}\\cdot x^{n} \\right)\\right)^{2}\\\\";
        solutionText+="from\\ the\\ function\\ f.\\\\";
        solutionText+="The\\ condition\\ for\\ R^{2}\\ to\\ be\\ a\\ minimum\\ in\\ case\\ of\\ polynom\\ of\\ the\\ "+power+ending+"\\ power\\ is\\ that\\\\";
        solutionText+="\\frac{\\partial \\left ( R^{2}\\right )}{\\partial a_{0}}=0\\\\ " +
                "\\frac{\\partial \\left ( R^{2} \\right )}{\\partial a_{1}}=0\\\\ " +
                "...\\\\"+
                "\\frac{\\partial \\left ( R^{2} \\right )}{\\partial a_{n}}=0\\\\";
        solutionText+="So\\ in\\ case\\ of\\ polynom\\ of\\ the\\ "+power+ending+"\\ power\\\\";
        solutionText+="\\frac{\\partial \\left ( R^{2}\\right )}{\\partial a_{0}}=-2\\cdot \\sum_{i=1}^{n}\\left ( y_{i}-\\left ( a_{0}+a_{1}\\cdot x+...+a_{n}\\cdot x^{n}\\right)\\right)\\cdot x^{n}=0\\\\";
        solutionText+="\\frac{\\partial \\left ( R^{2} \\right )}{\\partial a_{1}}=-2\\cdot \\sum_{i=1}^{n}\\left ( y_{i}-\\left ( a_{0}+a_{1}\\cdot x+...+a_{n}\\cdot x^{n}\\right)\\right)\\cdot x^{n-1}=0\\\\";
        solutionText+="...\\\\";
        solutionText+="\\frac{\\partial \\left ( R^{2} \\right )}{\\partial a_{n}}=-2\\cdot \\sum_{i=1}^{n}\\left ( y_{i}-\\left ( a_{0}+a_{1}\\cdot x+...+a_{n}\\cdot x^{n}\\right)\\right)=0\\\\";
        solutionText+="By\\ simplifying\\ these\\ lead\\ to\\ the\\ following\\ matrix\\ equation\\\\";
        solutionText+="Z\\cdot A=B\\ where\\\\";
        solutionText+="Z=\\begin{pmatrix}\n" +
                "n & \\sum_{i=1}^{n}x_{i} & \\sum_{i=1}^{n}x_{i}^{2} & ... & \\sum_{i=1}^{n}x_{i}^{n} \\\\ \n" +
                "\\sum_{i=1}^{n}x_{i} & \\sum_{i=1}^{n}x_{i}^{2} & \\sum_{i=1}^{n}x_{i}^{3} & ... & \\sum_{i=1}^{n}x_{i}^{n+1}\\\\ \n" +
                "... & ... & ... & ... & ...\\\\ \n" +
                "\\sum_{i=1}^{n}x_{i}^{n} & \\sum_{i=1}^{n}x_{i}^{n+1} & \\sum_{i=1}^{n}x_{i}^{n+2} & ... & \\sum_{i=1}^{n}x_{i}^{2n} \n" +
                "\\end{pmatrix}\\\\";
        solutionText+="A=\\begin{pmatrix}\n" +
                "a_{0}\\\\\n" +
                "a_{1}\\\\\n" +
                "...\\\\\n" +
                "a_{n} \n" +
                "\\end{pmatrix}\\\\";
        solutionText+="B=\\begin{pmatrix}\n" +
                "\\sum_{i=1}^{n}y_{i}\\\\\n" +
                "\\sum_{i=1}^{n}x_{i}\\cdot y_{i}\\\\\n" +
                "...\\\\\n" +
                "\\sum_{i=1}^{n}x_{i}^{n}\\cdot y_{i} \n" +
                "\\end{pmatrix}\\\\";
        solutionText+="After\\ calculating\\ the\\ corresponding\\ sums\\ of\\ statistic\\ data\\ we\\ get\\ the\\ following\\ matrix\\\\";
        sb = new StringBuilder("Z=\\begin{pmatrix}");
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                sb.append(matrix[i][j]);
                if(j!=(matrix.length-1)){
                    sb.append(" & ");
                }
            }
            if(i!=(matrix.length-1)){
                sb.append("\\\\");
            }
        }
        sb.append("\\end{pmatrix}");
        solutionText+=sb.toString()+"\\\\";
        solutionText+="By\\ analogy\\ the\\ matrix\\ B\\ is\\\\";
        sb = new StringBuilder("B=\\begin{pmatrix}");
        for(int i=0;i<additionalMatrix.length;i++){
            sb.append(additionalMatrix[i]);
            if(i!=(additionalMatrix.length)){
                sb.append("\\\\");
            }
        }
        sb.append("\\end{pmatrix}");
        solutionText+=sb.toString()+"\\\\";
        solutionText+="Nonhomogeneous\\ matrix\\ equations\\ of\\ the\\ form\\\\";
        solutionText+="Z\\cdot A = B\\\\";
        solutionText+="can\\ be\\ solved\\ by\\ taking\\ the\\ matrix\\ inverse\\ to\\ obtain\\\\";
        solutionText+="A=Z^{-1}\\cdot B\\\\";
        solutionText+="This\\ equation\\ will\\ have\\ a\\ nontrivial\\ solution\\ iff\\ the\\ determinant\\\\";
        solutionText+="det_{Z}\\neq 0\\\\";
        solutionText+="Thus\\ let\\ firstly\\ calculate\\ the\\ determinant\\ of\\ matrix\\ Z\\ by\\ expanding\\ it\\ by\\ minors.\\\\";
        solutionText+="To\\ expand\\ determinant\\ along\\ the\\ first\\ row,\\ we\\ need\\ to\\ find\\ the\\ minors\\ and\\ then\\\\";
        solutionText+="the\\ cofactors\\ of\\ the\\ first-row\\ entries:\\\\";
        sb = new StringBuilder();
        double[]minorsDets = new double[minors.size()];
        for(int i=0,t=0,u=0;i<minors.size();i++){
            double[][]m = minors.get(i).getMinor();
            double element = new BigDecimal(minors.get(i).getElement()).setScale(2,RoundingMode.CEILING).doubleValue();
            if(t==0){
                ending="^{st}";
            }
            if(t==1){
                ending="^{nd}";
            }
            if(t==2){
                ending="^{rd}";
            }
            if(t>=3){
                ending="^{th}";
            }
            if(m.length==2){
                if(u==0){
                    ending="^{st}";
                }
                if(u==1){
                    ending="^{nd}";
                }
                if(u==2){
                    ending="^{rd}";
                }
                if(u>=3){
                    ending="^{th}";
                }
                sb.append(String.valueOf(u+1)+ending+"\\ element\\ is\\ equal\\ to:\\\\");
                sb.append("a_{1"+(u+1)+"}"+"="+element+"\\\\");
                sb.append("Minor\\ of\\ the\\ "+(u+1)+ending+"\\ element:\\\\");
                sb.append("M_{1"+(u+1)+"}"+"=\\begin{pmatrix}\n");
                for(int k=0;k<m.length;k++){
                    for(int l=0;l<m.length;l++){
                        sb.append(String.valueOf(m[k][l]));
                        if(l!=(m.length-1)){
                            sb.append("\\ ");
                        }
                    }
                    sb.append("\\\\");
                }
                u++;
                sb.append(" \n\\end{pmatrix}\\\\");

                sb.append("As\\ the\\ matrix\\ of\\ the\\ minor\\ is\\ square\\ and\\ it's\\ of\\ size\\ 2\\times2,\\ determinant\\\\");
                sb.append("of\\ the\\ matrix\\ is\\ equal\\ to\\\\");
                sb.append("det_{M}_{1"+(u+1)+"}"+"="+m[0][0]+"\\cdot "+m[1][1]+"\\ -\\ "
                        +m[1][0]+"\\cdot "+m[1][0]+"\\ =\\ "+
                        new BigDecimal(m[0][0]*m[1][1]-m[1][0]*m[1][0]).setScale(2,RoundingMode.CEILING).doubleValue()
                        +"\\\\");
                minorsDets[i]=new BigDecimal(m[0][0]*m[1][1]-m[1][0]*m[1][0]).setScale(2,RoundingMode.CEILING).doubleValue();
                if(i!=minors.size()-1){
                    if(minors.get(i+1).getMinor().length>2){
                        double nextDet = DetCalculator.det(minors.get(i+1).getMinor(),null);
                        sb.append("Thus\\ according\\ to\\ the\\ formula\\\\");
                        sb.append("det_{A_{ij}}=\\sum(-1)^{i+j}\\cdot M_{ij}");
                        sb.append("det_{M"+"_{1"+(t+1)+"}}="+nextDet+"\\\\");
                        t++;
                    }
                }
            }else{
                u=0;
                sb.append(String.valueOf(t+1)+ending+"\\ element\\ is\\ equal\\ to:\\\\");
                sb.append("a_{1"+(t+1)+"}"+"="+element+"\\\\");
                sb.append("Minor\\ of\\ the\\ "+(t+1)+ending+"\\ element:\\\\");
                sb.append("M_{1"+(t+1)+"}"+"=\\begin{pmatrix}\n");
                for(int k=0;k<m.length;k++){
                    for(int l=0;l<m.length;l++){
                        sb.append(String.valueOf(m[k][l]));
                        if(l!=(m.length-1)){
                            sb.append("\\ ");
                        }
                    }
                    sb.append("\\\\");
                }
                sb.append(" \n\\end{pmatrix}\\\\");
                sb.append("Matrix\\ of\\ the\\ minor\\ is\\ square\\ and\\ it's\\ of\\ size\\ "+m.length+"\\times"+m.length+"\\ (e.q.\\ more\\ then\\ 2),\\ it\'s\\\\");
                sb.append("determinant\\ needs\\ to\\ be\\ also\\ expanded\\ along\\ the\\ 1^{st}\\ row\\ of\\ the\\ matrix\\ of\\ the\\ minor\\\\");
                sb.append("Minors\\ obtained\\ from\\ the\\ expansion\\ of\\ the\\ first\\ row\\ of\\ matrix:\\\\");
            }
        }
        sb.append("Using\\ the\\ formula\\ for\\ computing\\ the\\ determinant\\ we\\ get\\ that\\\\");
        sb.append("det_Z="+new BigDecimal(det).setScale(2,RoundingMode.CEILING).doubleValue()+"\\\\");
        sb.append("Using\\ the\\ formula\\ of\\ the\\ inverse\\ matrix\\ this\\ will\\ be\\\\");
        sb.append("Z^{-1}=\\frac{1}{"+inverseFactor+"} \\cdot \\\\ \\begin{pmatrix}\n");
        for(int k=0;k<matrix2.length;k++){
            for(int l=0;l<matrix2.length;l++){
                sb.append(String.valueOf(matrix2[k][l]));
                if(l!=(matrix2.length-1)){
                    sb.append("\\ ");
                }
            }
            sb.append("\\\\");
        }
        sb.append(" \n\\end{pmatrix}\\\\for\\ Z\\ matrix.\\\\");
        sb.append("After\\ multiplying\\\\");
        sb.append("Z^{-1}\\cdot B\\\\We\\ get\\ A\\ matrix\\ whose\\ each\\ element\\\\");
        sb.append("a_{0},\\ a_{1},\\ ...,\\ a_{n}\\\\ are\\ koefficients\\ of\\ the\\ polynom\\ approximation.\\\\");
        sb.append("Thus\\\\A=");
        sb.append("\\begin{pmatrix}\n");
        for(int k=0;k<multiplyMatrix.length;k++){
            sb.append(String.valueOf(multiplyMatrix[k]));
            sb.append("\\\\");
        }
        sb.append(" \n\\end{pmatrix}\\\\");
        solutionText+=sb.toString()+"\\\\";
        solutionText+="And\\ the\\ polynom\\ will\\ have\\ the\\ following\\ form\\\\";
        sb= new StringBuilder("y = ");
        String sign="+";
        for(int k=0;k<multiplyMatrix.length;k++){
            if(k==0){
            sb.append(String.valueOf(multiplyMatrix[k]));
            }
            if(k!=0){
                sb.append("\\cdot x^{"+k+"}");
            }
            if(k!=(multiplyMatrix.length-1)){
                sign = (multiplyMatrix[k+1]>0)?"\\ +\\ ":"\\ -\\ ";
                sb.append(sign+Math.abs(multiplyMatrix[k+1]));
            }

        }
        solutionText+=sb.toString()+"\\\\";
        }else{
            solutionText="\\begin{array}{l}";
            solutionText+="Result\\ polynom\\ will\\ have\\ the\\ following\\ form\\\\";
            StringBuilder sb= new StringBuilder("y = ");
            String sign="+";
            for(int k=0;k<multiplyMatrix.length;k++){
                if(k==0){
                    sb.append(String.valueOf(multiplyMatrix[k]));
                }
                if(k!=0){
                    sb.append("\\cdot x^{"+k+"}");
                }
                if(k!=(multiplyMatrix.length-1)){
                    sign = (multiplyMatrix[k+1]>0)?"\\ +\\ ":"\\ -\\ ";
                    sb.append(sign+Math.abs(multiplyMatrix[k+1]));
                }

            }
            solutionText+=sb.toString()+"\\\\";
        }
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
        if(power<=3){
        icon.setIconHeight(100,TeXConstants.ALIGN_CENTER);
        icon.setIconWidth(600,TeXConstants.ALIGN_LEFT);
        }else{
            icon.setIconHeight(50,TeXConstants.ALIGN_CENTER);
            icon.setIconWidth(600,TeXConstants.ALIGN_LEFT);
        }
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

