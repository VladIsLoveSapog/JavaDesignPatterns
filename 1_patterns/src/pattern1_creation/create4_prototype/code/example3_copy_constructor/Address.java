package pattern1_creation.create4_prototype.code.example3_copy_constructor;

/**
 * Адрес — мутабельный вложенный объект.
 */
public class Address {
    private String city;
    private String street;

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    /** Конструктор копирования */
    public Address(Address other) {
        this.city = other.city;
        this.street = other.street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return city + ", " + street;
    }
}
