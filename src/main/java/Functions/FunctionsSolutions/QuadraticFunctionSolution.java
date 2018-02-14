package Functions.FunctionsSolutions;

import Calculator.Answer;
import Calculator.Data;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import Calculator.DetCalculator;
import Functions.QuadraticFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
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
    private double det1,det2,det3,det4;
    private Answer answer;
    private ArrayList<Data> dataList;
    private ArrayList<DetCalculator.Minor>minors1,minors2,minors3,minors4;

    private ArrayList<String>quadraticFunctionSolution;

    public QuadraticFunctionSolution() {
    }

    public QuadraticFunctionSolution(QuadraticFunction function) {
        System.out.println("Solution of QF");
        this.function = function;
        this.matrix = function.getMatrix();
        this.matrix1=function.getMatrix1();
        this.matrix2=function.getMatrix2();
        this.matrix3 = ((QuadraticFunction)function).getMatrix3();
        this.additionalMatrix = function.getAdditionalMatrix();
        this.dataList = new ArrayList<>();
        for(Data d:function.getDataList()){
            this.dataList.add(d);
        }
        this.answer = function.getAnswer();
        this.quadraticFunctionSolution = new ArrayList<>();
        this.minors1 = new ArrayList<>();
        this.det1 = new BigDecimal(DetCalculator.det(matrix,minors1)).setScale(4, BigDecimal.ROUND_DOWN).doubleValue();
        this.minors2 = new ArrayList<>();
        this.det2 = new BigDecimal(DetCalculator.det(matrix1,minors2)).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
        this.minors3 = new ArrayList<>();
        this.det3 = new BigDecimal(DetCalculator.det(matrix2,minors3)).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
        this.minors4 = new ArrayList<>();
        this.det4 = new BigDecimal(DetCalculator.det(matrix3,minors4)).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
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
        solutionText+="The\\ least\\ squares\\ method\\ in\\ case\\ of\\ quadratic\\ function\\ is\\ for\\ finding\\ the\\\\";
        solutionText+="best-fitting\\ parabola\\ to\\ a\\ given\\ set\\ of\\ points\\ by\\ minimizing\\ the\\ sum\\ of\\ the\\\\";
        solutionText+="squares\\ of\\ the\\ offsets\\ (so-called\\ {}''the\\ residuals{}'')\\ of\\ the\\ points\\ from\\ the\\\\";
        solutionText+="parabola.\\ In\\ this\\ case\\ least\\ squares\\ method\\ proceeds\\ by\\ finding\\ the\\ sum\\ of\\\\";
        solutionText+="the\\ squares\\ of\\ the\\ vertical\\ deviations\\ R^{2}\\ of\\ a\\ set\\ of\\ n\\ data\\ points\\\\";
        solutionText+="R^{2}\\equiv \\sum_{i=1}^{n}(y_{i}-(a\\cdot x_{i}^{2}+b \\cdot x_{i}+c))^{2}\\\\";
        solutionText+="from\\ the\\ function\\ f\\ that\\ is\\ equal\\ to\\ the\\ following\\ expression:\\\\";
        solutionText+="f(x)=a\\cdot x^{2}+b \\cdot x+c\\\\";
        solutionText+="The\\ condition\\ for\\ R^{2}\\ to\\ be\\ a\\ minimum\\ in\\ case\\ of\\ quadratic\\ function\\ is\\ that\\\\";
        solutionText+="\\frac{\\partial \\left ( R^{2}\\right )}{\\partial a}=0\\ \\ " +
                "\\frac{\\partial \\left ( R^{2} \\right )}{\\partial b}=0\\ \\ " +
                "\\frac{\\partial \\left ( R^{2} \\right )}{\\partial c}=0\\\\";
        solutionText+="So\\ in\\ case\\ of\\ the\\ quadratic\\ function:\\\\";
        solutionText+="\\frac{\\partial \\left ( R^{2}\\right )}{\\partial a}=-2\\sum_{i=1}^{n}(y_{i}-(a\\cdot x_{i}^{2}+b\\cdot x_{i}+c))\\cdot x_{i}^{2}\\\\";
        solutionText+="\\frac{\\partial \\left ( R^{2} \\right )}{\\partial b}=-2\\sum_{i=1}^{n}(y_{i}-(a\\cdot x_{i}^{2}+b\\cdot x_{i}+c))\\cdot x_{i}\\\\";
        solutionText+="\\frac{\\partial \\left ( R^{2} \\right )}{\\partial c}=-2\\sum_{i=1}^{n}(y_{i}-(a\\cdot x_{i}^{2}+b\\cdot x_{i}+c))\\\\";
        solutionText+="By\\ simplifying\\ these\\ lead\\ to\\ the\\ equations\\\\";
        solutionText+="\\left\\{\\begin{matrix}\n" +
                "a\\cdot \\sum_{i=1}^{n}x_{i}^{4}+b\\cdot \\sum_{i=1}^{n}x_{i}^{3}+c\\cdot \\sum_{i=1}^{n}x_{i}^{2}=\\sum_{i=1}^{n}x_{i}^{2}\\cdot y_{i}\\\\ \n" +
                "a\\cdot \\sum_{i=1}^{n}x_{i}^{3}+b\\cdot \\sum_{i=1}^{n}x_{i}^{2}+c\\cdot \\sum_{i=1}^{n}x_{i}=\\sum_{i=1}^{n}x_{i}\\cdot y_{i}\\\\\n" +
                "a\\cdot \\sum_{i=1}^{n}x_{i}^{2}+b\\cdot \\sum_{i=1}^{n}x_{i}+c\\cdot n=\\sum_{i=1}^{n}y_{i}\n" +
                "\\end{matrix}\\right.\\\\";
        solutionText+="To\\ solve\\ this\\ let\\ present\\ calculating\\ of\\ the\\ corresponding\\ sums\\ of\\ statistic\\ data:\\\\";
        sb = new StringBuilder();
        sb.append("\\hline\n"+"\\ x_{i} & y_{i} & x_{i}^{2} & x_{i}^{3} & x_{i}^{4} & y_{i} \\cdot \\ x_{i} & y_{i} \\cdot \\ x_{i}^{2}\\\\\n"+"\\hline\n");
        for(Data d:this.dataList){
            double x2 = new BigDecimal(Math.pow(d.getX(),2)).setScale(4, BigDecimal.ROUND_DOWN).doubleValue();
            double x3 = new BigDecimal(Math.pow(d.getX(),3)).setScale(4, BigDecimal.ROUND_DOWN).doubleValue();
            double x4 = new BigDecimal(Math.pow(d.getX(),4)).setScale(4, BigDecimal.ROUND_DOWN).doubleValue();;
            double xy = new BigDecimal(d.getY()*d.getX()).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            double x2y = new BigDecimal(d.getY()*Math.pow(d.getX(),2)).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            sb.append(String.valueOf(new BigDecimal(d.getX()).setScale(4,BigDecimal.ROUND_DOWN).doubleValue())+
                    " & "+String.valueOf(d.getY())+
                    " & "+String.valueOf(x2)+
                    " & "+String.valueOf(x3)+
                    " & "+String.valueOf(x4)+
                    " & "+String.valueOf(xy)+
                    " & "+String.valueOf(x2y)+"\\\\\n");
            sb.append("\\hline\n");
        }
        sb.append("\\sum_{i=1}^{n}\\ x_{i} " +
                "& \\sum_{i=1}^{n}y_{i} " +
                "& \\sum_{i=1}^{n}x_{i}^{2}" +
                "& \\sum_{i=1}^{n}x_{i}^{3} " +
                "& \\sum_{i=1}^{n}x_{i}^{4}" +
                "& \\sum_{i=1}^{n} y_{i} \\cdot \\ x_{i} " +
                "& \\sum_{i=1}^{n} y_{i} \\cdot \\ x_{i}^{2}"+
                "\\hline\n");
        sb.append("\\hline\n");
        sb.append(matrix[1][2]+
                " & "+additionalMatrix[2]+
                " & "+matrix[1][1]+
                " & "+matrix[1][0]+
                " & "+matrix[0][0]+
                " & "+additionalMatrix[1]+
                " & "+additionalMatrix[2]);
        sb.append("\\hline\n");
        solutionText+="\\begin{tabular}{||r|l|2|3|4|5|6||}\n" +
                sb.toString() +"\\hline\n"+
                "\\end{tabular}\\\\";
        solutionText+="Thus\\ we\\ get\\ the\\ following\\ equation\\ system:\\\\";
        sb=new StringBuilder();
        sb.append(matrix[0][0]+"\\cdot a+"+matrix[0][1]+"\\cdot b+"+matrix[0][2]+"\\cdot c="+additionalMatrix[0]);
        solutionText+="\\left\\{\\begin{matrix}"+sb.toString()+"\n" +
                "\\\\ ";
        sb=new StringBuilder();
        sb.append(matrix[1][0]+"\\cdot a+"+matrix[1][1]+"\\cdot b+"+matrix[1][2]+"\\cdot c="+additionalMatrix[1]+"\\\\ ");
        solutionText+=sb.toString()+"\n";
        sb = new StringBuilder();
        sb.append(matrix[2][0]+"\\cdot a+"+matrix[2][1]+"\\cdot b+"+matrix[2][2]+"\\cdot c="+additionalMatrix[2]);
        solutionText+=sb.toString();
        solutionText+="\n" +
                "\\end{matrix}\\right.\\\\";
        solutionText+="Let\\ solve\\ this\\ by\\ Kramer\'s\\ method.\\\\";
        solutionText+="1.\\ Main\\ matrix\\ of\\ the\\ linear\\ equations\\ system\\ is:\\\\";
        solutionText+="\\begin{pmatrix}\n" +
                matrix[0][0]+" & "+matrix[0][1]+" & "+matrix[0][2]+"\\\\ \n" +
                matrix[1][0]+" & "+matrix[1][1]+" & "+matrix[1][2]+"\\\\ \n" +
                matrix[2][0]+" & "+matrix[2][1]+" & "+matrix[2][2]+"\n"+
                "\\end{pmatrix}\\\\";
        solutionText+="And\\ the\\ suplementary\\ column\\ is:\\\\";
        solutionText+="\\begin{pmatrix}\n" +
                additionalMatrix[0]+"\\\\"
                +additionalMatrix[1]+"\\\\" +
                additionalMatrix[2]+" \n"+
                "\\end{pmatrix}\\\\";
        solutionText+="As\\ main\\ matrix\\ is\\ square\\ and\\ it's\\ of\\ size\\ 3\\times3,\\ determinate\\ of\\ the\\ given\\ square\\\\";
        solutionText+="matrix\\ is\\ computed\\ by\\ expansion\\ of\\ the\\ determinant\\ by\\ minors:\\\\";
        solutionText+="To\\ expand\\ determinant\\ along\\ the\\ first\\ row,\\ we\\ need\\ to\\ find\\ the\\ minors\\ and\\ then\\\\";
        solutionText+="the\\ cofactors\\ of\\ the\\ first-row\\ entries:\\\\";
        sb = new StringBuilder();
        String ending="^{th}";
        double[]minorsDets = new double[minors1.size()];
        for(int i=0;i<minors1.size();i++){
            double[][]m = minors1.get(i).getMinor();
            double element = new BigDecimal(minors1.get(i).getElement()).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            if(m.length==2){
                if(i==0){
                    ending="^{st}";
                }
                if(i==1){
                    ending="^{nd}";
                }
                if(i==2){
                    ending="^{rd}";
                }
                sb.append(String.valueOf(i+1)+ending+"\\ element\\ is\\ equal\\ to:\\ "+element+"\\\\");
                sb.append("Minor\\ of\\ the\\ "+(i+1)+ending+"\\ element:\\\\");
                sb.append("\\begin{pmatrix}\n");
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
                sb.append("As\\ the\\ matrix\\ of\\ the\\ minor\\ is\\ square\\ and\\ it's\\ of\\ size\\ 2\\times2,\\ determinant\\\\");
                sb.append("of\\ the\\ matrix\\ is\\ equal\\ to\\\\");
                sb.append("det_{M_{"+1+","+(i+1)+"}}="+m[0][0]+"\\cdot "+m[1][1]+"\\ -\\ "
                        +m[1][0]+"\\cdot "+m[1][0]+"\\ =\\ "+
                        new BigDecimal(m[0][0]*m[1][1]-m[1][0]*m[1][0]).setScale(4,BigDecimal.ROUND_DOWN).doubleValue()
                        +"\\\\");
                minorsDets[i]=new BigDecimal(m[0][0]*m[1][1]-m[1][0]*m[1][0]).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            }
        }
        solutionText+=sb.toString()+"\\\\";
        solutionText+="Thus\\ the\\ determinant\\ of\\ the\\ main\\ matrix\\ is\\ equal\\ to:\\\\";
        sb = new StringBuilder("det_{main}=");
        for(int i=0;i<minors1.size();i++){
            sb.append("(-1)^{1+"+(i+1)+"}\\cdot"+minors1.get(i).getElement()+"\\cdot("+minorsDets[i]+")");
            if(i==minors1.size()-1){
                sb.append("=\\\\");
            }else {
                sb.append("+\\\\");
            }
        }
        sb.append(det1);
        solutionText+=sb.toString()+"\\\\";
        solutionText+="To\\ compute\\ determinants\\ of\\ variables\\ a,\\ b\\ and\\ c,\\ we\\ need\\ to\\ replace\\ corresponding\\\\";
        solutionText+="column\\ by\\ additional\\ column\\ of\\ matrix\\\\";
        solutionText+="2.\\ Thus\\ the\\ matrix\\ for\\ the\\ variable\\ a\\ is\\\\";
        solutionText+="\\begin{pmatrix}\n" +
                matrix1[0][0]+" & "+matrix1[0][1]+" & "+matrix1[0][2]+"\\\\ \n" +
                matrix1[1][0]+" & "+matrix1[1][1]+" & "+matrix1[1][2]+"\\\\ \n" +
                matrix1[2][0]+" & "+matrix1[2][1]+" & "+matrix1[2][2]+"\n"+
                "\\end{pmatrix}\\\\";
        solutionText+="As\\ the\\ given\\ matrix\\ is\\ also\\ square\\ and\\ it's\\ of\\ size\\ 3\\times3,\\ determinant\\ might\\ be\\\\";
        solutionText+="computed\\ by\\ expansion\\ of\\ the\\ determinant\\ by\\ minors:\\\\";
        solutionText+="To\\ expand\\ determinant\\ along\\ the\\ first\\ row,\\ we\\ need\\ to\\ find\\ the\\ minors\\ and\\ then\\\\";
        solutionText+="the\\ cofactors\\ of\\ the\\ first-row\\ entries:\\\\";
        sb = new StringBuilder();
        ending="^{th}";
        minorsDets = new double[minors2.size()];
        for(int i=0;i<minors2.size();i++){
            double[][]m = minors2.get(i).getMinor();
            double element = new BigDecimal(minors2.get(i).getElement()).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            if(m.length==2){
                if(i==0){
                    ending="^{st}";
                }
                if(i==1){
                    ending="^{nd}";
                }
                if(i==2){
                    ending="^{rd}";
                }
                sb.append(String.valueOf(i+1)+ending+"\\ element\\ is\\ equal\\ to:\\ "+element+"\\\\");
                sb.append("Minor\\ of\\ the\\ "+(i+1)+ending+"\\ element:\\\\");
                sb.append("\\begin{pmatrix}\n");
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
                sb.append("As\\ the\\ matrix\\ of\\ the\\ minor\\ is\\ square\\ and\\ it's\\ of\\ size\\ 2\\times2,\\ determinant\\\\");
                sb.append("of\\ the\\ matrix\\ is\\ equal\\ to\\\\");
                sb.append("det_{M_{"+1+","+(i+1)+"}}="+m[0][0]+"\\cdot "+m[1][1]+"\\ -\\ "
                        +m[1][0]+"\\cdot "+m[1][0]+"\\ =\\ "+
                        new BigDecimal(m[0][0]*m[1][1]-m[1][0]*m[1][0]).setScale(4,BigDecimal.ROUND_DOWN).doubleValue()
                        +"\\\\");
                minorsDets[i]=new BigDecimal(m[0][0]*m[1][1]-m[1][0]*m[1][0]).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            }
        }
        solutionText+=sb.toString()+"\\\\";
        solutionText+="Thus\\ the\\ determinant\\ of\\ the\\ given\\ matrix\\ is\\ equal\\ to:\\\\";
        sb = new StringBuilder("det_{a}=");
        for(int i=0;i<minors2.size();i++){
            sb.append("(-1)^{1+"+(i+1)+"}\\cdot"+minors2.get(i).getElement()+"\\cdot("+minorsDets[i]+")");
            if(i==minors2.size()-1){
                sb.append("=\\\\");
            }else {
                sb.append("+\\\\");
            }
        }
        sb.append(det2);
        solutionText+=sb.toString()+"\\\\";
        solutionText+="3.\\ As\\ well\\ the\\ matrix\\ for\\ the\\ variable\\ b\\ is\\\\";
        solutionText+="\\begin{pmatrix}\n" +
                matrix2[0][0]+" & "+matrix2[0][1]+" & "+matrix2[0][2]+"\\\\ \n" +
                matrix2[1][0]+" & "+matrix2[1][1]+" & "+matrix2[1][2]+"\\\\ \n" +
                matrix2[1][2]+" & "+matrix2[2][1]+" & "+matrix2[2][2]+"\n"+
                "\\end{pmatrix}\\\\";
        solutionText+="As\\ the\\ given\\ matrix\\ is\\ also\\ square\\ and\\ it's\\ of\\ size\\ 3\\times3,\\ determinant\\ might\\ be\\\\";
        solutionText+="computed\\ by\\ expansion\\ of\\ the\\ determinant\\ by\\ minors:\\\\";
        solutionText+="To\\ expand\\ determinant\\ along\\ the\\ first\\ row,\\ we\\ need\\ to\\ find\\ the\\ minors\\ and\\ then\\\\";
        solutionText+="the\\ cofactors\\ of\\ the\\ first-row\\ entries:\\\\";
        sb = new StringBuilder();
        ending="^{th}";
        minorsDets = new double[minors3.size()];
        for(int i=0;i<minors3.size();i++){
            double[][]m = minors3.get(i).getMinor();
            double element = new BigDecimal(minors3.get(i).getElement()).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            if(m.length==2){
                if(i==0){
                    ending="^{st}";
                }
                if(i==1){
                    ending="^{nd}";
                }
                if(i==2){
                    ending="^{rd}";
                }
                sb.append(String.valueOf(i+1)+ending+"\\ element\\ is\\ equal\\ to:\\ "+element+"\\\\");
                sb.append("Minor\\ of\\ the\\ "+(i+1)+ending+"\\ element:\\\\");
                sb.append("\\begin{pmatrix}\n");
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
                sb.append("As\\ the\\ matrix\\ of\\ the\\ minor\\ is\\ square\\ and\\ it's\\ of\\ size\\ 2\\times2,\\ determinant\\\\");
                sb.append("of\\ the\\ matrix\\ is\\ equal\\ to\\\\");
                sb.append("det_{M_{"+1+","+(i+1)+"}}="+m[0][0]+"\\cdot "+m[1][1]+"\\ -\\ "
                        +m[1][0]+"\\cdot "+m[1][0]+"\\ =\\ "+
                        new BigDecimal(m[0][0]*m[1][1]-m[1][0]*m[1][0]).setScale(4,BigDecimal.ROUND_DOWN).doubleValue()
                        +"\\\\");
                minorsDets[i]=new BigDecimal(m[0][0]*m[1][1]-m[1][0]*m[1][0]).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            }
        }
        solutionText+=sb.toString()+"\\\\";
        solutionText+="Thus\\ the\\ determinant\\ of\\ the\\ given\\ matrix\\ is\\ equal\\ to:\\\\";
        sb = new StringBuilder("det_{b}=");
        for(int i=0;i<minors3.size();i++){
            sb.append("(-1)^{1+"+(i+1)+"}\\cdot"+minors3.get(i).getElement()+"\\cdot("+minorsDets[i]+")");
            if(i==minors3.size()-1){
                sb.append("=\\\\");
            }else {
                sb.append("+\\\\");
            }
        }
        sb.append(det3);
        solutionText+=sb.toString()+"\\\\";
        solutionText+="4.\\ And\\ correspondingly\\ the\\ matrix\\ for\\ the\\ variable\\ c\\ is\\\\";
        solutionText+="\\begin{pmatrix}\n" +
                matrix3[0][0]+" & "+matrix3[0][1]+" & "+matrix3[0][2]+"\\\\ \n" +
                matrix3[1][0]+" & "+matrix3[1][1]+" & "+matrix3[1][2]+"\\\\ \n" +
                matrix3[1][2]+" & "+matrix3[2][1]+" & "+matrix3[2][2]+"\n"+
                "\\end{pmatrix}\\\\";
        solutionText+="As\\ the\\ given\\ matrix\\ is\\ also\\ square\\ and\\ it's\\ of\\ size\\ 3\\times3,\\ determinant\\ might\\ be\\\\";
        solutionText+="computed\\ by\\ expansion\\ of\\ the\\ determinant\\ by\\ minors:\\\\";
        solutionText+="To\\ expand\\ determinant\\ along\\ the\\ first\\ row,\\ we\\ need\\ to\\ find\\ the\\ minors\\ and\\ then\\\\";
        solutionText+="the\\ cofactors\\ of\\ the\\ first-row\\ entries:\\\\";
        sb = new StringBuilder();
        ending="^{th}";
        minorsDets = new double[minors4.size()];
        for(int i=0;i<minors4.size();i++){
            double[][]m = minors4.get(i).getMinor();
            double element = new BigDecimal(minors4.get(i).getElement()).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            if(m.length==2){
                if(i==0){
                    ending="^{st}";
                }
                if(i==1){
                    ending="^{nd}";
                }
                if(i==2){
                    ending="^{rd}";
                }
                sb.append(String.valueOf(i+1)+ending+"\\ element\\ is\\ equal\\ to:\\ "+element+"\\\\");
                sb.append("Minor\\ of\\ the\\ "+(i+1)+ending+"\\ element:\\\\");
                sb.append("\\begin{pmatrix}\n");
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
                sb.append("As\\ the\\ matrix\\ of\\ the\\ minor\\ is\\ square\\ and\\ it's\\ of\\ size\\ 2\\times2,\\ determinant\\\\");
                sb.append("of\\ the\\ matrix\\ is\\ equal\\ to\\\\");
                sb.append("det_{M_{"+1+","+(i+1)+"}}="+m[0][0]+"\\cdot "+m[1][1]+"\\ -\\ "
                        +m[1][0]+"\\cdot "+m[1][0]+"\\ =\\ "+
                        new BigDecimal(m[0][0]*m[1][1]-m[1][0]*m[1][0]).setScale(4,BigDecimal.ROUND_DOWN).doubleValue()
                        +"\\\\");
                minorsDets[i]=new BigDecimal(m[0][0]*m[1][1]-m[1][0]*m[1][0]).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            }
        }
        solutionText+=sb.toString()+"\\\\";
        solutionText+="Thus\\ the\\ determinant\\ of\\ the\\ given\\ matrix\\ is\\ equal\\ to:\\\\";
        sb = new StringBuilder("det_{c}=");
        for(int i=0;i<minors4.size();i++){
            sb.append("(-1)^{1+"+(i+1)+"}\\cdot"+minors4.get(i).getElement()+"\\cdot("+minorsDets[i]+")");
            if(i==minors4.size()-1){
                sb.append("=\\\\");
            }else {
                sb.append("+\\\\");
            }
        }
        sb.append(det4);
        solutionText+=sb.toString()+"\\\\";
        solutionText+="5.\\ With\\ calculated\\ determinants\\ of\\ a,\\ b,\\ c\\ varibles\\ and\\ main\\ determinant\\ we\\ can\\\\";
        solutionText+="compute\\ values\\ of\\ variables\\ a,\\ b\\ and\\ c.\\\\";
        solutionText+="a\\ = \\frac{det_{a}}{det_{main}}=\\frac{"+det2+"}{"+det1+"}="+answer.getA()+"\\\\";
        solutionText+="b\\ = \\frac{det_{b}}{det_{main}}=\\frac{"+det3+"}{"+det1+"}="+answer.getB()+"\\\\";
        solutionText+="c\\ = \\frac{det_{c}}{det_{main}}=\\frac{"+det4+"}{"+det1+"}="+answer.getC()+"\\\\";
        solutionText+="So\\ the\\ approximation\\ of\\ statistic\\ data\\ is\\ an\\ expression:\\\\";
        solutionText+="y\\ =\\ "+answer.getA()+"\\cdot x^{2}\\ "+((answer.getB()>0)?"+\\ ":"\\ ")+answer.getB()+"\\cdot x\\ "
                +((answer.getC()>0)?"+\\ ":"\\ ")+answer.getC()+"\\\\";
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
