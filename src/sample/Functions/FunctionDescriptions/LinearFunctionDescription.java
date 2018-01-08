package sample.Functions.FunctionDescriptions;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LinearFunctionDescription implements Description{

    private String description;
    private TeXIcon icon;
    private TeXFormula formula;
    private JLabel functionLabel;
    private static LinearFunctionDescription linearFunctionDescription;

    public LinearFunctionDescription() {
        System.out.println("Create Lin description");
    }

    public static LinearFunctionDescription getInstance() {
        if(linearFunctionDescription==null){
            linearFunctionDescription = new LinearFunctionDescription();
        }
        return linearFunctionDescription;
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
        description+="for\\i\\ =\\ 1,2,...,n\\\\";
        description+="For\\ a\\ linear\\ fit,\\\\";
        description+="f\\left(a\\right,b)=a+b\\cdot x\\\\";

        description+="\\frac{\\partial (e^{2})}{\\partial a_{i}}=0\\\\";
        description+="\\begin{bmatrix}a_{11}&a_{12}&\\cdots&a_{1n}\\\\a_{21}&a_{22}&\\cdots&a_{2n}\\\\\\cdots&\\cdots&\\cdots&\\cdots\\\\";*/
        description = "\\begin{array}{l}";
        description+="The\\ sum\\ of\\ the\\ minimal\\ squares\\ of\\\\" ;
        description+="distances\\ between\\ the\\ points\\ of\\ data\\\\";
        description+="and\\ the\\ points\\ of\\ the\\ graph\\ is\\ equal\\ to:\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ e^{2}=\\sum_{i=1}^{n}(y_{i}-f(x_{i},a_{1},a_{2},\\cdots\\ a_{k}))^{2}\\\\";
        description+="where\\ a_{1},a_{2},\\cdots\\ a_{k}\\ are\\ parameters\\\\";
        description+="The\\ condition\\ for\\ R^{2}\\ to\\ be\\ a\\ minimum\\ is\\ that:\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\frac{\\partial (e^{2})}{\\partial a_{i}}=0\\\\";
        description+="for\\ i\\ =\\ 1,2,...,n.\\\\";
        description+="For\\ a\\ linear\\ fit,\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ f(a,b)=a+b\\ x\\\\";
        description+="so\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ R^{2}(a,b)\\equiv\\sum_{i=1}^{n} [y_{i}-(a+b\\ x_{i})]^{2}\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\frac{\\partial (R^{2})}{\\partial a} = -2\\sum_{i=1}^{n}[y_{i}-(a+b\\ x_{i})]=0\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\frac{\\partial (R^{2})}{\\partial b} = -2\\sum_{i=1}^{n}[y_{i}-(a+b\\ x_{i})]x_{i}=0\\\\";
        description+="These\\ lead\\ to\\ the\\ equations\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ n\\ a + b\\sum_{i=1}^{n}x_{i}=\\sum_{i=1}^{n}y_{i}\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ a\\sum_{i=1}^{n}x_{i}+b\\sum_{i=1}^{n}x_{i}^{2}=\\sum_{i=1}^{n}x_{i}\\ y_{i}\\\\";
        description+="In\\ matrix\\ form,\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                "n & \\sum_{i=1}^{n}x_{i}\\\\ \n" +
                "\\sum_{i=1}^{n}x_{i} & \\sum_{i=1}^{n}x_{i}^{2}\n" +
                "\\end{bmatrix}\n" +
                "\\begin{bmatrix}\n" +
                "a \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ b\n" +
                "\\end{bmatrix}=\n" +
                "\\begin{bmatrix}\n" +
                "\\sum_{n=1}^{n}y_{i}\\\\\n" +
                "\\sum_{n=1}^{n}x_{i}\\ y_{i}\n" +
                "\\end{bmatrix}\\\\";
        description+="The\\ 2x2\\ matrix\\ inverse\\ is\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                "a \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ b\n" +
                "\\end{bmatrix} =\n" +
                "\\frac{1}{n\\sum_{i=1}^{n} x_{i}^2 -(\\sum_{i=1}^{n}x_{i})^{2}}\n" +
                "\\begin{bmatrix}\n" +
                "\\sum_{i=1}^{n}y_{i}\\sum_{i=1}^{n} x_{i}^{2}-\\sum_{i=1}^{n}x_{i}\\sum_{i=1}^{n}x_{i}\\ y_{i}\\\\\n" +
                "n\\sum_{i=1}^{n}x_{i}\\ y{i}-\\sum_{i=1}^{n}x_{i}\\sum_{i=1}^{n}y_{i} \n" +
                "\\end{bmatrix}\\\\";
        description+="so\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ a=\\frac{\\sum_{i=1}^{n}y_{i}\\sum_{i=1}^{n}x_{i}^{2}-\n" +
                "\\sum_{i=1}^{n}x_{i}\\sum_{i=1}^{n}x_{i}\\ y_{i}}{n\\sum_{i=1}^{n}x_{i}^{2}-\n" +
                "(\\sum_{i=1}^{n}x_{i})^{2}}=\\\\" +
                "\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\frac{\\bar{y}(\\sum_{i=1}^{n}x_{i}^{2})-\n" +
                "\\bar{x}\\sum_{i=1}^{n}x_{i}\\ y_{i}}{\\sum_{i=1}^{n}x_{i}^{2}-n\\ \\bar{x}^{2}}\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ b=\\frac{n\\sum_{i=1}^{n}x_{i}\\ y_{i}-\\sum_{i=1}^{n}x_{i}\\sum_{i=1}^{n}y_{i}}\n" +
                "{n\\sum_{i=1}^{n}x_{i}^{2}-(\\sum_{i=1}^{n}x_{i})^{2}}=\\\\" +
                "\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\frac{(\\sum_{i=1}^{n}x_{i}\\ y_{i})-n\\ \\bar{x}\\ \\bar{y}}\n" +
                "{\\sum_{i=1}^{n} x_{i}^{2}-n\\bar{x}^2}\\\\";
        description+="These\\ can\\ be\\ rewritten\\ in\\ a\\ simpler\\\\" +
                " form\\ by\\ defining\\ the\\ sums\\ of\\ squares\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ ss_{x}\\ _{x}=\\sum_{i=1}^{n}(x_{i}-\\bar{x})^{2}=\n" +
                "(\\sum_{i=1}^{n}x_{i}^{2})-n\\ \\bar{x}^{2}\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ ss_{y}\\ _{y}=\\sum_{i=1}^{n}(y_{i}-\\bar{y})^{2}=\n" +
                "(\\sum_{i=1}^{n}y_{i}^{2})-n\\ \\bar{y}^{2}\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ ss_{x}\\ _{y}=\\sum_{i=1}^{n}(x_{i}-\\bar{x})(y_{i}-\\bar{y})=\n" +
                "(\\sum_{i=1}^{n}x_{i}\\ y_{i})-n\\ \\bar{x}\\ \\bar{y}\\\\";
        description+="which\\ are\\ also\\ written\\ as\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\sigma_{x}^{2}=\\frac{ss_{x}\\ _{x}}{n}\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\sigma_{y}^{2}=\\frac{ss_{y}\\ _{y}}{n}\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ cov(x,y)=\\frac{ss_{x}\\ _{y}}{n}\\\\";
        description+="Here,\\ cov(x,y)\\ is\\ the\\ covariance\\ and\\\\" +
                "\\sigma_{x}^{2}\\ and\\ \\sigma_{y}^{2}\\ are\\ variances.\\\\";
        description+="In\\ terms\\ of\\ the\\ sums\\ of\\ squares,\\\\" +
                "the\\ regression\\ coefficient\\ b\\ is\\ given\\ by\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ b=\\frac{cov(x,y)}{\\sigma_{x}^{2}}=\\frac{ss_{x}\\ _{y}}{ss_{x}\\ _{x}}\\\\";
        description+="and\\ a\\ is\\ given\\ in\\ terms\\ of\\ b\\ using(\\diamond ) as \\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ a=\\bar{y}-b\\ \\bar{x}.\\\\";
        description+="The\\ overall\\ quality\\ of\\ the\\ fit\\ is\\\\" +
                " then\\ parameterized\\ in\\ terms\\\\" +
                " of\\ a\\ quantity\\ known\\ as\\ the\\ correlation\\\\" +
                " coefficient,\\ defined\\ by\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ r^{2}=\\frac{ss^{2}_{x}\\ _{y}}{ss_{x}\\ _{x}\\ ss_{y}\\ _{y}}\\\\";
        description+="which\\ gives\\ the\\ proportion\\ of\\ ss_{y}\\ _{y}\\\\" +
                " which\\ is\\ accounted\\ for\\ by\\ the\\ regression.\\\\";
        description+="Let\\ \\hat{y}_{i} \\ be\\ the\\ vertical\\ coordinate\\ of\\\\" +
                " the\\ best-fit\\ line\\ with\\ x-coordinate\\ x_{i},\\ so\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\hat{y}\\equiv a+b\\ x_{i},\\\\";
        description+="then\\ the\\ error\\ between\\ the\\ actual\\\\" +
                " vertical\\ point\\ y_i\\ and\\ the\\ fitted\\ point\\ is\\ given\\ by\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ e_{i}\\equiv y_{i}-\\hat{y}_{i}.\\\\";
        description+="Now\\ define\\ s^{2}\\ as\\ an\\ estimator\\ for\\ the\\ variance\\ in\\  e_{i},\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ s^{2}=\\sum_{i=1}^{n}\\frac{e_{i}^{2}}{n-2}\\\\";
        description+="Then\\ s\\  can\\ be\\ given\\ by\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ s=\\sqrt{\\frac{ss_{y}\\ _{y}-b\\ ss_{x}\\ _{y}}{n-2}}\n" +
                "=\\sqrt{\\frac{ss_{y}\\ _{y}-\\frac{ss^{2}_{x}\\ _{x}}{ss_{x}\\ _{x}}}{n-2}}\\\\";
        description+="The\\ standart\\ errors\\ for\\ a\\ b\\ are\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ SE(a)=s\\sqrt{\\frac{1}{n}+\\frac{\\bar{x}^{2}}{ss_{x}\\ _{x}}}\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ SE(b)=\\frac{s}{\\sqrt{ss_{x}\\ _{x}}}\\\\";
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