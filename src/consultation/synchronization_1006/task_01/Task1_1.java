package consultation.synchronization_1006.task_01;

public class Task1_1 {
    public static void main(String[] args) {

        MyThread3 t3 = new MyThread3();
        t3.start(); // создается новый поток
        //t3.run(); // не создается поток

    }
}
