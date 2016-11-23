package main.graphics;

import java.awt.*;

public class WideChoice extends Choice{

    public WideChoice() {
        super();
        setForeground(StandardColor.TEXT_COLOR);
        setFont(new Font("Dialog", Font.PLAIN, 24));
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
