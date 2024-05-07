package gui.view.Cells;

import gui.model.Cells.CellEntity;
import gui.view.EntityRenderer;

import java.awt.*;

public class BasicCellRenderer implements EntityRenderer<CellEntity> {
    @Override
    public void render(CellEntity entity, Graphics graphics) {
        Graphics2D g = (Graphics2D)graphics;

        g.setColor(entity.getColor());
        Point coords = entity.getCoords();
        int cellSize = entity.getCellSize();
        int gridStroke = entity.getGridStroke();
        g.fillRect(cellSize * coords.x + (coords.x + 1) * gridStroke, cellSize * coords.y + (coords.y + 1) * gridStroke, cellSize, cellSize);
    }
}
