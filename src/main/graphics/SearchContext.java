package main.graphics;

import javax.swing.*;
import java.awt.*;

public class SearchContext extends JPanel {
    public SearchContext() {
        super();
        setBackground(new Color(0x282828));
        JLabel label = new JLabel("Cerca");
        label.setFont(new Font("Dialog", Font.BOLD, 60));
        label.setForeground(new Color(0xeeeeee));
        add(label);
    }
}
