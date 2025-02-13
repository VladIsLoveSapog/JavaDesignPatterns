package oop.intro11_branching.code;

public class IfElseIfElseMain {
    public static void main(String[] args) {
        int score = 75;

        // Проверка различных условий
        if (score >= 90) {
            System.out.println("Отлично");
        } else if (score >= 75) {
            System.out.println("Хорошо");
        } else if (score >= 50) {
            System.out.println("Удовлетворительно");
        } else {
            System.out.println("Неудовлетворительно");
        }
    }
}
