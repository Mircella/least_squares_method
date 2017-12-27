package sample.Functions.FunctionDescriptions;

import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;

public interface Description {
    String createDiscription();
    TeXIcon createIcon();
    JLabel createLabel();
}
