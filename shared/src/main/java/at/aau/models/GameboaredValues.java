package at.aau.models;

import java.util.HashMap;

import at.aau.values.Color;

public class GameboaredValues {

    private static HashMap<Color, Integer> mapStartingPoint;

    private static HashMap<Color, Integer> mapGoalPoint;


    public static HashMap<Color, Integer> getMapStartingPoint() {
        if (mapStartingPoint == null) {
            mapStartingPoint = new HashMap<>();
            mapStartingPoint.put(at.aau.values.Color.YELLOW, 26);
            mapStartingPoint.put(at.aau.values.Color.PINK, 32);
            mapStartingPoint.put(at.aau.values.Color.RED, 6);
            mapStartingPoint.put(at.aau.values.Color.GREEN, 20);
            mapStartingPoint.put(at.aau.values.Color.LIGHT_BLUE, 9);
            mapStartingPoint.put(at.aau.values.Color.DARK_BLUE, 3);
        }
        return new HashMap<>();
    }

    public static HashMap<Color, Integer> getMapGoalPositons() {
        if (mapGoalPoint == null) {
            mapGoalPoint = new HashMap<>();
            mapGoalPoint.put(at.aau.values.Color.YELLOW, 26);
            mapGoalPoint.put(at.aau.values.Color.PINK, 32);
            mapGoalPoint.put(at.aau.values.Color.RED, 6);
            mapGoalPoint.put(at.aau.values.Color.GREEN, 20);
            mapGoalPoint.put(at.aau.values.Color.LIGHT_BLUE, 0);
            mapGoalPoint.put(at.aau.values.Color.DARK_BLUE, 29);
        }
        return new HashMap<>();
    }
}
