package pattern1_creation.create7_object_pool.code.example2_bullet_pool;

/**
 * Демонстрация паттерна Object Pool на примере пула игровых снарядов.
 *
 * <p>Программа симулирует упрощённый игровой цикл:
 * <ul>
 *   <li>Игрок стреляет — снаряд берётся из пула и активируется</li>
 *   <li>Снаряды летят — обновление позиции каждый «кадр»</li>
 *   <li>Снаряд попадает в цель — деактивируется и возвращается в пул</li>
 *   <li>Повторный выстрел — переиспользуется тот же объект</li>
 * </ul>
 *
 * @see BulletPool
 * @see Bullet
 */
public class GameMain {
    public static void main(String[] args) {
        // Создаём пул: 3 снаряда изначально, максимум 5, урон = 10
        BulletPool bulletPool = new BulletPool(3, 5, 10);
        System.out.println();

        // === 1. Серия выстрелов ===
        System.out.println("=== Серия выстрелов ===");
        Bullet b1 = bulletPool.borrowBullet();
        b1.activate(100, 0);

        Bullet b2 = bulletPool.borrowBullet();
        b2.activate(200, 0);

        Bullet b3 = bulletPool.borrowBullet();
        b3.activate(300, 0);

        bulletPool.printStatus();
        System.out.println();

        // === 2. Полёт снарядов (симуляция нескольких кадров) ===
        System.out.println("=== Полёт снарядов ===");
        for (int frame = 1; frame <= 3; frame++) {
            b1.move(50);
            b2.move(50);
            b3.move(50);
            System.out.println("  Кадр " + frame + ": b1.y=" + b1.getY()
                    + ", b2.y=" + b2.getY() + ", b3.y=" + b3.getY());
        }
        System.out.println();

        // === 3. Попадание — возврат снарядов в пул ===
        System.out.println("=== Попадание b1 и b3 — возврат в пул ===");
        System.out.println("  b1 нанёс " + b1.getDamage() + " урона!");
        bulletPool.returnBullet(b1);

        System.out.println("  b3 нанёс " + b3.getDamage() + " урона!");
        bulletPool.returnBullet(b3);

        bulletPool.printStatus();
        System.out.println();

        // === 4. Новые выстрелы — переиспользование объектов ===
        System.out.println("=== Новые выстрелы (переиспользование) ===");
        Bullet b4 = bulletPool.borrowBullet();
        b4.activate(150, 0);
        System.out.println("  b1 и b4 — один объект? " + (b1 == b4));

        Bullet b5 = bulletPool.borrowBullet();
        b5.activate(250, 0);
        System.out.println("  b3 и b5 — один объект? " + (b3 == b5));

        bulletPool.printStatus();
        System.out.println();

        // === 5. Исчерпание пула ===
        System.out.println("=== Исчерпание пула ===");
        // b2, b4, b5 активны (3 из 3), запросим ещё — пул расширится
        Bullet b6 = bulletPool.borrowBullet();
        b6.activate(350, 0);

        Bullet b7 = bulletPool.borrowBullet();
        b7.activate(400, 0);

        bulletPool.printStatus();

        // Все 5 из 5 заняты — следующий запрос выбросит исключение
        try {
            bulletPool.borrowBullet();
        } catch (RuntimeException e) {
            System.out.println("  Исключение: " + e.getMessage());
        }
    }
}
