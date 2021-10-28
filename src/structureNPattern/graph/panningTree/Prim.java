package structureNPattern.graph.panningTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Prim {
  static class Edge implements Comparable<Edge> {
    int weight;
    char v;
    char u;

    public Edge(int weight, char v, char u) {
      this.weight = weight;
      this.v = v;
      this.u = u;
    }

    @Override
    public String toString() {
      return "(" + weight + ", " + v + ", " + u + ")";
    }

    @Override
    public int compareTo(Edge o) {
      return this.weight - o.weight;
    }
  }

  private static final char START_NODE = 'A';
  private static final Edge[] edges = { new Edge(7, 'A', 'B'), new Edge(5, 'A', 'D'), new Edge(8, 'B', 'C'),
      new Edge(9, 'B', 'D'), new Edge(7, 'B', 'E'), new Edge(5, 'C', 'E'), new Edge(8, 'D', 'E'), new Edge(6, 'D', 'F'),
      new Edge(8, 'E', 'F'), new Edge(9, 'E', 'G'), new Edge(11, 'F', 'G'), };
  private static final Map<Character, List<Edge>> graqph = new HashMap<>();

  private static void init() {
    for (Edge edge : edges) {
      int weight = edge.weight;
      char u = edge.u;
      char v = edge.v;
      if (!graqph.containsKey(u)) {
        graqph.put(u, new ArrayList<>());
      }
      if (!graqph.containsKey(v)) {
        graqph.put(v, new ArrayList<>());
      }
      graqph.get(u).add(new Edge(weight, u, v));
      graqph.get(v).add(new Edge(weight, v, u));
    }
  }

  private static void firstProcess(PriorityQueue<Edge> pq, Set<Character> connectedNode) {
    List<Edge> adjEdgeList = graqph.get(START_NODE);
    connectedNode.add(START_NODE);
    for (Edge adjEdge : adjEdgeList) {
      pq.add(adjEdge);
    }
  }

  private static void print(List<Edge> list) {
    for (Edge edge : list) {
      System.out.println(edge);
    }
  }

  public static void main(String[] args) throws Exception {
    List<Edge> result = new ArrayList<>();
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    Set<Character> connectedNode = new HashSet<>();
    init();
    firstProcess(pq, connectedNode);
    while (!pq.isEmpty()) {
      Edge curEdge = pq.poll();
      if (!connectedNode.contains(curEdge.u)) {
        result.add(curEdge);
        connectedNode.add(curEdge.u);
        for (Edge edge : graqph.get(curEdge.u)) {
          if (!connectedNode.contains(edge.u)) {
            pq.add(edge);
          }
        }
      }
    }
    print(result);
  }
}
