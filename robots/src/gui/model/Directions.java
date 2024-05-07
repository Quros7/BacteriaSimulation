package gui.model;

import java.awt.*;

public enum Directions {
    NORTH_WEST,
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST;

    public Directions getStandardDirection(int command){
        int num = (command+ordinal()+7) % 8;
        return Directions.values()[num];
    }

    public Point getRelativeCoords(int command) {
        Directions standardDirection = getStandardDirection(command);
        return switch (standardDirection){
            case NORTH_WEST -> new Point(-1, -1);
            case NORTH -> new Point(0, -1);
            case NORTH_EAST -> new Point(1, -1);
            case EAST -> new Point(1, 0);
            case SOUTH_EAST -> new Point(1, 1);
            case SOUTH -> new Point(0, 1);
            case SOUTH_WEST -> new Point(-1, 1);
            case WEST -> new Point(-1, 0);
        };
    }
}