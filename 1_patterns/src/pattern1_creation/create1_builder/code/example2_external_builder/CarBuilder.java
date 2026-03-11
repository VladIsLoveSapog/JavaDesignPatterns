package pattern1_creation.create1_builder.code.example2_external_builder;

/**
 * Внешний Builder для класса Car.
 * <p>
 * Этот класс находится в отдельном файле CarBuilder.java — отдельно от Car.
 * <p>
 * Преимущества внешнего Builder перед статическим вложенным:
 * ──────────────────────────────────────────────────────────
 * 1. Файл Car.java остаётся маленьким — только описание автомобиля.
 * 2. Можно создать несколько Builder-ов для одного продукта
 * (CarBuilder, SportCarBuilder, ElectricCarBuilder).
 * 3. Builder можно наследовать.
 * <p>
 * Недостатки:
 * ───────────
 * 1. Конструктор продукта должен быть public (вместо private у вложенного Builder).
 */
public class CarBuilder {

    // Обязательные поля
    private final String brand;
    private final String model;
    private final int year;

    // Опциональные поля с умолчаниями
    private String engineType = "бензиновый";       // наиболее распространённый тип
    private double engineVolume = 1.6;              // объём двигателя в литрах
    private String color = "белый";                 // самый популярный цвет в России
    private String transmission = "механическая";   // базовая КПП
    private boolean hasAC = false;                  // кондиционер не включён в базу
    private boolean hasLeatherInterior = false;     // стандартный тканевый салон
    private boolean hasNavigator = false;           // без встроенного навигатора
    private int numberOfDoors = 4;                  // стандартный седан/хэтчбек

    /**
     * Конструктор принимает обязательные параметры.
     *
     * @param brand марка автомобиля
     * @param model модель автомобиля
     * @param year  год выпуска
     */
    public CarBuilder(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public CarBuilder engineType(String engineType) {
        this.engineType = engineType;
        return this;
    }

    public CarBuilder engineVolume(double volume) {
        this.engineVolume = volume;
        return this;
    }

    public CarBuilder color(String color) {
        this.color = color;
        return this;
    }

    public CarBuilder transmission(String transmission) {
        this.transmission = transmission;
        return this;
    }

    /**
     * Включает кондиционер.
     * Называется withAC() (не hasAC(true)) — читается как "с кондиционером".
     */
    public CarBuilder withAC() {
        this.hasAC = true;
        return this;
    }

    /**
     * Включает кожаный салон.
     */
    public CarBuilder withLeatherInterior() {
        this.hasLeatherInterior = true;
        return this;
    }

    /**
     * Включает встроенный навигатор.
     */
    public CarBuilder withNavigator() {
        this.hasNavigator = true;
        return this;
    }

    public CarBuilder numberOfDoors(int doors) {
        if (doors < 2 || doors > 6) {
            throw new IllegalArgumentException("Количество дверей должно быть от 2 до 6");
        }
        this.numberOfDoors = doors;
        return this;
    }

    /**
     * Создаёт объект Car.
     *
     * @return готовый автомобиль
     */
    public Car build() {
        return new Car(this);
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
}
