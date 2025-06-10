package multiThreadindIntro.homework;

import java.util.Scanner;

public class TarakansRaceApp {

    // Номер таракана, который победит
    private static int winnerNumber = 0;

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество тараканов: ");
        int numberOfCockroaches = scanner.nextInt();

        System.out.print("Введите расстояние (итерации): ");
        int raceDistance = scanner.nextInt();

        // Создаём массив для всех тараканьих потоков
        Thread[] cockroachThreads = new Thread[numberOfCockroaches];

        for (int i = 0; i < numberOfCockroaches; i++) {
            int cockroachNumber = i + 1;

            // Создаем таракана
            CockroachRunner cockroach = new CockroachRunner(cockroachNumber, raceDistance);

            // Добавляем таракана в поток
            cockroachThreads[i] = new Thread(cockroach);

            // Запускаем поток
            cockroachThreads[i].start();
        }

        // ждем главным потоком пока завершатся циклы
        for (Thread cockroachThread : cockroachThreads) {
            cockroachThread.join();
        }

        System.out.println("Гонка завершена!");
        if (winnerNumber != 0) {
            System.out.println("Победил таракан #" + winnerNumber + " (winner)!");
        } else {
            System.out.println("Никто не победил!");
        }
    }

    static void trySetWinner(int cockroachNumber) {
        if (winnerNumber == 0) {
            winnerNumber = cockroachNumber;
        }
    }
}