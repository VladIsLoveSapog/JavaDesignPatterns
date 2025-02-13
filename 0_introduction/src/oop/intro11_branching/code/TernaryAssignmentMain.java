package oop.intro11_branching.code;

public class TernaryAssignmentMain {
    public static void main(String[] args) {
        int age = 18;

        // Присваиваем статус на основе возраста
        String status = (age >= 18) ? "Взрослый" : "Несовершеннолетний";

        System.out.println("Статус: " + status);
    }
}
