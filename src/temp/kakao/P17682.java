package temp.kakao;

import java.util.Arrays;

public class P17682 {
  // S, D, T -> 1제곱, 2제곱 3제곱
  // * -> 해당 점수와 바로 전에 얻은 점수를 각 2배로, # -> 해당 점수는 마이너스
  // * -> 중첩 가능. 2개면 4배, 3개면 8배
  // *, # -> 중첩 가능. 이 경우, -2배
  int[] scoreArr;

  public static void main(String[] args) {
    System.out.println(new P17682().solution("1S2D*3T"));
  }

  public int solution(String dartResult) {
    scoreArr = new int[3];
    String[] dartArr = split(dartResult);
    for (int i = 0; i < 3; i++) calculate(i, dartArr[i]);
    return Arrays.stream(scoreArr).sum();
  }

  private String[] split(String str) {
    String[] ret = new String[3];
    int start = 0;
    int idx = 0;
    int lastIdx = str.length() - 1;
    char[] chars = str.toCharArray();

    for (int i = 0; i < lastIdx; i++) {
      if (isDelimiter(chars[i], chars[i + 1])) {
        String slice = str.substring(start, i + 1);
        ret[idx++] = slice;
        start = i + 1;
      }
    }
    ret[idx] = str.substring(start, lastIdx + 1);

    return ret;
  }

  private boolean isDelimiter(char cur, char next) {
    return (cur == '*' || cur == '#' || cur == 'S' || cur == 'D' || cur == 'T') && Character.isDigit(next);
  }

  private void calculate(int idx, String dart) {
    int typeIdx = getTypeIdx(dart);
    int score = getScore(Integer.parseInt(dart.substring(0, typeIdx)), dart.charAt(typeIdx));
    scoreArr[idx] = dart.length() == typeIdx + 1 ? score : applyBonus(dart, typeIdx, score, idx);
  }

  private int getTypeIdx(String dart) {
    int a = dart.indexOf('S');
    if (a != -1) return a;
    int b = dart.indexOf('D');
    if (b != -1) return b;
    return dart.indexOf('T');
  }

  private int getScore(int num, char type) {
    int i = switch (type) {
      case 'S' -> 1;
      case 'D' -> 2;
      case 'T' -> 3;
      default -> throw new UnsupportedOperationException();
    };
    return (int) Math.pow(num, i);
  }

  private int applyBonus(String dart, int typeIdx, int score, int idx) {
    char bonus = dart.charAt(typeIdx + 1);
    switch (bonus) {
      case '*':
        if (idx != 0) scoreArr[idx - 1] *= 2;
        return score * 2;
      case '#':
        return -score;
      default:
        throw new UnsupportedOperationException();
    }
  }
}
