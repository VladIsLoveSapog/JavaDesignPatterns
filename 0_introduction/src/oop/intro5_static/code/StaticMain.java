package oop.intro5_static.code;

public class StaticMain {
    public static void main(String[] args) {
        System.out.println(Counter.getCount());
        Counter.increaseCount();
        new Counter();
        System.out.println(Counter.getCount());
    }
}
