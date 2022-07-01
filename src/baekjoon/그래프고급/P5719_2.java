package baekjoon.그래프고급;

import java.io.*;
import java.util.*;

// 반례
//5 8
//0 4
//0 1 1
//0 2 1
//1 3 1
//2 3 1
//3 4 1
//0 4 6
//1 4 4
//2 4 4
//0 0
// 답 -> 6

/*
1. 간선 한 번 반복문 돌며 그래프 초기화 -> O(E) -> 10,000 [ 최대 간선 수 = 10,000 ]
1. 다익스트라 총 두 번 -> ElogE * 2 -> 132,877 * 2 -> 265,754
2. 간선 추적 [BackTracking] 최악 -> O(E) -> 10,000
3. 총 시간복잡도 -> O(2E+2ElogE) -> 285,754
 */
public class P5719_2 {
  private static Data data;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    data = new Data();

    while (true) {
      if (data.init(br)) break;
      // O(ElogE)
      dijkstra();
      // O(kE)
      backtracking(data.end);
      // O(ElogE)
      dijkstra();
      // O(2ElogE + kE)
      // 최대 간선 수 -> 10,000
      // 275,754 -> 시간 충분
      int answer = data.dist[data.end];

      bw.write((answer == Integer.MAX_VALUE ? -1 : answer) + "\n");
    }

    bw.write("\n");
    bw.flush();
    bw.close();
  }

  // 1. 최악의 경우, 시작점-도착점의 모든 간선이 다 최단경로인 경우임. 이 경우, 모든 간선이 blocked된다.
  // 2. 연산횟수는 모두 합치면 E^2이 된다.
  // 3. 간선의 최대 수는 10,000이고, 이를 제곱하면 100,000,000[1억]이 되고 이는 거의 1초의 시간이 걸린다.
  // 4. 하지만, 반복문 내부에서 이미 지나간 간선은 continue로 걸러지기 때문에, 1초보다는 덜 걸리는 듯 싶다.
  // 5. 대략 잡아서 간선의 수만큼만 시간복잡도가 되어 통과된듯 싶다 -> O(kE)
  private static void backtracking(int curIdx) {
    if (curIdx == data.start) return;
    for (int adjIdx : data.track[curIdx]) {
      if (data.blocked[adjIdx][curIdx]) continue;
      data.blocked[adjIdx][curIdx] = true;
      backtracking(adjIdx);
    }
  }

  //O(V+VELogE) -> V 보다 VElogE가 현저히 크므로, **O(VElogE)**
  private static void dijkstra() {
    // 1. 반복문 O(V+1) -> "i++" V번, "int i=0" 1번
    // 2. (C0+C1+C2+C3+C4)(V+1) -> K(V+1) -> KV+K
    // 3. 상수 제거, O(V)
    for (int i = 0; i < data.n; i++) {
      if (data.start == i) data.dist[i] = 0; // C0+C1+C2 [조건->C0, 배열에서 찾기->C1, 변수 할당->C2]
      else data.dist[i] = Integer.MAX_VALUE; // C3+C4 [배열에서 찾기->C3, 변수 할당->C4]
    }

    PriorityQueue<Node> pq = new PriorityQueue<>(); // C0
    pq.offer(new Node(data.start, 0)); // C1, C2
    // 1. 최악의 경우, 간선의 수만큼 반복 -> O(E)
    // 2. 간선의 수만큼 반복하여도, "cur.weight > data.dist[cur.idx]"를 통해 걸러진다.
    // 3. 실질적으로, 거의 노드의 수만큼만 반복하게 된다.
    // 4. 최종 시간 복잡도 -> O(VELogE)
    while (!pq.isEmpty()) {
      Node cur = pq.poll(); // C0
      if (cur.weight > data.dist[cur.idx]) continue;  // C1
      else if (data.graph[cur.idx] == null) continue; // C2
      // 1. 노드와 연결된 간선만큼 반복 -> O(E)
      // 2. (C3+C4+C5+C6+C7+C8+C9+C10+logE)+E -> (K+logE)E -> KE + ElogE
      // 3. 상수 제거, O(E+ElogE) -> 큰 값만 선택, O(ElogE)
      for (Node adj : data.graph[cur.idx]) {
        if (data.blocked[cur.idx][adj.idx]) continue; // C3
        int sum = cur.weight + adj.weight; // C4
        if (sum == data.dist[adj.idx]) { // C5
          // 경로 2개 이상 고려
          data.track[adj.idx].add(cur.idx); // C6
        } else if (sum < data.dist[adj.idx]) { // C7
          data.dist[adj.idx] = sum; // C8
          pq.offer(new Node(adj.idx, sum)); // 힙 자료구조 갱신 -> O(logE)
          // 다른 최단 경로를 찾을 경우, 갱신
          data.track[adj.idx].clear(); // C9
          data.track[adj.idx].add(cur.idx); // C10
        }
      }
    }
  }

  private static class Data {
    private int n;
    private int start;
    private int end;
    private List<Node>[] graph;
    private List<Integer>[] track;
    private int[] dist;
    private boolean[][] blocked;

    public boolean init(BufferedReader br) throws IOException {
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      if (n == 0 && m == 0) return true;

      st = new StringTokenizer(br.readLine());
      start = Integer.parseInt(st.nextToken());
      end = Integer.parseInt(st.nextToken());

      dist = new int[n];
      track = new ArrayList[n];
      for (int i = 0; i < n; i++) {
        track[i] = new ArrayList<>();
      }
      blocked = new boolean[n][n];
      graph = new ArrayList[n];
      // 간선 수만큼 반복 -> O(E)
      for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        // 노드 u
        int u = Integer.parseInt(st.nextToken());
        // 노드 u와 연결된 노드 v
        int v = Integer.parseInt(st.nextToken());
        // 도로의 길이
        int weight = Integer.parseInt(st.nextToken());
        if (graph[u] == null) graph[u] = new ArrayList<>();
        graph[u].add(new Node(v, weight));
      }
      return false;
    }
  }

  private static class Node implements Comparable<Node> {
    private final int idx;
    private final int weight;

    public Node(int idx, int weight) {
      this.idx = idx;
      this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
      return Integer.compare(this.weight, o.weight);
    }
  }
}
