package reflection.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class TableInfo {
    public static void runInfo(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        runInfo(clazz);
    }

    public static void runInfo(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            System.out.println(clazz.getName() + " not a Scheme"); // если не таблица
            return; // - завершение
        }
        Table tableAnn = clazz.getAnnotation(Table.class); // по объекту рефлексии получить анотацию
        String tableName = "".equals(tableAnn.name()) ? clazz.getSimpleName().toLowerCase() : tableAnn.name();
        Field[] fields = clazz.getDeclaredFields();
        List<String> idFields = Arrays.stream(fields)
                .filter(f -> f.isAnnotationPresent(Id.class))
                .map(Field::getName)
                .toList();
        List<String> uniqueIndexes = Arrays.stream(fields)
                .filter(f -> f.isAnnotationPresent(Index.class))
                .filter(f -> f.getAnnotation(Index.class).unique())
                .map(Field::getName)
                .toList();
        List<String> nonUniqueIndexes = Arrays.stream(fields)
                .filter(f -> f.isAnnotationPresent(Index.class))
                .filter(f -> !f.getAnnotation(Index.class).unique())
                .map(Field::getName)
                .toList();
        if (idFields.size() > 1) {
            throw new RuntimeException("Id duplicated");
        }
        if(idFields.isEmpty()){
            throw new RuntimeException("Id is not defined");
        }
        printTableInfo(tableName, idFields.get(0), uniqueIndexes, nonUniqueIndexes);
    }

    private static void printTableInfo(String tableName, String idField, List<String> uniqueIndexes, List<String> nonUniqueIndexes) {
        System.out.println("Table: " + tableName);
        System.out.println("Id: " + idField);
        System.out.println("\tUnique Indexes");
        uniqueIndexes.forEach(System.out::println);
        System.out.println("\tNon Unique Indexes");
        nonUniqueIndexes.forEach(System.out::println);
    }
}
