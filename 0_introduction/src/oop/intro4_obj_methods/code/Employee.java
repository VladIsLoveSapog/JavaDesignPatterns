package oop.intro4_obj_methods.code;


import java.util.Objects;
import java.util.UUID;

/**
 * Класс работника с правильно переопределенными методами
 * наследованными от Object:
 * - equals
 * - hashCode
 * - toString
 */
public class Employee {
    private UUID id;            //идентификатор
    private String firstName;   //имя
    private String lastName;    //фамилия

    // Конструктор с параметрами
    public Employee(String firstName, String lastName, UUID id) {
        this.id = Objects.requireNonNull(id, "Идентификатор не может быть null");
        this.firstName = validateString(firstName, "Имя не может быть пустым");
        this.lastName = validateString(lastName, "Фамилия не может быть пустой");
    }

    // Геттер для имени
    public String getFirstName() {
        return firstName;
    }

    // Сеттер для имени с проверкой
    public void setFirstName(String firstName) {
        this.firstName = validateString(firstName, "Имя не может быть пустым");
    }

    // Геттер для фамилии
    public String getLastName() {
        return lastName;
    }

    // Сеттер для фамилии с проверкой
    public void setLastName(String lastName) {
        this.lastName = validateString(lastName, "Фамилия не может быть пустой");
    }

    // Геттер для идентификатора
    public UUID getId() {
        return id;
    }

    // Сеттер для идентификатора с проверкой
    public void setId(UUID id) {
        this.id = Objects.requireNonNull(id, "Идентификатор не может быть null");
    }

    /**
     * Корректное переопределение метода equals для сравнения объектов.
     * Сравнение объектов по содержимому.
     *
     * @param o объект для сравнения
     * @return true если объекты равны, иначе false.
     */
    @Override
    public boolean equals(Object o) {
        //если две ссылки указывают на один объект, то он равен сам себе
        if (this == o) return true;

        //если объект для сравненич null, то они не равны
        if (Objects.isNull(o)) return false;

        //если объекты не относятся к одному классу, то они не равны
        if (getClass() != o.getClass()) return false;

        //явное приведение объекта для сравнения к типу
        //проверка равенства полей объекта
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id)
                && Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName);
    }

    /**
     * Корректное переопределение метода hashCode.
     * У всех базовых типов и коллекций уже есть коректная реализация метода hashCode.
     * Конструкция Objects.hash() вызывает методы hashCode переданных полей и подсчитывает
     * общий hash(). Нам необходимо лишь вызвать hash методы полей объекта.
     *
     * @return hash объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                firstName,
                lastName
        );
    }

    /**
     * Базовое переопределение toString.
     *
     * @return строковое представление объекта.
     */
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    /**
     * Проверка строки
     *
     * @param value        строка для проверки
     * @param errorMessage сообщение об ошибке
     * @return корректное значение
     */
    private String validateString(String value, String errorMessage) {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        return value;
    }
}
