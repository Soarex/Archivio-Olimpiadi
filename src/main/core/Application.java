package main.core;

import main.archive.*;
import main.graphics.AdminWindow;
import main.graphics.AthleteCardContext;
import main.graphics.CompetitionCardContext;

import javax.swing.*;
import java.util.Date;

public class Application {
    private static AdminWindow window;
    private static AthleteArchive athleteArchive;
    private static CompetitionArchive competitionArchive;
    private static short id = 0;

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

    public static void changeContext(JPanel context) {
        window.updateContext(context);
    }

    public static void showAthlete(Athlete a) {
        window.updateContext(new AthleteCardContext(a));
    }

    public static void showCompetition(Competition c) {
        window.updateContext(new CompetitionCardContext(c));
    }

    public static void addAthlete(Athlete a) {
        try {
            athleteArchive.write(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteAthlete(short id) {
        try {
            athleteArchive.delete(id);
            competitionArchive.deletePerAthlete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addCompetition(Competition c) {
        try {
            c.id = id;
            competitionArchive.write(c);
            id++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteCompetition(short id) {
        try {
            competitionArchive.delete(id);
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

    public static Competition[] getCompetitions(Discipline d) {
        try {
            return competitionArchive.search(d);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Competition[] getCompetitions(int l, int r) {
        try {
            return competitionArchive.get(l, r);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
