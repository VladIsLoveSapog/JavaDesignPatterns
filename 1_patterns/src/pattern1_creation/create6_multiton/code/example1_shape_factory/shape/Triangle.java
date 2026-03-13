package pattern1_creation.create6_multiton.code.example1_shape_factory.shape;

/**
 * Конкретный продукт — треугольник.
 *
 * <p>Реализует интерфейс {@link Shape} и представляет геометрическую фигуру «треугольник».
 * Экземпляры создаются через фабрику, управляемую мультитоном
 * {@link pattern1_creation.create6_multiton.code.example1_shape_factory.ShapeFactoryMultiton ShapeFactoryMultiton},
 * что гарантирует единственность фабрики для данного типа фигуры.</p>
 */
public class Triangle implements Shape {
    private String name;

    /**
     * Создаёт треугольник с указанным именем.
     *
     * @param name имя треугольника, используемое при отрисовке
     */
    public Triangle(String name) {
        this.name = name;
    }

    /**
     * Выводит информацию о треугольнике в стандартный поток вывода.
     * Формат: {@code Рисование треугольника: <имя>}
     */
    @Override
    public void draw() {
        System.out.println("Рисование треугольника: " + name);
    }
}
