package multiThreading_06_Integral_Thread_Pool.task;

import java.util.function.Function;

// Этот класс реализует Runnable и будет использоваться в отдельном потоке
public class SumRectangles implements Runnable {

    // Левая граница интеграла
    private double a;

    // Правая граница интеграла
    private double b;

    // Функция f(x), которую нужно интегрировать (например, x -> x * x)
    private Function<Double, Double> func;

    // Общее количество прямоугольников (точек разбиения)
    private int nParts;

    // Общее количество потоков (задач)
    private int nTasks;

    // Номер текущего потока (от 0 до nTasks - 1)
    private int threadNumber;

    // Сюда будет записан результат вычислений этого потока
    private double result;

    // Конструктор — передаём границы, функцию, общее количество задач и номер потока
    public SumRectangles(double a, double b, Function<Double, Double> func,
                         int nParts, int nTasks, int threadNumber) {
        this.a = a;
        this.b = b;
        this.func = func;
        this.nParts = nParts;
        this.nTasks = nTasks;
        this.threadNumber = threadNumber;
    }

    // Метод для получения результата работы потока
    public double getResult() {
        return result;
    }

    // Основной метод — запускается при вызове thread.start()
    @Override
    public void run() {
        // Ширина одного прямоугольника
        double delta = (b - a) / nParts;

        // Поток обрабатывает каждый nTasks-й прямоугольник, начиная с threadNumber
        for (int i = threadNumber; i < nParts; i += nTasks) {
            // Левая и правая граница прямоугольника
            double l = a + i * delta;
            double r = l + delta;

            // Считаем середину прямоугольника (метод средней точки)
            double mid = (l + r) / 2;

            // Площадь прямоугольника = f(mid) * ширина
            result += delta * func.apply(mid);
        }
    }
}
