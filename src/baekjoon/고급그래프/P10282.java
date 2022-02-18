package baekjoon.고급그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P10282 {
  private static int n;
  private static int d;
  private static int c;

  private static class Computer implements Comparable<Computer> {
    public int idx;
    public int path;

    public Computer(int idx, int path) {
      this.idx = idx;
      this.path = path;
    }

    @Override
    public int compareTo(Computer o) {
      return this.path - o.path;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    int t = Integer.parseInt(br.readLine());
    Map<Integer, List<Computer>> graph = new HashMap<>();
    Map<Integer, Integer> path = new HashMap<>();
    PriorityQueue<Computer> pq = new PriorityQueue<>();
    int max = 0;

    while (t-- > 0) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken()) + 1;
      d = Integer.parseInt(st.nextToken());
      c = Integer.parseInt(st.nextToken());

      for (int j = 0; j < d; j++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        if (!graph.containsKey(b)) {
          graph.put(b, new ArrayList<>());
        }
        graph.get(b).add(new Computer(a, Integer.parseInt(st.nextToken())));
      }

      max = Integer.MIN_VALUE;
      path.put(c, 0);
      pq.offer(new Computer(c, 0));
      while (!pq.isEmpty()) {
        Computer cur = pq.poll();
        if (graph.get(cur.idx) == null) {
          continue;
        }
        for (Computer adj : graph.get(cur.idx)) {
          int sum = path.get(cur.idx) + adj.path;
          if (path.get(adj.idx) == null || path.get(adj.idx) > sum) {
            path.put(adj.idx, sum);
            pq.offer(new Computer(adj.idx, sum));
          }
        }
      }

      for (int second : path.values()) {
        max = Math.max(max, second);
      }

      bw.write(path.keySet().size() + " " + max + "\n");
      path.clear();
      graph.clear();
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
