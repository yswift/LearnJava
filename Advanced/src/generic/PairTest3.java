package generic;

import java.time.LocalDate;

public class PairTest3 {
    public static void main(String[] args) {
        LocalDate[] birthdays = {
                LocalDate.of(1919, 5, 4),
                LocalDate.of(1927, 8, 1),
                LocalDate.of(1949, 10, 1),
        };
        LocalDate min = ArrayAlg.min(birthdays);
        System.out.println("min = " + min);
    }
    public static <T> Pair<T> makePair(Class<T> clazz) {
        try {
            return new Pair<>(clazz.newInstance(),clazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
