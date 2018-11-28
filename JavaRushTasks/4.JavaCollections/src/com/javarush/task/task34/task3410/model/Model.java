package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;

//  Этот класс будет отвечать за модель нашей игры.
public class Model {
    // это размер ячейки игрового поля
    public final static int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("..\\res\\levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
    }

    // Этот метод проверяет столкновение со стеной
    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction))
                return true;
        }
        return false;
    }

    //  Этот метод проверяет столкновение с ящиками
    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {
        Player player = gameObjects.getPlayer();
        Box stoppedBox = null;

        for (GameObject gameObject : gameObjects.getAll()) {
            if (gameObject instanceof Box && player.isCollision(gameObject, direction))
                stoppedBox = (Box)gameObject;
        }

        if(stoppedBox == null)
            return false;

        if (checkWallCollision(stoppedBox, direction)) {
            return true;
        }

        for (Box box : gameObjects.getBoxes()) {
            if (stoppedBox.isCollision(box, direction))
                return true;
        }

        switch (direction) {
            case LEFT:
                stoppedBox.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                stoppedBox.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                stoppedBox.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                stoppedBox.move(0, FIELD_CELL_SIZE);
        }

        return false;
    }
}
