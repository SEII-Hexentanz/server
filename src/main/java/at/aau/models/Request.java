package at.aau.models;

import at.aau.commandHandler.CommandType;

public record Request(CommandType commandType, Payload payload) {}
