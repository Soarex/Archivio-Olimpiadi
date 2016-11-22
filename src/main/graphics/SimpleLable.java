package main.graphics;

import javax.swing.*;
import java.awt.*;

public class SimpleLable extends JLabel{

        public SimpleLable(String label) {
            super(label);
            setForeground(StandardColor.TEXT_COLOR);
            setFont(new Font("Dialog", Font.PLAIN, 16));
            setIconTextGap(20);
        }


        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }

}
