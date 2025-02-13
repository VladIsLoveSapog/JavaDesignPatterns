package oop.intro4_obj_methods.code;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Класс проекта с правильно переопределенными методами
 * наследованными от Object:
 * - equals
 * - hashCode
 * - toString
 */
public class Project {
    private UUID id;                    // Идентификатор проекта
    private ProjectStatus status;       // Статус проекта
    private Employee author;            // Автор проекта
    private LocalDateTime startDate;    // Дата начала проекта
    private LocalDateTime endDate;      // Дата сдачи проекта

    /**
     * Конструктор с параметрами
     */
    public Project(ProjectStatus status, Employee author, LocalDateTime startDate, LocalDateTime endDate, UUID id) {
        this.id = Objects.requireNonNull(id, "Идентификатор не может быть null");
        this.status = Objects.requireNonNull(status, "Статус не может быть null");
        this.author = Objects.requireNonNull(author, "Автор не может быть null");
        this.startDate = Objects.requireNonNull(startDate, "Дата начала не может быть null");
        this.endDate = Objects.requireNonNull(endDate, "Дата сдачи не может быть null");
        validateDates(startDate, endDate);  // Проверка последовательности дат
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = Objects.requireNonNull(id, "Идентификатор не может быть null");
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = Objects.requireNonNull(status, "Статус не может быть null");
    }

    public Employee getAuthor() {
        return author;
    }

    public void setAuthor(Employee author) {
        this.author = Objects.requireNonNull(author, "Автор не может быть null");
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = Objects.requireNonNull(startDate, "Дата начала не может быть null");
        validateDates(startDate, this.endDate);  // Проверка последовательности дат
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = Objects.requireNonNull(endDate, "Дата сдачи не может быть null");
        validateDates(this.startDate, endDate);  // Проверка последовательности дат
    }

    /**
     * Метод для проверки корректности дат (дата начала должна быть раньше даты сдачи)
     *
     * @param startDate дата начала
     * @param endDate   дата сдачи
     */
    private void validateDates(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate))
            throw new IllegalArgumentException("Дата начала не может быть позже даты сдачи");
    }

    /**
     * Корректное переопределение метода equals.
     * ВАЖНО!!! Когда объект содержит внутри ссылки на другие объекты,
     * то у них тоже должен быть корректно переопределен метод equals.
     *
     * @param o объект для сравнения
     * @return true, если объекты равны. Иначе false.
     */
    @Override
    public boolean equals(Object o) {
        //если две ссылки указывают на один объект, то он равен сам себе
        if (this == o) return true;

        //если объект для сравненич null, то они не равны
        if (Objects.isNull(o)) return false;

        //если объекты не относятся к одному классу, то они не равны
        if (getClass() != o.getClass()) return false;

        //явное приведение объекта для сравнения к типу
        //проверка равенства полей объекта
        Project project = (Project) o;
        return status == project.status &&
                Objects.equals(author, project.author) &&
                Objects.equals(startDate, project.startDate) &&
                Objects.equals(endDate, project.endDate) &&
                Objects.equals(id, project.id);
    }

    /**
     * Корректное переопределение метода hashCode.
     * ВАЖНО!!! Когда объект содержит внутри ссылки на другие объекты,
     * то у них тоже должен быть корректно переопределен метод hashCode.
     *
     * @return hash объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                status,
                author,
                startDate,
                endDate,
                id);
    }

    /**
     * Переопределение метода toString.
     * Чтобы вложенные объекты корректно отображались у них также должен быть
     * переопределен метод toString.
     *
     * @return строкове представление объекта
     */
    @Override
    public String toString() {
        return "Project{" +
                "status=" + status +
                ", author=" + author +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", id=" + id +
                '}';
    }
}
