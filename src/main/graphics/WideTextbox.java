package main.graphics;

import javax.swing.*;
import java.awt.*;

public class WideTextbox extends JTextField{

    public WideTextbox() {
        super();
        setForeground(StandardColor.TEXT_COLOR);
        setFont(new Font("Dialog", Font.PLAIN, 24));
        setOpaque(false);
        setCaretColor(StandardColor.TEXT_COLOR);
    }

    public void setSelected(boolean b) {
        if(b) setForeground(StandardColor.TEXT_LIGHT_COLOR);
        else setForeground(StandardColor.TEXT_COLOR);
    }

    public Dimension getPreferredSize() {
        Dimension parentDimension = getParent().getSize();
        return new Dimension((int)(parentDimension.getWidth() - parentDimension.getWidth() * 0.2), 40);
    }

    public Dimension getMaximumSize() {
        Dimension parentDimension = getParent().getSize();
        return new Dimension((int)(parentDimension.getWidth() - parentDimension.getWidth() * 0.2), 40);
    }

    public Dimension getMinimumSize() {
        Dimension parentDimension = getParent().getSize();
        return new Dimension((int)(parentDimension.getWidth() - parentDimension.getWidth() * 0.2), 40);
    }

}
