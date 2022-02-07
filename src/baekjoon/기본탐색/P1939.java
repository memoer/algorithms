package baekjoon.기본탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
  int name;
  int weight;

  public Node(int name, int weight) {
    this.name = name;
    this.weight = weight;
  }
}

public class P1939 {
  private static List<Node>[] nodeList;

  public static void initNodeList(int N) {
    nodeList = new ArrayList[N + 1];
    for (int i = 0; i < N + 1; i++) {
      nodeList[i] = new ArrayList<>();
    }
  }

  public static int getHighWeight(int M, StringTokenizer st, BufferedReader br) throws IOException {
    int highWeight = 0;
    while (M-- > 0) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      nodeList[from].add(new Node(to, weight));
      nodeList[to].add(new Node(from, weight));
      highWeight = Math.max(highWeight, weight);
    }
    return highWeight;
  }

  private static int[] getFactory(StringTokenizer st, BufferedReader br) throws IOException {
    st = new StringTokenizer(br.readLine());
    int factoryA = Integer.parseInt(st.nextToken());
    int factoryB = Integer.parseInt(st.nextToken());
    return new int[] { factoryA, factoryB };
  }

  private static boolean isConnected(Queue<Integer> needVisit, boolean[] visited, int startFactory, int endFactory,
      int weight) {
    needVisit.add(startFactory);
    visited[startFactory] = true;
    while (!needVisit.isEmpty()) {
      int curNode = needVisit.poll();
      for (Node adjNode : nodeList[curNode]) {
        if (adjNode.weight >= weight) {
          if (adjNode.name == endFactory) {
            return true;
          } else if (!visited[adjNode.name]) {
            visited[adjNode.name] = true;
            needVisit.add(adjNode.name);
          }
        }
      }
    }
    return false;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int result = 0;
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    initNodeList(N);
    int lowWeight = 1;
    int highWeight = getHighWeight(M, st, br);
    int[] factory = getFactory(st, br);

    Queue<Integer> needVisit = new LinkedList<>();
    boolean[] visited = new boolean[N + 1];
    while (lowWeight <= highWeight) {
      int weight = (lowWeight + highWeight) / 2;
      if (isConnected(needVisit, visited, factory[0], factory[1], weight)) {
        lowWeight = weight + 1;
        result = weight;
      } else {
        highWeight = weight - 1;
      }
      Arrays.fill(visited, false);
      needVisit.clear();
    }
    System.out.println(result);

    br.close();
  }

}
