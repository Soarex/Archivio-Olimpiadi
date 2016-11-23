package main.graphics;

import main.archive.Athlete;
import main.archive.Competition;
import main.archive.Discipline;
import main.archive.Nation;
import main.core.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class AddCompetitionWindow extends JFrame {

    public AddCompetitionWindow() {
        super("Add Athlete");
        setSize(500, 200);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 50, 10));

        panel.setBackground(StandardColor.BACKGROUND_DARK_COLOR);
        panel.setFont(new Font("Dialog", Font.PLAIN, 20));

        Choice disciplineField = new Choice();
        TextField score = new TextField(10);
        Choice athleteField = new Choice();
        SimpleButton addButton = new SimpleButton("Add");

        Discipline[] arr = Discipline.values();
        for (Discipline d : arr)
            if (d != Discipline.NULL) disciplineField.add(d.getName());

        Athlete[] arr2 = Application.getAthletes(0, 1000);
        for (Athlete d : arr2)
            athleteField.add(d.name + " " + d.surname + " " + d.id);

        addButton.addActionListener((ActionEvent e) -> {
            String temp = athleteField.getSelectedItem();
            String[] temp1 = temp.split(" ");

            Application.addCompetition(new Competition(Discipline.get(disciplineField.getSelectedIndex()), Short.parseShort(temp1[2]), Float.parseFloat(score.getText())));
            JOptionPane.showMessageDialog(null, "Operazione eseguita", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });

        panel.add(new SimpleLable("Disciplina: "));
        panel.add(disciplineField);

        panel.add(new SimpleLable("Punteggio: "));
        panel.add(score);

        panel.add(new SimpleLable("Atleta: "));
        panel.add(athleteField);

        panel.add(new SimpleLable("Aggiungi: "));
        panel.add(addButton);

        add(panel);

        setVisible(true);

    }
}
