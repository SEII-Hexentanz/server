package at.aau;

import at.aau.commandHandler.CommandHandler;

public class Main {
    public static void main(String[] args) {
        var game = new Game();
        var commandHandler = new CommandHandler();
        var server = new Server(game, commandHandler);

        server.start();
    }
}