package oop.intro2_pojo;

public class PersonPojo {
    // Приватные поля для хранения данных
    private String name;
    private int age;

    // Конструктор по умолчанию
    public PersonPojo() {}

    // Конструктор с параметрами для удобства создания объекта с начальными значениями
    public PersonPojo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Геттер для получения имени
    public String getName() {
        return name;
    }

    // Сеттер для установки имени
    public void setName(String name) {
        this.name = name;
    }

    // Геттер для получения возраста
    public int getAge() {
        return age;
    }

    // Сеттер для установки возраста
    public void setAge(int age) {
        this.age = age;
    }
}

