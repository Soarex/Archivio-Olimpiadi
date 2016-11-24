package main.graphics;

import main.archive.Athlete;
import main.archive.Competition;
import main.core.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AthleteCardContext extends JPanel{
    private Competition[] current;
    private int l = 0, r = l + 10;

    public AthleteCardContext(Athlete a) {
        setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        JPanel center = new JPanel();
        JPanel bottom = new JPanel();
        JPanel result = new JPanel();

        top.setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);
        center.setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);
        bottom.setBackground(StandardColor.BACKGROUND_DARK_COLOR);
        result.setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);

        JLabel label = new JLabel(a.name + " " + a.surname);
        label.setFont(new Font("Dialog", Font.BOLD, 60));
        label.setForeground(StandardColor.TEXT_LIGHT_COLOR);
        top.add(label);

        current = Application.getCompetitions(a.id);


        if(current != null) {
            if(current.length == 0) result.add(new WideLabel("Nessuna gara trovata"));

            l = 0; r = l + 10;

            for(int i = l; i < r; i++)
                if(i < current.length)
                    result.add(getCompetitionButton(current[i]));
        }
        //center.add(result);

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

        SimpleButton removeButton = new SimpleButton("");
        removeButton.setIcon(new ImageIcon("res/system/error.png"));

        removeButton.addActionListener((ActionEvent e) -> {
            int res = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare l'atleta?", "Confirm",  JOptionPane.YES_NO_OPTION);
            if(res == JOptionPane.YES_OPTION) Application.deleteAthlete(a.id);
            Application.changeContext(new AthleteContext());
        });

        bottom.add(removeButton);


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
        add(result, BorderLayout.CENTER);
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
}
