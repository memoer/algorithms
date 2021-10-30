package structureNPattern.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

// DFS는 Stack을 사용함
public class DFS {
  static class Solution {
    private Map<Character, char[]> graph;

    public void init(char startNode, List<Character> answer, Stack<Character> s) {
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
      s.add(startNode);
    }

    public List<Character> solution(char startNode) {
      List<Character> answer = new ArrayList<>();
      Stack<Character> s = new Stack<>();
      init(startNode, answer, s);
      while (!s.isEmpty()) {
        char node = s.pop();
        answer.add(node);
        for (char adjNode : graph.get(node)) {
          s.push(adjNode);
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) throws Exception {
    for (char c : new Solution().solution('A')) {
      System.out.print(c + ", ");
    }
  }
}
