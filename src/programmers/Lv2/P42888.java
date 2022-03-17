package programmers.Lv2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P42888 {
  static class Solution {
    public String[] solution(String[] record) {
      List<String> result = new ArrayList<>();
      Map<String, String> map = new HashMap<>();
      String[][] splittedArr = new String[record.length][3];
      for (int i = 0; i < record.length; i++) {
        splittedArr[i] = record[i].split("\\s+");
        if (!splittedArr[i][0].equals("Leave")) {
          map.put(splittedArr[i][1], splittedArr[i][2]);
        }
      }
      for (String[] splitted : splittedArr) {
        if (splitted[0].equals("Change")) {
          continue;
        }
        result.add(map.get(splitted[1]) + (splitted[0].equals("Enter") ? "님이 들어왔습니다." : "님이 나갔습니다."));
      }
      return result.toArray(new String[0]);
    }
  }

  public static void main(String[] args) {
    String[] record = {
        "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"
    };
    for (String s : new Solution().solution(record)) {
      System.out.println(s);
    }
  }
}
