package pattern1_creation.create4_prototype.code.example4_prototype_registry;

/**
 * Абстрактная фигура — базовый прототип.
 * Использует ковариантный возвращаемый тип в clone().
 */
public abstract class Shape implements Cloneable {
    private String color;
    private int x;
    private int y;

    public Shape(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            //Не произойдёт — Shape реализует Cloneable
            throw new AssertionError(e);
        }
    }

    public abstract String getInfo();

    // геттеры и сеттеры

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return getInfo() + " [color=" + color + ", x=" + x + ", y=" + y + "]";
    }
}
