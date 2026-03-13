package pattern2_structural.struct2_facade.code.example1_car.engine;

/**
 * Каталитический конвертер (катализатор).
 * <p>
 * Очищает выхлопные газы, снижая выброс вредных веществ.
 * Включается при запуске двигателя и отключается при останове.
 * <p>
 * Является частью подсистемы двигателя, скрытой за фасадом.
 */
public class CatalyticConverter {

    /**
     * Включает каталитический конвертер.
     */
    public void on() {
        System.out.println("CatalyticConverter on");
    }

    /**
     * Отключает каталитический конвертер.
     */
    public void off() {
        System.out.println("CatalyticConverter off");
    }
}
