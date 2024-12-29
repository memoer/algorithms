package programmers.Lv1;

public class P340213 {

    public static void main(String[] args) {
        String video_len = "34:33";
        String pos = "13:00";
        String op_start = "00:55";
        String op_end = "02:55";
        String[] commands = {"next", "prev"};
        String solution = new Solution().solution(video_len, pos, op_start, op_end, commands);
        System.out.println(solution);
    }

    private static class Solution {

        /**
         * 10초 전으로 이동, 10초 후로 이동, 건너뛰기
         */
        public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
            int total = convertToInt(video_len);
            int cur = convertToInt(pos);
            int start = convertToInt(op_start);
            int end = convertToInt(op_end);

            if (cur >= start && cur <= end) {
                cur = end;
            }
            for (String command : commands) {
                switch (command) {
                    case "next" -> {
                        if (cur + 10 > total) {
                            cur = total;
                        } else {
                            cur += 10;
                        }
                    }
                    case "prev" -> {
                        if (cur - 10 < 0) {
                            cur = 0;
                        } else {
                            cur -= 10;
                        }
                    }
                }
                if (cur >= start && cur <= end) {
                    cur = end;
                }
            }

            return convertToStr(cur);
        }

        private int convertToInt(String str) {
            String[] split = str.split(":");
            return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        }

        private String convertToStr(int v) {
            int minutes = v / 60;
            int seconds = v % 60;
            return String.format("%02d:%02d", minutes, seconds);
        }
    }
}
