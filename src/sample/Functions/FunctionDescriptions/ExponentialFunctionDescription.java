package sample.Functions.FunctionDescriptions;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ExponentialFunctionDescription implements Description {
    private String description;
    private TeXIcon icon;
    private TeXFormula formula;
    private JLabel functionLabel;
    private static ExponentialFunctionDescription exponentialFunctionDescription;

    private ExponentialFunctionDescription() {
        System.out.println("Create Exp descr");
    }

    public static ExponentialFunctionDescription getInstance() {
        if(exponentialFunctionDescription==null){
            exponentialFunctionDescription = new ExponentialFunctionDescription();
        }
        return exponentialFunctionDescription;
    }

    public JLabel getFunctionLabel() {
        if(functionLabel==null||icon==null||description.equals("")){
            System.out.println("Smth does not exist");
        }
        return functionLabel;
    }

    @Override
    public String createDiscription() {
        description = "\\begin{array}{l}";
        description+="To\\ fit\\ functional\\ form\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ y =\\ A\\ e^{Bx}\\\\";
        description+="Take\\ the\\ logarithm\\ of\\ both\\ sides\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ ln\\ y=\\ ln\\ A + Bx\\\\";
        description+="The\\ best-fit\\ values\\ are\\ then\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ a=\\frac{\\sum_{i=1}^{n} ln\\ y_{i}\\sum_{i=1}^{n}x_{i}^{2} - \\sum_{i=1}^{n} x_{i}\\sum_{i=1}^{n} x_{i}\\ ln\\ y_{i} }\n" +
                "{n\\sum_{i=1}^{n} x_{i}^{2} - (\\sum_{i=1}^{n} x_{i})^{2}}\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ b=\\frac{n\\sum_{i=1}^{n}x_{i}\\ ln\\ y_{i} - \\sum_{i=1}^{n}x_{i}\\ \\sum_{i=1}^{n} ln\\ y_{i}}\n" +
                "{n\\sum_{i=1}^{n} x_{i}^{2} - (\\sum_{i=1}^{n} x_{i})^{2}}\\\\";
        description+="Where\\ B\\ = b\\ and\\ A\\ =exp(a)\\\\";
        description+="Now\\ minimize\\ the\\ function\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\sum_{i=1}^{n} y_{i}(ln\\ y_{i} - a - b\\ x_{i})^{2}.\\\\";
        description+="Applying\\ least\\ squares\\ fitting\\ gives\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ a\\sum_{i=1}^{n} y_{i} + b\\sum_{i=1}^{n} x_{i}\\ y_{i} = \\sum_{i=1}^{n} y_{i}\\ ln\\ y_{i}\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ a\\sum_{i=1}^{n} x_{i}\\ y_{i} + b\\sum_{i=1}^{n} x_{i}^{2}\\ y_{i} = \\sum_{i=1}^{n} x_{i}\\ y_{i}\\ ln\\ y_{i}\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                "\\sum_{i=1}^{n} y_{i} & \\sum_{i=1}^{n} x_{i}\\ y_{i}\\\\ \\sum_{i=1}^{n} x_{i}\\ y_{i}  \n" +
                " & \\sum_{i=1}^{n} x_{i}^{2}\\ y_{i}\n" +
                "\\end{bmatrix}\n" +
                "\\begin{bmatrix}\n" +
                "a \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ b \n" +
                "\\end{bmatrix}\n" +
                "=\\begin{bmatrix}\n" +
                "\\sum_{i=1}^{n} y_{i}\\ ln\\ y_{i}\\\\ \\sum_{i=1}^{n} x_{i}\\ y_{i}\\ ln\\ y_{i} \n" +
                "\n" +
                "\\end{bmatrix}\\\\";
        description+="Solving\\ for\\ a\\ and\\ b\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ a=\\frac{\\sum_{i=1}^{n} (x_{i}^{2}\\ y_{i})\\sum_{i=1}^{n}(y_{i} ln\\ y_{i})-\\sum_{i=1}^{n} (x_{i}\\ y_{i})\\sum_{i=1}^{n} (x_{i}\\ y_{i}\\ ln\\ y_{i})}\n" +
                "{\\sum_{i=1}^{n} y_{i}\\sum_{i=1}^{n} (x_{i}^{2}\\ y_{i})-(\\sum_{i=1}^{n} x_{i}\\ y_{i})^{2}}\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ b=\\frac{\\sum_{i=1}^{n} y_{i}\\ \\sum_{i=1}^{n} (x_{i}\\ y_{i}\\ ln\\ y_{i})-\\sum_{i=1}^{n} (x_{i}\\ y_{i})\\sum_{i=1}^{n} (y_{i}\\ ln\\ y_{i})}\n" +
                "{\\sum_{i=1}^{n} y_{y}\\ \\sum_{i=1}^{n} (x_{i}^{2}\\ y_{i})- (\\sum_{i=1}^{n} x_{i}\\ y_{i})^2 }\\\\";
        return description;
    }

    @Override
    public TeXIcon createIcon(String description) {
        if(description.equals("")){
            System.out.println("no descr");
            formula = new TeXFormula(true,"No formula");
        }else{
            formula = new TeXFormula(true, description);}
        icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 15);
        icon.setInsets(new Insets(0, 0, 0, 0));
        icon.setIconHeight(180,TeXConstants.ALIGN_CENTER);
        icon.setIconWidth(380,TeXConstants.ALIGN_LEFT);
        return icon;
    }

    @Override
    public JLabel createLabel(TeXIcon icon) {
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0,0,icon.getIconWidth(),icon.getIconHeight());
        if(icon==null){
            System.out.println("no icon");
        }
        functionLabel = new JLabel("Formulas",icon,JLabel.CENTER);
        functionLabel.setForeground(new Color(0, 0, 0));
        icon.paintIcon(functionLabel, g2, 10, 10);

        return functionLabel;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TeXIcon getIcon() {
        return icon;
    }

    public void setIcon(TeXIcon icon) {
        this.icon = icon;
    }

    public TeXFormula getFormula() {
        return formula;
    }

    public void setFormula(TeXFormula formula) {
        this.formula = formula;
    }

    public void setFunctionLabel(JLabel functionLabel) {
        this.functionLabel = functionLabel;
    }
}

