package spring4_di.annotation.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

//Бин, демонстрирующий внедрение зависимости через конструктор с @Autowired
@Component
public class Laptop {

    private final IProcessor processor;

    @Autowired
    public Laptop(@Qualifier("amdProcessor") IProcessor processor) {
        this.processor = processor;
    }

    public IProcessor getProcessor() {
        return processor;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "processor=" + processor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return Objects.equals(processor, laptop.processor);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(processor);
    }
}
