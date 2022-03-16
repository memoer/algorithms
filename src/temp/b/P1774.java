package temp.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P1774 {
  private static int[] parent;
  private static int[] rank;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int M = Integer.parseInt(st.nextToken());
    int[][] locationArr = new int[N + 1][2];
    parent = new int[N + 1];
    rank = new int[N + 1];

    for (int i = 1; i < N + 1; i++) {
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

    List<Edge> eList = new ArrayList<>();
    double result = 0;

    for (int i = 1; i < N; i++) {
      for (int j = i + 1; j < N + 1; j++) {
        eList.add(new Edge(i, j, getDist(locationArr[i], locationArr[j])));
      }
    }
    Collections.sort(eList);

    for (Edge edge : eList) {
      if (find(edge.a) != find(edge.b)) {
        union(edge.a, edge.b);
        result += edge.dist;
      }
    }
    System.out.println(String.format("%.2f", result));
  }

  public static double getDist(int[] a, int[] b) {
    return Math.sqrt(Math.pow(b[1] - a[1], 2) + Math.pow(b[0] - a[0], 2));
  }

  private static int find(int node) {
    if (node != parent[node]) {
      parent[node] = find(parent[node]);
    }
    return parent[node];
  }

  private static void union(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);
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
        rank[rootB] += 1;
      }
    }
  }

  private static class Edge implements Comparable<Edge> {
    public int a;
    public int b;
    public double dist;

    public Edge(int a, int b, double dist) {
      this.a = a;
      this.b = b;
      this.dist = dist;
    }

    @Override
    public int compareTo(Edge o) {
      return Double.compare(this.dist, o.dist);
    }
  }

}
