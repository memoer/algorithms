package programmers.Lv2;

import java.util.*;

public class P1844Other {
    static class Solution {
        public int solution(int[][] maps) {
            final int[][] NEXT_CANDIDATE = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            final int LENGTH_Y = maps.length;
            final int LENGTH_X = maps[0].length;

            Queue<Location> q = new LinkedList<>();
            q.offer(new Location(0, 0, 1));
            maps[0][0] = 0;
            while (!q.isEmpty()) {
                Location location = q.poll();
                if (location.x == LENGTH_X - 1 && location.y == LENGTH_Y - 1) {
                    return location.acc;
                }
                for (int[] next : NEXT_CANDIDATE) {
                    int nextY = location.y + next[0];
                    int nextX = location.x + next[1];
                    if (nextY >= LENGTH_Y || nextY < 0 || nextX >= LENGTH_X || nextX < 0) continue;
                    if (maps[nextY][nextX] == 0) continue;
                    // 미리 해당 좌표에 도착했다는 표시를 설정해야 한다.
                    // 그래야, 큐에 중복되는 좌표가 추가되지 않는다.
                    maps[nextY][nextX] = 0;
                    q.offer(new Location(nextY, nextX, location.acc + 1));
                }
            }
            return -1;
        }

        static class Location {

            int y;
            int x;
            int acc;

            public Location(int y, int x, int acc) {
                this.y = y;
                this.x = x;
                this.acc = acc;
            }
        }
    }

    public static void main(String[] args) {
        int[][] maps = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}
        };
        System.out.println(new P1844Other.Solution().solution(maps));
    }
}
