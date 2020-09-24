package maow.hacknetconsole4j.computer.security;

public class Proxy {
    private final int speed;
    private boolean isBypassed;

    public Proxy(int speed) {
        this.speed = speed;
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
