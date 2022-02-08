package baekjoon.core;

import java.util.Scanner;

// ? 이분탐색
// 1. M 이 주어짐 -> M 이하의 구간으로 나눌 수 있음 -> 3이라면 배열은 3개이하의 구간으로 나눠질 수 있음 [ 각 배열을 내부적으로 3배열, 2배열, 1배열으로 나누는 것 ]
// 2. 각 나눠진 구간 안에서 <최댓값 - 최소값>이 구간의 점수 -> 여러 개가 나옴 -> 3구간으로 나눌 경우 -> 구간의 점수가 3개가 나온다
// 3. 구간의 점수들 중, 최댓값을 하나 고른다.
// 4. 여러 구간의 점수들 최댓값 중에서 최솟값을 구한다.
public class _P13397 {
  private static boolean canDivide(int mid, int[] arr, int m) {
    int count = 1;
    int max = arr[0];
    int min = arr[0];
    for (int i = 1; i < arr.length; i++) {
      max = Math.max(arr[i], max);
      min = Math.min(arr[i], min);
      if (max - min > mid) {
        count++;
        max = arr[i];
        min = arr[i];
        if (count > m) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int MIN = Integer.MAX_VALUE;
    int MAX = Integer.MIN_VALUE;
    int answer = Integer.MAX_VALUE;
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
      MIN = Math.min(arr[i], MIN);
      MAX = Math.max(arr[i], MAX);
    }
    int left = 0;
    int right = MAX - MIN;
    int mid = 0;
    while (left <= right) {
      mid = (left + right) / 2;
      if (canDivide(mid, arr, m)) {
        answer = Math.min(answer, mid);
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    System.out.println(answer);
    sc.close();
  }
}
