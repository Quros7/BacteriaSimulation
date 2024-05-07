package gui.model;

import java.awt.*;

public interface Entity {
    void update(WorldContext context);
    Point getCoords();
}
