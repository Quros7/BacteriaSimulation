package gui.model.Cells;

import gui.model.WorldContext;

import java.awt.*;

public class PoisonCellEntity extends CellEntity {
    /**
     * Описывает яд
     */
    public PoisonCellEntity(Point p, int cellSize, int gridStroke) {
        super(p, Color.RED, cellSize, gridStroke);
    }

    @Override
    public void update(WorldContext context) {}
}
