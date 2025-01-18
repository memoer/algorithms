package programmers.Lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P1835_2 {

    public static void main(String[] args) {
        int n = 2;
        String[] data = {
            "M~C<2", "C~M>1"
        };
        int solution = new Solution().solution(n, data);
        System.out.println(solution);
    }

    private static class Solution {

        private final char[] users = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        private final int usersLen = users.length;
        private final List<Condition>[][] conditions = new List[usersLen][usersLen];
        private final Set<String> set = new HashSet<>();

        public int solution(int n, String[] data) {
            for (String s : data) {
                char me = s.charAt(0);
                char other = s.charAt(2);
                if (conditions[getIdx(me)][getIdx(other)] == null) {
                    conditions[getIdx(me)][getIdx(other)] = new ArrayList<>();
                }
                if (conditions[getIdx(other)][getIdx(me)] == null) {
                    conditions[getIdx(other)][getIdx(me)] = new ArrayList<>();
                }

                conditions[getIdx(me)][getIdx(other)].add(new Condition(s.charAt(3), s.charAt(4) - '0'));
                conditions[getIdx(other)][getIdx(me)].add(new Condition(s.charAt(3), s.charAt(4) - '0'));
            }

            int[] loc = new int[usersLen];
            Arrays.fill(loc, -1);
            dfs(new char[usersLen], loc, 0);

            return set.size();
        }

        private void dfs(char[] candidate, int[] loc, int idx) {
            if (idx == usersLen) {
                set.add(new String(candidate));
                return;
            }

            for (char user : users) {
                if (loc[getIdx(user)] != -1 || !isAvailable(user, idx - 1, candidate, loc)) {
                    continue;
                }

                candidate[idx] = user;
                loc[getIdx(user)] = idx;
                dfs(candidate, loc, idx + 1);
                loc[getIdx(user)] = -1;
                candidate[idx] = '\0';
            }
        }

        private boolean isAvailable(char user, int position, char[] candidate, int[] loc) {
            for (char other : candidate) {
                if (other == '\0' || this.conditions[getIdx(user)][getIdx(other)] == null) {
                    continue;
                }

                List<Condition> conditions = this.conditions[getIdx(user)][getIdx(other)];
                int gap = Math.abs(position - loc[getIdx(other)]);
                for (Condition condition : conditions) {
                    switch (condition.op) {
                        case '=' -> {
                            if (gap != condition.num) {
                                return false;
                            }
                        }
                        case '<' -> {
                            if (gap >= condition.num) {
                                return false;
                            }
                        }
                        case '>' -> {
                            if (gap <= condition.num) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }


        private int getIdx(char user) {
            return switch (user) {
                case 'A' -> 0;
                case 'C' -> 1;
                case 'F' -> 2;
                case 'J' -> 3;
                case 'M' -> 4;
                case 'N' -> 5;
                case 'R' -> 6;
                case 'T' -> 7;
                default -> throw new IllegalStateException("Unexpected value: " + user);
            };
        }


        private static class Condition {

            char op;
            int num;

            public Condition(char op, int num) {
                this.op = op;
                this.num = num;
            }
        }

    }
}
