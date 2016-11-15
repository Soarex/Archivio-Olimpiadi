package main;

public class Main {
    static AdminWindow a;

    public static void main(String[] args) {
        a = new AdminWindow();
        a.validate();
    }

    public static void updateWindow() {
        a.updateContext();
    }
}
