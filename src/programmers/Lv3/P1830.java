package programmers.Lv3;

import java.util.ArrayList;
import java.util.List;

public class P1830 {
  public static void main(String[] args) {
    String[] sentences = {"HaEaLaLaObWORLDb", "SpIpGpOpNpGJqOqA", "AxAxAxAoBoBoB"};
    for (String sentence : sentences) {
      System.out.println(new Solution().solution(sentence));
    }
  }

  private static class Solution {
    /*
    - 글자에 특정 단어를 넣어 만든 광고 글의 규칙
      1. 특정 단어 'X' 를 선택하여 글자 사이마다 같은 기호를 넣는다.
      2. 특정 단어 'Y' 를 선택하여 단어 앞뒤에 같은 기호를 넣는다.
      3. 위의 두 가지 규칙은 "한 단어"에 모두 적용될 수 있지만, "한 단어"어에 같은 규칙을 두 번 적용할 순 없다.
      4. 한 번 쓰인 특정 단어는 다시 쓰일 수 없다.
    - 위의 규칙으로 만들어진 광고 글에서, "공백"을 제거한다.
     */
    /*
     * sentence -> 영문 대소문자로만 이루어져있다. [대문자->원글자, 소문자->특정 단어]
     * sentence.length <= 1,000
     */
    /*
    * sentence의 규칙 적용 전 원래 문구를 리턴할 것.
       - 가능한 답이 여러 가지인 경우, 그중 하나를 리턴할 것.
       - 리턴하는 원래 문구는 (A)문장 앞뒤의 공백이 없어야 하며, (B)단어 사이의 공백은 한 글자이여야 한다.
     * sentence가 규칙에 따라 변환을 할 수 없는 문자열이라면, "invalid"를 리턴할 것.
     */
    private char[] chars;
    private int length;

    public String solution(String sentence) {
      this.chars = sentence.toCharArray();
      length = chars.length;
      int last = 0;
      List<String> list = new ArrayList<>();
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < length; i++) {
        if (satisfySplitCondition(i)) {
          addWord(list, sb);
          last = i;
        }
        sb.append(chars[i]);
      }
      list.add(sentence.substring(last, length));

      StringBuilder answer = new StringBuilder();
      for (String word : list) {
        if (Character.isLowerCase(word.charAt(0))) {
          int l = word.length() - 1;
          if (word.charAt(0) != word.charAt(l)) return "invalid";
          word = word.substring(1, l);
        }

        sb = new StringBuilder();
        char[] sChars = word.toCharArray();
        char pre = '\0';
        for (char ch : sChars) {
          if (Character.isLowerCase(ch)) {
            if (pre != '\0' && pre != ch) return "invalid";
            pre = ch;
          }
          if (Character.isUpperCase(ch)) sb.append(ch);
        }
        sb.append(" ");
        answer.append(sb.toString());
      }
      answer.deleteCharAt(answer.length() - 1);
      return answer.toString();
    }

    private void addWord(List<String> list, StringBuilder sb) {
      list.add(sb.toString());
      sb.delete(0, sb.length());
    }

    private boolean satisfySplitCondition(int idx) {
      if (idx - 2 < 0 || idx + 1 == length) return false;
      boolean onePostIsUpper = Character.isUpperCase(chars[idx + 1]);
      boolean curIsUpper = Character.isUpperCase(chars[idx]);
      boolean onePreIsUpper = Character.isUpperCase(chars[idx - 1]);
      boolean twoPreIsUpper = Character.isUpperCase(chars[idx - 2]);
      if (curIsUpper && onePreIsUpper && !twoPreIsUpper && !onePostIsUpper) return true;
      if (!curIsUpper && !onePreIsUpper && twoPreIsUpper && chars[idx] != chars[idx - 1]) return true;
      if (!curIsUpper && onePreIsUpper && !twoPreIsUpper && chars[idx] != chars[idx - 2]) return true;
      return false;
    }
  }
}
