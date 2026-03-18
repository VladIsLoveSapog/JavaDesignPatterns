package spring4_di.xml.bean;

import java.util.Objects;

//Бин, демонстрирующий внедрение зависимости через сеттер в XML (property ref)
public class FlexibleBean {
    private IExampleBean exampleBean;
    private String text;

    public FlexibleBean() {
    }

    public IExampleBean getExampleBean() {
        return exampleBean;
    }

    public void setExampleBean(IExampleBean exampleBean) {
        this.exampleBean = exampleBean;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "exampleBean=" + exampleBean +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlexibleBean that = (FlexibleBean) o;
        return Objects.equals(exampleBean, that.exampleBean)
                && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exampleBean, text);
    }
}
