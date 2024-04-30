package gui.view;

import gui.model.*;
import gui.model.Cells.BacteriaCellEntity;
import gui.model.Cells.FoodCellEntity;
import gui.model.Cells.PoisonCellEntity;
import gui.model.Cells.WallCellEntity;
import gui.view.Cells.BacteriaCellRenderer;
import gui.view.Cells.FoodCellRenderer;
import gui.view.Cells.PoisonCellRenderer;
import gui.view.Cells.WallCellRenderer;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class View {
    private final World world;
    private final GameGrid grid;
    public View(World world) {
        this.world = world;
        this.grid = new GameGrid(world);
    }

    private final Map<Class<?>, EntityRenderer<?>> renderes = Map.of(
            BacteriaCellEntity.class, new BacteriaCellRenderer(),
            FoodCellEntity.class, new FoodCellRenderer(),
            PoisonCellEntity.class, new PoisonCellRenderer(),
            WallCellEntity.class, new WallCellRenderer()
    );

    public void paint(Graphics g) {
        grid.drawGrid(g); // отрисовка игровой сетки
        List<Entity> entities = world.getEntities();
        for (Entity entity : entities) { // отрисовка активных клеток
            EntityRenderer<Entity> entityRenderer = (EntityRenderer<Entity>)renderes.get(entity.getClass());
            entityRenderer.render(entity, g);
        }
    }

    // метод, устанавливающий источник данных
    // dataSource интерфейс с набором методов
    // setSources
}
