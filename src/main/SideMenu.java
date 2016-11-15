package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class SideMenu extends JPanel {
    private JPanel currentContext = new AthleteContext();

    public SideMenu() {
        super();
        setBackground(new Color(0x181818));

        WideButton button = new WideButton("Atleti");
        WideButton button2 = new WideButton("Cerca");

        button.setSelected(true);
        button.setIcon(new ImageIcon("res/system/users.png"));
        button.addActionListener((ActionEvent e) -> {
            button.setSelected(true);
            button2.setSelected(false);
            currentContext = new AthleteContext();
            Main.updateWindow();
        });
        add(button);

        button2.setIcon(new ImageIcon("res/system/search.png"));
        button2.addActionListener((ActionEvent e) -> {
            button2.setSelected(true);
            button.setSelected(false);
            currentContext = new SearchContext();
            Main.updateWindow();
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
