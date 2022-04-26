package programmers.Lv2;

import java.util.*;

public class P72412_3 {
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        for (int result : new Solution().solution(info, query)) System.out.print(result + ", ");
        System.out.println();
    }

    static class Solution {
        public int[] solution(String[] info, String[] query) {
            final int INFO_LENGTH = info.length;
            final int QUERY_LENGTH = query.length;
            final byte JAVA = 0;
            final byte PYTHON = 1;
            final byte CPP = 2;
            int[] size = new int[3];
            int[] answer = new int[QUERY_LENGTH];
            List<Person>[] pListByLang = new ArrayList<>[3];
            for (int i = 0; i < 3; i++) {
                pListByLang[i] = new ArrayList<>();
            }

            for (String i : info) {
                String[] s = i.split(" ");
                switch (s[0]) {
                    case "java" -> pListByLang[JAVA].add(new Person(s));
                    case "python" -> pListByLang[PYTHON].add(new Person(s));
                    case "cpp" -> pListByLang[CPP].add(new Person(s));
                    default -> throw new UnsupportedOperationException();
                }
            }
            for (int i = 0; i < 3; i++) {
                size[i] = pListByLang[i].size();
                pListByLang[i].sort(Comparator.comparingInt(pre -> pre.score));
            }
            for (int i = 0; i < QUERY_LENGTH; i++) {
                String[] s1 = query[i].split(" and ");
                String[] s2 = s1[3].split(" ");
                final int SCORE = Integer.parseInt(s2[1]);
                int left = 0;
                int right = INFO_LENGTH - 1;
                int mid = (left + right) / 2;
                while (left <= right) {
                    mid = (left + right) / 2;
                    if (pList.get(mid).score >= SCORE) {
                        right = mid - 1;
                    } else if (pList.get(mid).score < SCORE) {
                        break;
                    }
                }
                int temp = 0;
                answer[i] = temp;

            }

            return answer;
        }

        static class Person {
            String job;
            String carrer;
            String food;
            int score;

            public Person(String[] s) {
                this.job = s[1];
                this.carrer = s[2];
                this.food = s[3];
                this.score = Integer.parseInt(s[4]);
            }

            private String getField(int i) {
                return switch (i) {
                    case 1 -> this.job;
                    case 2 -> this.carrer;
                    default -> throw new UnsupportedOperationException();
                };
            }

            public boolean check(String[] s1, String[] s2) {
                for (int i = 0; i < 3; i++) {
                    if (!s1[i].equals("-") && !getField(i).equals(s1[i])) {
                        return false;
                    }
                }
                if (!s2[0].equals("-") && !this.food.equals(s2[0])) {
                    return false;
                }
                return Integer.parseInt(s2[1]) <= this.score;
            }
        }
    }
}
