package maow.hacknetconsole4j.computer;

public class Port {
    private final int portNumber;
    private final String portName;
    private boolean isOpen;

    public Port(int portNumber, String portName) {
        this.portNumber = portNumber;
        this.portName = portName;
    }

    public int getPortNumber() {
        return portNumber;
    }
    public String getPortName() {
        return portName;
    }
    public boolean isOpen() {
        return isOpen;
    }
    public void setOpen(boolean open) {
        isOpen = open;
    }
}
