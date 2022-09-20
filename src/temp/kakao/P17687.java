package temp.kakao;

public class P17687 {
  public static void main(String[] args) {
    int n = 16, t = 16, m = 2, p = 1;
    System.out.println(new P17687().solution(n, t, m, p));
  }

  // n->진법, t->미리 구할 숫자의 개수[return 길이], m->게임에 참가하는 인원, p->튜브의 순
  public String solution(int n, int t, int m, int p) {
    StringBuilder ans = new StringBuilder();
    int curNum = 0;
    int curOrder = 1;

    while (true) {
      char[] chars = Integer.toString(curNum, n).toUpperCase().toCharArray();
      for (char ch : chars) {
        if (curOrder == p) ans.append(ch);
        if (ans.length() == t) return ans.toString();
        curOrder = nextOrder(curOrder, m);
      }
      curNum += 1;
    }
  }

  private int nextOrder(int curOrder, int m) {
    int nextOrder = curOrder + 1;
    return nextOrder <= m ? nextOrder : 1;
  }
}
