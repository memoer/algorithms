package programmers.highscore.fullsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class P86971 {

  public static void main(String[] args) {
    int n = 9;
    int[][] wires = new int[][]{
        {1, 3},
        {2, 3},
        {3, 4},
        {4, 5},
        {4, 6},
        {4, 7},
        {7, 8},
        {7, 9}
    };
    int solution = new Solution().solution(n, wires);
    System.out.println(solution);
  }

  private static class Solution {

    private int n;

    public int solution(int n, int[][] wires) {
      this.n = n;
      int answer = Integer.MAX_VALUE;
      for (int i = 0; i < n - 1; i++) {
        int[] disconnected = wires[i];
        wires[i] = null;
        Map<Integer, List<Integer>> graph = createGraph(wires);
        int diff = search(graph);
        answer = Math.min(answer, diff);
        wires[i] = disconnected;
      }
      return answer;
    }

    private Map<Integer, List<Integer>> createGraph(int[][] wires) {
      Map<Integer, List<Integer>> graph = new HashMap<>();
      for (int[] wire : wires) {
        if (wire == null) {
          continue;
        }
        graph.computeIfAbsent(wire[0], k -> new ArrayList<>()).add(wire[1]);
        graph.computeIfAbsent(wire[1], k -> new ArrayList<>()).add(wire[0]);
      }
      return graph;
    }

    private int search(Map<Integer, List<Integer>> graph) {
      boolean[] visited = new boolean[this.n + 1];
      Queue<Integer> q = new LinkedList<>();
      List<Integer> list = new ArrayList<>();
      for (int node = 1; node <= this.n; node++) {
        if (visited[node]) {
          continue;
        }
        int count = 0;
        q.offer(node);
        while (!q.isEmpty()) {
          Integer poll = q.poll();
          count += 1;
          visited[poll] = true;
          if (!graph.containsKey(poll)) {
            continue;
          }
          for (Integer adj : graph.get(poll)) {
            if (visited[adj]) {
              continue;
            }
            q.offer(adj);
          }
        }
        list.add(count);
      }
      return Math.abs(list.get(0) - list.get(1));
    }
  }
}
