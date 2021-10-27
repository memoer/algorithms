package programmers.search.complete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class S42839 {
  static class Solution {
    private Set<Integer> primeSet = new HashSet<>();

    private boolean isPrime(int number) {
      if (number < 2) {
        return false;
      }
      for (int i = 2; i < number; i++) {
        if (number % i == 0) {
          return false;
        }
      }
      return true;
    }

    private boolean isDuplicated(List<Integer> numArr) {
      for (int i = 0; i < numArr.size() - 1; i++) {
        for (int j = i + 1; j < numArr.size(); j++) {
          if (numArr.get(i) == numArr.get(j)) {
            return true;
          }
        }
      }
      return false;
    }

    public void getPrime(String[] s, List<Integer> idxList, int count) {
      if (count == 0) {
        return;
      }
      int sLength = s.length;
      for (int i = 0; i < s.length; i++) {
        idxList.add(i);
        getPrime(s, idxList, count - 1);
        idxList.remove(idxList.size() - 1);
        if (sLength == count) {
          int num = Integer.parseInt(s[i]);
          if (num > 1 && isPrime(num)) {
            primeSet.add(num);
          }
        } else {
          idxList.add(i);
          if (!isDuplicated(idxList)) {
            String numStr = "";
            for (int idx : idxList) {
              numStr += s[idx];
            }
            int numB = Integer.parseInt(numStr);
            if (isPrime(numB)) {
              primeSet.add(numB);
            }
          }
          idxList.remove(idxList.size() - 1);
        }
      }
    }

    public int solution(String numbers) {
      String[] s = numbers.split("");
      getPrime(s, new ArrayList<>(), s.length);
      return primeSet.size();
    }
  }

  public static void main(String[] args) {
    String numbers = "011";
    System.out.println(new Solution().solution(numbers));
  }
}

// switch (numbers.length()) {
// case 1: {
// int num = Integer.parseInt(s[0]);
// if (isPrime(num)) {
// primeSet.add(num);
// }
// }
// break;
// case 2: {
// for (int i = 0; i < s.length; i++) {
// int numA = Integer.parseInt(s[i]);
// if (numA > 1 && isPrime(numA)) {
// primeSet.add(numA);
// }
// for (int j = 0; j < s.length; j++) {
// if (!isDuplicated(new int[] { i, j })) {
// int numB = Integer.parseInt("" + s[i] + s[j]);
// if (isPrime(numB)) {
// primeSet.add(numB);
// }
// }
// }
// }
// break;
// }
// case 3: {
// for (int i = 0; i < s.length; i++) {
// int numA = Integer.parseInt(s[i]);
// if (numA > 1 && isPrime(numA)) {
// primeSet.add(numA);
// }
// for (int j = 0; j < s.length; j++) {
// if (!isDuplicated(new int[] { i, j })) {
// int numB = Integer.parseInt("" + s[i] + s[j]);
// if (isPrime(numB)) {
// primeSet.add(numB);
// }
// }
// for (int k = 0; k < s.length; k++) {
// if (!isDuplicated(new int[] { i, j, k })) {
// int numC = Integer.parseInt("" + s[i] + s[j] + s[k]);
// if (isPrime(numC)) {
// primeSet.add(numC);
// }
// }
// }
// }
// }
// }
// break;
// case 4:
// case 5:
// case 6:
// case 7:
// default:
// break;
// }