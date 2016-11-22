package main.graphics;

import main.core.Application;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class SideMenu extends JPanel {
    private JPanel currentContext = new AthleteContext();

    public SideMenu() {
        super();
        setBackground(StandardColor.BACKGROUND_DARK_COLOR);

        WideButton button = new WideButton("Atleti");
        WideButton button2 = new WideButton("Gare");

        button.setSelected(true);
        button.setIcon(new ImageIcon("res/system/users.png"));
        button.addActionListener((ActionEvent e) -> {
            button.setSelected(true);
            button2.setSelected(false);
            currentContext = new AthleteContext();
            Application.updateWindow();
        });
        add(button);

        button2.setIcon(new ImageIcon("res/system/document.png"));
        button2.addActionListener((ActionEvent e) -> {
            button2.setSelected(true);
            button.setSelected(false);
            currentContext = new CompetitionContext();
            Application.updateWindow();
        });
        add(button2);
    }

    public JPanel getSelectedContext() {
        return currentContext;
    }

    public Dimension getPreferredSize() {
        Dimension parentDimension = getParent().getSize();
        return new Dimension(200, (int)(parentDimension.getHeight()));
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
