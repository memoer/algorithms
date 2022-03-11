package temp.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2655 {
  private static class Brick {
    public int bottom;
    public int height;
    public int weight;
    public int idx;

    public Brick(int bottom, int height, int weight, int idx) {
      this.bottom = bottom;
      this.height = height;
      this.weight = weight;
      this.idx = idx;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] dp = new int[n];
    Brick[] arr = new Brick[n];
    Queue<Integer> q = new LinkedList<>();
    int max = 0;

    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      arr[i] = new Brick(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
          Integer.parseInt(st.nextToken()), i + 1);
    }
    br.close();
    Arrays.sort(arr, (pre, cur) -> cur.weight - pre.weight);

    for (int i = 0; i < n; i++) {
      dp[i] = arr[i].height;
    }

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[j].bottom > arr[i].bottom) {
          dp[i] = Math.max(dp[i], dp[j] + arr[i].height);
        }
      }
      max = Math.max(max, dp[i]);
    }

    for (int v : dp) {
      System.out.print(v + ", ");
    }
    System.out.println();

    for (int i = n - 1; i >= 0; i--) {
      if (dp[i] != max) {
        continue;
      }
      System.out.print(i + "/" + arr[i].idx + ", ");
      q.offer(arr[i].idx);
      max -= arr[i].height;
    }

    // 이건 49줄 쪽을 생각해보면, 벽돌을 아래에다 쌓는 구조임
    bw.write(q.size() + "\n");
    while (!q.isEmpty()) {
      bw.write(q.poll() + "\n");
    }

    bw.flush();
    bw.close();
  }
}
