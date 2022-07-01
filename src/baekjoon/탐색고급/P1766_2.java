package baekjoon.탐색고급;

import java.io.*;
import java.util.*;

/*
1. N개의 문제는 모두 풀어야 한다.
2. 먼저 푸는 것이 좋은 문제가 있는 문제는, 먼저 푸는 것이 좋은 문제를 "반드시" 먼저 풀어야 한다.
  - 먼저 푸는 것이 좋은 문제는 앞에 문제를 무조건 반드시 먼저 풀 것
3. 가능하면, 쉬운 문제부터 풀어야 한다.
  - 현재 바로 풀 수 있는 문제들 중에선, 가능한한 쉬운 문제부터 풀 것
 */
public class P1766_2 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    List<Integer>[] linked = new ArrayList[N + 1];
    int[] inDegree = new int[N + 1];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (linked[a] == null) {
        linked[a] = new ArrayList<>();
      }
      linked[a].add(b);
      inDegree[b] += 1;
    }
    br.close();

    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 1; i < N + 1; i++) {
      if (inDegree[i] == 0) {
        pq.offer(i);
      }
    }

    while (!pq.isEmpty()) {
      int first = pq.poll();
      bw.write(first + " ");
      if (linked[first] == null) {
        continue;
      }
      for (int next : linked[first]) {
        inDegree[next] -= 1;
        if (inDegree[next] == 0) {
          pq.offer(next);
        }
      }
    }

    bw.write("\n");
    bw.flush();
  }
}
