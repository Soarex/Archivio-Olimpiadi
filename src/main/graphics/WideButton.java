package main.graphics;

import javax.swing.*;
import java.awt.*;

public class WideButton extends JButton {
    public static final Color DEFAULT_TEXT_COLOR = new Color(0x999999);
    public static final Color HOVER_TEXT_COLOR = new Color(0xeeeeee);

    private boolean selected = false;

    public WideButton(String label) {
        super(label);
        super.setContentAreaFilled(false);
        super.setBorderPainted(false);
        setForeground(DEFAULT_TEXT_COLOR);
        setFocusPainted(false);
        setFont(new Font("Dialog", Font.PLAIN, 24));
        setIconTextGap(20);
    }

    public void setSelected(boolean value) {
        selected = value;
    }

    public Dimension getPreferredSize() {
        Dimension parentDimension = getParent().getSize();
        return new Dimension((int)(parentDimension.getWidth() - parentDimension.getWidth() * 0.1), 50);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getModel().isRollover())
            setForeground(HOVER_TEXT_COLOR);
        else
            setForeground(DEFAULT_TEXT_COLOR);

        if(selected) {
            setForeground(HOVER_TEXT_COLOR);
            g.setColor(new Color(0x0048FF));
            g.fillRect(0, 0, (int) (getWidth() * 0.05), getHeight());
        }
    }

}
