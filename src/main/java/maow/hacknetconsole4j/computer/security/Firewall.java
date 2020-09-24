package maow.hacknetconsole4j.computer.security;

public class Firewall {
    private final String password;
    private final int speed;
    private boolean isBypassed;

    public Firewall(String password, int speed) {
        this.password = password;
        this.speed = speed;
    }

    public String getPassword() {
        return password;
    }
    public int getSpeed() {
        return speed;
    }
    public boolean isBypassed() {
        return isBypassed;
    }
    public void setBypassed(boolean bypassed) {
        isBypassed = bypassed;
    }
}
