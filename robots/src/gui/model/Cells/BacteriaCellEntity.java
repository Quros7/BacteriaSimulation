package gui.model.Cells;

import gui.model.WorldContext;

import java.awt.*;

public class BacteriaCellEntity extends CellEntity {
    /**
     * Описывает бактерию
     */
    private final int BRAIN_SIZE = 64;
    private int[] brain;
    private int nextOperation = 0;
    private int health = 10;

    public BacteriaCellEntity(Point p, int cellSize, int gridStroke) {
        super(p, Color.BLUE, cellSize, gridStroke);
        brain = new int[BRAIN_SIZE];
        fillBrain();
    }

    private void fillBrain() {
        int minValue = 0;
        int maxValue = 7; // 63
        for (int i = 0; i < BRAIN_SIZE; i++) {
            brain[i] = minValue + (int) (Math.random() * (maxValue - minValue)); // рандомное значение
        }
    }
    @Override
    public void update(WorldContext context) {
        boolean do_commands = true;
        while (do_commands) {
            switch (brain[nextOperation]) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    int shift = context.moveCell(this, brain[nextOperation]);
                    // сдвиг: яд - 1, стена - 2, бактерия - 3, еда - 4, пусто - 5
                    nextOperation = (nextOperation + shift) % BRAIN_SIZE;
                    decreaseHealth(1);
                    do_commands = false;
                    break;
            }
        }
        if (health == 0) {
            context.killCell(this);
        }
    }

    public int getHealth() {
        return health;
    }
    public void increaseHealth(int amount) {
        health += amount;
    }
    public void decreaseHealth(int amount) {
        health -= amount;
    }
}
