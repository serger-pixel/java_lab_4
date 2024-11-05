package Classes;
import java.util.Iterator;

/**
 * Класс итератора очереди
 */
public class QueueIterator implements Iterator<JavaDouble>{
    /**
     * Локальная копия очереди
     */
    private JavaQueue localQueue;
    
    /**
     * Конструткор с параметром
     * @param c - очередь
     */
    public QueueIterator(JavaQueue c){
        localQueue = new JavaQueue();
        localQueue.addAll(c);
    }
    
    /**
     * Получение следующего элемента
     * @return элемент очереди
     */
    @Override
    public JavaDouble next() {
        return localQueue.remove();
    }

    /**
     * Проверка на наличие следующего элемента
     * @return результат проверки
     */
    @Override
    public boolean hasNext() {
        return localQueue.size() >= 1;
    } 
}
