package main.graphics;

import main.archive.Athlete;
import main.archive.Competition;
import main.core.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CompetitionContext extends JPanel {
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
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                result.removeAll();

                String text = searchBox.getText();
                text = text.replaceAll(" ", "");
                Competition[] arr = Application.getCompetitions(Short.parseShort(text));

                if(arr != null) {
                    if(arr.length == 0) result.add(new WideLabel("Nessuna corrispondenza trovata"));
                    for (Competition c : arr)
                        result.add(getCompetitionButton(c));

                }
                repaint();
                revalidate();
            }
        });
        search.add(button);
        center.add(search, BorderLayout.NORTH);
        center.add(result, BorderLayout.CENTER);

        SimpleButton leftButton = new SimpleButton("");
        leftButton.setIcon(new ImageIcon("res/system/left-arrow.png"));
        bottom.add(leftButton);

        SimpleButton addButton = new SimpleButton("");
        addButton.setIcon(new ImageIcon("res/system/plus.png"));
        bottom.add(addButton);


        SimpleButton rightButton = new SimpleButton("");
        rightButton.setIcon(new ImageIcon("res/system/right-arrow.png"));
        bottom.add(rightButton);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private WideButton getCompetitionButton(Competition c) {
        Athlete a = Application.getAthlete(c.athleteId);
        WideButton temp = new WideButton("<html><font color = '#0048FF'>Disciplina: </font>" + c.discipline +
                "<font color = '#0048FF'>  Punteggio: </font>" + c.score +
                "<font color = '#0048FF'>  Atleta: </font>" + a.name + " " + a.surname + "</html>"
        );
        temp.setIcon(new ImageIcon("res/system/center-alignment.png"));
        return temp;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
