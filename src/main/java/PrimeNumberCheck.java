import java.util.Arrays;

import static java.lang.Math.sqrt;

public class PrimeNumberCheck {
    public static boolean[] primeTable(int range) {
        boolean[] primeTable = new boolean[range];
        Arrays.fill(primeTable, Boolean.TRUE);
        primeTable[1] = false;
        for (int i = 2; i < sqrt(range); i++) {
            if (primeTable[i])
                for (int j = i + i; j < range; j = j + i) {
                    primeTable[j] = false;
                }
        }
        return primeTable;
    }

    public static boolean isNumberFromRangeIsPrime(int number, boolean[] primeTable ) {
        return primeTable[number];
    }
}
