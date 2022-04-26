package baekjoon.그래프고급;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P10282Other {
  private static int n;
  private static int d;
  private static int start;

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
    int[] path;
    PriorityQueue<Computer> pq = new PriorityQueue<>();
    int length = 0;
    int max = 0;

    while (t-- > 0) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken()) + 1;
      d = Integer.parseInt(st.nextToken());
      start = Integer.parseInt(st.nextToken());

      List<Computer>[] graph = new ArrayList[n];
      for (int i = 1; i < n; i++) {
        graph[i] = new ArrayList<>();
      }

      for (int j = 0; j < d; j++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        graph[b].add(new Computer(a, Integer.parseInt(st.nextToken())));
      }

      path = new int[n];
      for (int i = 1; i < n; i++) {
        if (i != start) {
          path[i] = Integer.MAX_VALUE;
        }
      }

      pq.offer(new Computer(start, 0));
      while (!pq.isEmpty()) {
        Computer cur = pq.poll();
        for (Computer adj : graph[cur.idx]) {
          int sum = path[cur.idx] + adj.path;
          if (path[adj.idx] > sum) {
            path[adj.idx] = sum;
            pq.offer(new Computer(adj.idx, sum));
          }
        }
      }

      length = 0;
      max = Integer.MIN_VALUE;
      for (int i = 1; i < n; i++) {
        if (path[i] == Integer.MAX_VALUE) {
          continue;
        }
        length += 1;
        max = Math.max(max, path[i]);
      }
      bw.write(length + " " + max + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
