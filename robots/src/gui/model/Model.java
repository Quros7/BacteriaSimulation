package gui.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<Entity> entities = new ArrayList<>();
    public Model(RobotEntity r) {
        entities.add(r);
    }

    public void updateModel() {
        for (Entity entity : entities) {
            entity.update();
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
