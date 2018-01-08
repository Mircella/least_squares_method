package sample.Functions.FunctionDescriptions;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class QuadraticFunctionDescription implements Description{
    private String description;
    private TeXIcon icon;
    private TeXFormula formula;
    private JLabel functionLabel;
    private static QuadraticFunctionDescription quadraticFunctionDescription;

    private QuadraticFunctionDescription() {
        System.out.println("Create Quad description");
    }

    public static QuadraticFunctionDescription getInstance() {
        if(quadraticFunctionDescription==null){
            quadraticFunctionDescription = new QuadraticFunctionDescription();
        }
        return quadraticFunctionDescription;
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
        description+="Quadratic:\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ y=a+b+c\\ x^2\n\\\\";
        description+="Minimizing\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ F(a,b,c)=\\sum_{i=1}^{n}(a+b\\ x_{i}+c\\ x_{i}^{2}-y_{i})^2\\\\";
        description+="requires\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\frac{\\partial F}{\\partial a}=\\frac{\\partial F}{\\partial b}=\\frac{\\partial F}{\\partial c}=0\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\frac{\\partial F}{\\partial a}=2\\sum_{i=1}^{n}(a+b\\ x_{i}+c\\ x_{i}^2-y_{i})= 0\\ leads\\ to\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\sum_{i=1}^{n}y_{i}=n\\ a+(\\sum_{i=1}^{n}x_{i})b+(\\sum_{i=1}^{n}x_{i}^{2})c\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\frac{\\partial F}{\\partial b}=2\\sum_{i=1}^{n}(a+b\\ x_{i}+c\\ x_{i}^2-y_i)x_i= 0\\ leads\\ to\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\sum_{i=1}^{n}y_i\\ x_i=a(\\sum_{i=1}^{n}x_i)+b(\\sum_{i=1}^{n}x_i^2)+c(\\sum_{i=1}^{n}x_i^2)\\\\";
        description+="Then\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                "n & \\sum_{i=1}^{n}x_i & \\sum_{i=1}^{n}x_i^2\\\\ \n" +
                "\\sum_{i=1}^{n}x_i & \\sum_{i=1}^{n}x_i^2 & \\sum_{i=1}^{n}x_i^3\\\\ \n" +
                "\\sum_{i=1}^{n}x_i^2 & \\sum_{i=1}^{n}x_i^3 & \\sum_{i=1}^{n}x_i^4\n" +
                "\\end{bmatrix}\n" +
                "\\begin{bmatrix}\n" +
                "a\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ \n" +
                "b\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ \n" +
                "c\n" +
                "\\end{bmatrix}\n" +
                "=\n" +
                "\\begin{bmatrix}\n" +
                "\\sum_{i=1}^{n}y_i\\\\ \n" +
                "\\sum_{i=1}^{n}x_i\\ y_i\\\\\n" +
                "\\sum_{i=1}^{n}x_i^2\\ y_i \n" +
                "\\end{bmatrix}\\\\";

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
        g2.fillRect(50,50,icon.getIconWidth(),icon.getIconHeight());
        if(icon==null){
            System.out.println("no icon");
        }
        functionLabel = new JLabel("Formulas",icon,JLabel.CENTER);
        functionLabel.setForeground(new Color(0, 0, 0));
        icon.paintIcon(functionLabel, g2, 10, 10);

        return functionLabel;
    }

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

