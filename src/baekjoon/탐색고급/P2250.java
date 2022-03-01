package baekjoon.탐색고급;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class P2250 {
  private static int[][] tree;
  private static Map<Integer, int[]> minMax = new HashMap<>();
  private static final int PARENT = 0;
  private static final int LEFT = 1;
  private static final int RIGHT = 2;
  private static int x = 0;
  private static int y = 0;

  private static void inOrder(int node, int curY) {
    y = Math.max(y, curY);
    if (tree[node][LEFT] != -1) {
      inOrder(tree[node][LEFT], curY + 1);
    }
    x += 1;
    if (!minMax.containsKey(curY)) {
      minMax.put(curY, new int[] { x, x });
    } else {
      minMax.put(curY, new int[] { minMax.get(curY)[0], x });
    }
    if (tree[node][RIGHT] != -1) {
      inOrder(tree[node][RIGHT], curY + 1);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int N = Integer.parseInt(br.readLine());
    int root = -1;
    int[] result = new int[2];
    tree = new int[N + 1][3];

    for (int i = 0; i < N; i++) {
      String[] input = br.readLine().split(" ");
      int me = Integer.parseInt(input[0]);
      int left = Integer.parseInt(input[1]);
      int right = Integer.parseInt(input[2]);
      tree[me][LEFT] = left;
      tree[me][RIGHT] = right;
      if (left != -1) {
        tree[left][PARENT] = me;
      }
      if (right != -1) {
        tree[right][PARENT] = me;
      }
    }
    for (int i = 1; i < N + 1; i++) {
      if (tree[i][PARENT] == 0) {
        root = i;
      }
    }
    inOrder(root, 1);
    for (int i = 1; i <= y; i++) {
      int diff = minMax.get(i)[1] - minMax.get(i)[0] + 1;
      if (result[1] < diff) {
        result[0] = i;
        result[1] = diff;
      }
    }
    System.out.println(result[0] + " " + result[1]);

    br.close();
  }
}
