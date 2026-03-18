package spring7_scope.singleton;

import java.util.Objects;

//Бин, инициализация которого прописывается в XML-файле
public class SingletonPerson {
    private String name;
    private String lastname;
    private Integer age;

    public SingletonPerson(String name,
                           String lastname,
                           Integer age) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingletonPerson that = (SingletonPerson) o;
        return Objects.equals(name, that.name)
                && Objects.equals(lastname, that.lastname)
                && Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, age);
    }
}
