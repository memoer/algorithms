package programmers.Lv3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P43162 {
  public static void main(String[] args) {
    int n = 3;
    int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
    System.out.println(new Solution().solution(n, computers));
  }

  private static class Solution {
    private List<Integer>[] graph;
    private boolean[] visited;

    public int solution(int n, int[][] computers) {
      int answer = 0;
      init(n, computers);
      for (int i = 0; i < n; i++) {
        if(visited[i]) continue;
        bfs(i);
        answer += 1;
      }
      return answer;
    }

    private void init(int n, int[][] computers) {
      graph = new ArrayList[n];
      visited = new boolean[n];
      for (int i = 0; i < n; i++) {
        int[] computer = computers[i];
        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j < n; j++) {
          if (i != j && computer[j] == 1) list.add(j);
        }
        graph[i] = list;
      }
    }

    private void bfs(int start) {
      Queue<Integer> q = new LinkedList<>();
      q.offer(start);
      visited[start] = true;
      while (!q.isEmpty()) {
        int node = q.poll();
        for (int adj : graph[node]) {
          if (visited[adj]) continue;
          q.offer(adj);
          visited[adj] = true;
        }
      }
    }
  }
}
