package gui.model;

import gui.model.Cells.*;
import java.awt.*;

public class WorldContext {
    private final World world;
    public WorldContext(World world) {
        this.world = world;
    }
    public CellEntity getEntityOnCoords(Point p) {
        return world.getEntityOnCoords(p);
    }

    /**
     * Выполняет одно из действий сделать шаг/схватить/посмотреть в зависимости от номера команды
     * @param cell
     * @param command
     * @return сдвиг счётчика команд
     */
    public int completeCommand(BacteriaCellEntity cell, int command){
        Commands commandType = Commands.getType(command);

        Point oldCoords = cell.getCoords();
        Directions cellDirection = cell.getCellDirection();
        Point relativeCoords = cellDirection.getRelativeCoords(command);
        Point neighbour_coords = cell.getNeighbourCoords(relativeCoords);
        CellEntity neighbour = getEntityOnCoords(neighbour_coords);
        if (neighbour == null) {
            if (neighbour_coords.x > -1 && neighbour_coords.x < getCellCountWidth() &&
                    neighbour_coords.y > -1 && neighbour_coords.y < getCellCountHeight()) {
                if(commandType == Commands.MOVE_CELL) {
                    world.moveBacteriaToCoords(oldCoords, neighbour_coords);
                    cell.moveTo(relativeCoords);
                }
                return 5; // пустая клетка
            }
            else {
                return 2; // граница мира (стена)
            }
        }
        else if (neighbour instanceof PoisonCellEntity) {
            if(commandType == Commands.MOVE_CELL) world.eatPoison(cell);
            if(commandType == Commands.CATCH_CELL) world.curePoison((PoisonCellEntity) neighbour);
            return 1;
        }
        else if (neighbour instanceof WallCellEntity) {
            return 2;
        }
        else if (neighbour instanceof BacteriaCellEntity) {
            return 3;
        }
        else if (neighbour instanceof FoodCellEntity) {
            if(commandType == Commands.MOVE_CELL) {
                int healSize = world.eatFood((FoodCellEntity) neighbour);
                cell.increaseHealth(healSize);
                world.moveBacteriaToCoords(oldCoords, neighbour_coords);
                cell.moveTo(relativeCoords);
            }
            if(commandType == Commands.CATCH_CELL){
                int healSize = world.eatFood((FoodCellEntity) neighbour);
                cell.increaseHealth(healSize);
            }
            return 4;
        }
        else return 0;
    }
    public void killCell(CellEntity cell) {
        world.killCell(cell);
    }
    public int getCellCountWidth() {return world.getCellCountWidth();}
    public int getCellCountHeight() {return world.getCellCountHeight();}
}
