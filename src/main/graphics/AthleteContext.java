package main.graphics;

import main.archive.Athlete;
import main.archive.Nation;
import main.core.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AthleteContext extends JPanel {
    private int l= 0, r = l + 10;
    private Athlete[] current;

    public AthleteContext() {
        setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);
        setLayout(new BorderLayout());


        JPanel top = new JPanel();
        JPanel center = new JPanel();
        JPanel bottom = new JPanel();
        JPanel search = new JPanel();
        JPanel result = new JPanel();
        top.setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);
        center.setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);
        bottom.setBackground(StandardColor.BACKGROUND_DARK_COLOR);
        result.setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);
        search.setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);

        center.setLayout(new BorderLayout());

        JLabel label = new JLabel("Atleti");
        label.setFont(new Font("Dialog", Font.BOLD, 60));
        label.setForeground(StandardColor.TEXT_LIGHT_COLOR);
        top.add(label);

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
        search.add(searchBox);

        SimpleButton button = new SimpleButton("");
        button.setHorizontalTextPosition(JLabel.LEFT);
        button.setIcon(new ImageIcon("res/system/search.png"));
        button.addActionListener((ActionEvent e) -> {
                result.removeAll();

                String text = searchBox.getText();
                text = text.replaceAll(" ", "");
                current = Application.getAthletes(text);


                if(current != null) {
                    if(current.length == 0) result.add(new WideLabel("Nessuna corrispondenza trovata"));

                    l = 0; r = l + 10;

                    for(int i = l; i < r; i++)
                        if(i < current.length)
                            result.add(getAthleteButton(current[i]));
                }
                repaint();
                revalidate();
        });

        search.add(button);
        center.add(search, BorderLayout.NORTH);

        current = Application.getAthletes(0, 100);

        if(current != null)
            for(int i = l; i < r; i++)
                if(i < current.length)
                    result.add(getAthleteButton(current[i]));

        center.add(result, BorderLayout.CENTER);

        SimpleButton leftButton = new SimpleButton("");
        leftButton.setIcon(new ImageIcon("res/system/left-arrow.png"));

        leftButton.addActionListener((ActionEvent e) -> {
            result.removeAll();
            l -= 10;

            if(l < 0) l = 0;

            r = l + 10;

            for(int i = l; i < r; i++)
                if(i < current.length)
                    result.add(getAthleteButton(current[i]));

            repaint();
            revalidate();
        });

        bottom.add(leftButton);

        SimpleButton addButton = new SimpleButton("");
        addButton.setIcon(new ImageIcon("res/system/plus.png"));

        addButton.addActionListener((ActionEvent e) -> {
            JFrame frame = new AddAthleteWindow();
        });

        bottom.add(addButton);


        SimpleButton rightButton = new SimpleButton("");
        rightButton.setIcon(new ImageIcon("res/system/right-arrow.png"));

        rightButton.addActionListener((ActionEvent e) -> {
            result.removeAll();
            l += 10;
            r = l + 10;

            for(int i = l; i < r; i++)
                if(i < current.length)
                    result.add(getAthleteButton(current[i]));

            repaint();
            revalidate();

        });

        bottom.add(rightButton);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private WideButton getAthleteButton(Athlete a) {
        WideButton temp = new WideButton("<html><font color = '#555555'>Nome: </font>" + a.name +
                "<font color = '#555555'>  Cognome: </font>" + a.surname +
                "<font color = '#555555'>  Data di nascita: </font>" + a.getDate() +
                "<font color = '#555555'>  Nazione: </font>" + a.nation.getName() +
                "<font color = '#555555'>  Pettorina: </font>" + a.id +  "</html>"
        );
        temp.setIcon(new ImageIcon("res/system/user.png"));

        temp.addActionListener((ActionEvent e) -> {
            Application.showAthlete(a);
        });
        return temp;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
