package Functions;

import Calculator.Analyse;
import Calculator.Answer;
import Calculator.Data;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;

public class FunctionAnalyse {

    private Analyse linAnalyse,logAnalyse,expAnalyse,quadAnalyse,invAnalyse,mainAnalyse;
    private ArrayList<Data>dataList;
    private Function function,mainFunction,function1,function2,function3,function4;
    private Answer answer;
    private String analyseText;
    private TeXIcon icon;
    private TeXFormula formula;
    private JLabel analyseLabel;
    private BufferedImage image;
    private double correlation,deviation,approximation,determination;

    public FunctionAnalyse(Analyse...analyses) {
        System.out.println("Analyse");
        this.function = analyses[0].getFunction();
        this.linAnalyse = analyses[0];
        this.function1 = analyses[1].getFunction();
        this.logAnalyse = analyses[1];
        this.expAnalyse = analyses[2];
        this.function2 = analyses[2].getFunction();
        this.quadAnalyse = analyses[3];
        this.function3 = analyses[3].getFunction();
        this.invAnalyse = analyses[4];
        this.function4 = analyses[4].getFunction();
        this.dataList = new ArrayList<>();
        for(Data d:function.getDataList()){
            this.dataList.add(d);
        }
        double min = analyses[0].getDeviation();
        for(int i=0;i<analyses.length;i++){
            if(min>analyses[i].getDeviation()){
                min = analyses[i].getDeviation();
                mainFunction= analyses[i].getFunction();
                mainAnalyse = analyses[i];
            }
        }
        this.correlation = mainAnalyse.getCorrelation();
        this.deviation = mainAnalyse.getDeviation();
        this.determination = mainAnalyse.getDetermination();
        this.approximation = mainAnalyse.getApproximation();
       // this.polAnalyse = analyses[5];
    }

    private String createAnalyseText(){
        double xMean=0;
        for(Data d:dataList){
            xMean+=d.getX();
        }
        double[]eps2=new double[dataList.size()];
        double[]a=new double[dataList.size()];
        double[]xx2=new double[dataList.size()];
        double[]xy = new double[dataList.size()];
        double[] x2=new double[dataList.size()];
        double[]y2=new double[dataList.size()];
        double sumX=0;
        double sumY=0;
        double sumXY=0;
        double sumX2=0;
        double sumY2=0;
        xMean = new BigDecimal(xMean/ dataList.size()).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
        analyseText="\\begin{array}{l}";
        analyseText+="To\\ evaluate\\ approximation\\ functions\\ it\'s\\ necessary\\ to\\ do\\ regression\\ analysis.\\\\";
        analyseText+="Result\\ functions\\\\";
        function = linAnalyse.getFunction();
        answer = function.getAnswer();
        analyseText+="Linear\\ function:\\\\y\\ =\\ "+answer.getA()+"\\cdot x\\ +\\ "+answer.getB()+"\\\\";
        function = logAnalyse.getFunction();
        answer = function.getAnswer();
        analyseText+="Logarithmic\\ function\\\\y\\ =\\ "+answer.getA()+"\\cdot \\ln x\\ +\\ "+answer.getB()+"\\\\";
        function = expAnalyse.getFunction();
        answer = function.getAnswer();
        analyseText+="Exponential\\ function\\\\y = e^{"+answer.getA()+"\\cdot x\\ +\\ "+answer.getB()+"}\\\\";
        function = quadAnalyse.getFunction();
        answer = function.getAnswer();
        analyseText+="Quadratic\\ function\\\\y\\ =\\ "+answer.getA()+"\\cdot x^{2}\\ "+((answer.getB()>0)?"+\\ ":"\\ ")+answer.getB()+"\\cdot x\\ "
                +((answer.getC()>0)?"+\\ ":"\\ ")+answer.getC()+"\\\\";
        function = invAnalyse.getFunction();
        answer = function.getAnswer();
        analyseText+="Inverse\\ ratio\\ function\\\\y=\\frac{"+answer.getA()+"}{x"+"}\\ +\\ "+answer.getB()+"\\\\";
        analyseText+="The\\ best\\ approximation\\ model\\ is\\ "+mainFunction.getFormula();
        analyseText+="1.\\ Firstly\\ let\\ represent\\ statistical\\ data\\ as\\ a\\ table:\\\\";
        StringBuilder sb = new StringBuilder();
        sb.append("\\hline\n"+"x_{i} & y_{i} & x_{i}\\cdot y_{i} & x_{i}^{2} & y_{i}^{2}\\\\\n"+"\\hline\n");
        for(int i=0;i<dataList.size();i++){
            Data d = dataList.get(i);
            xy[i]=new BigDecimal(d.getX()*d.getY()).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            x2[i]=new BigDecimal(Math.pow(d.getX(),2)).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            y2[i]=new BigDecimal(Math.pow(d.getY(),2)).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            sb.append(String.valueOf(d.getX())+
                    " & "+String.valueOf(d.getY())+
                    " & "+String.valueOf(xy[i])+
                    " & "+String.valueOf(x2[i])+
                    " & "+String.valueOf(y2[i])+"\\\\\n");
            sb.append("\\hline\n");
        }
        for(int i=0;i<dataList.size();i++){
            sumX+=dataList.get(i).getX();
            sumY+=dataList.get(i).getY();
            sumXY+=xy[i];
            sumX2+=x2[i];
            sumY2+=y2[i];
        }
        sb.append("\\sum_{i=1}^{n}x_{i}" +
                "& \\sum_{i=1}^{n}y_{i}" +
                "& \\sum_{i=1}^{n}(x_{i}\\cdot y_{i} " +
                "& \\sum_{i=1}^{n}x_{i}^{2}"+
                " & \\sum_{i=1}^{n}y_{i}^{2}"+
                "\\hline\n");
        sb.append(String.valueOf(sumX) +
                "& "+String.valueOf(sumY)+
                "& "+String.valueOf(sumXY) +
                "& "+String.valueOf(sumX2)+
                " & "+String.valueOf(sumY2)+
                "\\hline\n");
        sb.append("\\hline\n");
        analyseText+="\\begin{tabular}{||r|l|2|3|4||}\n" +
                sb.toString() +"\\end{tabular}\\\\";
        analyseText+="\\\\";
        analyseText+="2.\\ Let\'s\\ calculate\\ the\\ mean\\ value\\ of\\ x_{i},\\ \\forall i=1,...,n\\\\";
        analyseText+="\\overline{x}=\\frac{1}{n}\\cdot \\sum_{i=1}^{n}x_{i}="+xMean+"\\\\";
        sb = new StringBuilder();
        analyseText+="3.\\ Then\\ we\\ need\\ to\\ calculate\\ a\\ table\\ of\\ the\\ auxiliary\\ values:\\\\";
        sb.append("\\hline\n"+"\\widehat{y}_{i} & " +
                "x_{i}-\\overline{x} & " +
                "(x_{i}-\\overline{x})^{2} " +
                "& \\varepsilon _{i} " +
                "& \\varepsilon _{i}^{2} " +
                "& A_{i} \\\\\n"+"\\hline\n");
        for(int i=0;i<dataList.size();i++){
            double y = new BigDecimal(mainFunction.getResult(dataList.get(i).getX())).setScale(4, BigDecimal.ROUND_DOWN).doubleValue();
            double xx = new BigDecimal(dataList.get(i).getX()-xMean).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            xx2[i] = new BigDecimal(Math.pow(dataList.get(i).getX()-xMean,2)).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            double eps = new BigDecimal(dataList.get(i).getY()-y).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            eps2[i] = new BigDecimal(Math.pow(dataList.get(i).getY()-y,2)).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            a[i] = new BigDecimal(eps/dataList.get(i).getY()).setScale(4,BigDecimal.ROUND_DOWN).doubleValue();
            sb.append(String.valueOf(y)+
                    " & "+String.valueOf(xx)+
                    " & "+String.valueOf(xx2[i])+
                    " & "+String.valueOf(eps)+
                    " & "+String.valueOf(eps2[i])+
                    " & "+String.valueOf(a[i])+"\\\\\n");
            sb.append("\\hline\n");
        }
        sb.append("\\ - " +
                "& \\ - " +
                "& \\sum_{i=1}^{n}(x_{i}-\\overline{x})^{2} " +
                "& \\ -"+
                " & \\sum_{i=1}^{n} \\varepsilon _{i}^{2}"+
                " & \\sum_{i=1}^{n} \\ A _{i}"+
                "\\hline\n");
        sb.append("\\hline\n");
        double sumXX2=0;
        for(double d:xx2){
            sumXX2+=d;
        }
        double sumEPS2=0;
        for(double d:eps2){
            sumEPS2+=d;
        }
        double sumA=0;
        for(double d:a){
            sumA+=d;
        }
        sb.append("- & "+
                "- & "
                +new BigDecimal(sumXX2).setScale(4,BigDecimal.ROUND_DOWN)
                +" & - & "
                +new BigDecimal(sumEPS2).setScale(4,BigDecimal.ROUND_DOWN).doubleValue()+" & "
                +new BigDecimal(sumA).setScale(4,BigDecimal.ROUND_DOWN).doubleValue());
        sb.append("\\hline\n");
        analyseText+="\\begin{tabular}{||r|l|2|3|4|5||}\n" +
                sb.toString() +"\\hline\n"+
                "\\end{tabular}\\\\";
        analyseText+="where\\\\ \\widehat{y}_{i}=f(x_{i})\\\\";
        analyseText+="\\varepsilon _{i}=y_{i}-\\widehat{y}_{i}\\\\";
        analyseText+="A_{i}=\\left | \\frac{y_{i}-\\widehat{y_{i}}}{y_{i}} \\right |\\\\";
        analyseText+="To\\ evaluate\\ the\\ degree\\ of\\ ";
        analyseText+="4.\\ To\\ calculate\\ selected\\ correlation\\ factor\\ we\\ need\\ to\\ calcuate\\ covariation\\\\";
        analyseText+="of\\ the\\ dependent\\ and \\ independent\\ variables\\ x\\ and\\ y\\ and\\ their\\ mean-square\\\\";
        analyseText+="deviations:\\\\";
        analyseText+="cov(x,y)=\\frac{\\sum_{i=1}^{n}(x_{i}-\\overline{x})\\cdot (y_{i}-\\overline{y})}{n}\\\\";
        analyseText+="And\\ the\\ corresponding\\ mean-square\\ deviations\\ are\\ computed\\ in\\ the\\ following\\ way\\\\";
        analyseText+="s_{x}=\\sqrt{\\frac{\\sum_{i=1}^{n}(x_{i}-\\overline{x})^{2}}{n}}\\\\";
        analyseText+="s_{y}=\\sqrt{\\frac{\\sum_{i=1}^{n}(y_{i}-\\overline{y})^{2}}{n}}\\\\";
        analyseText+="Thus\\ correlation\\ factor\\ will\\ be\\ equal\\ to\\\\";
        analyseText+="r_{xy}=\\frac{cov(x,y)}{s_{x}\\cdot s_{y}}\\\\";
        analyseText+="And\\ after\\ the\\ simplifying\\ we\\ get\\ the\\ following\\ formula\\ of\\ correlation\\ factor:\\\\";
        analyseText+="r_{xy}=\\frac{n\\cdot\\sum_{i=1}^{n}(x_{i}\\cdot y_{i})-\\sum_{i=1}^{n}x_{i}\\cdot \\sum_{i=1}^{n}y_{i}}" +
                "{\\sqrt{(n\\cdot\\sum_{i=1}^{n}x_{i}^{2}-(\\sum_{i=1}^{n}x_{i})^{2})\\cdot (n\\cdot\\sum_{i=1}^{n}y_{i}^{2}" +
                "-(\\sum_{i=1}^{n}y_{i})^{2})}}\\\\";
        analyseText+="\\frac{"+dataList.size()+"\\cdot"+sumXY+"-"+sumX+"\\cdot"+sumY+"}" +
                "{\\sqrt{("+dataList.size()+"\\cdot "+sumX2+"-("+sumX+"))^{2}"+
                "("+dataList.size()+"\\cdot "+sumY2+"-("+sumY+"))^{2}"+"}}="+correlation+"\\\\";
        if(Math.abs(correlation)>=0.99&&Math.abs(correlation)<=1)
        analyseText+="As\\ correlation\\ factor\\in [0.99,1] \\ we\\ may\\ consider\\ it\\ as\\ excellent\\ correlation.\\\\";
        if(Math.abs(correlation)>=0.95&&Math.abs(correlation)<0.99)
            analyseText+="As\\ correlation\\ factor\\in [0.95,0.99) \\ we\\ may\\ consider\\ it\\ as\\ good\\ correlation.\\\\";
        if(Math.abs(correlation)>=0.90&&Math.abs(correlation)<0.95)
            analyseText+="As\\ correlation\\ factor\\in [0.90,0.95) \\ we\\ may\\ consider\\ it\\ as\\ satisfactory\\ correlation.\\\\";
        if(Math.abs(correlation)<0.90)
            analyseText+="As\\ correlation\\ factor\\in [0.95,0.99) \\ we\\ may\\ consider\\ it\\ as\\ bad\\\\" +
                    "correlation.\\\\";
        analyseText+="5.\\ Let\'s\\ calculate\\ determination\\ factor\\\\";
        analyseText+="R^{2}=r_{xy}^{2}="+determination;
        analyseText+="6.\\ Mean\\ value\\ of\\ the\\ approximation\\ mistake\\\\";
        analyseText+="\\overline {A}=\\frac{1}{n}\\cdot\\sum_{i=1}^{n}\\left | \\frac{y_{i}-\\widehat{y_{i}}}{y_{i}} \\right |\\cdot 100\\\\";
        analyseText+="Thus\\";
        analyseText+="\\overline {A}=\\frac{"+new BigDecimal(sumA).setScale(4,BigDecimal.ROUND_DOWN).doubleValue()+"}{"+dataList.size()+"}\\cdot 100 ="+approximation*100+"\\\\";
        analyseText+="And\\ the\\ value\\ of\\ the\\ mean\\ square\\ deviation\\ is\\\\";
        analyseText+="\\varepsilon ^{2}="+deviation+"\\\\";
        analyseText+="By\\ analogy\\ analysis\\ of\\ any\\ regression\\ model\\ can\\ be\\ done.\\\\";
        analyseText+="Linear\\ regression\\ model\\\\";
        analyseText+="Correlation\\ factor\\ "+linAnalyse.getCorrelation()+"\\\\";
        analyseText+="Approximation\\ factor\\ "+linAnalyse.getApproximation()+"\\\\";
        analyseText+="Mean\\ square\\ deviation\\ value\\ "+linAnalyse.getDeviation()+"\\\\";
        analyseText+="Determination\\ factor\\ "+linAnalyse.getDetermination()+"\\\\";
        analyseText+="Student\'s\\ factor\\ of\\ parameter\\ A\\ "+linAnalyse.getStudentA()+"\\\\";
        analyseText+="Student\'s\\ factor\\ of\\ parameter\\ B\\ "+linAnalyse.getStudentB()+"\\\\";
        analyseText+="Student\'s\\ factor\\ of\\ correlation\\ "+linAnalyse.getStudentR()+"\\\\";
        analyseText+="Fisher\'s\\ factor\\ "+linAnalyse.getFisher()+"\\\\";
        analyseText+="Exponential\\ regression\\ model\\\\";
        analyseText+="Correlation\\ factor\\ "+expAnalyse.getCorrelation()+"\\\\";
        analyseText+="Approximation\\ factor\\ "+expAnalyse.getApproximation()+"\\\\";
        analyseText+="Mean\\ square\\ deviation\\ value\\ "+expAnalyse.getDeviation()+"\\\\";
        analyseText+="Determination\\ factor\\ "+expAnalyse.getDetermination()+"\\\\";
        analyseText+="Student\'s\\ factor\\ of\\ parameter\\ A\\ "+expAnalyse.getStudentA()+"\\\\";
        analyseText+="Student\'s\\ factor\\ of\\ parameter\\ B\\ "+expAnalyse.getStudentB()+"\\\\";
        analyseText+="Student\'s\\ factor\\ of\\ correlation\\ "+expAnalyse.getStudentR()+"\\\\";
        analyseText+="Fisher\'s\\ factor\\ "+expAnalyse.getFisher()+"\\\\";
        analyseText+="Inverse\\ ratio\\ regression\\ model\\\\";
        analyseText+="Correlation\\ factor\\ "+invAnalyse.getCorrelation()+"\\\\";
        analyseText+="Approximation\\ factor\\ "+invAnalyse.getApproximation()+"\\\\";
        analyseText+="Mean\\ square\\ deviation\\ value\\ "+invAnalyse.getDeviation()+"\\\\";
        analyseText+="Determination\\ factor\\ "+invAnalyse.getDetermination()+"\\\\";
        analyseText+="Student\'s\\ factor\\ of\\ parameter\\ A\\ "+invAnalyse.getStudentA()+"\\\\";
        analyseText+="Student\'s\\ factor\\ of\\ parameter\\ B\\ "+invAnalyse.getStudentB()+"\\\\";
        analyseText+="Student\'s\\ factor\\ of\\ correlation\\ "+invAnalyse.getStudentR()+"\\\\";
        analyseText+="Fisher\'s\\ factor\\ "+invAnalyse.getFisher()+"\\\\";
        analyseText+="Loggarithmic\\ regression\\ model\\\\";
        analyseText+="Correlation\\ factor\\ "+logAnalyse.getCorrelation()+"\\\\";
        analyseText+="Approximation\\ factor\\ "+logAnalyse.getApproximation()+"\\\\";
        analyseText+="Mean\\ square\\ deviation\\ value\\ "+logAnalyse.getDeviation()+"\\\\";
        analyseText+="Determination\\ factor\\ "+logAnalyse.getDetermination()+"\\\\";
        analyseText+="Student\'s\\ factor\\ of\\ parameter\\ A\\ "+logAnalyse.getStudentA()+"\\\\";
        analyseText+="Student\'s\\ factor\\ of\\ parameter\\ B\\ "+logAnalyse.getStudentB()+"\\\\";
        analyseText+="Student\'s\\ factor\\ of\\ correlation\\ "+logAnalyse.getStudentR()+"\\\\";
        analyseText+="Fisher\'s\\ factor\\ "+logAnalyse.getFisher()+"\\\\";
        analyseText+="Quadratic\\ regression\\ model\\\\";
        analyseText+="Correlation\\ factor\\ "+quadAnalyse.getCorrelation()+"\\\\";
        analyseText+="Approximation\\ factor\\ "+quadAnalyse.getApproximation()+"\\\\";
        analyseText+="Mean\\ square\\ deviation\\ value\\ "+quadAnalyse.getDeviation()+"\\\\";
        analyseText+="Determination\\ factor\\ "+quadAnalyse.getDetermination()+"\\\\";

        return analyseText;
    }

    public TeXIcon createAnalyseIcon(String analyseText) {
        if(analyseText.equals("")){
            formula = new TeXFormula(true,"No analyse");
        }else{
            formula = new TeXFormula(true, analyseText);
        }
        icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 15);
        icon.setInsets(new Insets(0, 0, 0, 0));
        icon.setIconHeight(100,TeXConstants.ALIGN_CENTER);
        icon.setIconWidth(600,TeXConstants.ALIGN_LEFT);
        return icon;
    }

    public JLabel createSolutionLabel() {
        analyseText = createAnalyseText();
        icon = createAnalyseIcon(analyseText);
        image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0,0,icon.getIconWidth(),icon.getIconHeight());
        analyseLabel = new JLabel("Analyse",icon,JLabel.CENTER);
        analyseLabel.setForeground(new Color(0, 0, 0));
        icon.paintIcon(analyseLabel, g2, 0, 0);

        return analyseLabel;
    }

    public BufferedImage getImage() {
        return image;
    }
}
