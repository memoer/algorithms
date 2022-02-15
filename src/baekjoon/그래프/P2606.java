package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2606 {
  public static void main(String[] args) throws IOException {
    final int START_NODE = 1;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int C = Integer.parseInt(br.readLine());
    final int N = Integer.parseInt(br.readLine());
    List<Integer>[] list = new ArrayList[C + 1];
    boolean[] visited = new boolean[C + 1];
    Queue<Integer> needVisit = new LinkedList<>();
    int result = 0;

    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (list[a] == null) {
        list[a] = new ArrayList<>();
      }
      if (list[b] == null) {
        list[b] = new ArrayList<>();
      }
      list[a].add(b);
      list[b].add(a);
    }
    br.close();

    visited[START_NODE] = true;
    needVisit.offer(START_NODE);
    while (!needVisit.isEmpty()) {
      int node = needVisit.poll();
      for (int adjNode : list[node]) {
        if (!visited[adjNode]) {
          needVisit.offer(adjNode);
          visited[adjNode] = true;
          result += 1;
        }
      }
    }

    System.out.println(result);
  }
}
