package main.graphics;

import javax.swing.*;
import java.awt.*;

public class SimpleButton extends JButton {
    public static final Color DEFAULT_TEXT_COLOR = new Color(0x999999);
    public static final Color HOVER_TEXT_COLOR = new Color(0xeeeeee);

    public SimpleButton(String label) {
        super(label);
        super.setContentAreaFilled(false);
        super.setBorderPainted(true);
        setForeground(DEFAULT_TEXT_COLOR);
        setFocusPainted(false);
        setFont(new Font("Dialog", Font.PLAIN, 24));
        setIconTextGap(20);
    }

    public SimpleButton() {
        super();
        super.setContentAreaFilled(false);
        super.setBorderPainted(true);
        setForeground(DEFAULT_TEXT_COLOR);
        setFocusPainted(false);
        setFont(new Font("Dialog", Font.PLAIN, 24));
        setIconTextGap(20);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getModel().isRollover())
            setForeground(HOVER_TEXT_COLOR);
        else
            setForeground(DEFAULT_TEXT_COLOR);

    }
}
