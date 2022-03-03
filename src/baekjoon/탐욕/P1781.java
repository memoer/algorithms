package baekjoon.탐욕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 3
 * 1 1
 * 2 50
 * 2 100
 * -> 150이 나와야 한다.
 */
public class P1781 {
  private static class Problem implements Comparable<Problem> {
    public int deadline;
    public int noodle;

    public Problem(int deadline, int noodle) {
      this.deadline = deadline;
      this.noodle = noodle;
    }

    @Override
    public int compareTo(Problem o) {
      return Integer.compare(this.deadline, o.deadline);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int N = Integer.parseInt(br.readLine());
    int result = 0;
    List<Problem> pList = new ArrayList<>();
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      pList.add(new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    }
    br.close();

    Collections.sort(pList);
    for (int i = 0; i < N; i++) {
      Problem p = pList.get(i);
      pq.offer(p.noodle);
      if (p.deadline < pq.size()) {
        pq.poll();
      }
    }
    while (!pq.isEmpty()) {
      result += pq.poll();
    }
    System.out.println(result);
  }
}
