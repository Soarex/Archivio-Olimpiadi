package main.core;

import main.archive.*;
import main.graphics.AdminWindow;

import java.util.Date;

public class Application {
    private static AdminWindow window;
    private static AthleteArchive athleteArchive;
    private static CompetitionArchive competitionArchive;

    public static void init() {
        try {
            athleteArchive = new AthleteArchive(10000);
            competitionArchive = new CompetitionArchive(10000);

            window = new AdminWindow();
            window.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateWindow() {
        window.updateContext();
    }

    public static void addAthlete(Athlete a) {
        try {
            athleteArchive.write(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Athlete[] getAthletes(String name) {
        try {
            return athleteArchive.search(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Athlete[] getAthletes(int l, int r) {
        try {
            return athleteArchive.get(l, r);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Athlete getAthlete(short id) {
        try {
            return athleteArchive.read(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Competition[] getCompetitions(short athleteId) {
        try {
            return competitionArchive.search(athleteId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
