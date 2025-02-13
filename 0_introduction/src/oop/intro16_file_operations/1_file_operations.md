# Работа с файлами

В Java есть множество способов чтения и записи в файл, но их изучение и сравнение не в ходи в данный курс.

Работа с чтением/записью в Java может показаться сложной из-за обилия исключений....

## Запись

Для записи используем ```new BufferedWriter(new FileWriter("output.txt"));```

```BufferedWriter``` — это класс, который оборачивает другой ```Writer``` (например, ```FileWriter```) и добавляет
буферизацию для эффективной записи символов, строк и массивов символов. Буферизация означает, что данные сначала
накапливаются в буфере в памяти, а затем передаются в поток вывода крупными блоками, что снижает количество обращений к
физическому устройству ввода-вывода (например, диску).

Достаточно удобный ```writer```, поскольку поддерживает метод записи всего текста сразу.

### [Пример](code/FileWriteMain.java) записи

```java
public class Main {
    public static void main(String[] args) {
        String filePath = "0_introduction/src/oop/intro16_file_operations/text.txt"; // Путь к файлу
        String content = "Возможно вы захотите читать и/или сохранять данные в файл при выполнении лаборатоной работы."; // Содержимое для записи

        BufferedWriter writer = null;

        try {
            // Попытка открытия файла на запись сопровождается возможным IOException
            writer = new BufferedWriter(new FileWriter(filePath));

            // Попытка записи в файл сопровождается возможным IOException
            writer.write(content);

            System.out.println("Данные успешно записаны в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // В любом случае (с исключениями или без) необходимо закрыть поток записи
            try {
                // Попытка закрытия потока записи сопровождается возможным IOException
                writer.close();
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии файла: " + e.getMessage());
            }
        }
    }
}
```

## Чтение

Для записи используем ```new BufferedReader(new FileReader(filePath));```

```BufferedReader``` — это класс из пакета java.io, предназначенный для эффективного чтения текстовых данных из входного
потока. Он оборачивает другой ```Reader``` (например, ```FileReader```) и добавляет буферизацию для улучшения
производительности, уменьшая количество обращений к физическому устройству ввода-вывода (например, к диску).

### [Пример](code/FileReadMain.java)

```java
package oop.intro16_file_operations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Пример демонстрационный - а как оно было раньше....
 * Сейчас чуть лучше :)
 */
public class Main {
    public static void main(String[] args) {
        String filePath = "0_introduction/src/oop/intro16_file_operations/text.txt"; // Путь к файлу

        BufferedReader reader = null;
        try {
            // Попытка открытия файла на чтение сопровождается возможным IOException
            reader = new BufferedReader(new FileReader(filePath));

            // Попытка чтения из файла сопровождается возможным IOException
            String content = reader.readLine();

            System.out.println("Содержимое файла:");
            System.out.println(content);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // В любом случае (с исключениями или без) необходимо закрыть поток чтения
            try {
                // Попытка закрытия потока чтения сопровождается возможным IOException
                reader.close();
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии файла: " + e.getMessage());
            }
        }
    }
}
```

## Про ЛР

При выполнении ЛР абсолютно не важно будете ли вы работать с файлом или нет. Если будете, то не важно как. Важно
соблюсти все правила обработки и грамотной работы.