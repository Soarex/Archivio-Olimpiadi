package main.graphics;

import main.graphics.WideTextbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AthleteContext extends JPanel {
    public AthleteContext() {
        setBackground(new Color(0x282828));
        JLabel label = new JLabel("<html>Atleti    <br></html>");
        label.setFont(new Font("Dialog", Font.BOLD, 60));
        label.setForeground(new Color(0xeeeeee));
        add(label);

        WideTextbox searchBox = new WideTextbox();
        searchBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                searchBox.setSelected(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                searchBox.setSelected(false);
            }
        });
        add(searchBox);

        JLabel button = new JLabel("  ");
        button.setHorizontalTextPosition(JLabel.LEFT);
        button.setIcon(new ImageIcon("res/system/search.png"));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
        add(button);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
