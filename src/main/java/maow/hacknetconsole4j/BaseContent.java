package maow.hacknetconsole4j;

import maow.hacknetconsole4j.command.*;
import maow.hacknetconsole4j.computer.Node;
import maow.hacknetconsole4j.computer.Port;
import maow.hacknetconsole4j.computer.account.Account;
import maow.hacknetconsole4j.computer.account.AccountType;
import maow.hacknetconsole4j.computer.filesystem.File;
import maow.hacknetconsole4j.computer.filesystem.Filesystem;
import maow.hacknetconsole4j.computer.filesystem.Folder;
import maow.hacknetconsole4j.computer.program.CrackProgram;
import maow.hacknetconsole4j.computer.program.PortHackProgram;
import maow.hacknetconsole4j.computer.program.SSLTrojanProgram;
import maow.hacknetconsole4j.registry.CommandRegistry;
import maow.hacknetconsole4j.registry.NodeRegistry;
import maow.hacknetconsole4j.registry.ProgramRegistry;

public class BaseContent {
    public static void registerAll() {
        registerCommands();
        registerPrograms();
        registerNodes();
    }

    private static void registerCommands() {
        CommandRegistry.register(new HelpCommand());
        CommandRegistry.register(new ConnectCommand());
        CommandRegistry.register(new ProbeCommand());
        CommandRegistry.register(new CatCommand());
        CommandRegistry.register(new CdCommand());
        CommandRegistry.register(new LsCommand());
        CommandRegistry.register(new LoginCommand());
        CommandRegistry.register(new ScpCommand());
        CommandRegistry.register(new ExeCommand());
        CommandRegistry.register(new ShellCommand());
        CommandRegistry.register(new BypassCommand());
        CommandRegistry.register(new AnalyzeCommand());
        CommandRegistry.register(new SolveCommand());

        if (HacknetConsole4j.developerMode) {
            CommandRegistry.register(new GiveCommand());
            CommandRegistry.register(new FhCommand());
        }
    }

    private static void registerPrograms() {
        ProgramRegistry.register(new CrackProgram("SSHcrack", 22, 3000));
        ProgramRegistry.register(new CrackProgram("FTPBounce", 21, 8000));
        ProgramRegistry.register(new CrackProgram("SMTPoverflow", 25, 5000));
        ProgramRegistry.register(new CrackProgram("WebServerWorm", 80, 7000));
        ProgramRegistry.register(new SSLTrojanProgram());
        ProgramRegistry.register(new PortHackProgram());
    }

    private static void registerNodes() {
        NodeRegistry.register(new Node.Builder()
                .setName("Node")
                .setIpAddress("127.0.0.1")
                .build()
        );

        NodeRegistry.register(new Node.Builder()
                .setName("Test PC")
                .setIpAddress("111.111.111.111")
                .setPorts(new Port[]{
                        new Port(80, "HTTP"),
                        new Port(22, "SSH"),
                        new Port(443, "SSL"),
                        new Port(25, "SMTP"),
                })
                .setPortsToCrack(4)
                .setFilesystem(new Filesystem(new Folder[]{
                        new Folder("/", new File[]{
                                new File("test.txt", "Test file 1"),
                                new File("test2.txt", "Test file 2"),
                        }, new Folder[]{
                                new Folder("bin", new File[]{
                                        new File("nestedTest.txt", "Nested test file"),
                                        new File("SSHcrack.exe", "10101", ProgramRegistry.get("SSHcrack")),
                                }, null)
                        })
                }))
                .setAccounts(new Account[]{
                        new Account("admin", "password", AccountType.ADMIN),
                })
                .setFirewallSettings("morpheus", 50)
                .setProxySettings(5000)
                .build()
        );
    }
}
