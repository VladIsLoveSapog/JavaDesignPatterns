# Комбинация наследования и реализации

Ниже приведён пример, демонстрирующий, как класс может наследоваться от другого класса и одновременно реализовывать
несколько интерфейсов.

Предположим, у нас есть базовый класс ```Device```, который задаёт общие свойства для устройств, и два
интерфейса – ```Camera``` и ```GPS```, задающие функциональность камеры и ```GPS``` соответственно. Затем
класс ```Smartphone``` наследует ```Device``` и реализует оба интерфейса.

```java
// Базовый класс
public class Device {
    private String brand;

    public Device(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void powerOn() {
        System.out.println("Устройство включено.");
    }
}

// Интерфейс для работы с камерой
public interface Camera {
    void takePhoto();
}

// Интерфейс для работы с GPS
public interface GPS {
    void getLocation();
}

// Класс Smartphone наследует Device и реализует интерфейсы Camera и GPS
public class Smartphone extends Device implements Camera, GPS {

    public Smartphone(String brand) {
        super(brand);
    }

    @Override
    public void takePhoto() {
        System.out.println("Смартфон делает фотографию.");
    }

    @Override
    public void getLocation() {
        System.out.println("Смартфон определяет текущее местоположение с помощью GPS.");
    }

    public void useApps() {
        System.out.println("Использование приложений на смартфоне.");
    }
}

// Пример использования
public class Main {
    public static void main(String[] args) {
        Smartphone myPhone = new Smartphone("Samsung");
        myPhone.powerOn();             // Метод из базового класса Device
        System.out.println("Бренд: " + myPhone.getBrand());
        myPhone.takePhoto();           // Реализация метода из интерфейса Camera
        myPhone.getLocation();         // Реализация метода из интерфейса GPS
        myPhone.useApps();             // Собственный метод класса Smartphone
    }
}
```

- ```Device```: Базовый класс с полем brand и методами для получения информации об устройстве.
- ```Camera``` и ```GPS```: Интерфейсы, которые задают контракт для методов ```takePhoto()``` и ```getLocation()```
  соответственно.
- ```Smartphone```: Класс, который расширяет ```Device``` (то есть наследует его поля и методы) и одновременно реализует
  два интерфейса, предоставляя конкретную реализацию методов ```takePhoto()``` и ```getLocation()```.

Таким образом, класс ```Smartphone``` получает возможность использовать функциональность базового класса и, при этом,
обязуется реализовать методы, определённые в интерфейсах. Это пример комбинации наследования и множественной реализации
интерфейсов.
