package structureNPattern.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// 기본 프림 알고리즘은 간선 중심으로 알고리즘이 진행된다. [ 간선 중심? [ (8,'A','B'), (2,'A','C'), ... ] ]
// 개선 프림 알고리즘은 "노드 중심 + 우선순위 큐 사용" [ 노드 중심? { "A":{"B":8, "C":2}, ... } ]
public class Prim {
  enum EDGE {
    WEIGHT, CUR, ADJ
  }

  // 아래 알고리즘은 간선 중심으로 진행됨
  // 1. 특정 노드 선택 [ startNode ]
  // 2. 해당 노드의 인접한 모든 간선들을 우선순위 큐에 넣는다.
  // 3. 우선순위 큐에서 간선을 하나 뺸다.
  // 4. 인접한 노드가 연결되어 있지 않다면, 해당 간선을 result list 에 넣는다.
  // 4-1. 우선순위 큐에 인접한 노드의 인접한 간선들을 모두 넣는다.
  // 우선순위 큐가 비워질 때 까지, 3 ~ 4 를 반복
  static class Solution {
    // 간선 리스트
    private int[][] edgeList = { { 7, 'A', 'B' }, { 5, 'A', 'D' }, { 8, 'B', 'C' }, { 9, 'B', 'D' }, { 7, 'B', 'E' },
        { 5, 'C', 'E' }, { 8, 'D', 'E' }, { 6, 'D', 'F' }, { 8, 'E', 'F' }, { 9, 'E', 'G' }, { 11, 'F', 'G' }, };

    private void init(char startNode, Map<Character, List<int[]>> graph, List<Character> visited,
        PriorityQueue<int[]> pq) {
      for (int[] edge : edgeList) {
        int weight = edge[EDGE.WEIGHT.ordinal()];
        char a = (char) edge[EDGE.CUR.ordinal()];
        char b = (char) edge[EDGE.ADJ.ordinal()];
        if (!graph.containsKey(a)) {
          graph.put(a, new ArrayList<>());
        }
        if (!graph.containsKey(b)) {
          graph.put(b, new ArrayList<>());
        }
        graph.get(a).add(new int[] { weight, a, b });
        graph.get(b).add(new int[] { weight, b, a });
      }
      for (int[] edge : graph.get(startNode)) {
        pq.offer(edge);
      }
      visited.add(startNode);
    }

    public List<int[]> solution(char startNode) {
      List<int[]> result = new ArrayList<>();
      Map<Character, List<int[]>> graph = new HashMap<>();
      PriorityQueue<int[]> pq = new PriorityQueue<>((pre, cur) -> pre[0] - cur[0]);
      List<Character> visited = new ArrayList<>();
      init(startNode, graph, visited, pq);
      while (!pq.isEmpty()) {
        int[] edge = pq.poll();
        char adj = (char) edge[EDGE.ADJ.ordinal()];
        if (!visited.contains(adj)) {
          result.add(edge);
          visited.add(adj);
          for (int[] adjEdge : graph.get(adj)) {
            pq.offer(adjEdge);
          }
        }
      }
      return result;
    }
  }

  public static void main(String[] args) {
    List<int[]> answer = new Solution().solution('A');
    for (int[] edge : answer) {
      System.out.println(edge[0] + ", " + (char) edge[1] + ", " + (char) edge[2]);
    }
  }
}
