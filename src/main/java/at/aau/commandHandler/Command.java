package at.aau.commandHandler;

import at.aau.errors.DomainError;
import at.aau.models.Payload;
import at.aau.models.Player;
import at.aau.models.Response;
import io.vavr.control.Either;

@FunctionalInterface
public interface Command {
    Either<DomainError, Response> execute(Player player, Payload payload);
}
