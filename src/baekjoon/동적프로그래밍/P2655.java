package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

// 아래에는 밑면이 무조건 넓어야 하고,무게가 더 나가야 한다.
public class P2655 {
  private static class Brick {
    public int area;
    public int height;
    public int weight;
    public int idx;

    public Brick(int area, int height, int weight, int idx) {
      this.area = area;
      this.height = height;
      this.weight = weight;
      this.idx = idx;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken()) + 1;
    int[] dp = new int[N];
    Brick[] arr = new Brick[N];
    int max = Integer.MIN_VALUE;
    Stack<Integer> result = new Stack<>();

    arr[0] = new Brick(0, 0, 0, 0);
    for (int i = 1; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int area = Integer.parseInt(st.nextToken());
      int height = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      arr[i] = new Brick(area, height, weight, i);
    }
    br.close();
    Arrays.sort(arr, (pre, cur) -> pre.area - cur.area);

    for (int i = 1; i < N; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[i].weight > arr[j].weight) {
          dp[i] = Math.max(dp[i], dp[j] + arr[i].height);
        }
      }
      max = Math.max(max, dp[i]);
    }

    for (int i = N - 1; i >= 1; i--) {
      if (dp[i] == max) {
        result.push(arr[i].idx);
        max -= arr[i].height;
      }
    }

    // 49줄 로직을 생각해보면, 벽돌을 위에다 쌓는 구조임
    bw.write(result.size() + "\n");
    while (!result.isEmpty()) {
      bw.write(result.pop() + "\n");
    }
    bw.flush();
    bw.close();
  }
}
