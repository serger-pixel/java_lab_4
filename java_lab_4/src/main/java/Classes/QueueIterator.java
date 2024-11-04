package Classes;
import java.util.Iterator;

public class QueueIterator implements Iterator<Double>{
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
    public Double next() {
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
