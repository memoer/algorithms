package programmers.Lv3;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class P72413 {
  /**
   * 무방향 가중치 그래프
   * n -> 지점의 개수 [3 <= n <= 200]
   * s -> 출발 지점
   * a -> A의 도착지점
   * b -> B의 도착지점
   * fares -> 지점 사이의 예상 택시요금 [2 <= fares.length <= 4,950
   */
  // 두 사람이 s에서 출발해서 각각의 도착 지점까지 택시를 타고 간다고 가정할 때, 최저 예상 택시요금을 계산해서 return
  private List<Node>[] graph;
  private int[] path;
  private int n;

  public static void main(String[] args) {
    int n = 6, s = 4, a = 6, b = 2;
    int[][] fares = {
            {4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}
    };
    System.out.println(new P72413().solution(n, s, a, b, fares));
  }

  public int solution(int n, int s, int a, int b, int[][] fares) {
    this.graph = new List[n + 1];
    this.path = new int[n + 1];
    this.n = n;
    for (int[] fare : fares) {
      int start = fare[0];
      int end = fare[1];
      int weight = fare[2];
      if (graph[start] == null) graph[start] = new ArrayList<>();
      if (graph[end] == null) graph[end] = new ArrayList<>();
      graph[start].add(new Node(end, weight));
      graph[end].add(new Node(start, weight));
    }

    int ans = Integer.MAX_VALUE;
    for (int v = 1; v <= n; v++) {
      dijstra(v);
      ans = Integer.min(ans, path[s] + path[a] + path[b]);
    }

    return ans;
  }

  private void dijstra(int start) {
    for (int i = 1; i <= n; i++) path[i] = Integer.MAX_VALUE;
    path[start] = 0;

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0));
    while (!pq.isEmpty()) {
      Node cur = pq.poll();
      if (cur.weigth > path[cur.idx]) continue;
      if (graph[cur.idx] == null) continue;
      for (Node adj : graph[cur.idx]) {
        int sum = cur.weigth + adj.weigth;
        if (sum >= path[adj.idx]) continue;
        path[adj.idx] = sum;
        pq.offer(new Node(adj.idx, sum));
      }
    }
  }

  private static class Node implements Comparable<Node> {
    private int idx;
    private int weigth;

    public Node(int idx, int weigth) {
      this.idx = idx;
      this.weigth = weigth;
    }

    @Override
    public int compareTo(Node o) {
      return Integer.compare(weigth, o.weigth);
    }
  }

}
