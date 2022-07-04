package baekjoon.탐욕;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

// * 2, 11
// * -39, -37, -29, -28, -6
// 22 + 39 + 58 + 12 -> 151
public class P1461_2 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String[] input = scanner.nextLine().split(" ");
    String[] locations = scanner.nextLine().split(" ");
    scanner.close();

    // 내림차순
    PriorityQueue<Integer> positive = new PriorityQueue<>((pre, cur) -> Integer.compare(cur, pre));
    PriorityQueue<Integer> negative = new PriorityQueue<>((pre, cur) -> Integer.compare(cur, pre));
    // 1. 힙 추가 시간복잡도 -> O(logN)
    // 2. 2개 -> O(2logN)
    Arrays.stream(locations).mapToInt(Integer::parseInt).forEach(n -> {
      if (n >= 0) positive.add(n);
      else negative.add(-n);
    });

    int M = Integer.parseInt(input[1]);
    int direct = 1;
    int answer = 0;
    // 1. N 개의 책이 존재[<=50]
    // 2. M 이 1일 경우, 책은 한 개씩만 들고갈 수 있음 -> 최악 -> N 번만큼 반복문이 무조건 돈다.
    // 3. 내부적으로 `pq.pool()` 메소드가 실행되므로, 힙 삭제시 갱신 시간복잡도 -> O(logN)
    // 4. N 번 반복문이 돌고, 반복문이 돌 때마다, 힙 갱신 -> O(NlogN)
    while (!positive.isEmpty() || !negative.isEmpty()) {
      int pool = pool(peek(positive) > peek(negative) ? positive : negative, M);
      answer += pool * direct;
      direct = 2;
    }
    // 1. 총 시간 복잡도 -> O(NlogN + 2logN), N의 최대 수는 50
    // 2. O(50log50 + 2log50) -> 293
    System.out.println(answer);
  }

  private static int peek(PriorityQueue<Integer> pq) {
    return !pq.isEmpty() ? pq.peek() : 0;
  }

  private static int pool(PriorityQueue<Integer> pq, int M) {
    int pool = 0;
    for (int i = 0; !pq.isEmpty() && i < M; i++) {
      if (i == 0) pool = pq.poll();
      else pq.poll();
    }
    return pool;
  }
}
