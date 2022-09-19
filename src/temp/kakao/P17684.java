package temp.kakao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P17684 {
  List<Integer> ans;
  Map<String, Integer> map;
  String msg;
  int len;
  int startIdx;
  int num;

  public static void main(String[] args) {
    String msg = "KAKAO";
    for(int n : new P17684().solution(msg)) System.out.print(n+", ");
    System.out.println();
  }

  public int[] solution(String msg) {
    init(msg);

    while (this.startIdx < this.len) {
      String w = getW();
      int wLen = w.length();
      this.ans.add(this.map.get(w));
      addNextWord(wLen);
      this.startIdx += wLen;
    }

    return this.ans.stream().mapToInt(v -> v).toArray();
  }

  private String getW() {
    String w = null;
    StringBuilder sb = new StringBuilder();
    for (int i = startIdx; i < len; i++) {
      sb.append(msg.charAt(i));
      String target = sb.toString();
      if (map.containsKey(target)) w = target;
      else break;
    }
    return w;
  }

  private void addNextWord(int wLen) {
    int endIdx = this.startIdx + wLen + 1;
    if (endIdx > this.len) return;
    String c = msg.substring(this.startIdx, endIdx);
    this.map.put(c, this.num++);
  }

  private void init(String msg) {
    this.ans = new ArrayList<>();
    this.msg = msg;
    this.len = msg.length();
    this.startIdx = 0;
    this.num = 1;
    this.map = new HashMap<>();
    for (int i = 0; i < 26; i++) {
      char ch = (char) ('A' + i);
      map.put(String.valueOf(ch), this.num++);
    }
  }
}
