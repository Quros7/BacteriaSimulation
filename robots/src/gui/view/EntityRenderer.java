package gui.view;

import gui.model.Entity;

import java.awt.*;

public interface EntityRenderer<E extends Entity> {
    void render(E entity, Graphics graphics);
}
