package gui.model;

import gui.model.Cells.BacteriaCellEntity;
import gui.model.Cells.CellEntity;
import gui.model.Cells.FoodCellEntity;
import gui.model.Cells.WallCellEntity;
import gui.model.Cells.PoisonCellEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class World {
    private final int cellCountWidth; // кол-во клеток в ширину
    private final int cellCountHeight; // кол-во клеток в высоту
    private final int gameWindowWidth; // ширина игрового поля в пикселях
    private final int gameWindowHeight; // высота игрового поля в пикселях
    private int cellSize;
    private int gridStroke;

    private final int smallFood = 5;
    private final int mediumFood = 10;
    private final int bigFood = 15;

    private final List<Entity> entities = new ArrayList<>();
    private final List<CellEntity> deadCells = new ArrayList<>();
    private final List<CellEntity> newCells = new ArrayList<>();
    private HashMap<Point, CellEntity> entityMap = new HashMap<>();
    private WorldContext context;
    public World(int gw_width, int gw_height, int cellCountWidth, int cellCountHeight, int gridStroke) {
        context = new WorldContext(this);

        this.gameWindowWidth = gw_width;
        this.gameWindowHeight = gw_height;
        this.cellCountWidth = cellCountWidth;
        this.cellCountHeight = cellCountHeight;
        this.gridStroke = gridStroke;

        cellSize = (gw_width - (cellCountWidth + 1) * gridStroke) / cellCountWidth;

        spawnEntities();
        fillMatrix();
    }

    public void updateWorld() {
        for (Entity entity : entities) {
            entity.update(context);
        }
        deleteCells();
        createCells();
    }
    public List<Entity> getEntities() {
        return entities;
    }

    public CellEntity getEntityOnCoords(Point p) {
        if (entityMap.containsKey(p)) {
            return entityMap.get(p);
        }
        return null;
    }

    public void spawnEntities() {
        entities.add(new BacteriaCellEntity(new Point(0, 0), cellSize, gridStroke));
        entities.add(new BacteriaCellEntity(new Point(1, 0), cellSize, gridStroke));
        entities.add(new WallCellEntity(new Point(2, 0), cellSize, gridStroke));
        entities.add(new WallCellEntity(new Point(0, 2), cellSize, gridStroke));
        entities.add(new WallCellEntity(new Point(1, 2), cellSize, gridStroke));
        entities.add(new WallCellEntity(new Point(2, 1), cellSize, gridStroke));
        entities.add(new WallCellEntity(new Point(2, 2), cellSize, gridStroke));
        entities.add(new BacteriaCellEntity(new Point(3, 3), cellSize, gridStroke));
        entities.add(new BacteriaCellEntity(new Point(3, 4), cellSize, gridStroke));
        entities.add(new BacteriaCellEntity(new Point(4, 4), cellSize, gridStroke));
        entities.add(new FoodCellEntity(new Point(4, 3), cellSize, gridStroke, mediumFood));
        entities.add(new FoodCellEntity(new Point(6, 4), cellSize, gridStroke, mediumFood));
        entities.add(new WallCellEntity(new Point(1, 5), cellSize, gridStroke));
        entities.add(new PoisonCellEntity(new Point(1, 1), cellSize, gridStroke));
        entities.add(new PoisonCellEntity(new Point(2, 6), cellSize, gridStroke));
    }
    public void fillMatrix() {
        for (Entity entity: entities) {
            if (entity instanceof CellEntity) {
                Point coords = entity.getCoords();
                entityMap.put(coords, (CellEntity) entity);
            }
        }
    }
    public void moveBacteriaToCoords(Point oldCoords, Point newCoords) {
        entityMap.put(newCoords, entityMap.get(oldCoords));
        entityMap.remove(oldCoords);
    }
    public int eatFood(FoodCellEntity food) {
        int healingAmount = food.getHealingAmount();
        killCell(food);
        //food.kill();
        return healingAmount;
    }

    /**
     * Клетка съедает яд, и на её месте спавнится новый яд
     * @param cell
     */
    public void eatPoison(BacteriaCellEntity cell) {
        PoisonCellEntity newPoison = new PoisonCellEntity(cell.getCoords(), cellSize, gridStroke);
        killCell(cell);
        entityMap.put(cell.getCoords(), newPoison);
        newCells.add(newPoison);
    }

    /**
     * Удаляет клетку из хэшмапа и добавляет её в список мертвых клеток
     * @param cell
     */
    public void killCell(CellEntity cell) {
        deadCells.add(cell);
        entityMap.remove(cell.getCoords());
    }

    /**
     * Очищает списки entities и deadCells от мертвых клеток
     */
    public void deleteCells() {
        for (CellEntity cell : deadCells) {
            entities.remove(cell);
        }
        deadCells.clear();
    }

    /**
     * Добавляет в список entities все новые клетки из очереди newCells
     */
    public void createCells() {
        entities.addAll(newCells);
        newCells.clear();
    }
    public int getCellCountWidth() {return cellCountWidth;}
    public int getCellCountHeight() {return cellCountHeight;}
    public int getGameWindowWidth() {return gameWindowWidth;}
    public int getGameWindowHeight() {return gameWindowHeight;}
    public int getCellSize() {return cellSize;}
    public void setCellSize(int size) {cellSize = size;}
    public int getGridStroke() {return gridStroke;}
}
