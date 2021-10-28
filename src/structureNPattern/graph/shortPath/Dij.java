package structureNPattern.graph.shortPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;
import java.util.stream.Collectors;

// 방향성이 있는 그래프에서 최단경로 탐색

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

  @Override
  public String toString() {
    return "Name: " + this.name + ", distance: " + this.distance;
  }
}

public class Dij {
  private static final char START_NODE = 'A';
  private static final Map<Character, Integer> result = new HashMap<>();
  private static final Map<Character, List<Node>> graph = new HashMap<>();
  private static final Map<Character, List<Character>> path = new HashMap<>();

  public static void initResult() {
    result.put('A', 0);
    result.put('B', Integer.MAX_VALUE);
    result.put('C', Integer.MAX_VALUE);
    result.put('D', Integer.MAX_VALUE);
    result.put('E', Integer.MAX_VALUE);
    result.put('F', Integer.MAX_VALUE);
  }

  public static void initGraph() {
    graph.put('A', Arrays.asList(new Node('B', 8), new Node('C', 1), new Node('D', 2)));
    graph.put('B', Arrays.asList());
    graph.put('C', Arrays.asList(new Node('B', 5), new Node('D', 2)));
    graph.put('D', Arrays.asList(new Node('E', 3), new Node('F', 5)));
    graph.put('E', Arrays.asList(new Node('F', 1)));
    graph.put('F', Arrays.asList(new Node('A', 5)));
  }

  public static void initPath() {
    for (char nodeName : new char[] { 'A', 'B', 'C', 'D', 'E', 'F' }) {
      path.put(nodeName, new ArrayList<>());
    }
  }

  public static void printResult() {
    for (Entry<Character, Integer> entry : result.entrySet()) {
      List<Character> list = path.get(entry.getKey());
      list.add(0, START_NODE);
      System.out.println("[ weight: " + entry.getValue() + " ] "
          + String.join(" -> ", list.stream().map((d) -> Character.toString(d)).collect(Collectors.toList())));
    }
  }

  public static List<Character> dp(List<Character> list) {
    List<Character> newList = new ArrayList<>();
    for (char c : list) {
      newList.add(c);
    }
    return newList;
  }

  public static void addPath(Node curNode, Node adjNode) {
    List<Character> curPath = path.get(curNode.name);
    List<Character> adjPath = dp(curPath);
    adjPath.add(adjNode.name);
    path.put(adjNode.name, adjPath);
  }

  public static void main(String[] args) throws Exception {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    initResult();
    initGraph();
    initPath();
    pq.add(new Node(START_NODE, 0));
    while (!pq.isEmpty()) {
      Node curNode = pq.poll();
      List<Node> adjNodeList = graph.get(curNode.name);
      for (Node adjNode : adjNodeList) {
        int curDist = result.get(adjNode.name);
        int adjDist = curNode.distance + adjNode.distance;
        if (adjDist < curDist) {
          addPath(curNode, adjNode);
          pq.add(new Node(adjNode.name, adjDist));
          result.replace(adjNode.name, adjDist);
        }
      }
    }

    printResult();
  }
}
