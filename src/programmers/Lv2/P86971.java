package programmers.Lv2;

import java.util.*;

public class P86971 {
  public static void main(String[] args) {
//    int n = 9;
//    int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
    int n = 4;
    int[][] wires = {{1,2},{2,3},{3,4}};
    System.out.println(new Solution().solution(n, wires));
  }

  private static class Solution {
    private int answer;
    private Map<Integer, List<Integer>> graph;
    private boolean[][] connected;
    private int n;
    private int[][] wires;

    public int solution(int n, int[][] wires) {
      this.n = n + 1;
      this.wires = wires;
      init();

      for (int[] wire : wires) {
        connected[wire[0]][wire[1]] = false;
        connected[wire[1]][wire[0]] = false;
        int candidate = getCandidate();
        answer = Math.min(answer, candidate);
        connected[wire[0]][wire[1]] = true;
        connected[wire[1]][wire[0]] = true;
      }

      return answer;
    }

    private void init() {
      answer = Integer.MAX_VALUE;
      graph = new HashMap<>();
      connected = new boolean[n][n];
      for (int[] wire : wires) {
        int a = wire[0];
        int b = wire[1];
        connected[a][b] = true;
        connected[b][a] = true;
        if (!graph.containsKey(a)) graph.put(a, new ArrayList<>());
        if (!graph.containsKey(b)) graph.put(b, new ArrayList<>());
        graph.get(a).add(b);
        graph.get(b).add(a);
      }
    }

    private int getCandidate() {
      int diff = 0;
      boolean[] visited = new boolean[n];
      for (int i = 1; i < n; i++) {
        if (visited[i]) continue;
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        while (!q.isEmpty()) {
          count += 1;
          int cur = q.poll();
          for (int adj : graph.get(cur)) {
            if (!connected[cur][adj] || visited[adj]) continue;
            q.offer(adj);
            visited[adj] = true;
          }
        }
        if (diff == 0) diff = count;
        else diff = Math.abs(count - diff);
      }
      return diff;
    }
  }
}
