package at.aau.models;

import java.net.Socket;

public record Player(Socket socket, String name) {
}
