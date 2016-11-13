package main;

import javax.swing.*;
import java.awt.*;

public class AthleteContext extends JPanel {
    public AthleteContext() {
        setBackground(new Color(0x282828));

        JLabel label = new JLabel("Atleti");
        label.setFont(new Font("Dialog", Font.BOLD, 60));
        label.setForeground(new Color(0xeeeeee));
        add(label);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
