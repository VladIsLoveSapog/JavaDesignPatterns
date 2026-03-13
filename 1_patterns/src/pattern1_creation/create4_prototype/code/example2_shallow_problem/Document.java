package pattern1_creation.create4_prototype.code.example2_shallow_problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Документ содержит список абзацев (мутабельная коллекция).
 * Демонстрирует разницу между поверхностным и глубоким копированием.
 */
public class Document implements Cloneable {
    private String title;
    private List<Paragraph> paragraphs;

    public Document(String title) {
        this.title = title;
        this.paragraphs = new ArrayList<>();
    }

    public void addParagraph(Paragraph paragraph) {
        paragraphs.add(paragraph);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    /**
     * Поверхностное копирование — копируется только ссылка на список.
     * Оригинал и копия будут разделять один и тот же список абзацев!
     */
    public Document shallowClone() throws CloneNotSupportedException {
        return (Document) super.clone();
    }

    /**
     * Глубокое копирование — создаётся новый список,
     * каждый абзац копируется отдельно.
     */
    public Document deepClone() throws CloneNotSupportedException {
        Document copy = (Document) super.clone();
        copy.paragraphs = new ArrayList<>();
        for (Paragraph p : this.paragraphs) {
            copy.paragraphs.add(p.clone());
        }
        return copy;
    }

    @Override
    public String toString() {
        return "Document{title='" + title + "', paragraphs=" + paragraphs + "}";
    }
}
