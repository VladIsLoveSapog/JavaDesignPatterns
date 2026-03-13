package pattern1_creation.create6_multiton.code.example1_shape_factory;

import pattern1_creation.create6_multiton.code.example1_shape_factory.shape.Circle;
import pattern1_creation.create6_multiton.code.example1_shape_factory.shape.Shape;

/**
 * Фабрика для создания экземпляров {@link Circle}.
 *
 * <p>Имеет <b>package-private</b> модификатор доступа — создать экземпляр этой фабрики
 * напрямую из другого пакета невозможно. Единственный способ получить эту фабрику — обратиться к мультитону
 * {@link ShapeFactoryMultiton#getInstance(ShapeType)} с ключом {@link ShapeType#CIRCLE}.</p>
 *
 * <p>Такой подход гарантирует, что для типа {@code CIRCLE} существует ровно
 * одна фабрика, контролируемая мультитоном.</p>
 */
class CircleFactory implements ShapeFactory {

    /**
     * Создаёт новый экземпляр {@link Circle} с указанным именем.
     *
     * @param name имя круга
     * @return новый экземпляр {@link Circle}
     */
    @Override
    public Shape createShape(String name) {
        return new Circle(name);
    }
}
