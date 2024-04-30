package gui.model.Cells;

import gui.model.WorldContext;

import java.awt.*;

public class WallCellEntity extends CellEntity {
    /**
     * Описывает стену
     */
    public WallCellEntity(Point p, int cellSize, int gridStroke) {
        super(p, Color.DARK_GRAY, cellSize, gridStroke);
    }

    @Override
    public void update(WorldContext context) {

    }
}
