package programmers.Lv2;

public class P60057_3 {

    public static void main(String[] args) {
        String s = "aabbaccc";
        int solution = new Solution().solution(s);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(String s) {
            int len = s.length();
            int answer = len;
            int limit = len / 2;

            for (int size = 1; size <= limit; size++) {
                int temp = 0;
                int count = 1;
                int idx;
                String target = s.substring(0, size);
                for (idx = size; idx + size <= len; idx += size) {
                    String str = s.substring(idx, idx + size);
                    if (target.equals(str)) {
                        count += 1;
                    } else {
                        if (count == 1) {
                            temp += str.length();
                        } else {
                            temp += (int) Math.log10(count) + 1 + target.length();
                        }
                        target = str;
                        count = 1;
                    }
                }
                temp += target.length() + (len - idx) + (count == 1 ? 0 : (int) Math.log10(count) + 1);
                answer = Math.min(answer, temp);
            }

            return answer;
        }

    }
}
