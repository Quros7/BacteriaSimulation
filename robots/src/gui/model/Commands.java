package gui.model;

public enum Commands {
    MOVE_CELL,
    CATCH_CELL,
    LOOK;
    public static Commands getType(int command){
        if (command < 8) return MOVE_CELL;
        else if (command < 16) return CATCH_CELL;
        else return LOOK;
    }
}
