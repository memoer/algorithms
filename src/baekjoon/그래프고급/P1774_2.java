package baekjoon.그래프고급;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1774_2 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken()); // N<=1,000 [우주신들의 개수]
    final int M = Integer.parseInt(st.nextToken()); // M<=1,000 [신들과의 통로 개수]
    Algorithm algorithm = new Algorithm(N);
    // O(N) -> 1,000
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken()); // 0<=x<=1,000,000
      int y = Integer.parseInt(st.nextToken()); // 0<=y<=1,000,000
      algorithm.addCoordinate(i, x, y);
    }
    // O(N^2) -> 1,000,000
    algorithm.initEdge(N);
    // O(M) -> 1,000
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken()); // 0<=x<=1,000,000
      int v = Integer.parseInt(st.nextToken()); // 0<=y<=1,000,000
      algorithm.union(u, v);
    }
    br.close();

    double answer = 0;
    // 크루스칼 알고리즘, path-compression&union-by-rank 사용
    // O(ElogE) -> 1,000*10 -> 10,000
    for (Edge edge : algorithm.edgeList) {
      int u = edge.me;
      int v = edge.adj;
      if (algorithm.union(u, v)) answer += edge.length;
    }
    // O(N+N^2+M+ElogE) -> 1,000 + 1,000,000 + 1,000 + 10,000 -> 12,000 + 1,000,000 -> 1,012,000 -> 1초 충분
    System.out.println(String.format("%.2f", answer));
  }

  private static class Algorithm {
    private final int[] parent;
    private final int[] rank;
    private final int[][] coordinate;
    private final List<Edge> edgeList;

    public Algorithm(int n) {
      parent = new int[n + 1];
      rank = new int[n + 1];
      coordinate = new int[n + 1][2];
      edgeList = new ArrayList<>();
    }

    public void addCoordinate(int idx, int x, int y) {
      coordinate[idx] = new int[]{x, y};
      parent[idx] = idx;
      rank[idx] = 1;
    }

    // 최종 시간 복잡도 -> O(N^2 + NlogN) -> O(N^2) N^2가 상대적으로 크다.
    public void initEdge(int n) {
      // O(N^2) -> 1,000,000
      // 정확하게는 O(N(N+1)/2) -> 500,500
      for (int i = 1; i < n; i++) {
        int[] cur = coordinate[i];
        for (int j = i + 1; j <= n; j++) {
          int[] adj = coordinate[j];
          edgeList.add(new Edge(i, j, getLength(cur, adj)));
        }
      }
      // 길이 기준으로 오름차순
      // O(NlogN)
      edgeList.sort(Comparator.comparingDouble(pre -> pre.length));
    }

    public int find(int u) {
      if (u != parent[u]) parent[u] = find(parent[u]);
      return parent[u];
    }

    public boolean union(int u, int v) {
      int rootU = find(u);
      int rootV = find(v);
      if (rootU == rootV) return false;
      int rankU = rank[rootU];
      int rankV = rank[rootV];
      if (rankU > rankV) {
        parent[rootV] = rootU;
      } else {
        parent[rootU] = rootV;
        if (rankU == rankV) rank[rootV] = rankV + 1;
      }
      return true;
    }

    private double getLength(int[] cur, int[] adj) {
      double bottom = Math.pow(adj[0] - cur[0], 2);
      double height = Math.pow(adj[1] - cur[1], 2);
      return Math.sqrt(bottom + height);
    }
  }

  private static class Edge {
    int me;
    int adj;
    double length;

    public Edge(int me, int adj, double length) {
      this.me = me;
      this.adj = adj;
      this.length = length;
    }

    public Edge(int me, int adj) {
      this.me = me;
      this.adj = adj;
    }
  }
}
