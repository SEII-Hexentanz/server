package at.aau.values;

import java.io.Serializable;

public enum ResponseType implements Serializable {
    BAD_REQUEST, GAME_FULL, NOT_REGISTERED, UPDATE_STATE, PONG, GAME_END, DICE_ROLLED, MOVE_SUCCESSFUL, YOUR_TURN, NAME_ALREADY_EXISTS, PLAYER_SUCCESSFULLY_REGISTERED, MOVE_CHARACTER, CHEAT_USED_PLAYER
}
