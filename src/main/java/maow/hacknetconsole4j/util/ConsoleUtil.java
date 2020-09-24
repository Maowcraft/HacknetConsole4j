package maow.hacknetconsole4j.util;

public class ConsoleUtil {
    public static void print(String toPrint) {
        System.out.println(toPrint);
    }

    public static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
