package pattern2_structural.struct2_facade.code.example1_car.engine;

/**
 * Радиатор системы охлаждения.
 * <p>
 * Отводит тепло от двигателя. Управляется {@link CoolingController контроллером охлаждения}.
 * <p>
 * Является частью подсистемы двигателя, скрытой за фасадом.
 */
public class Radiator {

    /**
     * Включает радиатор.
     */
    public void on() {
        System.out.println("Radiator on.");
    }

    /**
     * Отключает радиатор.
     */
    public void off() {
        System.out.println("Radiator off.");
    }

    /**
     * Устанавливает скорость вращения вентилятора радиатора.
     */
    public void setSpeed() {
        System.out.println("Radiator set speed.");
    }
}
