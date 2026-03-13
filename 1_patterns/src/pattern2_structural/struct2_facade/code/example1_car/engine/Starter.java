package pattern2_structural.struct2_facade.code.example1_car.engine;

/**
 * Стартер двигателя.
 * <p>
 * Приводит в движение коленчатый вал для запуска двигателя.
 * <p>
 * Является частью подсистемы двигателя, скрытой за фасадом.
 */
public class Starter {

    /**
     * Запускает стартер.
     */
    public void start() {
        System.out.println("Start!!!");
    }
}
