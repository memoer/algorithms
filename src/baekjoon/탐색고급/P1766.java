package baekjoon.탐색고급;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1. N개의 문제는 모두 풀 것
 * 2. 가능하면, 쉬운 문제부터 푼다
 * 3. 먼저 푸는 것이 좋은 문제는 먼저 푼다.
 * - 1의 문제가 가장 쉬운 문제임
 * - 4 2 로 입력이 들어올 경우, 4를 먼저 푸는 게 좋음
 */
public class P1766 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    List<Integer>[] list = new ArrayList[n + 1];
    int[] inDegree = new int[n + 1];
    // 가능하면, 쉬운 문제부터 풀어야 한다.
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (list[a] == null) {
        list[a] = new ArrayList<>();
      }
      inDegree[b] += 1;
      list[a].add(b);
    }

    for (int i = 1; i < n + 1; i++) {
      if (inDegree[i] == 0) {
        pq.add(i);
      }
    }

    while (!pq.isEmpty()) {
      int cur = pq.poll();
      bw.write(cur + " ");
      if (list[cur] == null) {
        continue;
      }
      for (int connected : list[cur]) {
        inDegree[connected] -= 1;
        if (inDegree[connected] == 0) {
          pq.add(connected);
        }
      }
    }

    bw.write("\n");
    bw.flush();
    bw.close();
    br.close();
  }
}
