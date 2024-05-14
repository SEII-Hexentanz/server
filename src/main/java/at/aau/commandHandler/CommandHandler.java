package at.aau.commandHandler;

import at.aau.Game;
import at.aau.Player;
import at.aau.commands.PingCommand;
import at.aau.commands.PlayerMoveCommand;
import at.aau.commands.RegisterCommand;
import at.aau.commands.StartCommand;
import at.aau.models.Request;
import at.aau.models.Response;
import at.aau.payloads.UpdateStatePayload;
import at.aau.values.CommandType;
import at.aau.values.ResponseType;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private static final Map<CommandType, Command> commands = new HashMap<>() {{
        put(CommandType.PING, new PingCommand());
        put(CommandType.REGISTER, new RegisterCommand());
        put(CommandType.CHEAT, new RegisterCommand());
        put(CommandType.DICE_ROLL, new RegisterCommand());
        put(CommandType.PLAYER_MOVE, new PlayerMoveCommand());
        put(CommandType.START, new StartCommand());
        put(CommandType.TIMER, new RegisterCommand());
    }};

    public static void execute(Request request, Player player, Game game) {
        commands.get(request.commandType()).execute(game, player, request.payload());
        game.broadcast(new Response(ResponseType.UPDATE_STATE, new UpdateStatePayload(game.toModel())));
    }
}
