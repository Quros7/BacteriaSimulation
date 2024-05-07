package gui.viewmodel;

import gui.model.Entity;

import java.util.List;

public interface DataSource {
    int getCellCountWidth();
    int getCellCountHeight();
    int getGameWindowWidth();
    int getGameWindowHeight();
    int getCellSize();
    int getGridStroke();
    List<Entity> getEntities();
}
