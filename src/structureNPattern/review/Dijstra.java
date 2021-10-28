package structureNPattern.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
  char name;
  int distance;

  public Node(char name, int distance) {
    this.name = name;
    this.distance = distance;
  }

  @Override
  public int compareTo(Node o) {
    return this.distance - o.distance;
  }
}

public class Dijstra {
  static class Solution {
    private Map<Character, List<Node>> graph = new HashMap<>();
    private Map<Character, Integer> path = new HashMap<>();

    private void initGraph() {
      graph.put('A', Arrays.asList(new Node('B', 8), new Node('C', 1), new Node('D', 2)));
      graph.put('B', Arrays.asList());
      graph.put('C', Arrays.asList(new Node('B', 5), new Node('D', 2)));
      graph.put('D', Arrays.asList(new Node('E', 3), new Node('F', 5)));
      graph.put('E', Arrays.asList(new Node('F', 1)));
      graph.put('F', Arrays.asList(new Node('A', 5)));
    }

    private void initPath(char startNode) {
      for (char key : graph.keySet()) {
        path.put(key, key == startNode ? 0 : Integer.MAX_VALUE);
      }
    }

    public Map<Character, Integer> solution(char startNode) {
      PriorityQueue<Node> pq = new PriorityQueue<>();
      initGraph();
      initPath(startNode);
      pq.offer(new Node(startNode, 0));
      while (!pq.isEmpty()) {
        Node node = pq.poll();
        for (Node adjNode : graph.get(node.name)) {
          int sumDistance = path.get(node.name) + adjNode.distance;
          if (sumDistance < path.get(adjNode.name)) {
            path.replace(adjNode.name, sumDistance);
            pq.offer(adjNode);
          }
        }
      }
      return path;
    }
  }

  public static void main(String[] args) {
    Map<Character, Integer> result = new Solution().solution('A');
    for (char key : result.keySet()) {
      System.out.print(key + " : " + result.get(key) + ", ");
    }
    System.out.println();
  }
}
