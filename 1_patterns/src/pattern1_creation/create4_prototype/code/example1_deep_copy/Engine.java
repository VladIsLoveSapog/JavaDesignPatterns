package pattern1_creation.create4_prototype.code.example1_deep_copy;

/**
 * Класс двигателя. Обратите внимание, что раз данный класс - record, то методы toString, equals и hashCode для него по
 * умолчанию переопределяются правильно. Это пригодится при реализации данных методов в классе Auto.
 *
 * @param hp     лошадиные силы
 * @param volume объём
 */
public record Engine(Integer hp, Integer volume) implements Cloneable {
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
}
