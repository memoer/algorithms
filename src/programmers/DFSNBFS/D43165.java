package programmers.DFSNBFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class D43165 {
  static class Solution {
    private int answer = 0;

    private Map<Integer, List<Integer>> getGraph(int[][] computers) {
      Map<Integer, List<Integer>> graph = new HashMap<>();
      for (int i = 0; i < computers.length; i++) {
        List<Integer> temp = new ArrayList<>();
        for (int j = 0; j < computers[i].length; j++) {
          if (i == j) {
            continue;
          }
          temp.add(computers[i][j]);
        }
        graph.put(i, temp);
      }
      return graph;
    }

    public int solution(int n, int[][] computers) {
      Map<Integer, List<Integer>> graph = getGraph(computers);
      Stack<Integer> needVisit = new Stack<>();
      needVisit.add(0);
      while (!needVisit.isEmpty()) {
        int key = needVisit.pop();
        List<Integer> adjNodeList = graph.get(key);
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int n = 3;
    int[][] computers = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
    System.out.println(new Solution().solution(n, computers));
  }
}
