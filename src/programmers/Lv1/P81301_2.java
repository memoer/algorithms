package programmers.Lv1;

public class P81301_2 {

    public static void main(String[] args) {
        String s = "one4seveneight";
        int solution = new Solution().solution(s);
        System.out.println(solution);
    }

    private static class Solution {
        public int solution(String s) {
            StringBuilder sb = new StringBuilder();

            char[] charArray = s.toCharArray();
            int len = s.length();
            for (int i = 0; i < len; i++) {
                char ch = charArray[i];
                if (Character.isDigit(ch)) {
                    sb.append(ch);
                    continue;
                }

                int j = i;
                StringBuilder temp = new StringBuilder();
                for (; j < len; j++) {
                    temp.append(charArray[j]);
                    int n = convert(temp.toString());
                    if (n == -1) {
                        continue;
                    }
                    sb.append(n);
                    break;
                }
                i = j;
            }

            return Integer.parseInt(sb.toString());
        }

        private int convert(String s) {
            return switch (s) {
                case "zero" -> 0;
                case "one" -> 1;
                case "two" -> 2;
                case "three" -> 3;
                case "four" -> 4;
                case "five" -> 5;
                case "six" -> 6;
                case "seven" -> 7;
                case "eight" -> 8;
                case "nine" -> 9;
                default -> -1;
            };
        }
    }
}
