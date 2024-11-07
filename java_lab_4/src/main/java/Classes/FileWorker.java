package Classes;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;

/**
 * Класс для работы с файлами
 */
public abstract class FileWorker {
    
    static String _regEmptyPath = "[/\\.]";
    /**
     * Проверка на открытие файла
     * @param path путь
     * @return объект класса FileWriter, иначе null
     */
    public static boolean isOpen(String path){
        return ( 
                !Pattern.matches(_regEmptyPath ,path.substring(path.length() - 1)) &&
                (new File(path)).exists());
    }
    /**
     * Метод для записи очереди в файл
     * @param path путь файла
     * @param queue очередь
     * @return результат записи
     */
    public static boolean write(String path, JavaQueue queue){
        try{
            FileWriter writer = new FileWriter(path);
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
