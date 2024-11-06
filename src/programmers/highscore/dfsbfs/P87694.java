package programmers.highscore.dfsbfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class P87694 {

    public static void main(String[] args) {
        int[][] rectangle = {
            {1, 1, 7, 4},
            {3, 2, 5, 5},
            {4, 3, 6, 9},
            {2, 6, 8, 8},
        };
        int solution = new Solution().solution(rectangle, 1, 3, 7, 8);
        System.out.println(solution);
    }

    private static class Solution {

        private int len;
        private int[][] rectangle;
        private final int[][] direct = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
            this.len = rectangle.length;
            this.rectangle = rectangle;
            Set<String> visited = new HashSet<>();
            Set<String> load = init();
            int idx = 0;

            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{characterX, characterY, 0});
            visited.add(characterX + "" + characterY);
            while (!q.isEmpty()) {
                int[] loc = q.poll();
                if (loc[0] == itemX && loc[1] == itemY) {
                    return loc[2];
                }
                for (int[] v : direct) {
                    int[] next = getNext(loc, v);
                    if (next == null) {
                        continue;
                    }
                    idx = changeIdx(idx, next);
                    String s = next[0] + "" + next[1];
                    if (visited.contains(s) || !load.contains(s)) {
                        continue;
                    }
                    if (!isLine(next, rectangle[idx])) {
                        continue;
                    }
                    q.offer(new int[]{next[0], next[1], loc[2] + 1});
                    visited.add(s);
                }
            }

            return -1;
        }

        private int[] getNext(int[] loc, int[] next) {
            int nextX = loc[0] + next[0];
            int nextY = loc[1] + next[1];
            return nextX >= 1 && nextX <= 50 && nextY >= 1 && nextY <= 50 ? new int[]{nextX, nextY} : null;
        }

        private Set<String> init() {
            Set<String> set = new HashSet<>();
            Deque<int[]> dq = new ArrayDeque<>();
            int idx = 0;
            dq.push(new int[]{rectangle[idx][0], rectangle[idx][1]});

            while (!dq.isEmpty()) {
                int[] loc = dq.poll();
                idx = changeIdx(idx, loc);
                for (int[] v : direct) {
                    int[] next = getNext(loc, v);
                    if (next == null) {
                        continue;
                    }

                    String s = next[0] + "" + next[1];
                    if (set.contains(s)) {
                        continue;
                    } else if (!canGo(idx, next)) {
                        continue;
                    }

                    dq.push(next);
                    set.add(s);
                }
            }
            return set;
        }

        private int changeIdx(int idx, int[] target) {
            for (int i = 0; i < len; i++) {
                if (i == idx) {
                    continue;
                }
                int[] r = rectangle[i];
                if (r[0] <= target[0] && r[1] <= target[1] && r[2] >= target[0] && r[3] >= target[1]) {
                    return i;
                }
            }
            return idx;
        }

        private boolean canGo(int idx, int[] next) {
            int[] cur = rectangle[idx];
            for (int i = 0; i < len; i++) {
                if (i == idx) {
                    continue;
                }
                int[] r = rectangle[i];
                if (r[0] < next[0] && r[1] < next[1] && r[2] > next[0] && r[3] > next[1]) {
                    return false;
                }
            }
            return isLine(next, cur);
        }

        private boolean isLine(int[] next, int[] cur) {
            return (next[0] == cur[0] || next[0] == cur[2] || next[1] == cur[1] || next[1] == cur[3]) &&
                (next[0] >= cur[0] && next[1] >= cur[1] && next[0] <= cur[2] && next[1] <= cur[3]);
        }
    }
}
