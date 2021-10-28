package structureNPattern.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// BFS는 Queue 을 사용함
public class BFS {
  static class Solution {
    private Map<Character, char[]> graph;

    public void init() {
      graph = new HashMap<Character, char[]>();
      graph.put('A', new char[] { 'B', 'C' });
      graph.put('B', new char[] { 'D' });
      graph.put('D', new char[] { 'E', 'F' });
      graph.put('E', new char[] {});
      graph.put('F', new char[] {});
      graph.put('C', new char[] { 'G', 'H', 'I' });
      graph.put('G', new char[] {});
      graph.put('H', new char[] {});
      graph.put('I', new char[] { 'J' });
      graph.put('J', new char[] {});
    }

    public void print(List<Character> list) {
      for (char c : list) {
        System.out.print(c + ", ");
      }
      System.out.println();
    }

    public void solution(char startNode) {
      init();
      Queue<Character> needVisit = new LinkedList<>();
      List<Character> visited = new ArrayList<>();
      needVisit.offer(startNode);
      while (!needVisit.isEmpty()) {
        char node = needVisit.poll();
        visited.add(node);
        char[] adjNodeList = graph.get(node);
        for (char adjNode : adjNodeList) {
          if (!visited.contains(adjNode)) {
            needVisit.add(adjNode);
          }
        }
      }
      print(visited);
    }
  }

  public static void main(String[] args) throws Exception {
    new Solution().solution('A');
  }
}
