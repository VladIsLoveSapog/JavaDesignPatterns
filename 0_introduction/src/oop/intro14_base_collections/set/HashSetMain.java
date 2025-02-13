package oop.intro14_base_collections.set;

import java.util.HashSet;
import java.util.Set;

public class HashSetMain {
    public static void main(String[] args) {
        Set<String> colors = new HashSet<>();
        colors.add("Red");
        colors.add("Blue");
        colors.add("Green");
        colors.add("Red"); // Дубликат не добавится

        // Перебор элементов
        for (String color : colors) System.out.println(color);
    }
}
