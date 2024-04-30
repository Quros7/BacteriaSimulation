package gui.model;

import java.awt.*;
import java.awt.geom.Point2D;

public interface Entity {
    void update(WorldContext context);
    Point getCoords();
}
