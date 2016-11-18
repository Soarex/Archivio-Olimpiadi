package main;

public class Main {
    static AdminWindow a;

    public static void main(String[] args) {
        //a = new AdminWindow();
        //a.validate();
        try {
            AthleteArchive a = new AthleteArchive(10000);
            a.write(new Athlete("c", "i", Nation.USA, 0));
            a.write(new Athlete("a", "i", Nation.USA, 1));
            a.write(new Athlete("z", "i", Nation.USA, 2));
            a.write(new Athlete("j", "i", Nation.USA, 3));
            a.write(new Athlete("h", "i", Nation.USA, 4));
            a.write(new Athlete("c", "i", Nation.USA, 5));
            a.write(new Athlete("i", "i", Nation.USA, 6));

            Athlete[] arr = a.search("c");
            for(Athlete a1 : arr)
                System.out.println(a1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateWindow() {
        a.updateContext();
    }
}
