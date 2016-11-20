package main.core;

import main.archive.AthleteArchive;
import main.graphics.AdminWindow;

public class Application {
    private static AdminWindow window;
    private static AthleteArchive archive;

    public static void init() {
        try {
            archive = new AthleteArchive(10000);
            window = new AdminWindow();
            window.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateWindow() {
        window.updateContext();
    }
}
