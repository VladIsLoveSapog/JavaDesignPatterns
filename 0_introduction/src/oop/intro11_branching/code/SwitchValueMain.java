package oop.intro11_branching.code;

public class SwitchValueMain {
    public static void main(String[] args) {
        String day = "TUESDAY";
        String activity = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY" -> "Рабочий день";
            case "THURSDAY", "FRIDAY" -> "Подготовка к выходным";
            case "SATURDAY", "SUNDAY" -> "Выходной";
            default -> "Неверный день";
        };

        System.out.println("Сегодня: " + activity);
    }
}
