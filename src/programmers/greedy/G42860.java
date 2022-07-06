package programmers.greedy;

import java.util.Arrays;

public class G42860 {
  static class Solution {

    public int solution(String name) {
      int A_ASCII_CODE = 65;
      int MIDDLE_WEIGHT = 13;
      int[] cursorWeightList = new int[name.length()];
      int answer = 0;
      int cursor = 0;
      int moveWeight = 0;
      boolean[] checked = new boolean[name.length()];
      int completedNum = 0;
      Arrays.fill(checked, false);
      for (int i = 0; i < cursorWeightList.length; i++) {
        int weight = (int) name.charAt(i) - A_ASCII_CODE;
        if (weight == 0) {
          checked[i] = true;
          completedNum++;
        }
        if (weight > MIDDLE_WEIGHT) {
          int diffWeight = (weight - MIDDLE_WEIGHT) * 2;
          cursorWeightList[i] = weight - diffWeight;
        } else {
          cursorWeightList[i] = weight;
        }
      }
      while (completedNum != name.length()) {
        for (boolean b : checked) {
          System.out.print(b + ", ");
        }
        System.out.println(cursor);
        int lMoveWeight = 0;
        int rMoveWeight = 0;
        if (name.charAt(cursor) != 'A') {
          answer += cursorWeightList[cursor] + moveWeight;
          checked[cursor] = true;
          completedNum += 1;
        }
        for (int i = 1; i <= name.length(); i++) {
          int leftIdx = cursor - i < 0 ? name.length() + cursor - i : cursor - i;
          int rightIdx = cursor + i >= name.length() ? cursor + i - name.length() : cursor + i;
          if (leftIdx == rightIdx) {
            return answer;
          }
          lMoveWeight++;
          rMoveWeight++;
          if (name.charAt(leftIdx) != 'A' && name.charAt(rightIdx) != 'A') {
            if (checked[leftIdx] && checked[rightIdx]) {
              continue;
            } else if (!checked[leftIdx]) {
              moveWeight = lMoveWeight;
              cursor = leftIdx;
            } else if (!checked[rightIdx]) {
              moveWeight = rMoveWeight;
              cursor = rightIdx;
            } else {
              int totalLeftWeight = lMoveWeight + cursorWeightList[leftIdx];
              int totalRightWeight = rMoveWeight + cursorWeightList[rightIdx];
              moveWeight = totalLeftWeight < totalRightWeight ? lMoveWeight : rMoveWeight;
              cursor = totalLeftWeight < totalRightWeight ? leftIdx : rightIdx;
            }
            break;
          } else if (name.charAt(leftIdx) != 'A') {
            if (checked[leftIdx]) {
              continue;
            }
            moveWeight = lMoveWeight;
            cursor = leftIdx;
            break;
          } else if (name.charAt(rightIdx) != 'A') {
            if (checked[rightIdx]) {
              continue;
            }
            moveWeight = rMoveWeight;
            cursor = rightIdx;
            break;
          }
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    // 65 ~ 90
    String name = "ABAAAAAAAAABB";
    // String name = "ABABAAAAAAABA";
    System.out.println(new Solution().solution(name));
  }
}
