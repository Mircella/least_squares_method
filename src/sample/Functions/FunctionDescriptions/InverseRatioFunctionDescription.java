package sample.Functions.FunctionDescriptions;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class InverseRatioFunctionDescription implements Description {
    private String description;
    private TeXIcon icon;
    private TeXFormula formula;
    private JLabel functionLabel;
    private static InverseRatioFunctionDescription inverseRatioFunctionDescription;

    private InverseRatioFunctionDescription() {
        System.out.println("Create Inverse description");
    }

    public static InverseRatioFunctionDescription getInstance() {
        if(inverseRatioFunctionDescription==null){
            inverseRatioFunctionDescription = new InverseRatioFunctionDescription();
        }
        return inverseRatioFunctionDescription;
    }

    public JLabel getFunctionLabel() {
        if(functionLabel==null||icon==null||description.equals("")){
            System.out.println("Smth does not exist");
        }
        return functionLabel;
    }

    @Override
    public String createDiscription() {
        /*description = "\\begin{array}{l}";
        description+="The\\ sum\\ of\\ the\\ minimal\\ squares\\ of\\\\" +
                " distances\\ between\\ the\\ points\\ of\\ data\\\\" +
                " and\\ the\\ points\\ of\\ the\\ graph\\ is\\ equal\\ to:\\\\";
        description+="\\ e^{2}=\\sum_{i=1}^{n}(y_{i}-f(x_{i},a_{1},a_{2},\\cdots\\ a_{k}))^{2}\\\\";
        description+="\\ where\\ a_{1},a_{2},\\cdots\\ a_{k}\\ are\\ parameters\\\\";
        description+="The\\ condition\\ for\\ R^{2}\\ to\\ be\\ a\\ minimum\\ is\\ that:\\\\";
        description+="\\frac{\\partial (e^{2})}{\\partial a_{i}}=0\\\\";*/
        description="IRFD";

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
