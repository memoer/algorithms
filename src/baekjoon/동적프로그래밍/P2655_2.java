package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 위로 올라갈 수록, 무게가 가벼워져야 한다.
 * 2. 위로 올라갈 수록, 밑면이 좁아져야 한다.
 * 3. 밑면의 넓이가 같은 벽돌은 없으며, 무게가 같은 벽돌도 없다.
 * 4. 벽돌들의 높이는 같을 수도 있다.
 * 5. 벽돌은 회전시킬 수 없다. 무조건 정해진 벽돌 그 모양 그대로 사용해야 한다.
 */
public class P2655_2 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // N <= 100
    final int N = Integer.parseInt(br.readLine()) + 1;
    Brick[] bricks = new Brick[N];
    bricks[0] = new Brick(0, 0, 0, 0);
    for (int i = 1; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      // 0 < bottom, height, weight <= 10,000
      int bottom = Integer.parseInt(st.nextToken());
      int height = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      bricks[i] = new Brick(i, bottom, height, weight);
    }
    br.close();

    // 무게별 내림차순
    Arrays.sort(bricks, Comparator.comparingInt(o -> o.weight));
    int max = Integer.MIN_VALUE;
    int[] dp = new int[N];
    for (int i = 1; i < N; i++) {
      Brick cur = bricks[i];
      for (int j = 0; j < i; j++) {
        if (bricks[j].area < cur.area) dp[i] = Math.max(dp[i], dp[j] + cur.height);
      }
      max = Math.max(max, dp[i]);
    }
    Stack<Integer> stack = new Stack<>();
    for (int i = N - 1; i > 0; i--) {
      if (max == dp[i]) {
        stack.push(bricks[i].idx);
        max -= bricks[i].height;
      }
    }
    System.out.println(stack.size());
    while (!stack.isEmpty()) System.out.println(stack.pop());
  }

  private static class Brick {
    private final int idx;
    private final int area;
    private final int height;
    private final int weight;

    public Brick(int idx, int area, int height, int weight) {
      this.idx = idx;
      this.area = area;
      this.height = height;
      this.weight = weight;
    }
  }
}
