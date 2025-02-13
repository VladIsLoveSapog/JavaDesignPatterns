package oop.intro6_inheritance.code;

import java.util.Objects;

/**
 * Класс животного
 */
public class Animal {
    //Модификатор protected - закрывает от внешнего мира
    //Но оставляет прямой доступ в наследуемых классах
    protected String name;    //имя животного
    protected Integer age;    //возраст животного

    public Animal(String name, Integer age) {
        this.name = validateString(name, "Имя не может быть пустым");
        this.age = validateAge(age);
    }

    public void makeSound() {
        System.out.println("Животное издает звук.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = validateString(name, "Имя не может быть пустым");
    }

    // Геттер для возраста
    public int getAge() {
        return age;
    }

    // Сеттер для возраста с проверкой
    public void setAge(int age) {
        this.age = validateAge(age);
    }

    // Метод для проверки строки на пустое значение
    private String validateString(String value, String errorMessage) {
        if (Objects.isNull(value) || value.trim().isEmpty())
            throw new IllegalArgumentException(errorMessage);
        return value;
    }

    // Метод для проверки возраста на корректное значение
    private int validateAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Возраст не может быть отрицательным");
        return age;
    }

    /**
     * Корректное переопределение метода hashCode
     *
     * @return hash объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                age
        );
    }

    /**
     * Корректное переопределение метода equals.
     *
     * @param o объект для сравнения
     * @return true если объекты равны. Иначе false.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(age, animal.age) && Objects.equals(name, animal.name);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
