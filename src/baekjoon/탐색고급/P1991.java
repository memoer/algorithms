package baekjoon.탐색고급;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class P1991 {
  private static Map<String, String[]> map;

  private static void preOrder(String name, StringBuilder sb) {
    if (name.equals(".")) {
      return;
    }
    sb.append(name);
    preOrder(map.get(name)[0], sb);
    preOrder(map.get(name)[1], sb);
  }

  private static void inOrder(String name, StringBuilder sb) {
    if (name.equals(".")) {
      return;
    }
    inOrder(map.get(name)[0], sb);
    sb.append(name);
    inOrder(map.get(name)[1], sb);
  }

  private static void postOrder(String name, StringBuilder sb) {
    if (name.equals(".")) {
      return;
    }
    postOrder(map.get(name)[0], sb);
    postOrder(map.get(name)[1], sb);
    sb.append(name);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final String START_NODE = "A";
    final int N = Integer.parseInt(br.readLine());

    map = new HashMap<>();
    for (int i = 0; i < N; i++) {
      String[] input = br.readLine().split(" ");
      map.put(input[0], new String[] { input[1], input[2] });
    }

    StringBuilder[] sbList = { new StringBuilder(), new StringBuilder(), new StringBuilder() };
    preOrder(START_NODE, sbList[0]);
    inOrder(START_NODE, sbList[1]);
    postOrder(START_NODE, sbList[2]);

    for (StringBuilder sb : sbList) {
      System.out.println(sb.toString());
    }

    br.close();
  }
}
