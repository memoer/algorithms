package temp.kakao;

import java.util.Arrays;

public class P92335 {
  public static void main(String[] args) {
    int n = 1_000_000;
    int k = 5;
    System.out.println(new P92335().solution(n, k));
  }

  public int solution(int n, int k) {
    return (int) Arrays.stream(Integer.toString(n, k).split("0"))
            .filter(s -> s.length() > 0)
            .mapToLong(Long::parseLong)
            .reduce(0, (acc, v) -> isPrime(v) ? ++acc : acc);
  }

  private boolean isPrime(long num) {
    if (num == 1) return false;
    for (int i = 2; i <= Math.sqrt(num); i++) if (num % i == 0) return false;
    return true;
  }
}
