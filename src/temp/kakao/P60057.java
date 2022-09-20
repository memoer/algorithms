package temp.kakao;

/**
 * 압축할 문자열 s가 매개변수로 주어질 때, 1개 이상 단위로 문자열을 잘라 압축하여 표현한 문자열 중 가장 짧은 것의 길이를 return 하도록 solution 함수를 완성해주세요.
 */
public class P60057 {
  public static void main(String[] args) {
    String s = "aabbaccc";
    System.out.println(new P60057().solution(s));
  }

  public int solution(String s) {
    int ans = s.length();

    int len = s.length();
    int endI = len / 2;
    for (int i = 1; i <= endI; i++) {
      int sum = 0;
      int cnt = 1;
      int endJ = len - i;
      int j = i;
      String pre = s.substring(0, i);
      for (; j <= endJ; j += i) {
        String cur = s.substring(j, j + i);
        if (cur.equals(pre)) {
          cnt += 1;
        } else {
          sum += calculate(cnt, pre);
          cnt = 1;
          pre = cur;
        }
      }
      if (j < len) sum += (len - j);
      sum += (cnt == 1 ? i : calculate(cnt, pre));
      ans = Integer.min(ans, sum);
    }

    return ans;
  }

  private int calculate(int cnt, String pre) {
    int sum = pre.length();
    if (cnt == 1) return sum;
    return sum + (int) (Math.log10(cnt) + 1);
  }
}
