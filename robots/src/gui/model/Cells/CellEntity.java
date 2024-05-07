package gui.model.Cells;

import gui.model.Entity;

import java.awt.*;

public abstract class CellEntity implements Entity {
    /**
     * Описывает шаблонную клетку
     */
    private Color color;
    private Point coords;
    private int cellSize;
    private int gridStroke;
    public CellEntity(Point coords, Color color, int cellSize, int gridStroke) {
        this.coords = coords;
        this.color = color;
        this.cellSize = cellSize;
        this.gridStroke = gridStroke;
    }

    @Override
    public Point getCoords() {
        return coords;
    }

    /**
     * Перемещает клетку на указанный сдвиг (перегрузка translate() для координат, заданных как Point)
     * @param p
     */
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
