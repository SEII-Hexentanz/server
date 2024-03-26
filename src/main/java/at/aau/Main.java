package at.aau;

import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        var server = new Server();

        server.start();
    }
}