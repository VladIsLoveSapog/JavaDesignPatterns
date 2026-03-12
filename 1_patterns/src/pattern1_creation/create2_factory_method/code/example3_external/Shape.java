package pattern1_creation.create2_factory_method.code.example3_external;

/**
 * Интерфейс продукта — геометрическая фигура.
 *
 * Все конкретные фигуры реализуют этот интерфейс.
 * Клиентский код и фабрика работают только с этим типом.
 */
public interface Shape {

    /**
     * Нарисовать фигуру (вывести описание).
     */
    public void draw();

    /**
     * Вычислить площадь фигуры.
     *
     * @return площадь в условных единицах
     */
    public double area();
}
