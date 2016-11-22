package main.graphics;

import javax.swing.*;
import java.awt.*;

public class WideButton extends JButton {

    private boolean selected = false;

    public WideButton(String label) {
        super(label);
        super.setContentAreaFilled(false);
        super.setBorderPainted(false);
        setForeground(StandardColor.TEXT_COLOR);
        setFocusPainted(false);
        setFont(new Font("Dialog", Font.PLAIN, 24));
        setIconTextGap(20);
    }

    public void setSelected(boolean value) {
        selected = value;
    }

    public Dimension getPreferredSize() {
        Dimension parentDimension = getParent().getSize();
        return new Dimension((int)(parentDimension.getWidth() - parentDimension.getWidth() * 0.1), 64);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getModel().isRollover())
            setForeground(StandardColor.TEXT_LIGHT_COLOR);
        else
            setForeground(StandardColor.TEXT_COLOR);

        if(selected) {
            setForeground(StandardColor.TEXT_LIGHT_COLOR);
            g.setColor(StandardColor.ACCENT_COLOR);
            g.fillRect(0, 0, (int) (getWidth() * 0.05), getHeight());
        }
    }

}
