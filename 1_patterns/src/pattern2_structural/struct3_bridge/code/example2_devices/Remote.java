package pattern2_structural.struct3_bridge.code.example2_devices;

/**
 * Пульт дистанционного управления — <b>Абстракция</b> (Abstraction) в паттерне Bridge.
 *
 * <p>Предоставляет высокоуровневый API для управления устройством:
 * переключение питания, регулировка громкости и переключение каналов.
 * Вся работа делегируется объекту {@link Device}, который хранится в поле
 * {@link #device} — это и есть «мост» между абстракцией и реализацией.
 *
 * <p><b>Почему делегирование, а не наследование:</b> если бы {@code Remote}
 * наследовал конкретное устройство, мы получили бы комбинаторный взрыв классов
 * (RemoteTV, RemoteRadio, AdvancedRemoteTV, AdvancedRemoteRadio...).
 * Композиция через {@code Device} позволяет комбинировать любой пульт с любым
 * устройством без создания дополнительных подклассов.
 *
 * <p><b>Расширяемость:</b> подклассы (например, {@link AdvancedRemote}) могут
 * добавлять новые операции (mute, избранные каналы и т.д.), не затрагивая
 * иерархию устройств.
 *
 * @see Device
 * @see AdvancedRemote
 */
public class Remote {

    /**
     * Ссылка на конкретное устройство — «мост» к Реализации.
     *
     * <p>{@code protected} — чтобы подклассы ({@link AdvancedRemote}) могли
     * напрямую обращаться к устройству и добавлять свои операции.
     */
    protected Device device;

    /**
     * Создаёт пульт, привязанный к указанному устройству.
     *
     * <p><b>Почему устройство передаётся через конструктор:</b> пульт не имеет
     * смысла без устройства, поэтому зависимость обязательна и задаётся при создании.
     *
     * @param device устройство, которым будет управлять этот пульт
     */
    public Remote(Device device) {
        this.device = device;
    }

    /**
     * Переключает питание устройства: если включено — выключает, если выключено — включает.
     *
     * <p>Делегирует вызов {@link Device#isEnable()}, {@link Device#enable()}
     * и {@link Device#disable()}.
     */
    void togglePower() {
        if (device.isEnable()) device.disable();
        else device.enable();
    }

    /**
     * Уменьшает громкость устройства на 10 единиц.
     *
     * <p>Делегирует вызов {@link Device#getVolume()} и {@link Device#setVolume(Integer)}.
     */
    void volumeDown() {
        device.setVolume(device.getVolume() - 10);
    }

    /**
     * Увеличивает громкость устройства на 10 единиц.
     *
     * <p>Делегирует вызов {@link Device#getVolume()} и {@link Device#setVolume(Integer)}.
     */
    void volumeUp() {
        device.setVolume(device.getVolume() + 10);
    }

    /**
     * Переключает канал на один назад (текущий канал минус 1).
     *
     * <p>Делегирует вызов {@link Device#getChannel()} и {@link Device#setChannel(Integer)}.
     */
    void channelDown() {
        device.setChannel(device.getChannel() - 1);
    }

    /**
     * Переключает канал на один вперёд (текущий канал плюс 1).
     *
     * <p>Делегирует вызов {@link Device#getChannel()} и {@link Device#setChannel(Integer)}.
     */
    void channelUp() {
        device.setChannel(device.getChannel() + 1);
    }
}
