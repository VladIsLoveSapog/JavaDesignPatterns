package pattern1_creation.create4_prototype.code.example4_prototype_registry;

public class RegistryMain {
    /**
     * Демонстрация реестра прототипов (Prototype Registry) —
     * классическая реализация паттерна из книги GoF.
     */
    public static void main(String[] args) {
        //Создаём реестр и регистрируем шаблоны
        ShapeRegistry registry = new ShapeRegistry();
        registry.register("red-circle", new Circle("красный", 0, 0, 50));
        registry.register("blue-rect", new Rectangle("синий", 0, 0, 100, 200));

        System.out.println("=== Создание фигур из реестра ===");

        //Создаём фигуры из шаблонов — каждый вызов возвращает новый объект
        Shape circle1 = registry.create("red-circle");
        Shape circle2 = registry.create("red-circle");

        //Изменяем один клон — остальные не затронуты
        circle1.setX(10);
        circle1.setY(20);

        circle2.setX(100);
        circle2.setY(200);
        circle2.setColor("зелёный");

        System.out.println("Круг 1: " + circle1);
        System.out.println("Круг 2: " + circle2);
        System.out.println("Это разные объекты? " + (circle1 != circle2));
        System.out.println();

        Shape rect = registry.create("blue-rect");
        rect.setX(50);
        System.out.println("Прямоугольник: " + rect);

        //Шаблон в реестре не изменился
        System.out.println("Шаблон в реестре: " + registry.create("blue-rect"));
    }
}
