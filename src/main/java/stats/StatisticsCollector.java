package stats;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.OptionalInt;

public class StatisticsCollector {
    private final List<Long> integers = new ArrayList<>();
    private final List<Double> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    public void addInteger(long value) {
        integers.add(value);
    }

    public void addFloat(double value) {
        floats.add(value);
    }

    public void addString(String value) {
        strings.add(value);
    }

    public void printStats(boolean fullStats) {
        if (integers.isEmpty() && floats.isEmpty() && strings.isEmpty()) {
            System.out.println("\nНет данных для статистики.");
            return;
        }

        System.out.println("\n=== Статистика обработки ===");

        if (!integers.isEmpty()) {
            System.out.printf("Количество целых чисел: %d%n", integers.size());
            if (fullStats) {
                LongSummaryStatistics s = integers.stream().mapToLong(Long::longValue).summaryStatistics();
                System.out.printf(" Минимум: %d%n", s.getMin());
                System.out.printf(" Максимум: %d%n", s.getMax());
                System.out.printf(" Среднее: %.2f%n", s.getAverage());
                System.out.printf(" Сумма: %d%n", s.getSum());
            }
        }

        if (!floats.isEmpty()) {
            System.out.printf("Количество дробных чисел: %d%n", floats.size());
            if (fullStats) {
                DoubleSummaryStatistics s = floats.stream().mapToDouble(Double::doubleValue).summaryStatistics();
                System.out.printf(" Минимум: %.2f%n", s.getMin());
                System.out.printf(" Максимум: %.2f%n", s.getMax());
                System.out.printf(" Среднее: %.2f%n", s.getAverage());
                System.out.printf(" Сумма: %.2f%n", s.getSum());
            }
        }

        if (!strings.isEmpty()) {
            System.out.printf("Количество строк: %d%n", strings.size());
            if (fullStats) {
                OptionalInt maxLen = strings.stream().mapToInt(String::length).max();
                OptionalInt minLen = strings.stream().mapToInt(String::length).min();
                System.out.printf(" Количество символов в самой длинной строке: %d%n", maxLen.orElse(0));
                System.out.printf(" Количество символов в самой короткой строке: %d%n", minLen.orElse(0));
            }
        }
    }
}
