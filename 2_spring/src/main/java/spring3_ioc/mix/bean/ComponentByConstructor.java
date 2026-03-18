package spring3_ioc.mix.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Компонент, инициализация которого происходит через конструктор.
 * Компонент сразу создается через конструктор с параметрами.
 * <p>
 * Аннотация @Component указывает, что объекты данного класса будут бинами, т.е.
 * будут создаваться и управляться Spring Container.
 * Если к аннотации @Component не прописать bean id, то бину будет назначен дефолтный id.
 * Дефолтный bean id получается из имени класса, заменяя его первую заглавную букву на прописную.
 * <p>
 * Аннотация @PropertySource указывает файл, в котором лежат данные для подстановки при помощи аннотации @Value
 */
@Component
@PropertySource("component.property")
public class ComponentByConstructor {
    private String host;
    private String port;
    private String creationsType;
    private Integer connections;

    /**
     * Конструктор с параметрами.
     * Параметры беруться из resources/component.property
     */
    public ComponentByConstructor(@Value("${componentByConstructor.host}") String host,
                                  @Value("${componentByConstructor.port}") String port,
                                  @Value("${componentByConstructor.creationsType}") String creationsType,
                                  @Value("${componentByConstructor.connections}") Integer connections) {
        this.host = host;
        this.port = port;
        this.creationsType = creationsType;
        this.connections = connections;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCreationsType() {
        return creationsType;
    }

    public void setCreationsType(String creationsType) {
        this.creationsType = creationsType;
    }

    public Integer getConnections() {
        return connections;
    }

    public void setConnections(Integer connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", creationsType='" + creationsType + '\'' +
                ", connections=" + connections +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentByConstructor that = (ComponentByConstructor) o;
        return Objects.equals(host, that.host)
                && Objects.equals(port, that.port)
                && Objects.equals(creationsType, that.creationsType)
                && Objects.equals(connections, that.connections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port, creationsType, connections);
    }
}
