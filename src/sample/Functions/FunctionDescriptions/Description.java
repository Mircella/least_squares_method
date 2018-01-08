package sample.Functions.FunctionDescriptions;

import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;

public interface Description {
    String createDiscription();
    TeXIcon createIcon(String description);
    JLabel createLabel(TeXIcon icon);
    JLabel getFunctionLabel();
    String getDescription();
    void setDescription(String description);
    TeXIcon getIcon();
    void setIcon(TeXIcon icon);
    TeXFormula getFormula();
    void setFormula(TeXFormula formula);
    void setFunctionLabel(JLabel functionLabel);
}
