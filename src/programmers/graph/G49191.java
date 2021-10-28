package programmers.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class G49191 {
  static class Solution {
    private Map<Integer, List<Integer>> gameNumberOfPlayer = new HashMap<>();

    private void createListOfMap(int key) {
      if (!gameNumberOfPlayer.containsKey(key)) {
        gameNumberOfPlayer.put(key, new ArrayList<>());
      }
    }

    public int solution(int n, int[][] gameList) {
      int answer = 0;
      List<Integer> completePlayer = new ArrayList<>();
      for (int[] game : gameList) {
        int playerA = game[0];
        int playerB = game[1];
        createListOfMap(playerA);
        createListOfMap(playerB);
        gameNumberOfPlayer.get(playerA).add(1);
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int n = 5;
    int[][] results = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 }, };
    System.out.println(new Solution().solution(n, results));
  }
}
