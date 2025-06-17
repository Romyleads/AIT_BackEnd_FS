package consultation.io_0616;

import consultation.io_0602.Catty;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CatsWriterReader {
    public static void main(String[] args) {

        List<consultation.io_0602.Catty> cats = new ArrayList<>(List.of(

                new consultation.io_0602.Catty("Cat", 9, true),
                new consultation.io_0602.Catty("John", 10, true),
                new consultation.io_0602.Catty("Maria", 4, false),
                new consultation.io_0602.Catty("Baris", 1, true),
                new consultation.io_0602.Catty("Anna", 1, false),
                new consultation.io_0602.Catty("Catty", 4, true),
                new consultation.io_0602.Catty("Mara", 2, false),
                new consultation.io_0602.Catty("John", 10, true)

        )
        );

        File path = new File("src/consultation/io_0602/files");

        path.mkdirs();

        File file = new File(path, "cats.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // записать список кошек в файл
        writeCatsToFile(cats, file);

        // считать из файла кошек и вернуть список
        List<consultation.io_0602.Catty> newCats = readCatsFromFile(file);

        newCats.forEach(System.out::println);

    }

    public static void writeCatsToFile(List<consultation.io_0602.Catty> cats, File file) {

        // Удалить файл, если он существует
        if (file.exists()) file.delete();

        // Создадим файл если он не существует

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (consultation.io_0602.Catty cat : cats) {
            // Открываю поток записи, ставлю флаг, разрешающий дозапись в файл

            try (
                    FileWriter fileWriter = new FileWriter(file, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)

            ) {
                // name|age|isMale
                String toWriteStr = cat.getName() + "|" + cat.getAge() + "|" + cat.isMale();
                bufferedWriter.write(toWriteStr);
                bufferedWriter.newLine(); // перевод каретки на новую строку


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        System.out.println("Запись в файл завершена");

    }

    public static List<Catty> readCatsFromFile(File file) {
        List<Catty> cats = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            String line;

            // Буду считывать в цикле строки из файла, пока строки в файле есть

// Каждую строку буду считывать в переменную Line

            while ((line = bufferedReader.readLine()) != null) {

// Cat|9|true

                System.out.println("строка считана: " + line);

//TODO - BACCвесить в Јака объект (Cat) и добавить в список

                Catty cat = parseCatFromStringLine(line);

                cats.add(cat);

            }

        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

        return cats;

    }

    private static Catty parseCatFromStringLine(String line) {

//Cat 9/true

        //String concat = String.join("|", "name", "1","true");

// namee + 11+ | + true

        String[] strings = line.split("\\|");

        //  System.out.println("parse array: " + Arrays.toString(strings));


//

        //}

//[Cat, "9", "true"]

        return new Catty(strings[0], Integer.parseInt(strings[1]), Boolean.valueOf(strings[2]));

    }

}