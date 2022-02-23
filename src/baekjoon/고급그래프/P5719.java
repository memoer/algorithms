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
import java.util.StringTokenizer;

public class P5719 {

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

  private static List<Node>[] graph;
  private static List<Node>[] reverseGraph;
  private static int[] distArr;
  private static boolean[][] dropped;

  private static void initDistArr(final int N, final int START) {
    for (int i = 0; i < N; i++) {
      if (i != START) {
        distArr[i] = Integer.MAX_VALUE;
      }
    }
  }

  private static void dijkstra(final int N, final int START) {
    initDistArr(N, START);
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
        int sum = distArr[cur.idx] + adj.path;
        if (sum < distArr[adj.idx]) {
          distArr[adj.idx] = sum;
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
        if (distArr[adj.idx] + adj.path == distArr[cur.idx]) {
          q.offer(adj);
          dropped[adj.idx][cur.idx] = true;
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    while (true) {
      st = new StringTokenizer(br.readLine());
      final int N = Integer.parseInt(st.nextToken());
      final int M = Integer.parseInt(st.nextToken());
      if (N == 0) {
        break;
      }
      st = new StringTokenizer(br.readLine());
      final int START = Integer.parseInt(st.nextToken());
      final int END = Integer.parseInt(st.nextToken());

      graph = new ArrayList[N];
      reverseGraph = new ArrayList[N];
      for (int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        if (graph[u] == null) {
          graph[u] = new ArrayList<>();
        }
        if (reverseGraph[v] == null) {
          reverseGraph[v] = new ArrayList<>();
        }
        graph[u].add(new Node(v, p));
        reverseGraph[v].add(new Node(u, p));
      }

      dropped = new boolean[N][N];
      distArr = new int[N];
      dijkstra(N, START);
      bfs(START, END);
      dijkstra(N, START);

      if (distArr[END] != Integer.MAX_VALUE) {
        bw.write(distArr[END] + "\n");
      } else {
        bw.write(-1 + "\n");
      }
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
