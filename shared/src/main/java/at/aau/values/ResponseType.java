package at.aau.values;

import java.io.Serializable;

public enum ResponseType implements Serializable {
    BAD_REQUEST, GAME_FULL, NOT_REGISTERED, UPDATE_STATE, PONG, GAME_END
}
