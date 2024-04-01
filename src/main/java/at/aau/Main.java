package at.aau;

public class Main {
    public static void main(String[] args) {
        var game = new Game();
        var server = new Server(game);

        server.start();
    }
}