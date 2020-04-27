package generic;

public class ArrayAlg {
    // 得到数组a中的最大值和最小值
    public static Pair<String> minmax(String[] a) {
        if (a==null || a.length==0) {
            return null;
        }
        String min = a[0];
        String max = a[0];
        for (String s : a) {
            if (min.compareTo(s) > 0) {
                min = s;
            }
            if (max.compareTo(s) < 0) {
                max = s;
            }
        }
        return new Pair<>(min, max);
    }

    // 作业，实现此泛型方法
    public static <T extends Comparable<? super T>> Pair<T> minmax(T[] a) {
        return null;
    }

    // 得到数组中的中间值
    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }

    // 数组中的最小元素
    public static <T extends Comparable<? super T>> T min(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        T min = a[0];
        for (T v : a) {
            if (min.compareTo(v) > 0) {
                min = v;
            }
        }
        return min;
    }
}
