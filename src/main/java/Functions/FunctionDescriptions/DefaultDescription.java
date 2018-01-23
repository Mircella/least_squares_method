package Functions.FunctionDescriptions;

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
            description+="\\\\";
            description+="\\\\";
            description+="\\\\";
            description+="\\\\";
            description+="Transpose\\ of\\ a\\ Matrix\\\\";
            description+="A\\ matrix\\ which\\ is\\ formed\\ by\\ turning\\ all\\ the\\ rows\\ of\\\\" +
                    " a\\ given\\ matrix\\ into\\ columns\\ and\\ vice-versa.\\\\" +
                    " The\\ transpose\\ of\\ matrix\\ A\\ is\\ written\\ A^T.\\\\";
            description+="Example \\\\";
            description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                    "1 & 2 & 3\\\\ \n" +
                    "4 & 5 & 6\n" +
                    "\\end{bmatrix}^T=\n" +
                    "\\begin{bmatrix}\n" +
                    "1 & 4\\\\ \n" +
                    "2 & 5\\\\ \n" +
                    "3 & 6\n" +
                    "\\end{bmatrix}\n\\\\";
            description+="If\\ r\\ is\\ a\\ scalar\\ element\\ and\\ A\\ and\\ B\\ represent\\ matrices:\\\\";
            description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ (A^T)^T=A\\\\";
            description+="shows\\ that\\ the\\ transpose\\ of\\ a\\ transpose\\ matrix\\ is\\ the\\ original\\ matrix.\\\\";
            description+="\\\\";
            description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ (A+B)^T=A^T+B^T\\\\";
            description+="shows\\ that\\ the\\ transpose\\ of\\ two\\ added\\ matrices\\ is\\\\" +
                    " the\\ same\\ as\\ the\\ addition\\ of\\ the\\ two\\ transpose\\ matrices\\\\";
            description+="\\\\";
            description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ (rA)^T=rA^T\\\\";
            description+="shows\\ that\\ when\\ a\\ scalar\\ element\\ is\\ multiplied\\ to\\ a\\\\" +
                    " matrix,\\ the\\ order\\ of\\ transposition\\ is\\ irrelevant";
            description+="\\\\";
            description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ (AB)^T=B^TA^T\\\\";
            description+="shows\\ that\\ the\\ transpose\\ of\\ a\\ product\\ of\\ matrices\\ equals\\\\" +
                    " the\\ product\\ of\\ their\\ transposes\\ in\\ reverse\\ order";
            description+="\\\\";
            description+="\\\\";
            description+="\\\\";
            description+="\\\\";
            description+="Scalar\\ multiplication\\\\";
            description+="To\\ multiply\\ a\\ matrix\\ by\\ a\\ single\\ number\\ is\\\\";
            description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ 2\\ x\\begin{bmatrix}\n" +
                    "4 & 0\\\\ \n" +
                    "1 & -9\n" +
                    "\\end{bmatrix} = \n" +
                    "\\begin{bmatrix}\n" +
                    "8 & 0\\\\ \n" +
                    "2 & -18\n" +
                    "\\end{bmatrix}\\\\";
            description+="These\\ are\\ the\\ calculations:\\\\";
            description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ 2×4=8;\\ 2×0=0;\\ 2×1=2\\ 2×-9=-18\\\\";
            description+="We\\ call\\ the\\ number\\ ( 2\\ in\\ this\\ case)\\ a\\ scalar\\\\" +
                    " so\\ this\\ is\\ called\\ scalar\\ multiplication.\\\\";
            description+="\\\\";
            description+="\\\\";
            description+="\\\\";
            description+="\\\\";
            description+="Matrix\\ inverse.\\\\";
            description+="For\\ a\\ square\\ matrix\\ A,\\ the\\ inverse\\ is\\ written\\ A^{-1}\\\\";
            description+="Not\\ all\\ square\\ matrices\\ have\\ inverses.\\\\";
            description+="A\\ square\\ matrix\\ which\\ has\\ an\\ inverse\\ is\\ called\\ invertible\\ or\\ nonsingular\\\\";
            description+="and\\ a\\ square\\ matrix\\ without\\ an\\ inverse\\ is\\ called\\ noninvertible\\ or\\ singular.\\\\";
            description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ AA^{-1}=A^{-1}A=I\\\\";
            description+="Example:\\ For\\ matrix\\\\" +
                    "\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ A=\\begin{bmatrix}\n" +
                    "4 & 3 \\\\ \n" +
                    "3 & 2\n" +
                    "\\end{bmatrix}\n" +
                    "its\\ inverse\\ is\\\\" +
                    "\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ A^{-1}=\\begin{bmatrix}\n" +
                    "-2 & 3\\\\ \n" +
                    "3 & -4\n" +
                    "\\end{bmatrix}\n" +
                    "since\\\\";
            description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ AA^{-1}=\\begin{bmatrix}\n" +
                    "4 & 3\\\\ \n" +
                    "3 & 2\n" +
                    "\\end{bmatrix}\n" +
                    "\\begin{bmatrix}\n" +
                    "-2 & 3\\\\ \n" +
                    "3 & -4\n" +
                    "\\end{bmatrix}=\n" +
                    "\\begin{bmatrix}\n" +
                    "1 & 0\\\\ \n" +
                    "0 & 1\n" +
                    "\\end{bmatrix}\\\\";
            description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ A^{-1}A=\\begin{bmatrix}\n" +
                    "-2 & 3\\\\ \n" +
                    "3 & -4\n" +
                    "\\end{bmatrix}\n" +
                    "\\begin{bmatrix}\n" +
                    "4 & 3\\\\ \n" +
                    "3 & 2\n" +
                    "\\end{bmatrix}=\n" +
                    "\\begin{bmatrix}\n" +
                    "1 & 0\\\\ \n" +
                    "0 & 1\n" +
                    "\\end{bmatrix}\\\\";
            description+="Here\\ are\\ two\\ ways\\ to\\ find\\ the\\ inverse\\ of\\ a\\ matrix:\\\\";
            description+="1. Shortcut\\ for\\ 2x2\\ matrices\\\\";
            description+="For\\\\" +
                    "\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ A=\\begin{bmatrix}\n" +
                    "a & b\\\\ \n" +
                    "c & d\n" +
                    "\\end{bmatrix}\\ the\\ inverse\\ can\\ be\\ found\\ using\\ this\\ formula:\\\\";
            description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ A^{-1}=\\frac{1}{detA}\\begin{bmatrix}\n" +
                    "d & -b\\\\ \n" +
                    "-c & a\n" +
                    "\\end{bmatrix}=\\frac{1}{ad-bc}\\begin{bmatrix}\n" +
                    "d & -b\\\\ \n" +
                    "-c & a\n" +
                    "\\end{bmatrix}\\\\";
            description+="Example:\\\\" +
                    "\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                    "1 & 2\\\\ \n" +
                    "3 & 4\n" +
                    "\\end{bmatrix}^{-1}=\n" +
                    "\\frac{1}{-2}\n" +
                    "\\begin{bmatrix}\n" +
                    "4 & -2\\\\ \n" +
                    "-3 & 1\n" +
                    "\\end{bmatrix}=\n" +
                    "\\begin{bmatrix}\n" +
                    "-2 & 1\\\\ \n" +
                    "3/2 & -1/2\n" +
                    "\\end{bmatrix}\\\\";
            description+="2.Adjoint\\ method\\\\";
            description+="A^{-1}=\\frac{1}{detA}(adjoint\\ of\\ A)\\ or\\ A^{-1}=\\frac{1}{detA}(cofactor\\ matrix\\ of\\ A)^T\\\\";
            description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ A = \\begin{bmatrix}\n" +
                    "1 & 2 & 3\\\\ \n" +
                    "0 & 4 & 5\\\\ \n" +
                    "1 & 0 & 6\n" +
                    "\\end{bmatrix}\\\\";
            description+="The\\ cofactor\\ matrix\\ for\\ A\\ is\\\\" +
                    "\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                    "24 & 5 & -4\\\\ \n" +
                    "-12 & 3 & 2\\\\ \n" +
                    "-2 & -5 & 4\n" +
                    "\\end{bmatrix}\\\\";
            description+="so\\ the\\ adjoint\\ is\\" +
                    "\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\begin{bmatrix}\n" +
                    "24 & -12 & -2\\\\ \n" +
                    "5 & 3 & -5\\\\ \n" +
                    "-4 & 2 & 4\n" +
                    "\\end{bmatrix}\n" +
                    "Since\\ detA=22,\\ we\\ get\\\\";
            description+="\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ A^{-1}=\\frac{1}{22}\\begin{bmatrix}\n" +
                    "24 & -12 & -2\\\\ \n" +
                    "5 & 3 & -5\\\\ \n" +
                    "-4 & 2 & 4 \n" +
                    "\\end{bmatrix}\n" +
                    "\\begin{bmatrix}\n" +
                    "12/11 & -6/11 & -1/11\\\\ \n" +
                    "5/22 & 3/22 & -5/22\\\\ \n" +
                    "-2/11 & 1/11 & 2/11\n" +
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

