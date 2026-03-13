package pattern2_structural.struct2_facade.code.example1_car.engine;

/**
 * Топливный инжектор.
 * <p>
 * Управляет подачей топлива в камеру сгорания двигателя.
 * Использует {@link FuelPump} для нагнетания топлива при впрыске.
 * <p>
 * Является частью подсистемы двигателя, скрытой за фасадом.
 */
public class FuelInjector {

    /** Топливный насос для нагнетания топлива. */
    private FuelPump fuelPump = new FuelPump();

    /**
     * Включает топливный инжектор.
     */
    public void on() {
        System.out.println("Fuel Injector on.");
    }

    /**
     * Отключает топливный инжектор.
     */
    public void off() {
        System.out.println("Fuel Injector off.");
    }

    /**
     * Выполняет впрыск топлива, используя топливный насос.
     */
    public void inject() {
        System.out.println("Fuel Injector inject");
        fuelPump.pump();
    }
}
