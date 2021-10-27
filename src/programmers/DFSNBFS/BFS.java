package programmers.DFSNBFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFS {
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

  public static void print(List<Character> list) {
    for (char c : list) {
      System.out.print(c + ", ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    List<Character> visited = new ArrayList<>();
    Queue<Character> needVisit = new LinkedList<>();
    init();
    needVisit.offer(START_NODE);
    while (!needVisit.isEmpty()) {
      char node = needVisit.poll();
      char[] adjNodeList = graph.get(node);
      visited.add(node);
      for (char adjNode : adjNodeList) {
        if (!visited.contains(adjNode)) {
          needVisit.offer(adjNode);
        }
      }
    }
    print(visited);
  }
}
