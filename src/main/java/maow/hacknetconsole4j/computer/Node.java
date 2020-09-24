package maow.hacknetconsole4j.computer;

import maow.hacknetconsole4j.computer.account.Account;
import maow.hacknetconsole4j.computer.account.AccountType;
import maow.hacknetconsole4j.computer.filesystem.Filesystem;
import maow.hacknetconsole4j.computer.filesystem.Folder;
import maow.hacknetconsole4j.computer.security.Firewall;
import maow.hacknetconsole4j.computer.security.Proxy;

public class Node {
    private final String ipAddress;
    private final String name;
    private final Port[] ports;
    private final int portsToCrack;
    private final Account[] accounts;
    private final Filesystem filesystem;
    private final Firewall firewall;
    private final Proxy proxy;

    private Node(String ipAddress, String name, Port[] ports, int portsToCrack, Account[] accounts, Filesystem filesystem, Firewall firewall, Proxy proxy) {
        this.ipAddress = ipAddress;
        this.name = name;
        this.ports = ports;
        this.portsToCrack = portsToCrack;
        this.accounts = accounts;
        this.filesystem = filesystem;
        this.firewall = firewall;
        this.proxy = proxy;
    }

    private Account activeAccount;
    private Folder activeFolder;

    public void crack() {
        setActiveAccount(new Account("admin", "", AccountType.ADMIN));
    }

    public boolean isPortOpen(int portNumber) {
        for (Port port : ports) {
            if (port.getPortNumber() == portNumber) {
                return port.isOpen();
            }
        }
        return false;
    }
    public void setPortOpen(int portToOpen) {
        for (Port port : ports) {
            if (port.getPortNumber() == portToOpen) {
                port.setOpen(true);
            }
        }
    }

    public String getIpAddress() {
        return ipAddress;
    }
    public String getName() {
        return name;
    }
    public Port[] getPorts() {
        return ports;
    }
    public int getPortsToCrack() {
        return portsToCrack;
    }
    public Account[] getAccounts() {
        return accounts;
    }
    public Filesystem getFilesystem() {
        return filesystem;
    }
    public Firewall getFirewall() {
        return firewall;
    }
    public Proxy getProxy() {
        return proxy;
    }

    public Account getActiveAccount() {
        return activeAccount;
    }
    public void setActiveAccount(Account activeAccount) {
        this.activeAccount = activeAccount;
    }
    public Folder getActiveFolder() {
        return activeFolder;
    }
    public void setActiveFolder(Folder activeFolder) {
        this.activeFolder = activeFolder;
    }

    public static class Builder {
        private String ipAddress;
        private String name;
        private Port[] ports;
        private int portsToCrack;
        private Account[] accounts;
        private Filesystem filesystem;
        private Firewall firewall;
        private Proxy proxy;

        public Builder setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setPorts(Port[] ports) {
            this.ports = ports;
            return this;
        }
        public Builder setPortsToCrack(int portsToCrack) {
            this.portsToCrack = portsToCrack;
            return this;
        }
        public Builder setAccounts(Account[] accounts) {
            this.accounts = accounts;
            return this;
        }
        public Builder setFilesystem(Filesystem filesystem) {
            this.filesystem = filesystem;
            return this;
        }
        public Builder setFirewallSettings(String password, int speed) {
            this.firewall = new Firewall(password, speed);
            return this;
        }
        public Builder setProxySettings(int speed) {
            this.proxy = new Proxy(speed);
            return this;
        }

        public Node build() {
            return new Node(ipAddress, name, ports, portsToCrack, accounts, filesystem, firewall, proxy);
        }
    }
}
