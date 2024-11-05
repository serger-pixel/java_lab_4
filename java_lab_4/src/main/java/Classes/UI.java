package Classes;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Vector;

/**
 * Класс интерфейса
 */
public class UI {
    /**
     *  Объект сканера
     */
    private Scanner _sc; 
    /**
     * Сообщение для ввода пользователя
     */
    static private final String _messInput = "Enter double numbers splitted by space";
    /**
     * Сообщение ошибки
     */
    static private final String _messErr = "The pattern of numbers -  0-9.0-9 or"
            + " 0-9.(1-9)(0-9)";
    
    static public final String _messPath = "Enter path";
    
    static public final String _messPathError = "Wrong path";
            
    static public final String _messWriteErr = "Can't write in file";
    /**
     * Конструктор по умолчанию
     */
    public UI() {
        _sc = new Scanner(System.in);
    }

    /**
     * Запуск приложения
     */
    public void startApplication(){
        String userInput;
        do{
           System.out.println(_messInput); 
           userInput = scanInput();
           if (JavaQueue.stringDoublVer(userInput)){
               break;
           }
            System.out.println(_messErr);
        }while(!JavaQueue.stringDoublVer(userInput));
        Vector<JavaDouble> mass = JavaQueue.stringToVector(userInput);
        JavaQueue queue = new JavaQueue(mass);
        queue.sort();
        System.out.println(_messPath);
        userInput = scanInput();
        FileWriter writer = FileWorker.isOpen(userInput);
        while (writer == null){
            System.out.println(_messPathError);
            System.out.println(_messPath);
            userInput = scanInput();
        }
        FileWorker.write(writer, queue);
    }
    
    /**
     * Получение ввода пользователя
     * @return введённую строчку
     */
    private String scanInput(){
        String inputUser = _sc.nextLine();
        return inputUser;
    }
    
}