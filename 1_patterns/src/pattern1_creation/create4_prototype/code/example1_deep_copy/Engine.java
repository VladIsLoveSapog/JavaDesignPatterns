package pattern1_creation.create4_prototype.code.example1_deep_copy;

import java.util.Objects;

/**
 * Класс двигателя.
 */
public class Engine implements Cloneable {
    private final Integer hp;
    private final Integer volume;

    public Engine(Integer hp, Integer volume) {
        this.hp = hp;
        this.volume = volume;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getVolume() {
        return volume;
    }

    /**
     * Метод копирования.
     * super.clone(); - выполняет поверхностное копирование. И поскольку в классе двигателя все поля являются
     * примитивами поверхностного копирования достаточно.
     *
     * @return копия двигателя
     * @throws CloneNotSupportedException копируемые объекты не поддерживают Cloneable
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return Objects.equals(hp, engine.hp) && Objects.equals(volume, engine.volume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hp, volume);
    }

    @Override
    public String toString() {
        return "Engine{hp=" + hp + ", volume=" + volume + "}";
    }
}