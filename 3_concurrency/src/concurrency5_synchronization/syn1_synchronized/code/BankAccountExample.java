package concurrency5_synchronization.syn1_synchronized.code;

/**
 * Синхронизированный блок с отдельным объектом-монитором — банковский счёт.
 *
 * <p>Вместо {@code synchronized(this)} используется <b>приватный объект {@code lock}</b>.
 * Это позволяет:</p>
 * <ul>
 *   <li>Синхронизировать только нужную часть метода, а не весь метод целиком.</li>
 *   <li>Скрыть монитор от внешнего кода — никто извне не сможет захватить тот же монитор.</li>
 *   <li>Иметь несколько независимых блокировок для разных частей объекта.</li>
 * </ul>
 *
 * <p>Все три метода (deposit, withdraw, getBalance) синхронизированы
 * <em>на одном и том же объекте {@code lock}</em> — это гарантирует, что
 * чтение и запись баланса никогда не происходят одновременно.</p>
 */
public class BankAccountExample {

    private int balance = 0;

    // Приватный монитор: внешний код не может захватить эту блокировку напрямую
    private final Object lock = new Object();

    /**
     * Пополнение счёта.
     */
    public void deposit(int amount) {
        synchronized (lock) { // Захватываем монитор объекта lock
            balance += amount;
            System.out.println("[" + Thread.currentThread().getName() + "] Пополнение +" + amount
                                       + ", баланс: " + balance);
        } // Монитор автоматически освобождается при выходе из блока
    }

    /**
     * Списание — только если хватает средств.
     */
    public void withdraw(int amount) {
        synchronized (lock) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("[" + Thread.currentThread().getName() + "] Списание -" + amount
                                           + ", баланс: " + balance);
            } else {
                System.out.println(
                        "[" + Thread.currentThread().getName() + "] Недостаточно средств (баланс: " + balance + ")");
            }
        }
    }

    /**
     * Потокобезопасное чтение баланса.
     */
    public int getBalance() {
        synchronized (lock) {
            return balance;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Синхронизированный блок: банковский счёт ===\n");

        BankAccountExample account = new BankAccountExample();

        // Поток пополнений: 5 раз по 200
        Thread depositor = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account.deposit(200);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Пополнение");

        // Поток списаний: 5 раз по 150
        Thread withdrawer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account.withdraw(150);
                try {
                    Thread.sleep(70);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Списание");

        depositor.start();
        withdrawer.start();
        depositor.join();
        withdrawer.join();

        System.out.println("\nИтоговый баланс: " + account.getBalance());
    }
}
