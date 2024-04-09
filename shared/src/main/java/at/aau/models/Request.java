package at.aau.models;


import at.aau.payloads.Payload;
import at.aau.values.CommandType;

import java.io.Serial;
import java.io.Serializable;

public record Request(CommandType commandType, Payload payload) implements Serializable {
    @Serial
    private static final long serialVersionUID = -6675493441623482566L;
}
