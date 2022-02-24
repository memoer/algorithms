package baekjoon.고급그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class P5719 {
  private static int[] dist;
  private static List<Node>[] graph;
  private static boolean[][] dropped;
  private static List<Node>[] reverseGraph;

  private static void dijkstra(final int N, final int START) {
    for (int i = 0; i < N; i++) {
      dist[i] = Integer.MAX_VALUE;
    }
    dist[START] = 0;

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(START, 0));

    while (!pq.isEmpty()) {
      Node cur = pq.poll();
      if (graph[cur.idx] == null) {
        continue;
      }
      for (Node adj : graph[cur.idx]) {
        if (dropped[cur.idx][adj.idx]) {
          continue;
        }
        int sum = cur.path + adj.path;
        if (dist[adj.idx] > sum) {
          dist[adj.idx] = sum;
          pq.offer(new Node(adj.idx, sum));
        }
      }
    }
  }

  private static void bfs(final int START, final int END) {
    Queue<Node> q = new LinkedList<>();
    q.offer(new Node(END, 0));
    while (!q.isEmpty()) {
      Node cur = q.poll();
      if (reverseGraph[cur.idx] == null) {
        continue;
      }
      for (Node adj : reverseGraph[cur.idx]) {
        if (dist[adj.idx] + adj.path == dist[cur.idx]) {
          q.offer(adj);
          dropped[adj.idx][cur.idx] = true;
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    while (true) {
      String[] input = br.readLine().split(" ");
      final int N = Integer.parseInt(input[0]);
      final int M = Integer.parseInt(input[1]);
      if (N == 0 && M == 0) {
        break;
      }
      dist = new int[N];
      graph = new ArrayList[N];
      reverseGraph = new ArrayList[N];
      dropped = new boolean[N][N];

      input = br.readLine().split(" ");
      final int START = Integer.parseInt(input[0]);
      final int END = Integer.parseInt(input[1]);

      for (int i = 0; i < M; i++) {
        input = br.readLine().split(" ");
        int u = Integer.parseInt(input[0]);
        int v = Integer.parseInt(input[1]);
        int p = Integer.parseInt(input[2]);
        if (graph[u] == null) {
          graph[u] = new ArrayList<>();
        }
        graph[u].add(new Node(v, p));
      }

      dijkstra(N, START);
      bfs(START, END);
      dijkstra(N, START);

      if (dist[END] != Integer.MAX_VALUE) {
        bw.write(dist[END] + "\n");
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
    public int path;

    public Node(int idx, int path) {
      this.idx = idx;
      this.path = path;
    }

    @Override
    public int compareTo(Node o) {
      return this.path - o.path;
    }
  }
}
