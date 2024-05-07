package gui.model.Cells;

import gui.model.Directions;
import gui.model.WorldContext;

import java.awt.*;
import java.util.Random;

public class BacteriaCellEntity extends CellEntity {
    /**
     * Описывает бактерию
     */
    private final int BRAIN_SIZE = 64;
    private final int MAX_COMMAND_COUNT = 10;
    private int[] brain;
    private int nextCommand = 0;
    private int health = 20;
    private int eyeSize;
    private Directions cellDirection;

    public BacteriaCellEntity(Point p, int cellSize, int gridStroke) {
        super(p, Color.BLUE, cellSize, gridStroke);
        this.eyeSize = (int)Math.round(cellSize * 0.3) + ((int)Math.round(cellSize * 0.3)) % 2;
        brain = new int[BRAIN_SIZE];
        setCellDirection();
        fillBrain();
    }

    /**
     * Заполняет мозг бактерии случайными командами
     */
    private void fillBrain() {
        int minValue = 0;
        int maxValue = 63; // максимум 63
        for (int i = 0; i < BRAIN_SIZE; i++) {
            brain[i] = minValue + (int) (Math.random() * (maxValue - minValue));
        }
    }

    @Override
    public void update(WorldContext context) {
        // Сдвиг: яд - 1, стена - 2, бактерия - 3, еда - 4, пусто - 5
        int commandShift;
        int commandCount = 0;
        while (commandCount < MAX_COMMAND_COUNT) {
            int command = brain[nextCommand];
            //setCellDirection(command);
            if (command < 16) {
                commandShift = context.completeCommand(this, command);
                commandCount = MAX_COMMAND_COUNT;
            }
            else if (command < 24) {
                commandShift = context.completeCommand(this, command);
            }
            else if (command < 32) {
                rotateCell(command);
                commandShift = 1;
            }
            else commandShift = command; // безусловный переход

            commandCount++;

            nextCommand = (nextCommand + commandShift) % BRAIN_SIZE;

            decreaseHealth(1);
            if (health == 0) {
                context.killCell(this);
                commandCount = MAX_COMMAND_COUNT;
            }
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

    public int getEyeSize() {
        return eyeSize;
    }

    /**
     * Устанавливает случайное направление взгляда (используется в конструкторе бактерии)
     */
    private void setCellDirection(){
        cellDirection = Directions.values()[new Random().nextInt(Directions.values().length)];
    }

    private void setCellDirection(int command) {
        cellDirection = cellDirection.getStandardDirection(command);
    }

    /**
     * Поворачивает направление взгляда клетки
     * @param command
     */
    private void rotateCell(int command){
        cellDirection = cellDirection.getStandardDirection(command-23);
    }

    public Directions getCellDirection() {
        return cellDirection;
    }
}
