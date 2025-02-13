package oop.intro13_generics.code;

/**
 * Дженерик класс
 *
 * @param <T> тип с которым будет работать класс.
 */

class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

public class BoxMain {
    public static void main(String[] args) {
        Box<Integer> intBox = new Box<>();
        intBox.set(123);
        System.out.println(intBox.get() + " является Integer? "+ (intBox.get() instanceof Integer));

        Box<String> strBox = new Box<>();
        strBox.set("Hello");
        System.out.println(strBox.get() + " является String? "+ (strBox.get() instanceof String));
    }
}