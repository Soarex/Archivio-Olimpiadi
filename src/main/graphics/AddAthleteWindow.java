package main.graphics;

import main.archive.Athlete;
import main.archive.Nation;
import main.core.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class AddAthleteWindow extends JFrame {
    public AddAthleteWindow() {
        super("Add Athlete");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 50, 10));

        panel.setBackground(StandardColor.BACKGROUND_DARK_COLOR);
        panel.setFont(new Font("Dialog", Font.PLAIN, 20));

        TextField nameField = new TextField(25);
        TextField surnameField = new TextField(25);
        Choice nationField = new Choice();
        TextField dateField = new TextField(14);
        TextField idField = new TextField(3);
        SimpleButton addButton = new SimpleButton("Add");

        Nation[] arr = Nation.values();
        for(Nation n : arr)
            if(n != Nation.NULL)  nationField.add(n.getName());

        addButton.addActionListener((ActionEvent e) -> {
            try {
                LocalDate.parse(dateField.getText());
                if (!Application.athleteExists(Short.parseShort(idField.getText()))) {
                    Application.addAthlete(new Athlete(nameField.getText(), surnameField.getText(), Nation.get(nationField.getSelectedIndex()), LocalDate.parse(dateField.getText()), Integer.parseInt(idField.getText())));
                    JOptionPane.showMessageDialog(null, "Operazione eseguita", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                } else {
                    JOptionPane.showMessageDialog(null, "Pettorina già esistente", "Errore", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (DateTimeParseException er) {
                JOptionPane.showMessageDialog(null, "Formato data non corretto (aaaa-mm-gg)", "Errore", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panel.add(new SimpleLable("Nome: "));
        panel.add(nameField);

        panel.add(new SimpleLable("Cognome: "));
        panel.add(surnameField);

        panel.add(new SimpleLable("Data: "));
        panel.add(dateField);

        panel.add(new SimpleLable("Pettorina: "));
        panel.add(idField);

        panel.add(new SimpleLable("Nazione: "));
        panel.add(nationField);

        panel.add(new SimpleLable("Aggiungi: "));
        panel.add(addButton);

        add(panel);

        setVisible(true);

    }
}
