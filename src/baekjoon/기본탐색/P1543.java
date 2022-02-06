package baekjoon.기본탐색;

import java.util.Scanner;

// ? 탐색
public class P1543 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    StringBuilder target = new StringBuilder(sc.nextLine());
    String findStr = sc.nextLine();
    int idx = 0;
    int answer = 0;
    sc.close();
    for (; idx < target.length(); idx++) {
      String candidate = target.substring(idx, target.length());
      if (candidate.length() < findStr.length()) {
        break;
      }
      int findIdx = candidate.indexOf(findStr);
      if (findIdx != -1) {
        answer++;
        idx += findIdx + findStr.length() - 1;
      }
    }
    System.out.println(answer);
  }
}
