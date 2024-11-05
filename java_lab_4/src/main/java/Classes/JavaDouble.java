
package Classes;

import java.util.regex.Pattern;

/**
 *Класс вещественного числа
 */
public class JavaDouble {
    /**
     * Регулярное выражение для проверки вещественного чилса
     */
    static public String _regDouble = "^([-]?)((([1-9]{1,13})([0]*))|([0]{1}))(\\.[0-9]*)?";
    /**
     * Значение по умолчанию
     */
    static public double _defaultValue = 0.0;

    /**
     * Конструктор по умолчанию
     */
    public JavaDouble() {
        _value = _defaultValue;
    }
    
    /**
     * Конструктор с параметрами
     * @param value значение
     */
    public JavaDouble(double value){
        _value = value;
    }
    
    
    /**
     * Проверка, является ли строчка дробным числом
     * @param value строчка, включающая значение элемента
     * @return результат проверки
     */
    static public boolean doubleVer(String value){
       return Pattern.matches(_regDouble, value);
    }
    
    private double _value;
    /**
     * Сеттер 
     * @param value значение
     * @return результат проверки
     */
    public boolean setValue(String value){
        if (doubleVer(value)){
            _value = Double.parseDouble(value);
            return true;
        }
        return false;
    }
    
    /**
     * Геттер
     * @return значение объекта
     */
    public double getValue(){
        return _value;
    }
}
