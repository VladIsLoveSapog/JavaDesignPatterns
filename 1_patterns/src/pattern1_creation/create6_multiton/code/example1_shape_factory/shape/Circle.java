package pattern1_creation.create6_multiton.code.example1_shape_factory.shape;

/**
 * Конкретный продукт — круг.
 *
 * <p>Реализует интерфейс {@link Shape} и представляет геометрическую фигуру «круг».
 * Экземпляры создаются через фабрику, управляемую мультитоном
 * {@link pattern1_creation.create6_multiton.code.example1_shape_factory.ShapeFactoryMultiton ShapeFactoryMultiton},
 * что гарантирует единственность фабрики для данного типа фигуры.</p>
 */
public class Circle implements Shape {
    private String name;

    /**
     * Создаёт круг с указанным именем.
     *
     * @param name имя круга, используемое при отрисовке
     */
    public Circle(String name) {
        this.name = name;
    }

    /**
     * Выводит информацию о круге в стандартный поток вывода.
     * Формат: {@code Рисование круга: <имя>}
     */
    @Override
    public void draw() {
        System.out.println("Рисование круга: " + name);
    }
}
