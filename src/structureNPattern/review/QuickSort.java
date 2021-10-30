package structureNPattern.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import structureNPattern.util.RandomList;

// 대표적인 분할 정복
// Memoization 사용 안 함
// 하향식 접근법 -> 큰 문제를 해결하기 위해, 작은 문제로 간다.
public class QuickSort {
  static class Solution {
    static final int LENGTH = 10;

    private void addNumToLeftNRight(List<Integer> list, int pivot, List<Integer> left, List<Integer> right) {
      for (int num : list) {
        if (num == -1) {
          continue;
        }
        if (num < pivot) {
          left.add(num);
        } else {
          right.add(num);
        }
      }
    }

    private List<Integer> sort(List<Integer> list) {
      if (list.size() <= 1) {
        return list;
      }
      int mid = list.size() / 2;
      int pivot = list.get(mid);
      list.set(mid, -1);
      List<Integer> left = new ArrayList<>();
      List<Integer> right = new ArrayList<>();
      addNumToLeftNRight(list, pivot, left, right);
      List<Integer> sortedLeft = sort(left);
      sortedLeft.add(pivot);
      List<Integer> sortedRight = sort(right);
      return Stream.concat(sortedLeft.stream(), sortedRight.stream()).collect(Collectors.toList());
    }

    public List<Integer> solution() {
      List<Integer> list = Arrays.stream(RandomList.get(LENGTH)).boxed().collect(Collectors.toList());
      print(list);
      return sort(list);
    }
  }

  public static void print(List<Integer> list) {
    for (int num : list) {
      System.out.print(num + ", ");
    }
    System.out.println();
  }

  public static void main(String[] args) throws Exception {
    List<Integer> sorted = new Solution().solution();
    print(sorted);
  }
}

// 7,3,1,5,9
// 1, 7,3,5,9
// 1, 3,5,7,9