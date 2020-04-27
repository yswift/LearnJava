package generic;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class UTminmax {
    @Test
    public void testDouble() {
        Double[] ds = {0.1, 100.0, 20.0, 40.0};
        Pair<Double> mm = ArrayAlg.minmax(ds);
        assertEquals("最小值", Double.valueOf(0.1), mm.getFirst());
        assertEquals("最大值", Double.valueOf(100.0), mm.getSecond());
    }

    @Test
    public void testLocalDate() {
        LocalDate[] ds = {
                LocalDate.of(1919, 5, 4),
                LocalDate.of(1927, 8, 1),
                LocalDate.of(1949, 10, 1),
        };
        Pair<LocalDate> mm = ArrayAlg.minmax(ds);
        assertEquals("最小值", ds[0], mm.getFirst());
        assertEquals("最大值", ds[2], mm.getSecond());
    }

}
