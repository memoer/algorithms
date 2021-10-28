package structureNPattern.graph.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Stack;

// DFS는 Stack 을 사용
public class DFS {
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
    Stack<Character> needVisit = new Stack<Character>();
    List<Character> visitedNode = new ArrayList<Character>();
    init();
    needVisit.add(startNode);
    while (!needVisit.isEmpty()) {
      char nodeToVisit = needVisit.pop();
      // 방문한 노드
      visitedNode.add(nodeToVisit);
      char[] adjacentNodeList = graph.get(nodeToVisit);
      for (char node : adjacentNodeList) {
        if (!visitedNode.contains(node)) {
          // 방문하지 않은 노드만
          needVisit.add(node);
        }
      }
    }
    print(visitedNode);
  }
}
