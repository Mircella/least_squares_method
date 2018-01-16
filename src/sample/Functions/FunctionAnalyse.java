package sample.Functions;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import sample.Calculator.Analyse;
import sample.Calculator.Answer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FunctionAnalyse {

    private Analyse linAnalyse,logAnalyse,expAnalyse,quadAnalyse,invAnalyse,polAnalyse;
    private Function function;
    private Answer answer;
    private String analyseText;
    private TeXIcon icon;
    private TeXFormula formula;
    private JLabel analyseLabel;
    private BufferedImage image;

    public FunctionAnalyse(Analyse...analyses) {
        System.out.println("Analyse");
        this.linAnalyse = analyses[0];
        this.logAnalyse = analyses[1];
        this.expAnalyse = analyses[2];
        this.quadAnalyse = analyses[3];
        this.invAnalyse = analyses[4];
       // this.polAnalyse = analyses[5];
    }

    private String createAnalyseText(){
        StringBuilder sb = new StringBuilder();

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
        analyseText+="Analyse\\ of\\ linear\\ function\\\\";
        /*analyseText+=linAnalyse.toString()+"\\\\";
        analyseText+="Analyse 2\\\\";
        analyseText+=logAnalyse.toString()+"\\\\";
        analyseText+="Analyse 3\\\\";
        analyseText+=expAnalyse.toString()+"\\\\";
        analyseText+="Analyse 4\\\\";
        analyseText+=quadAnalyse.toString()+"\\\\";
        analyseText+="Analyse 5\\\\";
        analyseText+=invAnalyse.toString()+"\\\\";*/
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
