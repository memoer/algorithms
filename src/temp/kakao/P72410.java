package temp.kakao;

public class P72410 {
  public static void main(String[] args) {
    String new_id = "...!@BaT#*..y.abcdefghijklm";
    System.out.println(new P72410().solution(new_id));
  }

  public String solution(String new_id) {
    StringBuilder ans = new StringBuilder(
            new_id.toLowerCase()
                    .replaceAll("[^\\w\\-_.]", "")
                    .replaceAll("\\.{2,}", ".")
                    .replaceAll("^[.]|[.]$", "")
    );
    if (ans.length() == 0) ans.append("a");
    if (ans.length() >= 16) ans = new StringBuilder(ans.substring(0, 15).replaceAll("[.]$", ""));
    if (ans.length() <= 2) while (ans.length() != 3) ans.append(ans.charAt(ans.length() - 1));
    return ans.toString();
  }
}
