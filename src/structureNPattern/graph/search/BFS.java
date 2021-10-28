package structureNPattern.graph.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// BFS는 Queue 을 사용함
public class BFS {
  private static Map<Character, char[]> graph;

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

  public static void main(String[] args) throws Exception {
    char startNode = 'A';
    Queue<Character> needVisit = new LinkedList<Character>();
    List<Character> visitedNode = new ArrayList<Character>();
    init();
    needVisit.add(startNode);
    while (!needVisit.isEmpty()) {
      char nodeToVisit = needVisit.poll();
      visitedNode.add(nodeToVisit);
      char[] adjacentNodeList = graph.get(nodeToVisit);
      for (char node : adjacentNodeList) {
        if (!visitedNode.contains(node)) {
          needVisit.add(node);
        }
      }
    }
    print(visitedNode);
  }
}
