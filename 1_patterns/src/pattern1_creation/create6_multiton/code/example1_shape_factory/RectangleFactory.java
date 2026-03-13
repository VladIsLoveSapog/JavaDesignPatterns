package pattern1_creation.create6_multiton.code.example1_shape_factory;

import pattern1_creation.create6_multiton.code.example1_shape_factory.shape.Rectangle;
import pattern1_creation.create6_multiton.code.example1_shape_factory.shape.Shape;

/**
 * Фабрика для создания экземпляров {@link Rectangle}.
 *
 * <p>Имеет <b>package-private</b> модификатор доступа — создать экземпляр этой фабрики
 * напрямую из другого пакета невозможно. Единственный способ получить эту фабрику — обратиться к мультитону
 * {@link ShapeFactoryMultiton#getInstance(ShapeType)} с ключом {@link ShapeType#RECTANGLE}.</p>
 *
 * <p>Такой подход гарантирует, что для типа {@code RECTANGLE} существует ровно
 * одна фабрика, контролируемая мультитоном.</p>
 */
class RectangleFactory implements ShapeFactory {

    /**
     * Создаёт новый экземпляр {@link Rectangle} с указанным именем.
     *
     * @param name имя прямоугольника
     * @return новый экземпляр {@link Rectangle}
     */
    @Override
    public Shape createShape(String name) {
        return new Rectangle(name);
    }
}
