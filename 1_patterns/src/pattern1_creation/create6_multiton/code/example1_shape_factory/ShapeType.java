package pattern1_creation.create6_multiton.code.example1_shape_factory;

/**
 * Перечисление типов фигур — ключ мультитона {@link ShapeFactoryMultiton}.
 *
 * <p>Каждый элемент перечисления соответствует ровно одной фабрике фигур.
 * Использование {@code enum} в качестве ключа позволяет применять {@link java.util.EnumMap} для эффективного и
 * типобезопасного хранения экземпляров мультитона.</p>
 *
 * <p>Для добавления нового типа фигуры достаточно добавить элемент
 * в это перечисление и зарегистрировать соответствующую фабрику в статическом блоке инициализации
 * {@link ShapeFactoryMultiton}.</p>
 */
public enum ShapeType {
    /**
     * Круг — соответствует фабрике {@code CircleFactory}.
     */
    CIRCLE,

    /**
     * Прямоугольник — соответствует фабрике {@code RectangleFactory}.
     */
    RECTANGLE,

    /**
     * Треугольник — соответствует фабрике {@code TriangleFactory}.
     */
    TRIANGLE
}
