package baekjoon.탐색기본;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1939_2 {
  /*
  시간복잡도 측정
   1. 간선의 수 최대 -> 100,000 -> 총 100,000 돌 수 있음
   2. 프림 [ElogE] -> 간선의 수 최대 100,000 -> 즉, (100,000)log(100,000)이 된다. -> 시간복잡도 계산시, "1,660,964"
   3. 최종 시간복잡도 계산 -> 1,660,964 + 100,000 -> 1,760,964-> 1초 충분
   */
  /*
  알고리즘 생각
   - 최소신장트리가 아닌, 최대신장트리로 구한 후, 간선의 가중치가 가장 작은 걸 반환하면 된다.
   */
  /*
  - N -> 노드 수
  - M -> 간선 수
   */
  public static void main(String[] args) throws IOException {
    int answer = Integer.MAX_VALUE;
    Data data = new Data();
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    boolean[] visited = new boolean[data.N + 1];

    for (Edge edge : data.LIST[data.START]) {
      pq.offer(edge);
    }
    for (int i = 0; i < data.N; i++) {
      if (i != data.START) {
        for (Edge edge : data.LIST[i]) {
          if (edge.NEXT == data.START) {
            pq.offer(edge);
          }
        }
      }
    }
    visited[data.START] = true;
    while (!pq.isEmpty()) {
      Edge cur = pq.poll();
      if (visited[cur.NEXT]) {
        continue;
      }
      answer = Integer.min(answer, cur.WEIGHT);
      if (cur.NEXT == data.END) {
        break;
      }
      visited[cur.NEXT] = true;
      for (Edge edge : data.LIST[cur.NEXT]) {
        if (!visited[edge.NEXT]) {
          pq.offer(edge);
        }
      }
    }

    System.out.println(answer);
  }

  private static class Data {
    private final int N;
    private final int M;
    private final int START;
    private final int END;
    /*
    1. 순수 List<Edge> LIST로만 진행했을 때는, 시간 초과가 나왔음
    2. while 문 내부의 for 반복문에서, 한 개의 간선에 대한 다음 간선에 인접한 노드들만 돌리면 되는데, 모든 간선을 다 돌아가며 불필요한 반복문이 생겨서 시간초과가 나온 듯 하다.
        - Ex) Edge(1,2,10) 일 경우, 1과 인접한 2의 노드에 대한 인접한 간선들만 돌면 되는데, 2의 노드가 아닌 다른 노드들까지 모두 반복문을 도는 상황
        - Ex) 1-2 연결되어 있고 "2-3, 2-4, 2-5" 연결될 경우, while 문에서 "1-2" Edge를 검사하고 for문을 돌릴 때, "2-3, 2-4, 2-5"만 돌려야 하는데,
        "3-2, 3-4, 3-5, ..." 인 다른 간선들 까지 탐색을 하고 있는 상황
    3. LIST를 배열로 만들고, 간선 시작 idx가 1이면 배열의 1 idx에 넣어주는 방식으로 진행했다.
    4. 이렇게 하면, 한 개의 간선에 대해서 다음 노드 idx를 특정지어 넘겨주고, 넘겨준 다음 노드의 인접한 간선들만 탐색할 수 있게 된다.
        - 마찬가지로, 1-2 연결되어 있고, "2-3, 2-4, 2-5" 연결되어있을 경우, LIST[2] 를 넘겨주어 2에 인접한 노드들만 탐색한다.
     */

    private final List<Edge>[] LIST;

    public Data() throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      // input-1
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      LIST = new List[N + 1];
      for (int i = 0; i < N + 1; i++) {
        LIST[i] = new ArrayList<>();
      }
      // input-2
      for (int i = 0; i < M; i++) {
        // 최대 -> 100,000
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());
        LIST[a].add(new Edge(b, weight));
        LIST[b].add(new Edge(a, weight));
      }
      // input-3
      st = new StringTokenizer(br.readLine());
      START = Integer.parseInt(st.nextToken());
      END = Integer.parseInt(st.nextToken());
      br.close();
    }
  }

  private static class Edge implements Comparable<Edge> {
    private final int NEXT;
    private final int WEIGHT;

    public Edge(int NEXT, int WEIGHT) {
      this.NEXT = NEXT;
      this.WEIGHT = WEIGHT;
    }


    @Override
    public int compareTo(Edge o) {
      return Integer.compare(o.WEIGHT, this.WEIGHT);
    }
  }
}
