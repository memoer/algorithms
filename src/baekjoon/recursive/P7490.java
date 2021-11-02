package baekjoon.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P7490 {
  public static List<String> answer = new ArrayList<>();

  public static int calc(StringBuilder sb) {
    int sum = 0;
    String[] addList = sb.toString().replace(" ", "").split("\\+");
    for (String add : addList) {
      sum += Arrays.stream(add.split("-")).mapToInt(x -> Integer.valueOf(x)).reduce((pre, cur) -> pre - cur).getAsInt();
    }
    return sum;
  }

  public static void dfs(char candidateN, int endN, StringBuilder sb) {
    if (candidateN - '0' == endN + 1) {
      if (calc(sb) == 0) {
        answer.add(sb.toString());
      }
      return;
    }
    char nextN = (char) (candidateN + 1);
    sb.append(" " + candidateN);
    dfs(nextN, endN, sb);
    sb.delete(sb.length() - 2, sb.length());
    sb.append("+" + candidateN);
    dfs(nextN, endN, sb);
    sb.delete(sb.length() - 2, sb.length());
    sb.append("-" + candidateN);
    dfs(nextN, endN, sb);
    sb.delete(sb.length() - 2, sb.length());
  }

  public static void print() {
    for (String s : answer) {
      System.out.println(s);
    }
  }

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int testCase = Integer.valueOf(sc.nextLine());
    for (int i = 0; i < testCase; i++) {
      int endN = Integer.valueOf(sc.nextLine());
      StringBuilder sb = new StringBuilder();
      sb.append("1");
      dfs('2', endN, sb);
      if (i + 1 != testCase) {
        answer.add("");
      }
    }
    print();
    sc.close();
  }
}
