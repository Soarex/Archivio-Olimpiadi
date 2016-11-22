package main.graphics;

import javax.swing.*;
import java.awt.*;

public class WideLabel extends JLabel{

    public WideLabel(String label) {
        super(label);
        setForeground(StandardColor.TEXT_LIGHT_COLOR);
        setFont(new Font("Dialog", Font.PLAIN, 24));
        setIconTextGap(20);
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
