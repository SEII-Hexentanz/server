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
    private static final Map<CommandType, Command> commands = new HashMap<>() {{
        put(CommandType.PING, new PingCommand());
        put(CommandType.REGISTER, new RegisterCommand());
        put(CommandType.CHEAT, new PingCommand());
        put(CommandType.DICE_ROLL, new DiceRollCommand());
        put(CommandType.START, new StartCommand());
        put(CommandType.PLAYER_MOVE, new MoveCommand());
        put(CommandType.RECONNECT, new ReconnectCommand());
        put(CommandType.NAME_ALREADY_EXISTS, new RegisterCommand());
        put(CommandType.PLAYER_REGISTER_OKAY, new RegisterCommand());
        put(CommandType.CHEAT_USED, new CheatCommand());
    }};

    public static void execute(Request request, Player player, Game game) {
        commands.get(request.commandType()).execute(game, player, request.payload());
        game.broadcast(new Response(ResponseType.UPDATE_STATE, new UpdateStatePayload(game.toModel())));
    }
}
