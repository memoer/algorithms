package temp.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class B1939 {
  private static List<Node>[] list;
  private static int N;
  private static Node START;
  private static int END;

  private static class Node {
    public int idx;
    public int weight;

    public Node(int idx, int weight) {
      this.idx = idx;
      this.weight = weight;
    }
  }

  private static boolean isAvailable(int mid) {
    boolean[] visited = new boolean[N + 1];
    Stack<Node> needVisit = new Stack<>();
    needVisit.add(START);
    visited[START.idx] = true;
    while (!needVisit.isEmpty()) {
      Node cur = needVisit.pop();
      for (Node adj : list[cur.idx]) {
        if (visited[adj.idx] || adj.weight < mid) {
          continue;
        } else if (adj.idx == END) {
          return true;
        }
        needVisit.push(adj);
        visited[adj.idx] = true;
      }
    }
    return false;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int left = 1;
    int right = Integer.MIN_VALUE;
    int result = 0;
    N = Integer.parseInt(st.nextToken());
    final int M = Integer.parseInt(st.nextToken());
    list = new ArrayList[N + 1];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      // a번 섬과 b번 섬 사이에 중량제한이 w인 다리가 존재
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      if (list[a] == null) {
        list[a] = new ArrayList<>();
      }
      if (list[b] == null) {
        list[b] = new ArrayList<>();
      }
      list[a].add(new Node(b, w));
      list[b].add(new Node(a, w));
      right = Math.max(right, w);
    }
    st = new StringTokenizer(br.readLine());
    START = new Node(Integer.parseInt(st.nextToken()), 0);
    END = Integer.parseInt(st.nextToken());
    br.close();

    while (left <= right) {
      int mid = (left + right) / 2;
      if (isAvailable(mid)) {
        left = mid + 1;
        result = mid;
      } else {
        right = mid - 1;
      }
    }

    System.out.println(result);
  }
}
