package src;
import java.math.BigDecimal;

public class Fibonacci {
    private int[] fibs;
    public BigDecimal chamdasR;
    public int chamdasDp;

    public Fibonacci() {
        fibs = new int[1000];
        chamdasDp = 0;
        chamdasR = new BigDecimal(0);
    }

    public int executeDP(int n) {
        chamdasDp++;
        if (n <= 1) return 1;
        if (fibs[n] != 0) return fibs[n];
        fibs[n] = executeDP(n - 1) + executeDP(n - 2);
        return fibs[n];
    }

    public int executeRecursive(int n) {
        chamdasR = chamdasR.add(new BigDecimal(1));
        if (n <= 1) return 1;
        return executeRecursive(n - 1) + executeRecursive(n - 2);
    }
}