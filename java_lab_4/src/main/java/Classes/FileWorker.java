package Classes;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Класс для работы с файлами
 */
public abstract class FileWorker {
    /**
     * Проверка на открытие файла
     * @param path путь
     * @return объект класса FileWriter, иначе null
     */
    public static FileWriter isOpen(String path){
        try{
            FileWriter writer = new FileWriter(path, false);
            return writer;
        }
        catch(IOException ex){
            return null;
        }
    }
    /**
     * Метод для записи очереди в файл
     * @param path путь файла
     * @param queue очередь
     * @return результат записи
     */
    public static boolean write(FileWriter writer, JavaQueue queue){
        try{
            QueueIterator localIterator = queue.iterator();
            while(localIterator.hasNext()){
                JavaDouble el = localIterator.next();
                writer.write(el.getValue() + " ");
                }
            writer.flush();
            return true;
        }
        catch(IOException ex){
            return false;
        }
    }
}
