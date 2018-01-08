package sample.Functions.FunctionDescriptions;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PolynomDescription implements Description {
    private String description;
    private TeXIcon icon;
    private TeXFormula formula;
    private JLabel functionLabel;
    private static PolynomDescription polynomDescription;

    private PolynomDescription() {
        System.out.println("Create Pol descr");
    }

    public static PolynomDescription getInstance() {
        if(polynomDescription==null){
            polynomDescription = new PolynomDescription();
        }
        return polynomDescription;
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
        description+="Generalizing\\ from\\ a\\ straight\\ line\\\\" +
                " (i.e.,\\ first\\ degree\\ polynomial)\\ to\\ a\\ k-th\\ degree\\ polynomial\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ y=a_0+a_1\\ x+ ... +a_k\\ x^k,\\\\";
        description+="the\\ residual\\ is\\ given\\ by\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ R^2=\\sum_{i=1}^{n}[y_i-(a_0+a_1\\ x+ ... +a_k\\ x^k)]^2.\\\\";
        description+="The\\ partial\\ derivatives\\ (again\\ dropping\\ superscripts)\\ are\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\frac{\\partial (R^2)}{\\partial a_0}=-2\\sum_{i=1}^{n}[y_i-(a_0+a_1\\ x+ ... +a_k\\ x^k)]=0\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\frac{\\partial (R^2)}{\\partial a_1}=-2\\sum_{i=1}^{n}[y_i-(a_0+a_1\\ x+ ... +a_k\\ x^k)]x=0\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\frac{\\partial (R^2)}{\\partial a_k}=-2\\sum_{i=1}^{n}[y_i-(a_0+a_1\\ x+ ... +a_k\\ x^k)]x^k=0\\\\";
        description+="These\\ lead\\ to\\ the\\ equations\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ a_0\\ n+a_1\\sum_{i=1}^{n}x_i+...+a_k\\sum_{i=1}^{n}x_i^k=\\sum_{i=1}^{n}y_i\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ a_0\\sum_{i=1}^{n}x_i+a_1\\sum_{i=1}^{n}x_i^2+...+a_k\\sum_{i=1}^{n}x_i^{k+1}=\\sum_{i=1}^{n}x_i\\ y_i\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ a_0\\sum_{i=1}^{n}x_i^k+a_1\\sum_{i=1}^{n}x_i^{k+1}+...+a_k\\sum_{i=1}^{n}x_i^{2k}=\\sum_{i=1}^{n}x_i^k\\ y_i\\\\";
        description+="or,\\ in\\ matrix\\ form\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                "n & \\sum_{i=1}^{n}x_i & .... & \\sum_{i=1}^{n}x_i^k\\\\ \n" +
                "\\sum_{i=1}^{n}x_i  & \\sum_{i=1}^{n}x_i ^2 & ... & \\sum_{i=1}^{n}x_i^{k+1} \\\\ \n" +
                "... & ... & ... & ...\\\\ \n" +
                "\\sum_{i=1}^{n}x_i^k & \\sum_{i=1}^{n}x_i^{k+1} &  ... & \\sum_{i=1}^{n}x_i^{2k}\n" +
                "\\end{bmatrix}\n" +
                "\\begin{bmatrix}\n" +
                "a_0\\\\\\\\\\\\\\\\\\\\\\\\ a_1\n" +
                "\\\\\\\\\\\\\\\\\\\\\\\\ ... \n" +
                "\\\\\\\\\\\\\\\\\\\\\\\\ a_k\n" +
                "\\end{bmatrix}\n" +
                "=\\begin{bmatrix}\n" +
                "\\sum_{i=1}^{n}y_i\\\\ \n" +
                "\\sum_{i=1}^{n}x_i\\ y_i\\\\ \n" +
                "...\\\\ \n" +
                "\\sum_{i=1}^{n}x_i^k\\ y_i\n" +
                "\n" +
                "\\end{bmatrix}\\\\";
        description+="This\\ is\\ a\\ Vandermonde\\ matrix.\\ We\\ can\\ also\\ obtain\\\\" +
                " the\\ matrix\\ for\\ a\\ least\\ squares\\ fit\\ by\\ writing\\\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                "1 & x_1 & ... & x_1^k\\\\ \n" +
                "1 & x_2 & ... & x_2^k\\\\ \n" +
                "... & ... & ... & ...\\\\ \n" +
                "1 & x_n & ... & x_n^k \n" +
                "\\end{bmatrix}\n" +
                "\\begin{bmatrix}\n" +
                "a_0\\\\\\\\\\ \n" +
                "a_1\\\\\\\\\\ \n" +
                "...\\\\\\\\\\ \n" +
                "a_k\n" +
                "\\end{bmatrix}\n" +
                "=\n" +
                "\\begin{bmatrix}\n" +
                "y_1\\\\\\\\\\ \n" +
                "y_2\\\\\\\\\\ \n" +
                "...\\\\\\\\\\ \n" +
                "y_n\n" +
                "\\end{bmatrix}\\\\";
        description+="Premultiplying\\ both\\ sides\\ by\\ the transpose\\\\" +
                " of\\ the\\ first\\ matrix\\ then\\ gives\n\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                "1 & 1 & ... & 1 \\\\ \n" +
                "x_1 & x_2 & ... & x_n\\\\ \n" +
                "... & ... & ... & ...\\\\ \n" +
                "x_1^k & x_2^k & ... & x_n^k\n" +
                "\\end{bmatrix}\n" +
                "\\begin{bmatrix}\n" +
                "1 & x_1 & ... & x_1^k\\\\ \n" +
                "1 & x_2 & ... & x_2^k\\\\ \n" +
                "... & ... & ... & ...\\\\ \n" +
                "1 & x_n & ... & x_n^k\n" +
                "\\end{bmatrix}\n" +
                "\\begin{bmatrix}\n" +
                "a_0\\\\\\\\\\ \n" +
                "a_1\\\\\\\\\\ \n" +
                "...\\\\\\\\\\ \n" +
                "a_k\n" +
                "\\end{bmatrix}\\\\";
        description+="=";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                "1 & 1 & ... & 1 \\\\ \n" +
                "x_1 & x_2 & ... & x_n\\\\ \n" +
                "... & ... & ... & ...\\\\ \n" +
                "x_1^k & x_2^k & ... & x_n^k\n" +
                "\\end{bmatrix}\n" +
                "\\begin{bmatrix}\n" +
                "y_1\\\\\\\\ \n" +
                "y_2\\\\\\\\ \n" +
                "...\\\\\\\\ \n" +
                "y_n \n" +
                "\\end{bmatrix}\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                "n & \\sum_{i=1}^{n}x_i & ... & \\sum_{i=1}^{n}x_i^k\\\\ \n" +
                "\\sum_{i=1}^{n}x_i & \\sum_{i=1}^{n}x_i^2 & ... & \\sum_{i=1}^{n}x_i^{k+1} \\\\ \n" +
                "... & ... & ... & ...\\\\ \n" +
                "\\sum_{i=1}^{n}x_i^k & \\sum_{i=1}^{n}x_i^{k+1} & ... & \\sum_{i=1}^{n}x_i^{2k}\n" +
                "\\end{bmatrix}\n" +
                "\\begin{bmatrix}\n" +
                "a_0\\\\\\\\\\\\\\\\\\\\\\\\\\ \n" +
                "a_1\\\\\\\\\\\\\\\\\\\\\\\\\\ \n" +
                "...\\\\\\\\\\\\\\\\\\\\\\\\\\ \n" +
                "a_k\n" +
                "\\end{bmatrix}=\n" +
                "\\begin{bmatrix}\n" +
                "\\sum_{i=1}^{n}y_i\\\\ \n" +
                "\\sum_{i=1}^{n}x_i\\ y_i\\\\ \n" +
                "...\\\\ \n" +
                "\\sum_{i=1}^{n}x_i^k\\ y_i\n" +
                "\\end{bmatrix}\\\\";
        description+="As\\ before,\\ given\\ n\\ points\\ (x_i,y_i)\\ and\\ fitting\\\\" +
                " with\\ polynomial\\ coefficients\\ a_0,\\ ...,\\ a_k\\ gives\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                "y_1\\\\\\\\\\ \n" +
                "y_2\\\\\\\\\\ \n" +
                "...\\\\\\\\\\ \n" +
                "y_n\n" +
                "\\end{bmatrix}\n" +
                "=\n" +
                "\\begin{bmatrix}\n" +
                "1 & x_1 & x_1^2 & ... & x_1^k\\\\ \n" +
                "1 & x_2  & x_2^2 & ... & x_2^k\\\\ \n" +
                "... & ... & ... & ... & ...\\\\ \n" +
                "1 & x_n & x_n^2 & ... & x_n^k \n" +
                "\\end{bmatrix}\n" +
                "\\begin{bmatrix}\n" +
                "a_0\\\\\\\\\\ \n" +
                "a_1\\\\\\\\\\ \n" +
                "...\\\\\\\\\\ \n" +
                "a_k\n" +
                "\\end{bmatrix}\\\\";
        description+="In\\ matrix\\ notation,\\ the\\ equation\\\\" +
                " for\\ a\\ polynomial\\ fit\\ is\\ given\\ by\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ y=X\\ a\\\\";
        description+="This\\ can\\ be\\ solved\\ by\\ premultiplying\\\\" +
                " by\\ the\\ transpose\\ X^T,\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ X^T\\ y=X^TX\\ a.\\\\";
        description+="This\\ matrix\\ equation\\ can\\ be\\ solved\\ numerically,\\\\" +
                " or\\ can\\ be\\ inverted\\ directly\\ if\\ it\\ is\\ well\\ formed,\\\\" +
                " to\\ yield\\ the\\ solution\\ vector\\\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ a=(X^TX)^{-1}X^Ty.\\\\";
        description+="Setting\\ k=1\\ in\\ the\\ above\\ equations\\\\" +
                " reproduces\\ the\\ linear\\ solution.\\\\";
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


