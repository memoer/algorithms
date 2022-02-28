package baekjoon.고급그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class P1774 {
  private static class Edge {
    public int a;
    public int b;
    public double weight;

    public Edge(int a, int b, double weight) {
      this.a = a;
      this.b = b;
      this.weight = weight;
    }
  }

  private static Map<Integer, Integer> parent = new HashMap<>();
  private static Map<Integer, Integer> rank = new HashMap<>();

  private static double getWeight(int[] a, int[] b) {
    int x = a[0] - b[0];
    int y = a[1] - b[1];
    return Math.sqrt((x * x) + (y * y));
  }

  private static int find(int i) {
    if (i != parent.get(i)) {
      parent.put(i, find(parent.get(i)));
    }
    return parent.get(i);
  }

  private static void union(int rootA, int rootB) {
    int rankA = rank.get(rootA);
    int rankB = rank.get(rootB);
    if (rankA > rankB) {
      parent.put(rootB, rootA);
    } else {
      parent.put(rootA, rootB);
      if (rankA == rankB) {
        rank.put(rootB, rankB + 1);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    List<int[]> list = new ArrayList<>();
    List<Edge> graph = new ArrayList<>();
    double result = 0;

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      list.add(new int[] { x, y });
    }
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        graph.add(new Edge(i + 1, j + 1, getWeight(list.get(i), list.get(j))));
      }
    }
    for (int i = 1; i < n + 1; i++) {
      parent.put(i, i);
      rank.put(i, 0);
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int rootA = find(Integer.parseInt(st.nextToken()));
      int rootB = find(Integer.parseInt(st.nextToken()));
      union(rootA, rootB);
    }
    br.close();

    graph.sort((pre, cur) -> {
      if (pre.weight < cur.weight) {
        return -1;
      }
      return 1;
    });
    for (Edge edge : graph) {
      int rootA = find(edge.a);
      int rootB = find(edge.b);
      if (rootA != rootB) {
        union(rootA, rootB);
        result += edge.weight;
      }
    }
    System.out.printf("%.2f\n", result);
  }
}
