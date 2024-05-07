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
    public World(int cellCountWidth, int cellCountHeight, int cellSize, int gridStroke) {
        context = new WorldContext(this);

        this.cellCountWidth = cellCountWidth;
        this.cellCountHeight = cellCountHeight;
        this.gridStroke = gridStroke;
        this.cellSize = cellSize;
        this.gameWindowWidth = cellCountWidth * cellSize + (cellCountWidth + 1) * gridStroke;
        this.gameWindowHeight = cellCountHeight * cellSize + (cellCountHeight + 1) * gridStroke;

        spawnEntities();
        fillMatrix();
    }

    /**
     * Обновляет все entity, уничтожает мертвые клетки и спавнит новые
     */
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

    /**
     * Возвращает клетку, находящуюся на указанных координатах
     * @param p
     * @return
     */
    public CellEntity getEntityOnCoords(Point p) {
        if (entityMap.containsKey(p)) {
            return entityMap.get(p);
        }
        return null;
    }

    /**
     * Создание всех клеток мира
     */
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

    /**
     * Заполняет entityMap информацией о координатах всех заспавненных клеток
     */
    public void fillMatrix() {
        for (Entity entity: entities) {
            if (entity instanceof CellEntity) {
                Point coords = entity.getCoords();
                entityMap.put(coords, (CellEntity) entity);
            }
        }
    }

    /**
     * Перемещает клетку со старых координат на новые
     * @param oldCoords
     * @param newCoords
     */
    public void moveBacteriaToCoords(Point oldCoords, Point newCoords) {
        entityMap.put(newCoords, entityMap.get(oldCoords));
        entityMap.remove(oldCoords);
    }

    /**
     * Клетка съедает еду, еда удаляется; съевшая еду клетка увеличивает своё здоровье на число, которое вернет функция
     * @param food
     * @return
     */
    public int eatFood(FoodCellEntity food) {
        int healingAmount = food.getHealingAmount();
        killCell(food);
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
     * Превращает клетку яда в еду (удаляет яд, создаёт новую еду на тех же координатах)
     * @param poison
     */
    public void curePoison(PoisonCellEntity poison){
        FoodCellEntity newFood = new FoodCellEntity(poison.getCoords(), cellSize, gridStroke, mediumFood);
        killCell(poison);
        entityMap.put(newFood.getCoords(), newFood);
        newCells.add(newFood);
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
    public int getGridStroke() {return gridStroke;}
}
