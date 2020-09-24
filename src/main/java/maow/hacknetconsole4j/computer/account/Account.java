package maow.hacknetconsole4j.computer.account;

public class Account {
    private final String username;
    private final String password;
    private final AccountType type;

    public Account(String username, String password, AccountType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public AccountType getType() {
        return type;
    }
}
