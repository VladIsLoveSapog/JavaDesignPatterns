package pattern1_creation.create1_builder.code.example2_external_builder;

import java.util.Objects;

/**
 * Автомобиль — продукт паттерна Builder с ВНЕШНИМ Builder-классом.
 * <p>
 * Зачем выносить Builder в отдельный файл?
 * ────────────────────────────────────────
 * 1. Single Responsibility Principle: Car описывает автомобиль, CarBuilder — его сборку.
 * 2. Файл Car.java остаётся компактным и читаемым.
 * 3. Для одного продукта можно создать несколько Builder-ов:
 * CarBuilder (общий), ElectricCarBuilder (только для электромобилей) и т.д.
 */
public class Car {

    // Обязательные поля
    private final String brand;     // марка: Toyota, BMW, Tesla
    private final String model;     // модель: Camry, X5, Model 3
    private final int year;         // год выпуска

    // Опциональные поля с умолчаниями
    private final String engineType;            // тип двигателя: бензиновый, дизельный, электрический
    private final double engineVolume;          // объём двигателя в литрах (0.0 для электро)
    private final String color;                 // цвет кузова
    private final String transmission;          // тип КПП: механическая, автоматическая, вариатор
    private final boolean hasAC;                // есть ли кондиционер
    private final boolean hasLeatherInterior;   // кожаный салон
    private final boolean hasNavigator;         // встроенная навигация
    private final int numberOfDoors;            // количество дверей

    /**
     * Конструктор принимает Builder и копирует из него все поля.
     * Вызывается только из CarBuilder.build() — использовать CarBuilder напрямую.
     */
    public Car(CarBuilder builder) {
        this.brand = builder.getBrand();
        this.model = builder.getModel();
        this.year = builder.getYear();
        this.engineType = builder.getEngineType();
        this.engineVolume = builder.getEngineVolume();
        this.color = builder.getColor();
        this.transmission = builder.getTransmission();
        this.hasAC = builder.isHasAC();
        this.hasLeatherInterior = builder.isHasLeatherInterior();
        this.hasNavigator = builder.isHasNavigator();
        this.numberOfDoors = builder.getNumberOfDoors();
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getEngineType() {
        return engineType;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public String getColor() {
        return color;
    }

    public String getTransmission() {
        return transmission;
    }

    public boolean isHasAC() {
        return hasAC;
    }

    public boolean isHasLeatherInterior() {
        return hasLeatherInterior;
    }

    public boolean isHasNavigator() {
        return hasNavigator;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && Double.compare(engineVolume, car.engineVolume) == 0 && hasAC == car.hasAC && hasLeatherInterior == car.hasLeatherInterior && hasNavigator == car.hasNavigator && numberOfDoors == car.numberOfDoors && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(engineType, car.engineType) && Objects.equals(color, car.color) && Objects.equals(transmission, car.transmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year, engineType, engineVolume, color, transmission, hasAC, hasLeatherInterior, hasNavigator, numberOfDoors);
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", engineType='" + engineType + '\'' +
                ", engineVolume=" + engineVolume +
                ", color='" + color + '\'' +
                ", transmission='" + transmission + '\'' +
                ", hasAC=" + hasAC +
                ", hasLeatherInterior=" + hasLeatherInterior +
                ", hasNavigator=" + hasNavigator +
                ", numberOfDoors=" + numberOfDoors +
                '}';
    }
}
