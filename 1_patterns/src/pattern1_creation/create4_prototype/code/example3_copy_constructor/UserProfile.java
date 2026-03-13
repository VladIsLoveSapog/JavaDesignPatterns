package pattern1_creation.create4_prototype.code.example3_copy_constructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Профиль пользователя.
 * Демонстрирует создание копии через конструктор копирования
 * и статический фабричный метод — без Cloneable.
 */
public class UserProfile {
    private final String username;
    private String email;
    private Address address;
    private List<String> interests;

    public UserProfile(String username, String email, Address address, List<String> interests) {
        this.username = username;
        this.email = email;
        this.address = address;
        this.interests = interests;
    }

    /**
     * Конструктор копирования — глубокое копирование всех мутабельных полей.
     * Преимущества перед clone():
     * - Нет CloneNotSupportedException
     * - Нет приведения типов
     * - Вызывается конструктор (можно валидировать)
     * - Работает с final полями
     */
    public UserProfile(UserProfile other) {
        this.username = other.username;                     //String — неизменяемый, безопасно
        this.email = other.email;                           //String — неизменяемый, безопасно
        this.address = new Address(other.address);          //глубокое копирование через конструктор копирования
        this.interests = new ArrayList<>(other.interests);  //глубокое копирование коллекции
    }

    /**
     * Статический фабричный метод копирования
     */
    public static UserProfile copyOf(UserProfile other) {
        return new UserProfile(other);
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", interests=" + interests +
                '}';
    }
}
