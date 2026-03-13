package pattern1_creation.create6_multiton.code.example1_shape_factory.shape;

/**
 * Конкретный продукт — прямоугольник.
 *
 * <p>Реализует интерфейс {@link Shape} и представляет геометрическую фигуру «прямоугольник».
 * Экземпляры создаются через фабрику, управляемую мультитоном
 * {@link pattern1_creation.create6_multiton.code.example1_shape_factory.ShapeFactoryMultiton ShapeFactoryMultiton},
 * что гарантирует единственность фабрики для данного типа фигуры.</p>
 */
public class Rectangle implements Shape {
    private String name;

    /**
     * Создаёт прямоугольник с указанным именем.
     *
     * @param name имя прямоугольника, используемое при отрисовке
     */
    public Rectangle(String name) {
        this.name = name;
    }

    /**
     * Выводит информацию о прямоугольнике в стандартный поток вывода.
     * Формат: {@code Рисование прямоугольника: <имя>}
     */
    @Override
    public void draw() {
        System.out.println("Рисование прямоугольника: " + name);
    }
}
