package programmers.Lv2;

import java.util.ArrayList;
import java.util.List;

public class P17683 {

    public static void main(String[] args) {
        String m = "ABCDEFG";
        String[] musicinfos = {
            "12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"
        };
        String solution = new Solution().solution(m, musicinfos);
        System.out.println(solution);
    }

    private static class Solution {

        public String solution(String m, String[] musicinfos) {
            List<Music> candidates = new ArrayList<>();
            int len = musicinfos.length;

            String target = edit(m);
            for (int i = 0; i < len; i++) {
                String[] info = musicinfos[i].split(",");

                int time = getTime(info);
                String sheet = getSheet(edit(info[3]), time);

                if (sheet.contains(target)) {
                    Music music = new Music(i, info[2], time);
                    candidates.add(music);
                }
            }

            if (candidates.isEmpty()) {
                return "(None)";
            } else if (candidates.size() == 1) {
                return candidates.get(0).name;
            } else {
                candidates.sort((o1,o2)->{
                    int condition1 = Integer.compare(o2.minutes, o1.minutes);
                    return condition1 != 0 ? condition1 : Integer.compare(o1.idx, o2.idx);
                });
                return candidates.get(0).name;
            }
        }

        private int getTime(String[] arr) {
            int start = Integer.parseInt(arr[0].substring(0,2)) * 60 + Integer.parseInt(arr[0].substring(3));
            int end = Integer.parseInt(arr[1].substring(0,2)) * 60 + Integer.parseInt(arr[1].substring(3));
            return end - start;
        }

        private String getSheet(String replaced, int time) {
            int len = replaced.length();
            StringBuilder sb = new StringBuilder();
            int div = time / len;
            int mod = time % len;
            sb.append(replaced.repeat(div));
            for (int i = 0; i < mod; i++) {
                sb.append(replaced.charAt(i));
            }
            return sb.toString();
        }


        private String edit(String str) {
            StringBuilder sb = new StringBuilder();
            int len = str.length() - 1;

            for (int i = 0; i < len; i++) {
                String sub = str.substring(i, i + 2);
                String changed = change(sub);
                if (changed == null) {
                    sb.append(sub.charAt(0));
                } else {
                    sb.append(changed);
                    i += 1;
                }
            }

            if (str.charAt(len) != '#') {
                sb.append(str.charAt(len));
            }

            return sb.toString();
        }

        private String change(String str) {
            return switch (str) {
                case "C#" -> "!";
                case "D#" -> "@";
                case "F#" -> "#";
                case "G#" -> "$";
                case "A#" -> "%";
                case "B#" -> "^";
                default -> null;
            };
        }

        private static class Music {

            public int idx;
            public String name;
            public int minutes;

            public Music(int idx, String name, int minutes) {
                this.idx = idx;
                this.name = name;
                this.minutes = minutes;
            }
        }
    }
}
