package oop.intro11_branching.code;

public class SwitchYieldMain {
    public static void main(String[] args) {
        int month = 2;
        int days = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> {
                System.out.println("Февраль");
                yield 28; // возвращаем значение через yield
            }
            default -> throw new IllegalArgumentException("Неверный месяц: " + month);
        };

        System.out.println("Дней в месяце: " + days);
    }
}
