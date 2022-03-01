package baekjoon.그래프고급;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P1774 {
  private static int[] parent;
  private static int[] rank;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken()) + 1;
    final int M = Integer.parseInt(st.nextToken());
    int[][] locationArr = new int[N][2];
    parent = new int[N];
    rank = new int[N];
    List<Edge> edgeList = new ArrayList<>();
    double result = 0;

    for (int i = 1; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      locationArr[i][0] = Integer.parseInt(st.nextToken());
      locationArr[i][1] = Integer.parseInt(st.nextToken());
      parent[i] = i;
    }
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
    br.close();

    for (int i = 1; i < N - 1; i++) {
      for (int j = i + 1; j < N; j++) {
        edgeList.add(new Edge(i, j, getDistance(locationArr[i], locationArr[j])));
      }
    }
    Collections.sort(edgeList);

    for (Edge edge : edgeList) {
      if (find(edge.me) != find(edge.adj)) {
        union(edge.me, edge.adj);
        result += edge.path;
      }
    }
    bw.write(String.format("%.2f", result) + "\n");
    bw.flush();
    bw.close();
  }

  private static double getDistance(int[] a, int[] b) {
    return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
  }

  private static int find(int node) {
    if (node != parent[node]) {
      parent[node] = find(parent[node]);
    }
    return parent[node];
  }

  private static void union(int nodeA, int nodeB) {
    int rootA = find(nodeA);
    int rootB = find(nodeB);
    if (rootA == rootB) {
      return;
    }
    int rankA = rank[rootA];
    int rankB = rank[rootB];
    if (rankA > rankB) {
      parent[rootB] = rootA;
    } else {
      parent[rootA] = rootB;
      if (rankA == rankB) {
        rank[rootB] = rankB + 1;
      }
    }
  }

  private static class Edge implements Comparable<Edge> {
    public int me;
    public int adj;
    public double path;

    public Edge(int me, int adj, double path) {
      this.me = me;
      this.adj = adj;
      this.path = path;
    }

    @Override
    public int compareTo(Edge o) {
      return Double.compare(this.path, o.path);
    }
  }
}
