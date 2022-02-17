package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1325 {
  private static int bfs(int c, Map<Integer, List<Integer>> graph, int N) {
    Queue<Integer> needVisit = new LinkedList<>();
    boolean[] visited = new boolean[N + 1];
    int result = 0;

    visited[c] = true;
    needVisit.offer(c);
    while (!needVisit.isEmpty()) {
      int cur = needVisit.poll();
      if (graph.get(cur) == null) {
        continue;
      }
      for (int adj : graph.get(cur)) {
        if (!visited[adj]) {
          needVisit.offer(adj);
          visited[adj] = true;
          result += 1;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int M = Integer.parseInt(st.nextToken());
    int[] network = new int[N + 1];
    int max = Integer.MIN_VALUE;
    Map<Integer, List<Integer>> graph = new HashMap<>();

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (!graph.containsKey(b)) {
        graph.put(b, new ArrayList<>());
      }
      graph.get(b).add(a);
    }

    for (int c : graph.keySet()) {
      int count = bfs(c, graph, N);
      network[c] = count;
      max = Math.max(max, count);
    }

    for (int i = 0; i < N + 1; i++) {
      if (network[i] == max) {
        bw.write(i + " ");
      }
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
