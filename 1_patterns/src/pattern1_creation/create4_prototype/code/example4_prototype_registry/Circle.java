package pattern1_creation.create4_prototype.code.example4_prototype_registry;

public class Circle extends Shape {
    private int radius;

    public Circle(String color, int x, int y, int radius) {
        super(color, x, y);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String getInfo() {
        return "Circle(r=" + radius + ")";
    }
}
