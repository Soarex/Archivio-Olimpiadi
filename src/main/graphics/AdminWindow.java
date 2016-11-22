package main.graphics;

import javax.swing.*;
import java.awt.*;

public class AdminWindow extends JFrame{
    private SideMenu sideMenu;
    private Container contentPane;

    public AdminWindow() {
        super("Admin window");
        setSize(1280, 720);
        setResizable(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        contentPane = getContentPane();
        sideMenu = new SideMenu();

        add(sideMenu, BorderLayout.WEST);
        add(new AthleteContext(), BorderLayout.CENTER);
    }

    public void updateContext() {
        contentPane.removeAll();
        add(sideMenu, BorderLayout.WEST);
        add(sideMenu.getSelectedContext(), BorderLayout.CENTER);
        validate();
    }

}
