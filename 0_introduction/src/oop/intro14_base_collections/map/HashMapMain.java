package oop.intro14_base_collections.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapMain {
    public static void main(String[] args) {
        Map<String, Integer> population = new HashMap<>();
        population.put("USA", 331);
        population.put("India", 1391);
        population.put("China", 1441);

        // Перебор элементов
        for (String country : population.keySet())
            System.out.println(country + ": " + population.get(country) + " млн");

        // Проверка на наличие ключа
        if (population.containsKey("USA"))
            System.out.println("Население США: " + population.get("USA") + " млн");
    }
}
