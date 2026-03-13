package pattern2_structural.struct3_bridge.code.example2_devices;

/**
 * Телевизор — <b>Конкретная реализация</b> (Concrete Implementation) в паттерне Bridge.
 *
 * <p>Реализует интерфейс {@link Device}, предоставляя конкретное поведение
 * для телевизора: управление питанием, громкостью и каналами.
 *
 * <p><b>Почему это «Реализация», а не «Абстракция»:</b> в паттерне Bridge
 * иерархия устройств ({@code Device} -> {@code TV}, {@code Radio}) — это
 * сторона Реализации. Сторона Абстракции — это пульты ({@link Remote},
 * {@link AdvancedRemote}). Пульт делегирует работу устройству через «мост».
 *
 * <p><b>Независимость от пультов:</b> {@code TV} ничего не знает о том, какой
 * пульт им управляет. Можно подключить обычный {@link Remote} или продвинутый
 * {@link AdvancedRemote} — телевизор не меняется.
 *
 * @see Device
 * @see Remote
 */
public class TV implements Device {

    /**
     * Текущее состояние питания: {@code true} — включён, {@code false} — выключен.
     */
    private boolean status;

    /**
     * Номер текущего канала.
     *
     * <p>Инициализируется значением {@code 1}, чтобы избежать {@link NullPointerException}
     * при арифметических операциях с {@link Integer} до первого вызова {@link #setChannel(Integer)}.
     */
    private Integer channel = 1;

    /**
     * Текущий уровень громкости.
     *
     * <p>Инициализируется значением {@code 50}, чтобы избежать {@link NullPointerException}
     * при арифметических операциях с {@link Integer} до первого вызова {@link #setVolume(Integer)}.
     */
    private Integer volume = 50;

    /** {@inheritDoc} */
    @Override
    public boolean isEnable() {
        return this.status;
    }

    /**
     * Включает телевизор, устанавливая {@link #status} в {@code true}.
     */
    @Override
    public void enable() {
        this.status = true;
    }

    /**
     * Выключает телевизор, устанавливая {@link #status} в {@code false}.
     */
    @Override
    public void disable() {
        this.status = false;
    }

    /**
     * Возвращает текущий уровень громкости телевизора.
     *
     * @return текущая громкость
     */
    @Override
    public Integer getVolume() {
        return this.volume;
    }

    /**
     * Устанавливает уровень громкости телевизора.
     *
     * @param volume новое значение громкости
     */
    @Override
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    /**
     * Возвращает номер текущего канала телевизора.
     *
     * @return номер текущего канала
     */
    @Override
    public Integer getChannel() {
        return this.channel;
    }

    /**
     * Переключает телевизор на указанный канал.
     *
     * @param channel номер нового канала
     */
    @Override
    public void setChannel(Integer channel) {
        this.channel = channel;
    }
}
