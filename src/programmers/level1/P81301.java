package programmers.level1;

public class P81301 {
  static class Solution {
    public int solution(String s) {
      Template[] tArr = { new Template("zero", 0), new Template("one", 1), new Template("two", 2),
          new Template("three", 3), new Template("four", 4),
          new Template("five", 5), new Template("six", 6), new Template("seven", 7), new Template("eight", 8),
          new Template("nine", 9) };
      for (Template t : tArr) {
        s = s.replaceAll(t.s, "" + t.n);
      }
      return Integer.parseInt(s);
    }

    private class Template {
      public String s;
      public int n;

      public Template(String s, int n) {
        this.s = s;
        this.n = n;
      }
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().solution("one4seveneight"));
  }
}
