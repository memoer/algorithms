package temp.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1939 {
  private static class Node {
    public int id;
    public int weight;

    public Node(int id, int weight) {
      this.id = id;
      this.weight = weight;
    }
  }

  private static boolean isConnected(int weight, int start, int end, List<Node>[] list, Queue<Integer> needVisit,
      boolean[] visited) {
    needVisit.add(start);
    visited[start] = true;
    while (!needVisit.isEmpty()) {
      int curNode = needVisit.poll();
      for (Node adjNode : list[curNode]) {
        if (adjNode.weight >= weight) {
          if (adjNode.id == end) {
            return true;
          } else if (!visited[adjNode.id]) {
            needVisit.add(adjNode.id);
            visited[adjNode.id] = true;
          }
        }
      }
    }
    return false;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    List<Node>[] list = new ArrayList[N + 1];
    int lowWeight = Integer.MAX_VALUE;
    int highWeight = Integer.MIN_VALUE;
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      if (list[start] == null) {
        list[start] = new ArrayList<>();
      }
      if (list[end] == null) {
        list[end] = new ArrayList<>();
      }
      list[start].add(new Node(end, weight));
      list[end].add(new Node(start, weight));
      lowWeight = Math.min(lowWeight, weight);
      highWeight = Math.max(highWeight, weight);
    }

    st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());

    Queue<Integer> needVisit = new LinkedList<>();
    boolean[] visited = new boolean[N + 1];
    int result = 0;

    while (lowWeight <= highWeight) {
      int weight = (lowWeight + highWeight) / 2;
      if (isConnected(weight, start, end, list, needVisit, visited)) {
        lowWeight = weight + 1;
        result = weight;
      } else {
        highWeight = weight - 1;
      }
      needVisit.clear();
      Arrays.fill(visited, false);
    }

    System.out.println(result);
    br.close();
  }
}
