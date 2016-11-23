package main.graphics;

import main.archive.Athlete;
import main.archive.Competition;
import main.archive.Discipline;
import main.core.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CompetitionContext extends JPanel {
    private Competition[] current;
    private int l = 0, r = l + 10;

    public CompetitionContext() {
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

        JLabel label = new JLabel("Gare");
        label.setFont(new Font("Dialog", Font.BOLD, 60));
        label.setForeground(StandardColor.TEXT_LIGHT_COLOR);
        top.add(label);


        /*
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
        */

        WideChoice searchBox = new WideChoice();
        Discipline[] arr = Discipline.values();

        for(Discipline d : arr)
            if(d != Discipline.NULL)
               searchBox.add(d.getName());

        search.add(searchBox);

        SimpleButton button = new SimpleButton("");
        button.setHorizontalTextPosition(JLabel.LEFT);
        button.setIcon(new ImageIcon("res/system/search.png"));
        button.addActionListener((ActionEvent e) -> {
                result.removeAll();

                current = Application.getCompetitions(arr[searchBox.getSelectedIndex() + 1]);

                if(current != null) {
                    if(current.length == 0) result.add(new WideLabel("Nessuna corrispondenza trovata"));

                    l = 0; r = l + 10;

                    for(int i = l; i < r; i++)
                        if(i < current.length)
                            result.add(getCompetitionButton(current[i]));
                }
                repaint();
                revalidate();
        });
        search.add(button);
        center.add(search, BorderLayout.NORTH);
        center.add(result, BorderLayout.CENTER);

        current = Application.getCompetitions(0, 100);

        if(current != null)
            for(Competition a : current)
                result.add(getCompetitionButton(a));

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
                    result.add(getCompetitionButton(current[i]));

            repaint();
            revalidate();
        });

        bottom.add(leftButton);

        SimpleButton addButton = new SimpleButton("");
        addButton.setIcon(new ImageIcon("res/system/plus.png"));

        addButton.addActionListener((ActionEvent e) -> {
            JFrame frame = new AddCompetitionWindow();
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
                    result.add(getCompetitionButton(current[i]));

            repaint();
            revalidate();

        });

        bottom.add(rightButton);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private WideButton getCompetitionButton(Competition c) {
        Athlete a = Application.getAthlete(c.athleteId);
        WideButton temp = new WideButton("<html><font color = '#555555'>Disciplina: </font>" + c.discipline.getName() +
                "<font color = '#555555'>  Punteggio: </font>" + c.score +
                "<font color = '#555555'>  Atleta: </font>" + a.name + " " + a.surname + "</html>"
        );
        temp.setIcon(new ImageIcon("res/system/center-alignment.png"));
        return temp;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
