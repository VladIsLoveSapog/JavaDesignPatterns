package oop.intro12_loops.code;

import java.util.ArrayList;
import java.util.List;

public class ForEachMain {
    public static void main(String[] args) {
        // Используем for-each для перебора массива
        int[] numbers = {1, 2, 3, 4, 5};
        for (int num : numbers) System.out.println("Число: " + num);


        // Используем for-each для перебора элементов списка
        List<String> fruits = new ArrayList<>();
        fruits.add("Яблоко");
        fruits.add("Банан");
        fruits.add("Апельсин");
        for (String fruit : fruits) System.out.println("Фрукт: " + fruit);

    }
}
