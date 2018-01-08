package sample.Functions.FunctionDescriptions;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DefaultDescription implements Description{
    private String description;
    private TeXIcon icon;
    private TeXFormula formula;
    private JLabel functionLabel;
    private static DefaultDescription defaultDescription;

    private DefaultDescription() {
        System.out.println("Create default descr");
    }

    public static DefaultDescription getInstance() {
        if(defaultDescription==null){
            defaultDescription = new DefaultDescription();
        }
        return defaultDescription;
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
        description+="Matrix Addition\\\\";
        description+="Denote\\ the\\ sum\\ of\\ two\\ matrices\\ A\\ and\\ B\\\\" +
                " (of\\ the\\ same\\ dimensions)\\ by\\ C=A+B.\\ The\\ sum\\ is\\\\" +
                " defined\\ by\\ adding\\ entries\\ with\\ the\\ same\\ indices\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ c_i\\ _j\\equiv a_i\\ _j+b_i\\ _j\\\\";
        description+="over\\ all\\ i\\ and\\ j.\\ For\\ example,\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                "a_{11} & a_{12}\\\\ \n" +
                "a_{21} & a_{22}\n" +
                "\\end{bmatrix}\n" +
                "\\begin{bmatrix}\n" +
                "b_{11} & b_{12}\\\\ \n" +
                "b_{21} & b_{22}\n" +
                "\\end{bmatrix}\n" +
                "=\n" +
                "\\begin{bmatrix}\n" +
                "a_{11}+b_{11} & a_{12}+b_{12}\\\\ \n" +
                "a_{21}+b_{21} & a_{22}+b_{22}\n" +
                "\\end{bmatrix}\\\\";
        description+="Matrix\\ addition\\ is\\ therefore\\ both\\ commutative\\ and\\ associative.\\\\";
        description+="\\\\";
        description+="\\\\";
        description+="\\\\";
        description+="\\\\";
        description+="Matrix\\ Multiplication\\\\";
        description+="The\\ product\\ C\\ of\\ two\\ matrices\\ A\\ and\\ B\\ is\\ defined\\ as\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ c_i\\ _k=a_i\\ _j\\ b_j\\ _k\\\\";
        description+="where\\ j\\ is\\ summed\\ over\\ for\\ all\\ possible\\ values\\ of\\ i\\ and\\ k\\\\"+
                " Therefore,\\ in\\ order\\ for\\ matrix\\ multiplication\\ to\\ be\\ defined,\\\\" +
                " the\\ dimensions\\ of\\ the\\ matrices\\ must\\ satisfy\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                "c_{11} & c_{12}\\\\ \n" +
                "c_{21} & c_{22}\n" +
                "\\end{bmatrix}= \n" +
                "\\begin{bmatrix}\n" +
                "a_{11} & a_{12}\\\\ \n" +
                "a_{21} & a_{22}\n" +
                "\\end{bmatrix}\n" +
                "\\begin{bmatrix}\n" +
                "b_{11}  & b_{12} \\\\ \n" +
                "b_{21}  & b_{22} \n" +
                "\\end{bmatrix}\\\\" ;
        description+="where\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ c_{11}=a_{11}b_{11}+a_{12}b_{21}\\\\" +
                "\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ c_{12}=a_{11}b_{12}+a_{12}b_{22}\\\\" +
                "\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ c_{21}=a_{21}b_{11}+a_{12}b_{21}\\\\" +
                "\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ c_{22}=a_{21}b_{12}+a_{22}b_{22}\\\\";
        description+="Matrix\\ multiplication\\ is\\ associative\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ [a\\ b\\ c]_i\\ _j=a_i\\ _l\\ b_l\\ _k\\ c_k\\ _j\\\\";
        description+="Matrix\\ multiplication\\ is\\ also\\ distributive.\\\\" +
                " If\\ A\\ and\\ B\\ are\\ m×n\\ matrices\\ and\\ C\\ and\\ D\\ are\\ n×p\\ matrices,\\ then\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ A(C+D)=AC+AD\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ (A+B)C=AC+BC\\\\";
        description+="\\\\";
        description+="\\\\";
        description+="\\\\";
        description+="\\\\";
        description+="Determinant\\\\";
        description+=" A\\ nonhomogeneous\\ system\\ of\\ linear\\ equations\\\\" +
                " has\\ a\\ unique\\ solution\\ iff\\ the\\ determinant\\ of\\ the\\\\" +
                " system's\\ matrix\\ is\\ nonzero\\ (i.e.,\\ the\\ matrix\\ is\\ nonsingular)\\\\";
        description+="For\\ example:\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ a_{1x}+a_{2y}+a_{3z}=0\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ b_{1x}+b_{2y}+b_{3z}=0\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ c_{1x}+c_{2y}+c_{3z}=0\\\\";
        description+="gives\\ the\\ expression\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ a_1b_2c_3-a_1b_3c_2 + a_2b_3c_1-a_2b_1c_3 + a_3b_1c_2-a_3b_2c_1 = 0, \\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ a_1x+a_2y+a_3z=0\\\\";
        description+="which\\ is\\ called\\ the\\ determinant\\ for\\ this\\ system\\ of\\ equation.\\\\" +
                " Determinants\\ are\\ defined\\ only\\ for\\ square\\ matrices.\\\\";
        description+="If\\ the\\ determinant\\ of\\ a\\ matrix\\ is\\ 0,\\ the\\ matrix\\ is\\ said\\ to\\ be\\ singular,\\\\" +
                " and\\ if\\ the\\ determinant\\ is\\ 1,\\ the\\ matrix\\ is\\ said\\ to\\ be\\ unimodular.\\\\";
        description+="A\\ 2×2\\ determinant\\ is\\ defined\\ to\\ be\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ det\\begin{bmatrix}\n" +
                "a & b\\\\ \n" +
                "c & d\n" +
                "\\end{bmatrix}=\n" +
                "\\begin{vmatrix}\n" +
                "a & b\\\\ \n" +
                "c & d\n" +
                "\\end{vmatrix}=\n" +
                "ad-bc\\\\";
        description+="A\\ general\\ determinant\\ for\\ a\\ matrix\\ A\\ has\\ a\\ value\\\\";
        description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{vmatrix}\n" +
                "A\n" +
                "\\end{vmatrix}\n" +
                "= \\sum_{i=1}^{n}a_{ij}C_{ij}\\\\";
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

