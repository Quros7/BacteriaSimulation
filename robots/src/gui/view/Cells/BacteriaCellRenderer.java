package gui.view.Cells;

import gui.model.Cells.BacteriaCellEntity;
import gui.model.Cells.CellEntity;

import java.awt.*;

public class BacteriaCellRenderer extends BasicCellRenderer {
    @Override
    public void render(CellEntity entity, Graphics graphics) {
        Graphics2D g = (Graphics2D)graphics;

        drawCell(g, (BacteriaCellEntity) entity);
    }
    private void drawCell(Graphics2D g, BacteriaCellEntity entity) {
        Point coords = entity.getCoords();
        int cellSize = entity.getCellSize();
        int gridStroke = entity.getGridStroke();
        String health = String.valueOf(entity.getHealth());

        int cell_x = cellSize * coords.x + (coords.x + 1) * gridStroke;
        int cell_y = cellSize * coords.y + (coords.y + 1) * gridStroke;

        Font font = new Font("Arial", Font.BOLD, 16);
        g.setFont(font);
        FontMetrics fontMetrics = g.getFontMetrics();

        g.setColor(entity.getColor());
        g.fillRect(cell_x, cell_y, cellSize, cellSize);
        g.setColor(Color.BLACK);
        g.drawString(health, cell_x + (cellSize - fontMetrics.stringWidth(health)) / 2, cell_y + (cellSize - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent());
    }
}
