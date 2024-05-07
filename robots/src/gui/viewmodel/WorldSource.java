package gui.viewmodel;

import gui.model.Entity;
import gui.model.World;

import java.util.List;

public class WorldSource implements DataSource {
    private final World source;
    public WorldSource(World source) {
        this.source = source;
    }
    @Override
    public int getCellCountWidth() {
        return source.getCellCountWidth();
    }

    @Override
    public int getCellCountHeight() {
        return source.getCellCountHeight();
    }

    @Override
    public int getGameWindowWidth() {
        return source.getGameWindowWidth();
    }

    @Override
    public int getGameWindowHeight() {
        return source.getGameWindowHeight();
    }

    @Override
    public int getCellSize() {
        return source.getCellSize();
    }

    @Override
    public int getGridStroke() {
        return source.getGridStroke();
    }

    @Override
    public List<Entity> getEntities() {
        return source.getEntities();
    }
}
