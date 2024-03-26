package at.aau;

import java.net.ServerSocket;

public class Server {
    void start() {
        System.out.println("Server started");
        try (var serverSocket = new ServerSocket(8080)) {
            while (true) {
                new Connection(serverSocket.accept()).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
