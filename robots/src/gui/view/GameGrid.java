package gui.view;

import gui.model.Cells.BacteriaCellEntity;
import gui.model.World;
import gui.viewmodel.WorldSource;

import java.awt.*;

public class GameGrid {
    private final int cellCountWidth;
    private final int cellCountHeight;
    private final int gw_width;
    private final int gw_height;
    private final int cellSize;
    private final int gridStroke;
    public GameGrid(WorldSource world) {
        this.cellCountWidth = world.getCellCountWidth();
        this.cellCountHeight = world.getCellCountHeight();
        this.gw_width = world.getGameWindowWidth();
        this.gw_height = world.getGameWindowHeight();
        this.cellSize = world.getCellSize();

        this.gridStroke = world.getGridStroke();

        System.out.println("cellCountWidth = " + cellCountWidth);
        System.out.println("cellCountHeight = " + cellCountHeight);
        System.out.println("gw_width = " + gw_width);
        System.out.println("gw_height = " + gw_height);
        System.out.println("cellSize = " + cellSize);
        System.out.println("gridStroke = " + world.getGridStroke());
    }

    /** Отрисовывает клеточную сетку
     * @param g
     */
    public void drawGrid(Graphics g) {
        // Graphics2D g2 = (Graphics2D) g;
        // g2.setStroke(new BasicStroke(gridStroke));
        g.setColor(Color.GRAY);

        for (int i = 0; i <= cellCountWidth; i++) { // вертикальные линии
            g.fillRect(cellSize * i + i * gridStroke, 0, gridStroke, gw_height);
        }
        for (int i = 0; i <= cellCountHeight; i++) { // горизонтальные линии
            g.fillRect(0, cellSize * i + i * gridStroke, gw_width, gridStroke);
        }
    }
}
