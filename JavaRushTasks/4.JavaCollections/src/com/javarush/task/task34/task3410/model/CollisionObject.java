package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public CollisionObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * Этот метод должен возвращаться true, если при перемещении текущего объекта в направлении direction на
     * FIELD_CELL_SIZE произойдет столкновение с объектом gameObject, переданным в качестве параметра.
     * Иначе - возвращать false. Столкновением считать совпадение координат x и y.
     *
     * @param gameObject
     * @param direction
     * @return
     */
    public boolean isCollision(GameObject gameObject, Direction direction) {
        switch (direction) {
            case UP:
                return (getX() == gameObject.getX()
                        && getY() - Model.FIELD_CELL_SIZE == gameObject.getY())
                        ? true : false;
            case RIGHT:
                return (getX() + Model.FIELD_CELL_SIZE == gameObject.getX()
                        && getY() == gameObject.getY())
                        ? true : false;
            case DOWN:
                return (getX() == gameObject.getX()
                        && getY() + Model.FIELD_CELL_SIZE == gameObject.getY())
                        ? true : false;
            case LEFT:
                return (getX() - Model.FIELD_CELL_SIZE == gameObject.getX()
                        && getY() == gameObject.getY())
                        ? true : false;
            default:
                return false;
        }
    }
}
