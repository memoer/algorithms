package programmers.Lv2;


import java.util.*;

public class P72412 {
    static class Solution {
        private Pointer[][] index;

        // 지원자가 지원서에 입력한 4가지의 정보와 획득한 코딩테스트 점수를 하나의 문자열로 구성한 값의 배열 info
        // 개발팀이 궁금해하는 문의조건이 문자열 형태로 담긴 배열 query
        public int[] solution(String[] info, String[] query) {
            int[] answer = new int[query.length];
            this.index = new Pointer[3][2];
            for (String s : info) {
                String[] splitted = s.split(" ");
                Pointer pointer = getPointer(splitted);
                if (pointer == null) {
                    pointer = new Pointer();
                    this.index[getLanguage(splitted[0])][getCarrer(splitted[1])] = pointer;
                }
                Map<String, List<Integer>> hashMap = pointer.getHashMap(splitted[2]);
                hashMap.computeIfAbsent(splitted[3], k -> new ArrayList<>());
                hashMap.get(splitted[3]).add(Integer.parseInt(splitted[4]));
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    if (this.index[i][j] == null) {
                        continue;
                    }
                    this.index[i][j].sort();
                }
            }
            final int LENGTH = query.length;
            for (int i = 0; i < LENGTH; i++) {
                answer[i] = accumulator(query[i]);
            }
            return answer;
        }

        private int accumulator(String query) {
            String[] splitted = query.split(" and ");
            String first = splitted[0];
            String second = splitted[1];
            String third = splitted[2];
            String[] last = splitted[3].split(" ");
            int[] a;
            int[] b;
            String[] c;
            int acc = 0;
            if (first.equals("-")) {
                a = new int[]{0, 1, 2};
            } else {
                a = new int[]{getLanguage(first)};
            }
            if (second.equals("-")) {
                b = new int[]{0, 1};
            } else {
                b = new int[]{getCarrer(second)};
            }
            if (third.equals("-")) {
                c = new String[]{"junior", "senior"};
            } else {
                c = new String[]{third};
            }
            for (int i : a) {
                for (int j : b) {
                    Pointer pointer = this.index[i][j];
                    if (pointer == null) {
                        continue;
                    }
                    for (String k : c) {
                        Map<String, List<Integer>> hashMap = pointer.getHashMap(k);
                        if (hashMap == null) {
                            continue;
                        }
                        if (last[0].equals("-")) {
                            for (List<Integer> list : hashMap.values()) {
                                if (list == null) {
                                    continue;
                                }
                                int temp = list.size();
                                for (int v : list) {
                                    if (v < Integer.parseInt(last[1])) {
                                        temp -= 1;
                                    } else {
                                        break;
                                    }
                                }
                                acc += temp;
                            }
                        } else if (hashMap.get(last[0]) != null) {
                            int temp = hashMap.get(last[0]).size();
                            for (int v : hashMap.get(last[0])) {
                                if (v < Integer.parseInt(last[1])) {
                                    temp -= 1;
                                } else {
                                    break;
                                }
                            }
                            acc += temp;
                        }
                    }
                }
            }
            return acc;
        }

        private Pointer getPointer(String[] s) {
            return this.index[getLanguage(s[0])][getCarrer(s[1])];
        }

        private int getLanguage(String s) {
            return switch (s) {
                case "java" -> 0;
                case "python" -> 1;
                case "cpp" -> 2;
                default -> throw new UnsupportedOperationException();
            };
        }

        private int getCarrer(String s) {
            return switch (s) {
                case "backend" -> 0;
                case "frontend" -> 1;
                default -> throw new UnsupportedOperationException();
            };
        }


        static class Pointer {
            private Map<String, List<Integer>> junior;
            private Map<String, List<Integer>> senior;

            public void put(String key, int value) {
            }

            public Map<String, List<Integer>> getHashMap(String type) {
                switch (type) {
                    case "junior" -> {
                        if (this.junior == null) {
                            this.junior = new HashMap<>();
                        }
                        return this.junior;
                    }
                    case "senior" -> {
                        if (this.senior == null) {
                            this.senior = new HashMap<>();
                        }
                        return this.senior;
                    }
                    default -> throw new UnsupportedOperationException();
                }
            }

            public void sort() {
                if (this.junior != null) {
                    for (List<Integer> list : this.junior.values()) {
                        if (list == null) {
                            continue;
                        }
                        list.sort((a, b) -> a - b);
                    }
                }
                if (this.senior != null) {
                    for (List<Integer> list : this.senior.values()) {
                        if (list == null) {
                            continue;
                        }
                        list.sort((a, b) -> a - b);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] strings = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] strings1 = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        for (int result : new Solution().solution(strings, strings1)) {
            System.out.print(result + ", ");
        }
        System.out.println();
    }
}
