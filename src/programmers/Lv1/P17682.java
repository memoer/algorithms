package programmers.Lv1;

import java.util.ArrayList;
import java.util.List;

/**
 * 3번의 기회 각 기회마다 0~10점 S영역 -> 1제곱, D영역 -> 2제곱, T영역-> 3제곱 계산
 * <p>
 * *상 -> 해당/바로 전 점수 모두 2배 *상 -> 바로 전 점수 없을 경우, 해당 점수만 2배 *상 -> 중첩 가능, 이 경우 점수는 4배가 됨 #상 -> 해당 점수 마이너스 *상, #상 -> 중첩 가능. 이
 * 경우, #상 점수는 -2배
 * <p>
 * S, D, T은 점수마다 하나씩 존재 *상, #상 -> 점수마다 하나만 존재하거나 없음
 * <p>
 * 인풋 값의 포맷은 "점수|영역|상"
 */
public class P17682 {

    static class Solution {
        private int score = -1;
        private int square = -1;
        private char award = '\n';
        private int idx = 0;

        public int solution(String dartResult) {
            int len = dartResult.length();
            List<Integer> list = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                char ch = dartResult.charAt(i);
                if (Character.isDigit(ch)) {
                    sb.append(ch);
                    if (i != 0 && score != -1) {
                        calculate(list);
                        addIdx();
                    }
                } else {
                    if (score == -1) {
                        score = Integer.parseInt(sb.toString());
                        sb.setLength(0);
                    }
                    if (ch == '*' || ch == '#') {
                        award = ch;
                    } else if (square == -1) {
                        square = getSquare(ch);
                    }
                }
            }
            calculate(list);

            return list.stream().reduce(0, Integer::sum);
        }

        private void calculate(List<Integer> list) {
            int n = (int) Math.pow(score, square);
            if (award == '*') {
                list.add(n * 2);
                if (idx - 1 >= 0) {
                    list.set(idx - 1, list.get(idx - 1) * 2);
                }
            } else if (award == '#') {
                list.add(-n);
            } else {
                list.add(n);
            }
        }

        private void addIdx() {
            score = -1;
            square = -1;
            award = '\n';
            idx += 1;
        }

        private int getSquare(char ch) {
            return switch (ch) {
                case 'S' -> 1;
                case 'D' -> 2;
                case 'T' -> 3;
                default -> throw new IllegalStateException("Unexpected value: " + ch);
            };
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution("1D2S#10S"));
    }
}
