package pattern1_creation.create7_object_pool.code.example2_bullet_pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Пул игровых снарядов — реализация паттерна Object Pool.
 *
 * <p>Управляет коллекцией объектов {@link Bullet}, выдавая неактивные
 * снаряды для повторного использования и принимая их обратно после
 * деактивации (попадания или выхода за экран).
 *
 * <p>В отличие от
 * {@link pattern1_creation.create7_object_pool.code.example1_connection_pool.ConnectionPool},
 * этот пул не использует синглтон, так как в игре может быть несколько типов
 * снарядов (пули, ракеты и т.д.), каждый со своим пулом.
 *
 * @see Bullet
 */
public class BulletPool {
    private final List<Bullet> pool;
    private final int maxSize;
    private final int defaultDamage;

    /**
     * Создаёт пул снарядов с начальным набором и ограничением по размеру.
     *
     * @param initialSize   количество снарядов, создаваемых при инициализации
     * @param maxSize       максимальное количество снарядов в пуле
     * @param defaultDamage урон по умолчанию для создаваемых снарядов
     */
    public BulletPool(int initialSize, int maxSize, int defaultDamage) {
        this.pool = new ArrayList<>();
        this.maxSize = maxSize;
        this.defaultDamage = defaultDamage;
        for (int i = 0; i < initialSize; i++) {
            pool.add(new Bullet(defaultDamage));
        }
        System.out.println("Пул снарядов создан: начальный размер = " + initialSize
                + ", максимум = " + maxSize);
    }

    /**
     * Выдаёт неактивный снаряд из пула.
     *
     * <p>Ищет первый неактивный снаряд. Если все активны и размер пула
     * меньше {@code maxSize}, создаёт новый. Если пул исчерпан —
     * выбрасывает {@link RuntimeException}.
     *
     * @return свободный {@link Bullet}
     * @throws RuntimeException если все снаряды активны и пул достиг максимального размера
     */
    public Bullet borrowBullet() {
        for (Bullet bullet : pool) {
            if (!bullet.isActive()) {
                return bullet;
            }
        }
        if (pool.size() < maxSize) {
            Bullet newBullet = new Bullet(defaultDamage);
            pool.add(newBullet);
            System.out.println("  Пул расширен, текущий размер: " + pool.size());
            return newBullet;
        }
        throw new RuntimeException("Пул снарядов исчерпан!");
    }

    /**
     * Возвращает снаряд в пул, сбрасывая его состояние.
     *
     * @param bullet снаряд для возврата
     */
    public void returnBullet(Bullet bullet) {
        bullet.deactivate();
        bullet.reset();
    }

    /**
     * Возвращает количество активных (выданных) снарядов.
     *
     * @return число активных снарядов
     */
    public int getActiveCount() {
        int count = 0;
        for (Bullet bullet : pool) {
            if (bullet.isActive()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Возвращает общий размер пула (активные + неактивные).
     *
     * @return размер пула
     */
    public int getPoolSize() {
        return pool.size();
    }

    /**
     * Выводит текущее состояние пула.
     */
    public void printStatus() {
        System.out.println("Состояние пула снарядов: всего = " + pool.size()
                + ", активных = " + getActiveCount()
                + ", свободных = " + (pool.size() - getActiveCount()));
    }
}
