/*
===========================
ТОП-10 АННОТАЦИЙ LOMBOK 🔴
===========================

🔴 @Data
    // Создаёт: геттеры, сеттеры, toString(), equals(), hashCode(), required constructor

🔴 @Builder
    // Паттерн Builder: Test.builder().title("Hi").score(5).build()

🔴 @Getter
    // Генерирует getX() для всех полей

🔴 @Setter
    // Генерирует setX() для всех полей

🔴 @ToString
    // Генерирует читаемый вывод toString()

🔴 @EqualsAndHashCode
    // Генерирует equals() и hashCode()

🔴 @NoArgsConstructor
    // Конструктор без аргументов

🔴 @AllArgsConstructor
    // Конструктор со всеми полями

🔴 @SneakyThrows
    // Позволяет не писать throws / try-catch

🔴 @Slf4j
    // Добавляет логгер: log.info("...")

===========================
ДОПОЛНИТЕЛЬНЫЕ АННОТАЦИИ
===========================

@RequiredArgsConstructor
    // Конструктор для final и @NonNull полей

@Value
    // Как @Data, но все поля final (immutable класс)

@Accessors
    // Меняет стиль геттеров/сеттеров (например, fluent)

@Synchronized
    // Безопасный аналог synchronized (как synchronized-метод)

@Cleanup
    // Авто .close() в finally для try-with-resources

@Log, @Log4j, @CommonsLog
    // Аналоги @Slf4j для других логгеров

===========================
СТРУКТУРА И РЕКОМЕНДАЦИЯ
===========================

Рекомендуется использовать:
- @Data для моделей (если нет ограничений)
- @Builder для удобной инициализации
- @Slf4j для логирования
- @SneakyThrows для удобства в try/catch
- @NoArgsConstructor / @AllArgsConstructor — в зависимости от структуры класса

Полный список аннотаций — в документации: https://projectlombok.org/features
*/

public class Test {
}
