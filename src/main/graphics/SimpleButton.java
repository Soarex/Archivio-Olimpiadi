package main.graphics;

import javax.swing.*;
import java.awt.*;

public class SimpleButton extends JButton {

    public SimpleButton(String label) {
        super(label);
        super.setContentAreaFilled(false);
        super.setBorderPainted(true);
        setForeground(StandardColor.TEXT_COLOR);
        setFocusPainted(false);
        setFont(new Font("Dialog", Font.PLAIN, 24));
        setIconTextGap(20);
    }

    public SimpleButton() {
        super();
        super.setContentAreaFilled(false);
        super.setBorderPainted(true);
        setForeground(StandardColor.TEXT_COLOR);
        setFocusPainted(false);
        setFont(new Font("Dialog", Font.PLAIN, 24));
        setIconTextGap(20);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getModel().isRollover())
            setForeground(StandardColor.TEXT_LIGHT_COLOR);
        else
            setForeground(StandardColor.TEXT_COLOR);

    }
}
