package at.aau.commands;

import at.aau.commandHandler.Command;
import at.aau.errors.DomainError;
import at.aau.models.Payload;
import at.aau.models.Player;
import at.aau.models.Response;
import io.vavr.control.Either;

public class PingCommand implements Command {
    @Override
    public Either<DomainError, Response> execute(Player player, Payload payload) {
        System.out.println("Pong");
        return null;
    }
}
