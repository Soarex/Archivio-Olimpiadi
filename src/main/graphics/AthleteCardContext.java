package main.graphics;

import main.archive.Athlete;
import main.archive.Competition;
import main.core.Application;

import javax.swing.*;
import java.awt.*;

public class AthleteCardContext extends JPanel{
    private Competition[] current;
    private int l = 0, r = l + 10;

    public AthleteCardContext(Athlete a) {
        setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        JPanel center = new JPanel();
        JPanel result = new JPanel();

        top.setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);
        center.setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);
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

        add(top, BorderLayout.NORTH);
        add(result, BorderLayout.CENTER);
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
