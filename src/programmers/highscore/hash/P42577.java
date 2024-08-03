package programmers.highscore.hash;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class P42577 {

  public static void main(String[] args) {

  }

  class Solution {

    public boolean solution(String[] phoneBook) {
      int min = Integer.MAX_VALUE;
      for (String s : phoneBook) {
        int len = s.length();
        if (min > len) {
          min = len;
        }
      }

      Map<String, List<String>> map = new HashMap<>();
      for (String s : phoneBook) {
        String substring = s.substring(0, min);
        if (!map.containsKey(substring)) {
          map.put(substring, new ArrayList<>());
        }
        map.get(substring).add(s);
      }

      for (Entry<String, List<String>> entry : map.entrySet()) {
        List<String> list = entry.getValue();
        int size = list.size();
        if (size < 2) {
          continue;
        }
        list.sort((Comparator.comparingInt(String::length)));
        for (int i = 0; i < size - 1; i++) {
          String a = list.get(i);
          for (int j = i + 1; j < size; j++) {
            String b = list.get(j);
            if (b.startsWith(a)) {
              return false;
            }
          }
        }
      }
      return true;
    }
  }
}
