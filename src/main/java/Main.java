import Base.*;

import java.util.Comparator;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String... args) {

      Trader raoul = new Trader( "Raoul", "Cambridge");
      Trader mario = new Trader( "Mario", "Milan");
      Trader alan  = new Trader( "Alan",  "Cambridge");
      Trader brian = new Trader( "Brian", "Cambridge");

      List<Transaction> transactions = Arrays.asList(
          new Transaction(brian, 2011, 300),
          new Transaction(raoul, 2012, 1000),
          new Transaction(raoul, 2011, 400),
          new Transaction(mario, 2012, 710),
          new Transaction(mario, 2012, 700),
          new Transaction(alan,  2012, 950)
      );

      getLine();

      // 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей
      // к большей).

      getMsg("Все транзакции за 2011 год, отсортированые их по сумме.");

      transactions.stream()
          .filter(transaction -> transaction.getYear() == 2011)
          .sorted((tr1, tr2) -> (tr1.equals(tr2) ? 1 : -1))
          .forEach(System.out::println);

      getLine();

      // 2. Вывести список неповторяющихся городов, в которых работают трейдеры.

      getMsg("Список неповторяющихся городов, в которых работают трейдеры.");

      transactions.stream()
          .map(transaction -> transaction.getTrader().getCity())
          .collect(Collectors.toSet())
          .forEach(System.out::println);

      getLine();

      // 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.

      getMsg("Все трейдеры из Кембриджа, отсортированные по именам.");

      transactions.stream()
          .filter(transaction -> transaction.getTrader().getCity().equals(raoul.getCity()))
          .collect(Collectors.toSet())
          .forEach(transaction -> System.out.println(transaction.getTrader().getName()));

      getLine();

      // 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном
      // порядке.
      String result = transactions.stream()
          .map(Transaction::getTrader)
          .map(Trader::getName)
          .distinct()
          .sorted()
          .collect(Collectors.joining(", "));

      getMessage("Строка со всеми именами трейдеров, отсортированными в алфавитном порядке", result);

      // 5. Выяснить, существует ли хоть один трейдер из Милана.
      transactions.stream()
          .map(Transaction::getTrader)
          .filter(trader -> trader.getCity().equals("Milan"))
          .limit(1)
          .forEach(System.out::println);

      getLine();

      // 6. Вывести суммы всех транзакций трейдеров из Кембриджа
      result = transactions.stream()
          .filter(transaction ->
              transaction.getTrader().getCity().equals(raoul.getCity())
          )
          .map(Transaction::getValue)
          .reduce(0, Integer::sum).toString();

      getMessage("Суммы всех транзакций трейдеров из Кембриджа", result);

      // 7. Какова максимальная сумма среди всех транзакций?

      result = transactions.stream()
          .map(Transaction::getValue)
          .max(Comparator.naturalOrder())
          .orElse(0)
          .toString();

      getMessage("Максимальная сумма среди всех транзакций", result);

      // 8. Найти транзакцию с минимальной суммой.

      result = transactions.stream()
          .map(Transaction::getValue)
          .min(Comparator.naturalOrder())
          .orElse(0)
          .toString();

      getMessage("Транзакция с минимальной суммой", result);

    }

    private static void getLine() {
      System.out.println("---------------------");
    }

    private static void getMessage(String msg, String target) {
      System.out.printf("%s: %s\n", msg, target);
      getLine();
    }

    private static void getMsg(String msg) {
      System.out.printf("%s\n", msg);
    }
}
