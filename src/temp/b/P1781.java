package temp.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1781 {

  public static void main(String[] args) throws IOException {
    int result = 0;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int N = Integer.parseInt(br.readLine());
    Problem[] pArr = new Problem[N];
    PriorityQueue<Problem> pq = new PriorityQueue<>();
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      pArr[i] = new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
    br.close();

    Arrays.sort(pArr, (pre, cur) -> pre.deadLine - cur.deadLine);
    for (Problem p : pArr) {
      if (pq.isEmpty() || p.deadLine > pq.size()) {
        pq.offer(p);
      } else if (p.deadLine == pq.size() && p.noodle > pq.peek().noodle) {
        pq.poll();
        pq.offer(p);
      }
    }
    for (Problem p : pq) {
      result += p.noodle;
    }
    System.out.println(result);
  }

  private static class Problem implements Comparable<Problem> {
    public int deadLine;
    public int noodle;

    public Problem(int deadLine, int noodle) {
      this.deadLine = deadLine;
      this.noodle = noodle;
    }

    @Override
    public String toString() {
      return this.deadLine + ", " + this.noodle;
    }

    @Override
    public int compareTo(Problem o) {
      return this.noodle - o.noodle;
    }
  }
}
