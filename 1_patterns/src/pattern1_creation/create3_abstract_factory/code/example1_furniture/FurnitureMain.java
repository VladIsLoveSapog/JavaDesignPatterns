package pattern1_creation.create3_abstract_factory.code.example1_furniture;

import pattern1_creation.create3_abstract_factory.code.example1_furniture.baroque.BaroqueFurnitureFactory;
import pattern1_creation.create3_abstract_factory.code.example1_furniture.gotik.GoticFurnitureFactory;
import pattern1_creation.create3_abstract_factory.code.example1_furniture.modern.ModernFurnitureFactory;

/**
 * Демонстрация использования паттерна Абстрактная Фабрика на примере мебели.
 *
 * <p>Показывает основные сценарии работы с {@link AbstractFurnitureFactory}:</p>
 * <ul>
 *   <li>Создание семейств мебели в разных стилях через конкретные фабрики</li>
 *   <li>Переключение стиля изменением одной строки — присваиванием другой фабрики</li>
 *   <li>Клиент работает только с абстрактными типами {@link Chair},
 *       {@link Sofa}, {@link Table}, не зная конкретных классов продуктов</li>
 * </ul>
 */
public class FurnitureMain {
    public static void main(String[] args) {
        // Создание мебели в стиле модерн
        AbstractFurnitureFactory factory = new ModernFurnitureFactory();
        Chair modernChair = factory.createChair();
        Sofa modernSofa = factory.createSofa();
        Table modernTable = factory.createTable();
        System.out.println("Мебель в стиле модерн: " + modernChair + modernSofa + modernTable);

        // Переключение на стиль готика — достаточно изменить одну строку
        factory = new GoticFurnitureFactory();
        Chair goticChair = factory.createChair();
        Sofa goticSofa = factory.createSofa();
        Table goticTable = factory.createTable();
        System.out.println("Мебель в стиле готика: " + goticChair + goticSofa + goticTable);

        // Переключение на стиль барокко
        factory = new BaroqueFurnitureFactory();
        Chair baroqueChair = factory.createChair();
        Sofa baroqueSofa = factory.createSofa();
        Table baroqueTable = factory.createTable();
        System.out.println("Мебель в стиле барокко: " + baroqueChair + baroqueSofa + baroqueTable);
    }
}
