package programmers.Lv2;

import java.math.BigInteger;

public class P62048_2 {

    public static void main(String[] args) {

    }

    private static class Solution {

        public long solution(int w, int h) {
            long a = w, b = h;
            long gcd = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).longValue();
            return (long) (a * b) - (a + b - gcd);
        }

    }
}
