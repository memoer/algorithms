package baekjoon.그래프고급;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class P5719 {
  private static int[] dist;
  private static List<Node>[] graph;
  private static boolean[][] dropped;
  private static List<Integer>[] targetGraph;

  private static void dijkstra(final int N, final int start) {
    for (int i = 0; i < N; i++) {
      dist[i] = Integer.MAX_VALUE;
    }
    dist[start] = 0;

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0));

    while (!pq.isEmpty()) {
      Node cur = pq.poll();
      if (dist[cur.idx] < cur.weight || graph[cur.idx] == null) {
        continue;
      }
      for (Node adj : graph[cur.idx]) {
        if (dropped[cur.idx][adj.idx]) {
          continue;
        }
        int sum = cur.weight + adj.weight;
        if (sum == dist[adj.idx]) {
          targetGraph[adj.idx].add(cur.idx);
        } else if (sum < dist[adj.idx]) {
          targetGraph[adj.idx].clear();
          targetGraph[adj.idx].add(cur.idx);
          dist[adj.idx] = sum;
          pq.offer(new Node(adj.idx, sum));
        }
      }
    }
  }

  private static void backTracking(int start, int sIdx) {
    if (start == sIdx) {
      return;
    }
    for (int next : targetGraph[sIdx]) {
      if (!dropped[next][sIdx]) {
        dropped[next][sIdx] = true;
        backTracking(start, next);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    while (true) {
      String[] input = br.readLine().split(" ");
      int n = Integer.parseInt(input[0]);
      int m = Integer.parseInt(input[1]);
      if (n == 0 && m == 0) {
        break;
      }
      dist = new int[n];
      graph = new ArrayList[n];
      targetGraph = new ArrayList[n];
      dropped = new boolean[n][n];
      for (int i = 0; i < n; i++) {
        graph[i] = new ArrayList<>();
        targetGraph[i] = new ArrayList<>();
      }

      input = br.readLine().split(" ");
      int start = Integer.parseInt(input[0]);
      int end = Integer.parseInt(input[1]);
      for (int i = 0; i < m; i++) {
        input = br.readLine().split(" ");
        int u = Integer.parseInt(input[0]);
        int v = Integer.parseInt(input[1]);
        int p = Integer.parseInt(input[2]);
        if (graph[u] == null) {
          graph[u] = new ArrayList<>();
        }
        graph[u].add(new Node(v, p));
      }

      dijkstra(n, start);
      backTracking(start, end);
      dijkstra(n, start);

      if (dist[end] != Integer.MAX_VALUE) {
        bw.write(dist[end] + "\n");
      } else {
        bw.write(-1 + "\n");
      }
    }

    bw.flush();
    bw.close();
    br.close();
  }

  private static class Node implements Comparable<Node> {
    public int idx;
    public int weight;

    public Node(int idx, int weight) {
      this.idx = idx;
      this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
      return this.weight - o.weight;
    }
  }
}
