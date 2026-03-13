package pattern1_creation.create7_object_pool.code.example2_bullet_pool;

import pattern1_creation.create7_object_pool.code.Reusable;

/**
 * Игровой снаряд (пуля) — повторно используемый объект для паттерна Object Pool.
 *
 * <p>В компьютерных играх снаряды создаются и уничтожаются очень часто
 * (десятки–сотни раз в секунду). Создание нового объекта каждый раз нагружает сборщик мусора и может вызвать заметные
 * «подтормаживания». Пул снарядов позволяет переиспользовать объекты без аллокаций.
 *
 * <p>Реализует {@link Reusable}, чтобы пул мог сбросить состояние
 * снаряда перед повторной выдачей.
 */
public class Bullet implements Reusable {
    private double x;
    private double y;
    private int damage;
    private boolean active;

    /**
     * Создаёт неактивный снаряд с нулевыми координатами.
     *
     * @param damage урон, наносимый снарядом при попадании
     */
    public Bullet(int damage) {
        this.damage = damage;
        this.x = 0;
        this.y = 0;
        this.active = false;
    }

    /**
     * Активирует снаряд в указанной позиции (выстрел).
     *
     * @param startX начальная координата X
     * @param startY начальная координата Y
     */
    public void activate(double startX, double startY) {
        this.x = startX;
        this.y = startY;
        this.active = true;
        System.out.println("  Снаряд активирован в позиции (" + x + ", " + y + ")");
    }

    /**
     * Деактивирует снаряд (попадание или выход за границы экрана).
     */
    public void deactivate() {
        this.active = false;
        System.out.println("  Снаряд деактивирован в позиции (" + x + ", " + y + ")");
    }

    /**
     * Перемещает снаряд вперёд на указанное расстояние по оси Y.
     *
     * @param deltaY смещение по оси Y
     */
    public void move(double deltaY) {
        this.y += deltaY;
    }

    /**
     * Сбрасывает снаряд в исходное состояние для повторного использования.
     */
    @Override
    public void reset() {
        this.x = 0;
        this.y = 0;
        this.active = false;
    }

    /**
     * Проверяет, активен ли снаряд.
     *
     * @return {@code true}, если снаряд в полёте
     */
    public boolean isActive() {
        return active;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Возвращает урон снаряда.
     *
     * @return урон при попадании
     */
    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "Bullet{x=" + x + ", y=" + y +
                ", damage=" + damage + ", active=" + active + '}';
    }
}
