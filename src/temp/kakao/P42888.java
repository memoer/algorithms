package temp.kakao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P42888 {
  public static void main(String[] args) {
    String[] record = new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 " +
            "Prodo", "Change uid4567 Ryan"};
    for (String s : new P42888().solution(record)) {
      System.out.println(s);
    }
  }

  public String[] solution(String[] record) {
    List<String[]> list = new ArrayList<>();
    Map<String, String> map = new HashMap<>();

    for (String s : record) {
      String[] split = s.split(" ");
      String type = split[0];
      String id = split[1];
      switch (type) {
        case "Enter":
          list.add(new String[]{id, "님이 들어왔습니다."});
        case "Change":
          map.put(id, split[2]);
          break;
        case "Leave":
          list.add(new String[]{id, "님이 나갔습니다."});
          break;
      }
    }
    return list.stream().map(v -> map.get(v[0]) + v[1]).toArray(String[]::new);
  }
}
