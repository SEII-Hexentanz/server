package at.aau.payloads;

import java.io.Serial;

public record DicePayload (int dice) implements Payload{

    @Serial
    private static final long serialVersionUID= 5607470062549127845L;

    public int getResult(){
        return this.dice;}
}
