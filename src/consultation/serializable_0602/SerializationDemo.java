package consultation.serializable_0602;

import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {
        Person person = new Person("Alice", 25);

        File path = new File("src/consultation/serializable_0602/files");

        // mkdirs(),mkdirs()

        path.mkdirs();

        File file = new File(path, "text.txt");

        try {

            // создавать новый файл, если файл не существует
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("file is file: " + file.isFile());
        System.out.println("file is directory: " + file.isDirectory());

        // Сериализация с буферизацией

        try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(file))

        )){
        out.writeObject(person);
        System.out.println("Объект сериализован в файл: "+ file.getAbsolutePath());


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Десериализация с буферизацией

        try(ObjectInputStream in = new ObjectInputStream(

            new BufferedInputStream(new FileInputStream(file))
        ))

        {
            Person restored = (Person) in.readObject();
            System.out.println("Объект восстановлен: " + restored);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
