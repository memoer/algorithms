package programmers.level1;

import java.util.ArrayList;
import java.util.List;

public class P17682 {
  static class Solution {
    private final char NUMBER = 'N';
    private final char BONUS = 'B';
    private final char AWARD = 'A';
    private List<String> stageList;
    private int[] scoreArr;

    private List<String> getStageList(String dartResult) {
      List<String> sList = new ArrayList<>();
      int resultLength = dartResult.length();
      int idx = 0;
      for (int i = 0; i < resultLength; i++) {
        char c = dartResult.charAt(i);
        if (getType(c) != NUMBER || (c == '0' && idx == i - 1)) {
          continue;
        }
        sList.add(dartResult.substring(idx, i));
        idx = i;
      }
      sList.add(dartResult.substring(idx, resultLength));
      return sList;
    }

    private char getType(char c) {
      if (c >= 48 && c <= 57) {
        return NUMBER;
      } else if (c == 'S' || c == 'D' || c == 'T') {
        return BONUS;
      } else if (c == '*' || c == '#') {
        return AWARD;
      }
      return '\0';
    }

    private int getNumber(String s) {
      char c = s.charAt(0);
      if (c == 49 && s.charAt(1) == 48) {
        return 10;
      }
      return c - '0';
    }

    private int getBonus(char c) {
      switch (c) {
        case 'S':
          return 1;
        case 'D':
          return 2;
        case 'T':
          return 3;
        default:
          throw new UnsupportedOperationException();
      }
    }

    private int getAward(char c, int i) {
      if (c == '*') {
        if (i != 0) {
          scoreArr[i - 1] *= 2;
        }
        return 2;
      }
      return -1;
    }

    private int getResult() {
      int result = 0;
      for (int i = 0; i < scoreArr.length; i++) {
        result += scoreArr[i];
      }
      return result;
    }

    public int solution(String dartResult) {
      stageList = getStageList(dartResult);
      int stageLength = stageList.size();
      scoreArr = new int[stageLength];

      for (int i = 0; i < stageLength; i++) {
        String stage = stageList.get(i);
        int score = 0;
        for (int j = 0; j < stage.length(); j++) {
          char c = stage.charAt(j);
          char type = getType(c);
          if (type == NUMBER) {
            score = getNumber(stage.substring(0, 2));
            if (score == 10) {
              j += 1;
            }
          } else if (type == BONUS) {
            score = (int) Math.pow(score, getBonus(c));
          } else if (type == AWARD) {
            score *= getAward(c, i);
          }
        }
        scoreArr[i] = score;
      }
      return getResult();
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution("1D2S#10S"));
  }
}
