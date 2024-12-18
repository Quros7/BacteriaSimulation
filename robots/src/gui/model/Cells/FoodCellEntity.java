package gui.model.Cells;

import gui.model.WorldContext;

import java.awt.*;

public class FoodCellEntity extends CellEntity {
    /**
     * Описывает еду
     */
    private final int healingAmount;
    public FoodCellEntity(Point p, int healingAmount) {
        super(p, Color.GREEN);
        this.healingAmount = healingAmount;
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    @Override
    public void update(WorldContext context) {}
}
