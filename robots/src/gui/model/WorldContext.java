package gui.model;

import gui.model.Cells.*;

import java.awt.*;
import java.util.HashMap;

public class WorldContext {
    private final World world;
    public WorldContext(World world) {
        this.world = world;
    }
    public CellEntity getEntityOnCoords(Point p) {
        return world.getEntityOnCoords(p);
    }
    private HashMap<Integer, Point> steps = new HashMap<>();
    {
        steps.put(0, new Point(-1, -1));
        steps.put(1, new Point(0, -1));
        steps.put(2, new Point(1, -1));
        steps.put(3, new Point(1, 0));
        steps.put(4, new Point(1, 1));
        steps.put(5, new Point(0, 1));
        steps.put(6, new Point(-1, 1));
        steps.put(7, new Point(-1, 0));
    }
    public int moveCell(BacteriaCellEntity c, int nextOperation) {
        Point oldCoords = c.getCoords();
        Point neighbour_coords = c.getNeighbourCoords(steps.get(nextOperation));
        CellEntity neighbour = getEntityOnCoords(neighbour_coords);
        if (neighbour == null) {
            if (neighbour_coords.x > -1 && neighbour_coords.x < getCellCountWidth() &&
                    neighbour_coords.y > -1 && neighbour_coords.y < getCellCountHeight()) {
                world.moveBacteriaToCoords(oldCoords, neighbour_coords);
                c.moveTo(steps.get(nextOperation));
                // переместились в пустую клетку, в конце для этого (return 5;)
            }
            else {
                return 2; // граница мира (стена)
            }
        } else if (neighbour instanceof PoisonCellEntity) {
            world.eatPoison(c);
            return 1;
        } else if (neighbour instanceof WallCellEntity) {
            return 2;
        } else if (neighbour instanceof BacteriaCellEntity) {
            return 3;
        } else if (neighbour instanceof FoodCellEntity) {
            int healSize = world.eatFood((FoodCellEntity) neighbour);
            c.increaseHealth(healSize);
            world.moveBacteriaToCoords(oldCoords, neighbour_coords);
            c.moveTo(steps.get(nextOperation));
            return 4;
        }
        return 5; // пустая клетка
    }
    public void killCell(CellEntity cell) {
        world.killCell(cell);
    }
    public int getCellCountWidth() {return world.getCellCountWidth();}
    public int getCellCountHeight() {return world.getCellCountHeight();}
}
