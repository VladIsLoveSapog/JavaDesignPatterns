package pattern1_creation.create2_factory_method.code.example3_external;

/**
 * Демонстрация варианта «внешний класс со статическими фабричными методами».
 *
 * ShapeFactory полностью отделён от иерархии продуктов.
 * Клиентский код не создаёт фигуры напрямую через new — только через фабрику.
 * Это даёт централизованную валидацию и возможность менять логику создания
 * в одном месте.
 */
public class ShapeMain {
    public static void main(String[] args) {
        System.out.println("=== Создание фигур через ShapeFactory ===");
        System.out.println();

        // Создаём фигуры через статические методы фабрики
        Shape circle = ShapeFactory.createCircle(5.0);
        Shape rectangle = ShapeFactory.createRectangle(4.0, 6.0);
        Shape square = ShapeFactory.createSquare(3.0);

        // Работаем с фигурами через интерфейс Shape
        System.out.println("Фигура 1:");
        circle.draw();
        System.out.printf("  Площадь: %.2f%n", circle.area());

        System.out.println("Фигура 2:");
        rectangle.draw();
        System.out.printf("  Площадь: %.2f%n", rectangle.area());

        System.out.println("Фигура 3:");
        square.draw();
        System.out.printf("  Площадь: %.2f%n", square.area());

        System.out.println();

        // Демонстрация валидации в фабрике
        System.out.println("=== Попытка создать фигуру с неверными параметрами ===");
        try {
            Shape invalid = ShapeFactory.createCircle(-1.0); // должно выбросить исключение
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка перехвачена: " + e.getMessage());
        }
    }
}
