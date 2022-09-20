package temp.kakao;

import java.util.Arrays;

public class P17686 {
  private static final int HEAD = 0;
  private static final int NUMBER = 1;
  private static final int TAIL = 2;

  public static void main(String[] args) {
    String[] files = new String[]{
            "foo9"
    };
    for (String s : new P17686().solution(files)) System.out.print(s + ", ");
    System.out.println();
  }

  // HEAD -> 숫자가 아닌 문자
  // NUMBER -> 모두 숫자
  // TAIL -> 숫자 (or) 글자 (or) 아무 글자도 없을 수 있음
  // HEAD 부분을 대소문자 구분하지 않고 사전순 정렬 -> NUMBER 숫자 순으로 오름차순 정렬[숫자 앞의 0은 무시된다.] -> 원래 입력의 주어진 순서 유지
  public String[] solution(String[] files) {
    return Arrays.stream(files)
            .map(this::split)
            .sorted(this::sort)
            .map(this::merge)
            .toArray(String[]::new);
  }

  private String[] split(String file) {
    String[] ret = new String[3];
    int len = file.length();
    char[] chars = file.toCharArray();
    int start = 0;
    int i = 1;
    for (; i < len; i++) {
      char ch = chars[i];
      if (ret[HEAD] == null) {
        if (!Character.isDigit(ch)) continue;
        ret[HEAD] = file.substring(start, i);
        start = i;
      } else if (ret[NUMBER] == null) {
        if (Character.isDigit(ch)) continue;
        ret[NUMBER] = file.substring(start, i);
        start = i;
      } else {
        ret[TAIL] = file.substring(start);
        break;
      }
    }
    if (ret[NUMBER] == null) ret[NUMBER] = file.substring(start, i);
    return ret;
  }

  private String merge(String[] split) {
    StringBuilder sb = new StringBuilder();
    for (String s : split) if (s != null) sb.append(s);
    return sb.toString();
  }

  private int sort(String[] a, String[] b) {
    int h = a[HEAD].toLowerCase().compareTo(b[HEAD].toLowerCase());
    if (h != 0) return h;
    return Integer.compare(Integer.parseInt(a[NUMBER]), Integer.parseInt(b[NUMBER]));
  }
}
