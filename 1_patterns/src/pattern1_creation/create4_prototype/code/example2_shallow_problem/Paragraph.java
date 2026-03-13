package pattern1_creation.create4_prototype.code.example2_shallow_problem;

/**
 * Абзац документа — мутабельный объект.
 */
public class Paragraph implements Cloneable {
    private String text;

    public Paragraph(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected Paragraph clone() throws CloneNotSupportedException {
        return (Paragraph) super.clone();
    }

    @Override
    public String toString() {
        return "Paragraph{\"" + text + "\"}";
    }
}
