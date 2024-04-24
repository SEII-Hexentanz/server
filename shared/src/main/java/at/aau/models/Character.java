package at.aau.models;

import java.io.Serial;
import java.io.Serializable;

import at.aau.values.CharacterState;

public class Character implements Serializable
{

    @Serial
    private static final long serialVersionUID = -8185411860134618456L;

    private int position;
    private CharacterState status;
    Character[] characters;
    public Character() {
        this.position = 0;
        this.status = CharacterState.HOME;


    }

}
