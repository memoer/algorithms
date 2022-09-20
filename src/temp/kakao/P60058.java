package temp.kakao;

public class P60058 {
  public static void main(String[] args) {
    String p = "(()())()";
    System.out.println(new P60058().solution(p));
  }

  /**
   * "), ("의 개수가 모두 같고, 짝도 맞는다면 -> 올바름
   * "), ("의 개수가 같다면 -> 균형잡힘
   */
  // 인자로 균형잡힌 문자열이 들어옵니다. 이를 올바른 문자열로 반환하세요.
  public String solution(String p) {
    return process(new StringBuilder(p)).toString();
  }

  private StringBuilder process(StringBuilder s) {
    if (s.length() == 0) return s;
    StringBuilder[] splitUV = splitUV(s);
    StringBuilder u = splitUV[0];
    StringBuilder v = splitUV[1];
    if (u.charAt(0) == '(') return u.append(process(v));
    else {
      convert(u);
      return process(v).append(")").insert(0, "(").append(u);
    }
  }

  private StringBuilder[] splitUV(StringBuilder sb) {
    int a = 0;
    int b = 0;
    int i = 0;
    int len = sb.length();
    for (; i < len; i++) {
      if (sb.charAt(i) == '(') a += 1;
      else b += 1;
      if (a == b) break;
    }
    // u[0] -> 최소 균형 잡힌 문자열
    return new StringBuilder[]{new StringBuilder(sb.substring(0, i + 1)), new StringBuilder(sb.substring(i + 1))};
  }

  private void convert(StringBuilder sb) {
    sb.deleteCharAt(0);
    sb.deleteCharAt(sb.length() - 1);
    int len = sb.length();
    for (int i = 0; i < len; i++) {
      if (sb.charAt(i) == ')') sb.replace(i, i + 1, "(");
      else sb.replace(i, i + 1, ")");
    }
  }
}
