package programmers.DFSNBFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DFS {
  private static Map<Character, char[]> graph;
  private static char START_NODE = 'A';

  public static void init() {
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

  private static void print(List<Character> list) {
    for (char num : list) {
      System.out.print(num + " -> ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Stack<Character> needVisit = new Stack<>();
    List<Character> visited = new ArrayList<>();
    init();
    needVisit.add(START_NODE);
    while (!needVisit.isEmpty()) {
      char node = needVisit.pop();
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
