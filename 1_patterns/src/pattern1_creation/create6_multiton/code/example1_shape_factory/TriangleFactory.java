package pattern1_creation.create6_multiton.code.example1_shape_factory;

import pattern1_creation.create6_multiton.code.example1_shape_factory.shape.Shape;
import pattern1_creation.create6_multiton.code.example1_shape_factory.shape.Triangle;

/**
 * Фабрика для создания экземпляров {@link Triangle}.
 *
 * <p>Имеет <b>package-private</b> модификатор доступа — создать экземпляр этой фабрики
 * напрямую из другого пакета невозможно. Единственный способ получить эту фабрику — обратиться к мультитону
 * {@link ShapeFactoryMultiton#getInstance(ShapeType)} с ключом {@link ShapeType#TRIANGLE}.</p>
 *
 * <p>Такой подход гарантирует, что для типа {@code TRIANGLE} существует ровно
 * одна фабрика, контролируемая мультитоном.</p>
 */
class TriangleFactory implements ShapeFactory {

    /**
     * Создаёт новый экземпляр {@link Triangle} с указанным именем.
     *
     * @param name имя треугольника
     * @return новый экземпляр {@link Triangle}
     */
    @Override
    public Shape createShape(String name) {
        return new Triangle(name);
    }
}
