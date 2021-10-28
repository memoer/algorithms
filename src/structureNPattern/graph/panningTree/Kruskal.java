package structureNPattern.graph.panningTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kruskal {
  static class Edge {
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
  }

  private static final char[] vertices = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
  private static final Edge[] edges = { new Edge(7, 'A', 'B'), new Edge(5, 'A', 'D'), new Edge(8, 'B', 'C'),
      new Edge(9, 'B', 'D'), new Edge(7, 'B', 'E'), new Edge(5, 'C', 'E'), new Edge(8, 'E', 'F'), new Edge(9, 'E', 'G'),
      new Edge(7, 'D', 'E'), new Edge(6, 'D', 'F'), new Edge(11, 'F', 'G'), };
  private static final Map<Character, Integer> rank = new HashMap<>();
  private static final Map<Character, Character> parent = new HashMap<>();
  private static final List<Edge> result = new ArrayList<>();

  private static void init() {
    // ! 크루스칼은 가중치가 가장 작은 간선부터 시작한다.
    Arrays.sort(edges, (pre, cur) -> pre.weight - cur.weight);
    for (char v : vertices) {
      rank.put(v, 0);
      parent.put(v, v);
    }
  }

  private static void union(char rootV, char rootU) {
    int rankV = rank.get(rootV);
    int rankU = rank.get(rootU);
    if (rootV > rootU) {
      parent.replace(rootU, rootV);
    } else {
      parent.replace(rootV, rootU);
      if (rankV == rankU) {
        rank.replace(rootU, rankU + 1);
      }
    }
  }

  private static char findRoot(char node) {
    char parentNode = parent.get(node);
    if (node != parentNode) {
      // path compression
      parent.replace(node, findRoot(parentNode));
    }
    return parent.get(node);
  }

  private static void print() {
    for (Edge edge : result) {
      System.out.println(edge);
    }
  }

  public static void main(String[] args) throws Exception {
    init();
    for (Edge edge : edges) {
      System.out.println(edge);
      char nodeV = edge.v;
      char nodeU = edge.u;
      char rootV = findRoot(nodeV);
      char rootU = findRoot(nodeU);
      // System.out.println(rootV + ", " + rootU);
      if (rootV != rootU) {
        union(rootV, rootU);
        result.add(edge);
      }
    }
    print();
  }
}
