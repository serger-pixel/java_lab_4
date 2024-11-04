package Classes;
import java.io.DataOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.Queue;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileWriter;

/**
 * Класс очереди
 */
public class JavaQueue implements Queue<Double>{
    /**
     * Регулярное выражения для проверки дробного числа
     */
    static public String _regDouble = "^((([1-9]{1,13})([0]*))|([0]{1}))(\\.[0-9]*)?";
    /**
     * Индекс первого элемента
     */
    static public int _firstInd = 0;
    /**
     * Минимальное значение
     */
    static public Double minEl= 0.0;    
    /**
     * Внутрений контейнер
     */
    private Vector<Double> container;
    
    /**
     * Конструктор по умолчанию
     */
    public JavaQueue(){
        container =  new Vector<Double>();
    }
    
    /**
     * Конструктор с параметрами
     * @param seq последовательность с дробными числами
     */
    public JavaQueue(Vector<Double> seq){
        container = new Vector<Double>();
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
    public boolean addAll(Collection<? extends Double> c) {
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
    public Double remove(){
        Double el = container.firstElement();
        container.remove(_firstInd);
        return el;
    }
    
    /**
     * Возращение и удаление первого элемента, или возвращение null, если очередь пустая
     * @return первый элемент очереди
     */
    @Override
    public Double poll(){
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
    public Double peek(){
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
    public Double element(){
        return container.firstElement();
    }
    
    /**
     * Добавление элемента в конец очереди
     * @param el
     * @return 
     */
    @Override
    public boolean add(Double el){
        return container.add(el);
    }
    
    /**
     * Вставка элемента в очередь
     * @param el значение элемента
     * @return результат вставки
     */
    @Override
    public boolean offer(Double el){
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
            if (!doubleVer(el)){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Проверка, является ли строчка дробным числом
     * @param value строчка, включающая значение элемента
     * @return результат проверки
     */
    static public boolean doubleVer(String value){
       return Pattern.matches(_regDouble, value);
    }
    
    /**
     * Преобразование строки в вектор
     * @param input строчка со значениями
     * @return 
     */
    static public Vector<Double> stringToVector(String input){
        Vector<Double> localContainer = new Vector<Double>();
        String[] elements = input.split(" ");
        for (var el: elements){
            localContainer.add(Double.parseDouble(el));
        }
        return localContainer;
    }
    
    /**
     * Нахождение максимального элемента
     * @return максимальный элемент
     */
    public Double maxEl(){
        Double max = minEl;
        QueueIterator localIterator = iterator();
        while(localIterator.hasNext()){
            Double localEl = localIterator.next();
            if (max <= localEl){
                max = localEl;
            }
        }
        return max;
    }
    
    /**
     * Сортировка очереди(самый большой элемент на первом месте)
     */
    public void sort(){
        Double el = peek();
        Double max = maxEl();
        while(el != max){
            el = remove();
            add(el);
            el = peek();
        }
    }
    
    /**
     * Запись результата сортировки в файл
     */
    public void writeIntoFile(){
        try{
            FileWriter writer = new FileWriter("./src/main/java/results/data.txt", false);
//            DataOutputStream file= new DataOutputStream(new FileOutputStream("./src/main/java/results/data.txt"));
            QueueIterator localIterator = iterator();
            while(localIterator.hasNext()){
                writer.write((localIterator.next()).toString());
                writer.write(" ");
                }
            writer.flush();
        }
        catch(FileNotFoundException ex){
            System.out.println("File is not founded");
        }
        catch(IOException ex){
            System.out.println("Failed to make a record");
        }
        
    }
}
