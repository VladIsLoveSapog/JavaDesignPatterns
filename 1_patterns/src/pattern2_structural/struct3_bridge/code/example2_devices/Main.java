package pattern2_structural.struct3_bridge.code.example2_devices;

/**
 * <b>Демонстрация</b> паттерна Bridge на примере пультов и устройств.
 *
 * <p>Показывает, как {@link Remote} (Абстракция) и {@link Device} (Реализация)
 * работают вместе через «мост»: пульт делегирует все операции устройству,
 * при этом обе иерархии расширяются независимо.
 *
 * <p><b>Что демонстрируется:</b>
 * <ul>
 *   <li>Обычный пульт {@link Remote} управляет телевизором {@link TV}</li>
 *   <li>Продвинутый пульт {@link AdvancedRemote} управляет радио {@link Radio}
 *       и добавляет функцию {@link AdvancedRemote#mute()}</li>
 * </ul>
 *
 * <p><b>Ключевая идея:</b> чтобы добавить новый пульт или новое устройство,
 * достаточно создать один класс — без комбинаторного взрыва подклассов.
 */
public class Main {

    /**
     * Точка входа — создаёт пульты, привязывает их к устройствам
     * и демонстрирует управление через «мост».
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {

        // --- Обычный пульт + Телевизор ---
        // Создаём конкретную реализацию (TV) и передаём в абстракцию (Remote)
        Device tv = new TV();
        Remote remote = new Remote(tv);

        // Включаем телевизор через пульт — делегируется в tv.enable()
        remote.togglePower();
        // Увеличиваем громкость на 10 — делегируется в tv.setVolume(tv.getVolume() + 10)
        remote.volumeUp();
        // Переключаем канал вперёд — делегируется в tv.setChannel(tv.getChannel() + 1)
        remote.channelUp();

        System.out.println("=== TV через обычный пульт (Remote) ===");
        System.out.println("Включён: " + tv.isEnable());       // true
        System.out.println("Громкость: " + tv.getVolume());     // 60 (50 по умолчанию + 10)
        System.out.println("Канал: " + tv.getChannel());        // 2  (1 по умолчанию + 1)
        System.out.println();

        // --- Продвинутый пульт + Радио ---
        // Тот же интерфейс Device, но другая конкретная реализация (Radio)
        Device radio = new Radio();
        AdvancedRemote advancedRemote = new AdvancedRemote(radio);

        // Включаем радио
        advancedRemote.togglePower();
        // Увеличиваем громкость на 10
        advancedRemote.volumeUp();
        // Используем функцию продвинутого пульта — mute() устанавливает громкость в 0
        advancedRemote.mute();

        System.out.println("=== Radio через продвинутый пульт (AdvancedRemote) ===");
        System.out.println("Включено: " + radio.isEnable());    // true
        System.out.println("Громкость: " + radio.getVolume());   // 0 (после mute)
        System.out.println("Канал: " + radio.getChannel());      // 1 (не менялся)
    }
}
