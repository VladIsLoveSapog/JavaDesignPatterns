package pattern1_creation.create6_multiton.code.example1_shape_factory;

import pattern1_creation.create6_multiton.code.example1_shape_factory.shape.Shape;

/**
 * Интерфейс фабрики фигур.
 *
 * <p>Определяет контракт для создания объектов {@link Shape}.
 * Конкретные реализации ({@code CircleFactory}, {@code RectangleFactory}, {@code TriangleFactory}) имеют
 * package-private модификатор доступа и доступны только через мультитон {@link ShapeFactoryMultiton}.</p>
 *
 * <p>Клиентский код получает экземпляр {@code ShapeFactory} из мультитона
 * и вызывает {@link #createShape(String)} для создания конкретной фигуры, не зная о реализации фабрики.</p>
 */
public interface ShapeFactory {

    /**
     * Создаёт новый экземпляр фигуры с указанным именем.
     *
     * @param name имя создаваемой фигуры
     * @return новый экземпляр {@link Shape}
     */
    Shape createShape(String name);
}
