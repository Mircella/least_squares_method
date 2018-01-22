package main.java.Functions.FunctionsSolutions;

import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.image.BufferedImage;

public interface Solution {
    TeXIcon createSolutionIcon();
    JLabel createSolutionLabel();
    public BufferedImage getImage();
}

