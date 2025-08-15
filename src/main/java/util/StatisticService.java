package util;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalInt;

public class StatisticService {

    private int intCount = 0;
    private int floatCount = 0;
    private int stringCount = 0;

    private final List<Integer> integers = new ArrayList<>();
    private final List<Double> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    public void addInteger(int value) {

        intCount++;
        integers.add(value);

    }

    public void addFloat(double value) {

        floatCount++;
        floats.add(value);

    }

    public void addString(String value) {

        stringCount++;
        strings.add(value);

    }

    public void printStats(boolean fullStats) {

        System.out.println("\n=== Статистика обработки ===");

        if (intCount > 0) {

            System.out.printf("Количество целых чисел: %d%n", intCount);

            if (fullStats) {

                IntSummaryStatistics intStats = integers.stream()
                        .mapToInt(Integer::intValue)
                        .summaryStatistics();

                System.out.printf(" Минимум: %d%n", intStats.getMin());
                System.out.printf(" Максимум: %d%n", intStats.getMax());
                System.out.printf(" Среднее: %.2f%n", intStats.getAverage());
                System.out.printf(" Сумма: %d%n", intStats.getSum());

            }

        }

        if (floatCount > 0) {

            System.out.printf("Количество дробных чисел: %d%n", floatCount);

            if (fullStats) {

                DoubleSummaryStatistics floatStats = floats.stream()
                        .mapToDouble(Double::doubleValue)
                        .summaryStatistics();

                System.out.printf(" Минимум: %.2f%n", floatStats.getMin());
                System.out.printf(" Максимум: %.2f%n", floatStats.getMax());
                System.out.printf(" Среднее: %.2f%n", floatStats.getAverage());
                System.out.printf(" Сумма: %.2f%n", floatStats.getSum());

            }

        }

        if (stringCount > 0) {

            System.out.printf("Количество строк: %d%n", stringCount);

            if (fullStats) {

                OptionalInt maxLen = strings.stream()
                        .mapToInt(String::length)
                        .max();
                OptionalInt minLen = strings.stream()
                        .mapToInt(String::length)
                        .min();

                System.out.printf(" Количество символов в самой длинной строке: %d%n",
                        maxLen.orElse(0));
                System.out.printf(" Количество символов в самой короткой строке: %d%n",
                        minLen.orElse(0));

            }

        }

    }

}
