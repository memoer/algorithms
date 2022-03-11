package temp.Search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1766 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int M = Integer.parseInt(st.nextToken());
    List<Integer>[] order = new ArrayList[N + 1];
    int[] inDegree = new int[N + 1];
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (order[a] == null) {
        order[a] = new ArrayList<>();
      }
      order[a].add(b);
      inDegree[b] += 1;
    }
    br.close();

    for (int i = 1; i < N + 1; i++) {
      if (inDegree[i] == 0) {
        pq.offer(i);
      }
    }
    while (!pq.isEmpty()) {
      int target = pq.poll();
      bw.write(target + " ");
      if (order[target] == null) {
        continue;
      }
      for (int next : order[target]) {
        inDegree[next] -= 1;
        if (inDegree[next] == 0) {
          pq.offer(next);
        }
      }
    }
    bw.write("\n");

    bw.flush();
    bw.close();
  }
}
