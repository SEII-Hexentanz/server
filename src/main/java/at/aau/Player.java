package at.aau;

import at.aau.models.Character;
import at.aau.models.Response;
import at.aau.values.CharacterState;
import at.aau.values.Color;
import io.vavr.control.Option;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public final class Player implements Comparable<Player>, Serializable {
    private final String name;
    private final int age;

    private ArrayList<Character> characters;
    /**
     * Option.some() player is online - reconnecting with same name is not allowed.
     * Option.none() player is offline - allowed to reconnect and register with new socket.
     */
    transient public Option<Connection> connection;
    private Color color;

    public Player(Connection connection, String name, int age) {
        this.connection = Option.of(connection);
        this.name = name;
        this.color = Color.RED;
        this.age = age;

        createCharacters();
    }

    public void send(Response response) {
        connection.peek(c -> c.send(response));
    }

    /**
     * Sorts players by age in descending order.
     */
    @Override
    public int compareTo(Player o) {
        return o.age() - this.age();
    }

    public String name() {
        return name;
    }

    public Color color() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int age() {
        return age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Player) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.color, that.color) &&
                this.age == that.age &&
                Objects.equals(this.characters, that.characters);
    }

    private void createCharacters() {
        characters = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            characters.add(new Character(0, CharacterState.HOME));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, age);
    }

    public at.aau.models.Player toModel() {
        return new at.aau.models.Player(name, age, color, characters);
    }
}
