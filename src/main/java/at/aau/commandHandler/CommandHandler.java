package at.aau.commandHandler;

import at.aau.Game;
import at.aau.Player;
import at.aau.commands.*;
import at.aau.models.Request;
import at.aau.models.Response;
import at.aau.payloads.UpdateStatePayload;
import at.aau.values.CommandType;
import at.aau.values.ResponseType;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private final Map<CommandType, Command> commands = new HashMap<>();

    public CommandHandler() {
        commands.put(CommandType.PING, new PingCommand());
        commands.put(CommandType.REGISTER, new RegisterCommand());
        commands.put(CommandType.CHEAT, new PingCommand());
        commands.put(CommandType.DICE_ROLL, new DiceRollCommand());
        commands.put(CommandType.START, new StartCommand());
        commands.put(CommandType.PLAYER_MOVE, new MoveCommand());
        commands.put(CommandType.RECONNECT, new ReconnectCommand());
        commands.put(CommandType.NAME_ALREADY_EXISTS, new RegisterCommand());
        commands.put(CommandType.PLAYER_REGISTER_OKAY, new RegisterCommand());
        commands.put(CommandType.CHEAT_USED, new PingCommand());
    }

    public void execute(Request request, Player player, Game game) {
        commands.get(request.commandType()).execute(game, player, request.payload());
        game.broadcast(new Response(ResponseType.UPDATE_STATE, new UpdateStatePayload(game.toModel())));
    }
}
