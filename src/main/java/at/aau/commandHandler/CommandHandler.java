package at.aau.commandHandler;

import at.aau.Game;
import at.aau.Player;
<<<<<<< HEAD
import at.aau.commands.PingCommand;
import at.aau.commands.PlayerMoveCommand;
import at.aau.commands.RegisterCommand;
import at.aau.commands.StartCommand;
=======
import at.aau.commands.*;
>>>>>>> 06b0995acc216745fb010da4c4e0584f3c7407d6
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
<<<<<<< HEAD
        put(CommandType.CHEAT, new RegisterCommand());
        put(CommandType.DICE_ROLL, new PingCommand());
        put(CommandType.PLAYER_MOVE, new PlayerMoveCommand());
        put(CommandType.START, new StartCommand());
=======
        put(CommandType.CHEAT, new PingCommand());
        put(CommandType.DICE_ROLL, new DiceRollCommand());
        put(CommandType.START, new StartCommand());
        put(CommandType.PLAYER_MOVE, new MoveCommand());
        put(CommandType.RECONNECT, new ReconnectCommand());
>>>>>>> 06b0995acc216745fb010da4c4e0584f3c7407d6
    }};

    public static void execute(Request request, Player player, Game game) {
        commands.get(request.commandType()).execute(game, player, request.payload());
        game.broadcast(new Response(ResponseType.UPDATE_STATE, new UpdateStatePayload(game.toModel())));
    }
}
