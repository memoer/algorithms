package temp.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P5719 {
  private static int n;
  private static int m;
  private static int start;
  private static int end;
  private static List<Node>[] graph;
  private static List<Integer>[] parent;
  private static int[] path;
  private static boolean[][] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      if (n == 0 && m == 0) {
        break;
      }

      st = new StringTokenizer(br.readLine());
      start = Integer.parseInt(st.nextToken());
      end = Integer.parseInt(st.nextToken());

      graph = new ArrayList[n];
      parent = new ArrayList[n];
      for (int i = 0; i < n; i++) {
        graph[i] = new ArrayList<>();
        parent[i] = new ArrayList<>();
      }
      for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        graph[Integer.parseInt(st.nextToken())]
            .add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
      }

      path = new int[n];
      visited = new boolean[n][n];
      dijkstra();
      backtracking(end);
      dijkstra();

      if (path[end] == Integer.MAX_VALUE) {
        bw.write(-1 + "\n");
      } else {
        bw.write(path[end] + "\n");
      }
    }

    bw.flush();
    bw.close();
    br.close();
  }

  private static void dijkstra() {
    for (int i = 0; i < n; i++) {
      path[i] = Integer.MAX_VALUE;
    }
    path[start] = 0;

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0));
    while (!pq.isEmpty()) {
      Node cur = pq.poll();
      if (cur.path > path[cur.idx]) {
        continue;
      }
      for (Node adj : graph[cur.idx]) {
        if (visited[cur.idx][adj.idx]) {
          continue;
        }
        int sum = cur.path + adj.path;
        if (sum == path[adj.idx]) {
          parent[adj.idx].add(cur.idx);
        } else if (sum < path[adj.idx]) {
          path[adj.idx] = sum;
          pq.offer(new Node(adj.idx, sum));
          parent[adj.idx].clear();
          parent[adj.idx].add(cur.idx);
        }
      }
    }
  }

  private static void backtracking(int targetIdx) {
    if (start == targetIdx) {
      return;
    }
    for (int adjIdx : parent[targetIdx]) {
      if (!visited[adjIdx][targetIdx]) {
        visited[adjIdx][targetIdx] = true;
        backtracking(adjIdx);
      }
    }
  }

  static class Node implements Comparable<Node> {
    public int idx;
    public int path;

    public Node(int idx, int path) {
      this.idx = idx;
      this.path = path;
    }

    @Override
    public int compareTo(Node o) {
      return Integer.compare(this.path, o.path);
    }
  }
}
