package maow.hacknetconsole4j.program;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockProgram implements Program {
    @Override
    public String getName() {
        return "Clock";
    }

    @Override
    public int getTimeRunning() {
        return 0;
    }

    @Override
    public boolean isBuiltIn() {
        return false;
    }

    @Override
    public void run(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }
}
