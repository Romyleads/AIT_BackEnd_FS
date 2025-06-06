package src.reflection.homework.infra;

import src.reflection.homework.model.Calculator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class CalculatorUtils {

    public static void caller(String action, String a, String b) {
        try {
            Class<Calculator> clazz = Calculator.class;

            Constructor<Calculator> constructor = clazz.getDeclaredConstructor();
            Calculator calculator = constructor.newInstance();

            // Вызываем метод operate, передавая название действия
            Method method = clazz.getDeclaredMethod("operate", String.class, String.class, String.class);
            method.invoke(calculator, a, b, action);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
