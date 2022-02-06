package baekjoon.재귀;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P7490 {
  private static List<String> result = new ArrayList<>();

  private static void dfs(StringBuilder candidate, int curNum, int lastNum) {
    if (curNum > lastNum) {
      if (isAnswer(candidate)) {
        result.add(candidate.toString());
      }
      return;
    }

    for (char c : new char[] { ' ', '+', '-' }) {
      candidate.append("" + c + curNum);
      dfs(candidate, curNum + 1, lastNum);
      candidate.delete(candidate.length() - 2, candidate.length());
    }
  }

  private static boolean isAnswer(StringBuilder sb) {
    int sum = 0;
    String[] addList = sb.toString().replaceAll(" ", "").split("\\+");
    for (String add : addList) {
      sum += Arrays.stream(add.split("-")).mapToInt(Integer::parseInt).reduce((pre, cur) -> pre - cur).getAsInt();
    }
    return sum == 0;
  }

  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    final int TEST_CASE = Integer.parseInt(sc.nextLine());

    for (int i = 0; i < TEST_CASE; i++) {
      if (i != 0) {
        result.add("");
      }
      final int N = Integer.parseInt(sc.nextLine());
      StringBuilder sb = new StringBuilder();
      sb.append("1");
      dfs(sb, 2, N);
    }

    for (String s : result) {
      bw.write(s + "\n");
    }
    bw.flush();
    bw.close();
    sc.close();
  }
}
