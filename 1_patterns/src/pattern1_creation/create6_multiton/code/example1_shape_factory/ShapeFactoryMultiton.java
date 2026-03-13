package pattern1_creation.create6_multiton.code.example1_shape_factory;

import java.util.EnumMap;
import java.util.Map;

import static pattern1_creation.create6_multiton.code.example1_shape_factory.ShapeType.*;

/**
 * Мультитон фабрик фигур.
 *
 * <p>Управляет фиксированным набором экземпляров {@link ShapeFactory},
 * по одному на каждый элемент {@link ShapeType}. Использует {@link EnumMap} для типобезопасного и эффективного хранения
 * фабрик.</p>
 *
 * <h3>Жадная инициализация</h3>
 * <p>Все фабрики создаются в статическом блоке инициализации при загрузке класса.
 * Это гарантирует потокобезопасность создания без необходимости синхронизации на уровне проверки существования
 * экземпляра (double-checked locking не нужен).</p>
 *
 * <h3>Контроль доступа</h3>
 * <p>Конкретные фабрики ({@code CircleFactory}, {@code RectangleFactory},
 * {@code TriangleFactory}) имеют package-private модификатор доступа. Единственный способ получить фабрику извне пакета
 * — через метод {@link #getInstance(ShapeType)}.</p>
 *
 * <h3>Пример использования</h3>
 * <pre>{@code
 * ShapeFactory factory = ShapeFactoryMultiton.getInstance(ShapeType.CIRCLE);
 * Shape circle = factory.createShape("Мой круг");
 * circle.draw();
 * }</pre>
 *
 * @see ShapeFactory
 * @see ShapeType
 */
public class ShapeFactoryMultiton {

    /**
     * Хранилище экземпляров мультитона: один экземпляр {@link ShapeFactory} на каждый {@link ShapeType}.
     */
    private static final Map<ShapeType, ShapeFactory> instance = new EnumMap<>(ShapeType.class);

    static {
        instance.put(CIRCLE, new CircleFactory());
        instance.put(RECTANGLE, new RectangleFactory());
        instance.put(TRIANGLE, new TriangleFactory());
    }

    /**
     * Приватный конструктор для предотвращения создания экземпляров класса-мультитона.
     */
    private ShapeFactoryMultiton() {
    }

    /**
     * Возвращает единственный экземпляр фабрики для указанного типа фигуры.
     *
     * <p>Метод синхронизирован для обеспечения потокобезопасности при
     * одновременном доступе из нескольких потоков.</p>
     *
     * @param type тип фигуры, для которого запрашивается фабрика
     * @return экземпляр {@link ShapeFactory}, соответствующий указанному типу
     */
    public static synchronized ShapeFactory getInstance(ShapeType type) {
        return instance.get(type);
    }
}
