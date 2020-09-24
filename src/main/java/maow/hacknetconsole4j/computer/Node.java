package maow.hacknetconsole4j.computer;

import maow.hacknetconsole4j.computer.account.Account;
import maow.hacknetconsole4j.computer.account.AccountType;
import maow.hacknetconsole4j.computer.filesystem.File;
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
    private final Node[] linkedNodes;

    private Node(String ipAddress, String name, Port[] ports, int portsToCrack, Account[] accounts, Filesystem filesystem, Firewall firewall, Proxy proxy, Node[] linkedNodes) {
        this.ipAddress = ipAddress;
        this.name = name;
        this.ports = ports;
        this.portsToCrack = portsToCrack;
        this.accounts = accounts;
        this.filesystem = filesystem;
        this.firewall = firewall;
        this.proxy = proxy;
        this.linkedNodes = linkedNodes;
    }

    private Account activeAccount;
    private Folder activeFolder;

    public String getIpAddress() {
        return ipAddress;
    }
    public String getName() {
        return name;
    }
    public Port[] getPorts() {
        if (ports == null) {
            return new Port[]{};
        }
        return ports;
    }
    public int getPortsToCrack() { return portsToCrack; }
    public Account[] getAccounts() {
        if (accounts == null) {
            return new Account[]{};
        }
        return accounts;
    }
    public Filesystem getFilesystem() {
        if (filesystem == null) {
            return new Filesystem(new Folder[]{});
        }
        return filesystem;
    }
    public Firewall getFirewall() {
        return firewall;
    }
    public Proxy getProxy() {
        return proxy;
    }
    public Node[] getLinkedNodes() {
        if (linkedNodes == null) {
            return new Node[]{};
        }
        return linkedNodes;
    }
    public Account getActiveAccount() {
        if (activeAccount == null) {
            return new Account("guest", "", AccountType.NONE);
        }
        return activeAccount;
    }
    public void setActiveAccount(Account activeAccount) {
        this.activeAccount = activeAccount;
    }
    public Folder getActiveFolder() {
        if (activeFolder == null && filesystem == null) {
            return new Folder("/", new File[]{});
        } else if (activeFolder == null) {
            return filesystem.getFolders()[0];
        }
        return activeFolder;
    }
    public void setActiveFolder(Folder activeFolder) {
        this.activeFolder = activeFolder;
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
    public void crack() {
        if (accounts == null || accounts.length == 0) {
            setActiveAccount(new Account("admin", "", AccountType.ADMIN));
        } else {
            for (Account account : accounts) {
                if (account.getType() == AccountType.ADMIN) {
                    setActiveAccount(account);
                    return;
                }
            }
        }
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
        private Node[] linkedNodes;

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
        public Builder setLinkedNodes(Node[] linkedNodes) {
            this.linkedNodes = linkedNodes;
            return this;
        }

        public Node build() {
            return new Node(ipAddress, name, ports, portsToCrack, accounts, filesystem, firewall, proxy, linkedNodes);
        }
    }
}
