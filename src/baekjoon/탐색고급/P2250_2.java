package baekjoon.탐색고급;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class P2250_2 {
  private static int[][] tree;
  private static final int PARENT = 0;
  private static final int LEFT = 1;
  private static final int RIGHT = 2;
  private static final Map<Integer, int[]> map = new HashMap<>();
  private static int column = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int N = Integer.parseInt(br.readLine());
    tree = new int[N + 1][3];

    // 노드 수만큼 반복 -> O(N)
    // 최대 노드 수 -> 10,000
    for (int i = 1; i <= N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int me = Integer.parseInt(st.nextToken());
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());
      tree[me][LEFT] = left;
      tree[me][RIGHT] = right;
      if (left != -1) tree[left][PARENT] = me;
      if (right != -1) tree[right][PARENT] = me;
    }
    br.close();

    // 노드 수만큼 반복 -> O(N)
    // 최대 노드 수 ->  10,000
    int root = IntStream.range(1, N + 1).filter(i -> tree[i][PARENT] == 0).findFirst().orElseThrow();

    inOrder(root, 1);

    map
            .entrySet()
            .stream()
            .map(e -> new int[]{e.getKey(), e.getValue()[1] - e.getValue()[0]})
            .min((pre, cur) -> pre[1] == cur[1] ? Integer.compare(pre[0], cur[0]) : Integer.compare(cur[1], pre[1]))
            .map(e -> e[0] + " " + (e[1] + 1))
            .ifPresent(System.out::println);
  }

  // 노드 수만큼 반복 -> O(N)
  private static void inOrder(int target, int level) {
    if (tree[target][LEFT] != -1) inOrder(tree[target][LEFT], level + 1);
    // 중위 탐색
    column += 1;
    if (map.get(level) == null) map.put(level, new int[]{column, column});
    else map.get(level)[1] = column;
    if (tree[target][RIGHT] != -1) inOrder(tree[target][RIGHT], level + 1);
  }
}
