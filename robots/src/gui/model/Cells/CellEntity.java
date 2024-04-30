package gui.model.Cells;

import gui.model.Entity;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class CellEntity implements Entity {
    /**
     * Описывает шаблонную клетку
     */
    private Color color;
    private Point coords;
    private int cellSize;
    private int gridStroke;
    public CellEntity(Point p, Color c, int cs, int gs) {
        this.coords = p;
        this.color = c;
        this.cellSize = cs;
        this.gridStroke = gs;
    }

    @Override
    public Point getCoords() {
        return coords;
    }
    public void moveTo(Point p) {
        coords.translate(p.x, p.y);
    }
    public Point getNeighbourCoords(Point p) {
        return new Point(coords.x + p.x, coords.y + p.y);
    }
    public Color getColor() {return color;}
    public int getCellSize() {return cellSize;}
    public int getGridStroke() {return gridStroke;}
}
