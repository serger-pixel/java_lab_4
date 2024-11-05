package Classes;
import java.io.DataOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.Queue;
import java.util.Vector;

/**
 * Класс очереди
 */
public class JavaQueue implements Queue<JavaDouble>{
    /**
     * Индекс первого элемента
     */
    static public int _firstInd = 0;
    /**
     * Минимальное значение
     */
    static public JavaDouble minEl= new JavaDouble();    
    /**
     * Внутрений контейнер
     */
    private Vector<JavaDouble> container;
    
    /**
     * Конструктор по умолчанию
     */
    public JavaQueue(){
        container =  new Vector<JavaDouble>();
    }
    
    /**
     * Конструктор с параметрами
     * @param seq последовательность с дробными числами
     */
    public JavaQueue(Vector<JavaDouble> seq){
        container = new Vector<JavaDouble>();
        container.addAll(seq);
    }

    /**
     * Сохранение только те элемент очереди, которые содержаться в коллекии
     * @param c коллекция
     * @return результат выполнения операции
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return container.retainAll(c);
    }

    /**
     * Удаление только тех элемент очереди, которые содержаться в коллекии
     * @param c коллекция
     * @return результат выполнения операции
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return container.removeAll(c);
    }

    /**
     * Добавление элементов коллекции в конец очереди
     * @param c коллекция
     * @return результат выполнения операции
     */
    @Override
    public boolean addAll(Collection<? extends JavaDouble> c) {
        return container.addAll(c);
    }

    /**
     * Проверка наличия элементов коллекции в очереди
     * @param c коллекция
     * @return результат проверки
     */
    @Override
    public boolean containsAll(Collection c) {
        return container.containsAll(c);
    }

    /**
     * Удаление элемента из очереди
     * @param o элемент
     * @return результат выполнения операции
     */
    @Override
    public boolean remove(Object o) {
        return container.remove(o);
    }

    /**
     * Преобразование очереди к массиву и сохранение его в другом массиве
     * @return результат выполнения операции
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return container.toArray(a);
    }

    /**
     * Преобразование очереди к массиву
     * @return результат выполнения операции
     */
    @Override
    public Object[] toArray() {
        return container.toArray();
    }
    
    /**
     * Создание итератора
     * @return 
     */
    @Override
    public QueueIterator iterator() {
        return new QueueIterator(this);
    }

    /**
     * Проверка наличия элемента
     * @param o элемент
     * @return результат проверки
     */
    @Override
    public boolean contains(Object o) {
        return container.contains(o);
    }

    /**
     * Проверка на пустоту очереди
     * @return результат проверки
     */
    @Override
    public boolean isEmpty() {
        return container.isEmpty();
    }

    /**
     * Возвращение размера очереди
     * @return размер очереди
     */
    @Override
    public int size() {
        return container.size();
    }
    
    /**
     * Удаление всех элементов очереди
     */
    @Override
    public void clear(){
        while(!container.isEmpty()){
            remove();
        }
    }
    
    /**
     * Возращение и удаление первого элемента
     * @return первый элемент очереди
     */
    @Override
    public JavaDouble remove(){
        JavaDouble el = container.firstElement();
        container.remove(_firstInd);
        return el;
    }
    
    /**
     * Возращение и удаление первого элемента, или возвращение null, если очередь пустая
     * @return первый элемент очереди
     */
    @Override
    public JavaDouble poll(){
        if (container.isEmpty()){
            return null;
        }
        return remove();
    }
    
    /**
     * Возращение первого элемента или null
     * @return первый элемен
     */
    @Override
    public JavaDouble peek(){
        if (container.isEmpty()){
            return null;
        }
        return element();
    }
    
    /**
     * Возращение первого элемента
     * @return первый элемен
     */
    @Override
    public JavaDouble element(){
        return container.firstElement();
    }
    
    /**
     * Добавление элемента в конец очереди
     * @param el
     * @return 
     */
    @Override
    public boolean add(JavaDouble el){
        return container.add(el);
    }
    
    /**
     * Вставка элемента в очередь
     * @param el значение элемента
     * @return результат вставки
     */
    @Override
    public boolean offer(JavaDouble el){
        try{
            return container.add(el);
        }
        catch(OutOfMemoryError ex){
            return false;
        }
    }
    
    /**
     * Проверка строчки на наличие только дробных чисел
     * @param input строчка, включающая элементы
     * @return результат проверки
     */
    static public boolean stringDoublVer(String input){
        String[] elements = input.split(" ");
        for (var el: elements){
            if (!JavaDouble.doubleVer(el)){
                return false;
            }
        }
        return true;
    }
    
   
    /**
     * Преобразование строки в вектор
     * @param input строчка со значениями
     * @return 
     */
    static public Vector<JavaDouble> stringToVector(String input){
        Vector<JavaDouble> localContainer = new Vector<JavaDouble>();
        String[] elements = input.split(" ");
        for (var el: elements){
            localContainer.add(new JavaDouble(Double.parseDouble(el)));
        }
        return localContainer;
    }
    
    /**
     * Нахождение максимального элемента
     * @return максимальный элемент
     */
    public JavaDouble maxEl(){
        JavaDouble max = minEl;
        QueueIterator localIterator = iterator();
        while(localIterator.hasNext()){
            JavaDouble localEl = localIterator.next();
            if (max.getValue() <= localEl.getValue()){
                max = localEl;
            }
        }
        return max;
    }
    
    /**
     * Сортировка очереди(самый большой элемент на первом месте)
     */
    public void sort(){
        JavaDouble el = peek();
        JavaDouble max = maxEl();
        while(el.getValue() != max.getValue()){
            el = remove();
            add(el);
            el = peek();
        }
    }
}
