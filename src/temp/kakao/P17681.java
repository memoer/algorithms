package temp.kakao;

import java.util.stream.IntStream;

public class P17681 {
  public static void main(String[] args) {
    int[] arr1 = new int[]{9, 20, 28, 18, 11};
    int[] arr2 = new int[]{30, 1, 21, 17, 28};
    new P17681().solution(5, arr1, arr2);
  }

  public String[] solution(int n, int[] arr1, int[] arr2) {
    return IntStream.range(0, n)
            .mapToObj(i -> getBinary(n, arr1[i] | arr2[i]))
            .map(sb -> convert(n, sb))
            .toArray(String[]::new);
  }

  private StringBuilder getBinary(int n, int num) {
    StringBuilder sb = new StringBuilder();
    while (num > 1) {
      sb.append(num % 2);
      num /= 2;
    }
    sb.append(num);
    int length = sb.length();
    if (length < n) for (; length < n; length++) sb.append("0");
    return sb.reverse();
  }

  private String convert(int n, StringBuilder sb) {
    for (int i = 0; i < n; i++) {
      if (sb.charAt(i) == '1') sb.replace(i, i + 1, "#");
      else sb.replace(i, i + 1, " ");
    }
    return sb.toString();
  }
}
