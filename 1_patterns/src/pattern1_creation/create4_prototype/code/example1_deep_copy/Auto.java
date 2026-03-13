package pattern1_creation.create4_prototype.code.example1_deep_copy;


import java.util.Objects;

/**
 * Класс автомобиля. 10 параметров нужны для иллюстрации удобства использования паттерна Прототип (метод clone и
 * интерфейс Cloneable)
 * <p>
 * При переопределении метода clone важно чтобы соблюдалось следующее: - У объектов разные ссылки prototype != clone -
 * Классы у объектов идентичны prototype.getClass() == clone.getClass() - Объекты идентичны после копирования (для этого
 * надо правильно переопределить equals())
 */
public class Auto implements Cloneable {
    String owner;               //хозяин
    String brand;               //бренд авто
    Engine engine;              //тип двигателя
    Gearbox gearbox;            //тип коробки передач
    Color color;                //цвет автомобиля
    Integer mileage;            //пробег
    Integer seatCapacity;       //кол-во посадочных мест
    Integer wheelCount;         //кол-во колес
    Integer accidentsNumber;    //кол-во аварий
    Long price;                 //стоимость

    public Auto(String owner,
                String brand,
                Engine engine,
                Gearbox gearbox,
                Color color,
                Integer mileage,
                Integer seatCapacity,
                Integer wheelCount,
                Integer accidentsNumber,
                Long price) {
        this.owner = owner;
        this.brand = brand;
        this.engine = engine;
        this.gearbox = gearbox;
        this.color = color;
        this.mileage = mileage;
        this.seatCapacity = seatCapacity;
        this.wheelCount = wheelCount;
        this.accidentsNumber = accidentsNumber;
        this.price = price;
    }

    /**
     * Для проверки правильности копирования переопределим метод equals
     *
     * @param otherObject объект для сравнения
     * @return true если объекты идентичны
     */
    @Override
    public boolean equals(Object otherObject) {
        if (Objects.isNull(otherObject)) return false;
        if (this == otherObject) return true;
        if (getClass() != otherObject.getClass()) return false;

        Auto other = (Auto) otherObject;

        return owner.equals(other.owner) &&
                brand.equals(other.brand) &&
                engine.equals(other.engine) &&
                gearbox.equals(other.gearbox) &&
                color.equals(other.color) &&
                mileage.equals(other.mileage) &&
                seatCapacity.equals(other.seatCapacity) &&
                wheelCount.equals(other.wheelCount) &&
                accidentsNumber.equals(other.accidentsNumber) &&
                price.equals(other.price);
    }

    /**
     * Метод копирования. Сначала см. Engine
     * <p>
     * Поскольку класс Auto в качестве поля содержит класс Engine, то "поверхностного" копирования недостаточно. Такая
     * же проблема может быть при создании конструктора копирования в лоб. Обычно в Java для создания копий предпочитают
     * всё же clone, а не конструктор копирования.
     *
     * @return копию автомобиля
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Auto copy = (Auto) super.clone();           //поверхностно скопировали что можно
        copy.engine = (Engine) this.engine.clone(); //докопировали вложенные объекты
        return copy;
    }

    @Override
    public String toString() {
        return this.getClass() +
                "[owner=" + owner +
                ",brand=" + brand +
                ",engine=" + engine +
                ",gearbox=" + gearbox +
                ",color=" + color +
                ",mileage=" + mileage +
                ",seatCapacity=" + seatCapacity +
                ",wheelCount=" + wheelCount +
                ",accidentsNumber=" + accidentsNumber +
                ",price=" + price +
                "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                owner,
                brand,
                engine,
                gearbox,
                color,
                mileage,
                seatCapacity,
                wheelCount,
                accidentsNumber,
                price
        );
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Integer seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public Integer getWheelCount() {
        return wheelCount;
    }

    public void setWheelCount(Integer wheelCount) {
        this.wheelCount = wheelCount;
    }

    public Integer getAccidentsNumber() {
        return accidentsNumber;
    }

    public void setAccidentsNumber(Integer accidentsNumber) {
        this.accidentsNumber = accidentsNumber;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
