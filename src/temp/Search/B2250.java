package temp.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * ! 노드가 1개일 경우를 생각할 것! [아래 2가지가 1쌍]
 * ? 28줄 -> {x,0} 말고 {x,x} 이여야 함 -> 노드가 1개일 경우를 생각해보면 됨
 * ? 68줄 -> 밑에 출력하는 부분(sysout)말고, 여기서 +1을 해주어야함 -> 노드가 1개일 경우를 생각해보면 됨
 */
public class B2250 {
  private static final int LEFT = 0;
  private static final int RIGHT = 1;
  private static final int NONE = -1;
  private static int[][] arr;
  private static int x = 0;
  private static Map<Integer, int[]> map = new HashMap<>();

  private static void inOrder(int me, int depth) {
    if (arr[me][LEFT] != NONE) {
      inOrder(arr[me][LEFT], depth + 1);
    }
    x += 1;
    if (!map.containsKey(depth)) {
      map.put(depth, new int[] { x, x });
    } else {
      map.put(depth, new int[] { map.get(depth)[0], x });
    }
    if (arr[me][RIGHT] != NONE) {
      inOrder(arr[me][RIGHT], depth + 1);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] result = new int[2];
    int root = 0;
    arr = new int[n + 1][2];

    for (int i = 1; i <= n; i++) {
      root += i;
    }
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int m = Integer.parseInt(st.nextToken());
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());
      arr[m][LEFT] = left;
      arr[m][RIGHT] = right;
      if (left != NONE) {
        root -= left;
      }
      if (right != NONE) {
        root -= right;
      }
    }
    br.close();

    inOrder(root, 1);
    for (int depth : map.keySet()) {
      int length = map.get(depth)[1] - map.get(depth)[0] + 1;
      if (result[1] < length || (result[1] == length && result[0] > depth)) {
        result[0] = depth;
        result[1] = length;
      }
    }
    System.out.println(result[0] + " " + result[1]);
  }
}
