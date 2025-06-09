package consultation.io_0602;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CatsWriterReader {
    public static void main(String[] args) {

        List<Catty> cats = new ArrayList<>(List.of(

                new Catty("Cat", 9 , true),
                new Catty("John", 10 , true),
                new Catty("Maria", 4 , false),
                new Catty("Baris", 1 , true),
                new Catty("Anna", 1 , false),
                new Catty("Catty", 4 , true),
                new Catty("Mara", 2 , false),
                new Catty("John", 10 , true)

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
        writeCatsToFile(cats,file);

        // считать из файла кошек и вернуть список
        List<Catty> newCats = readCatsFromFile(file);

        newCats.forEach(System.out::println);

    }

    public static void writeCatsToFile(List<Catty> cats, File file) {

        // Удалить файл, если он существует
        if (file.exists()) file.delete();

        // Создадим файл если он не существует

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Catty cat:cats){
            // Открываю поток записи, ставлю флаг, разрешающий дозапись в файл

            try (
                    FileWriter fileWriter=new FileWriter(file,true);
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter)

            ){
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
        return new ArrayList<>();

    }
}
