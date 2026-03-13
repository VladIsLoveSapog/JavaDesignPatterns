package pattern3_behavior.behavior3_command.code.example2_text_editor;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * История выполненных команд (вспомогательный класс).
 *
 * <p>Использует {@link Deque} в режиме стека (LIFO) для хранения
 * выполненных команд. Класс {@link Editor} (Invoker) добавляет команды
 * после выполнения и извлекает их при отмене.
 *
 * <p><b>Связь с паттерном Снимок (Memento):</b> {@code History} хранит
 * последовательность команд, каждая из которых содержит достаточно
 * информации для отмены. Это похоже на хранение снимков состояния,
 * но вместо полного состояния хранятся инкрементальные изменения.
 *
 * <p><b>Почему {@link Deque}, а не {@link java.util.Stack}:</b>
 * класс {@code Stack} унаследован от {@code Vector} и является устаревшим.
 * Документация Java рекомендует использовать {@code Deque} (например,
 * {@link ArrayDeque}) для реализации стека.
 */
public class History {

    /** Стек выполненных команд (LIFO). */
    private Deque<Command> commandHistory = new ArrayDeque<>();

    /**
     * Добавляет команду в вершину стека.
     *
     * @param cmd выполненная команда
     */
    public void push(Command cmd) {
        commandHistory.push(cmd);
    }

    /**
     * Извлекает и удаляет команду с вершины стека.
     *
     * @return последняя выполненная команда, или {@code null} если стек пуст
     */
    public Command pop() {
        if (!commandHistory.isEmpty()) return commandHistory.pop();
        return null;
    }

    /**
     * Проверяет, пуст ли стек команд.
     *
     * @return {@code true}, если команд в истории нет
     */
    public boolean isEmpty() {
        return commandHistory.isEmpty();
    }
}
