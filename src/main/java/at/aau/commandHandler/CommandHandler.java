package at.aau.commandHandler;

import at.aau.commands.PingCommand;
import at.aau.models.Player;
import at.aau.models.Request;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private static final Map<CommandType, Command> commands = new HashMap<>() {{
        put(CommandType.PING, new PingCommand());
    }};

    public static void execute(Request request, Player player) {
        commands.get(request.commandType()).execute(player, request.payload());
    }
}
