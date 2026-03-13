package pattern1_creation.create4_prototype.code.example4_prototype_registry;

public class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(String color, int x, int y, int width, int height) {
        super(color, x, y);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String getInfo() {
        return "Rectangle(" + width + "x" + height + ")";
    }
}
