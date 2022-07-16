package programmers.Lv2;

import java.util.HashMap;
import java.util.Map;

public class P49993 {

  public static void main(String[] args) {
    String skill = "CBD";
    String[] trees = {"BACDE", "CBADF", "AECB", "BDA"};
    //    System.out.println(new Solution().solution(skill, trees));

    String s = "CBADF".replaceAll("[^CBD]", "");
    int i = "CBD".indexOf(s);
    if (i != 0) {
      // remove
    }
  }

  private static class Solution {

    /*
     * a-> 1 <= skill.length <= 26
     * b-> 1 <= trees.length <= 20
     * c-> 2 <= trees[i].length <= 26 [중복X]
     */
    public int solution(String skill, String[] trees) {
      int answer = 0;
      Map<Character, Integer> skillMap = getSkillMap(skill);

      // O(b) -> 최대 20
      for (String tree : trees) {
        // O(c) -> 최대 26
        if (!isAvailable(tree, skillMap)) continue;
        answer += 1;
      }
      return answer;
    }

    private Map<Character, Integer> getSkillMap(String skill) {
      Map<Character, Integer> map = new HashMap<>();
      int length = skill.length();
      // O(a) -> 최대 26
      for (int order = 0; order < length; order++) {
        map.put(skill.charAt(order), order);
      }
      return map;
    }

    private boolean isAvailable(String tree, Map<Character, Integer> skillMap) {
      int cur = 0;
      char[] skillArr = tree.toCharArray();
      // O(c) -> 최대 26
      for (char s : skillArr) {
        // O(1)
        if (!skillMap.containsKey(s)) continue;
        // O(1)
        else if (skillMap.get(s) != cur) return false;
        cur += 1;
      }
      return true;
    }
  }
}
