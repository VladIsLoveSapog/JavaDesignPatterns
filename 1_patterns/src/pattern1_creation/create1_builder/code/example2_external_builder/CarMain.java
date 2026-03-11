package pattern1_creation.create1_builder.code.example2_external_builder;

/**
 * Демонстрация паттерна Builder с внешним Builder-классом.
 *
 * Фокус: CarBuilder — отдельный публичный класс в том же пакете.
 * Показывает плюсы (чистота кода, несколько Builder-ов)
 * и особенности (public конструктор у продукта — компромисс внешнего Builder).
 */
public class CarMain {

    public static void main(String[] args) {

        System.out.println("=== Пример 2: Внешний Builder — Car + CarBuilder ===\n");

        // ─── Сценарий 1: Базовая комплектация ────────────────────────────────
        System.out.println("── Сценарий 1: Базовая комплектация ──");
        // Только обязательные параметры — все опциональные берутся из умолчаний:
        // бензиновый 1.6л, белый цвет, механика, без допов, 4 двери
        Car basic = new CarBuilder("Lada", "Granta", 2023)
                .build();
        System.out.println(basic);
        System.out.println();

        // ─── Сценарий 2: Люкс-комплектация ───────────────────────────────────
        System.out.println("── Сценарий 2: Люкс-комплектация ──");
        Car luxury = new CarBuilder("BMW", "X5", 2024)
                .engineType("бензиновый")
                .engineVolume(3.0)
                .color("чёрный металлик")
                .transmission("автоматическая")
                .withAC()
                .withLeatherInterior()
                .withNavigator()
                .numberOfDoors(5)
                .build();
        System.out.println(luxury);
        System.out.println();

        // ─── Сценарий 3: Электромобиль ────────────────────────────────────────
        System.out.println("── Сценарий 3: Электромобиль ──");
        // У электромобиля нет объёма двигателя — передаём 0.0
        Car electric = new CarBuilder("Tesla", "Model 3", 2024)
                .engineType("электрический")
                .engineVolume(0.0)
                .color("красный")
                .transmission("вариатор")
                .withAC()
                .withLeatherInterior()
                .withNavigator()
                .build();
        System.out.println(electric);
        System.out.println();

        // ─── Сценарий 4: Переиспользование Builder ────────────────────────────
        System.out.println("── Сценарий 4: Переиспользование Builder (два похожих авто) ──");
        // Builder можно переиспользовать — создаём два автомобиля одной модели, но разного цвета.
        // Обратите внимание: CarBuilder — изменяемый объект, поэтому второй вызов build()
        // создаст точно такой же автомобиль, если не изменить поля.
        CarBuilder sharedBuilder = new CarBuilder("Toyota", "Camry", 2023)
                .engineVolume(2.5)
                .transmission("автоматическая")
                .withAC();

        Car camryWhite = sharedBuilder.color("белый").build();
        Car camryBlack = sharedBuilder.color("чёрный").build();

        System.out.println("Камри белая:  " + camryWhite);
        System.out.println("Камри чёрная: " + camryBlack);
    }
}
