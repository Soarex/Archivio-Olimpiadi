package main.graphics;

import main.archive.Athlete;
import main.archive.Competition;
import main.core.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class CompetitionCardContext extends JPanel {
    public CompetitionCardContext(Competition c) {
        setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        JPanel center = new JPanel();
        JPanel bottom = new JPanel();

        top.setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);
        center.setBackground(StandardColor.BACKGROUND_LIGHT_COLOR);
        bottom.setBackground(StandardColor.BACKGROUND_DARK_COLOR);

        Athlete a = Application.getAthlete(c.athleteId);

        JLabel label = new JLabel(a.name + " " + c.discipline.getName());
        label.setFont(new Font("Dialog", Font.BOLD, 60));
        label.setForeground(StandardColor.TEXT_LIGHT_COLOR);
        top.add(label);

        SimpleButton removeButton = new SimpleButton("");
        removeButton.setIcon(new ImageIcon("res/system/error.png"));

        removeButton.addActionListener((ActionEvent e) -> {
            int res = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare la gara?", "Confirm",  JOptionPane.YES_NO_OPTION);
            if(res == JOptionPane.YES_OPTION) {
                Application.deleteCompetition(c.id);
                Application.changeContext(new CompetitionContext());
            }
        });
        bottom.add(removeButton);

        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);
    }

}
