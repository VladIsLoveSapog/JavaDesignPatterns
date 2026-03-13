package pattern2_structural.struct7_flyweight.code.example1_menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Клиент (Client) — демонстрация паттерна Легковес на примере заказов в ресторане.
 *
 * <p>Какую проблему мы решаем в данном случае?
 * Для каждой отдельной позиции в заказе на какой-то стол мы могли создавать
 * новый объект, но в данном случае они служат лишь обозначением и сами по себе
 * не имеют никакой функциональной нагрузки.
 *
 * <p>Допустим 3 стола заказали пиццу Carbonara.
 * Мы могли бы создать 3 разных объекта пиццы с названием Carbonara,
 * но поскольку пицца не несёт функциональной нагрузки помимо обозначения,
 * мы можем безопасно разделять один экземпляр между заказами.
 */
public class Main {
    //Наше меню
    public static MenuFactory menuFactory = new MenuFactory();

    /**
     * История заказов.
     * Указывается стол и список заказов для него
     */
    public static Map<Integer, List<MenuEntry>> orders = new HashMap<>();

    /**
     * Взятие заказа
     *
     * @param table    номер стола
     * @param position позиция меню
     */
    public static void takeOrder(Integer table, String position) {
        //Получаем позицию из меню (объект нужного блюда)
        var order = menuFactory.getPosition(position);

        //магия, схожая с merge, только возволяющая создавать объекты :)
        //Для уменьшения кол-ва кода в примере, можете не вникать и делать в лоб.
        orders.computeIfAbsent(table, k -> new ArrayList<>()).add(order);
    }

    public static void main(String[] args) {
        takeOrder(1, "Pizza Hawaii");
        takeOrder(2, "Pizza Hawaii");
        takeOrder(2, "Pizza Funghi");
        takeOrder(2, "Salad Caesar");
        takeOrder(3, "Pizza Carbonara");
        takeOrder(4, "Pizza Carbonara");
        takeOrder(4, "Salad Greek");
        takeOrder(4, "Pizza Calzone");

        //При выводе обратите внимание, что каждой пиццы существует только по 1 экземпляру
        //это видно по адресам в памяти
        orders.forEach((k, v) -> System.out.println(v));

        System.out.println("----------------");

        orders.forEach((k, v) -> v.forEach(item -> item.serve(k)));
    }
}
