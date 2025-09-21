package programmers.Lv2;

import java.util.Arrays;

public class P81302_2 {

    public static void main(String[] args) {
        String[][] places = {
            {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
            {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
            {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
            {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
            {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };
        int[] solution = new Solution().solution(places);
        for (int i : solution) {
            System.out.print(i + ", ");
        }
    }

    private static class Solution {

        int[] answer = new int[5];
        boolean[][] visited = new boolean[5][5];
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public int[] solution(String[][] places) {
            Arrays.fill(answer, 1);

            for (int i = 0; i < 5; i++) {
                String[] place = places[i];

                loop: for (int y = 0; y < 5; y++) {
                    for (int x = 0; x < 5; x++) {
                        if (place[y].charAt(x) != 'P') {
                            continue;
                        }

                        visited[y][x] = true;
                        dfs(i, place, y, x, 0);
                        visited[y][x] = false;

                        if (answer[i] == 0) {
                            break loop;
                        }
                    }
                }
            }

            return answer;
        }

        private void dfs(int i, String[] place, int y, int x, int move) {
            if (move > 2) {
                return;
            } else if (move != 0) {
                char target = place[y].charAt(x);
                if (target == 'P') {
                    answer[i] = 0;
                    return;
                }
            }

            for (int[] direction : directions) {
                if (answer[i] == 0) {
                    return;
                }

                int ny = y + direction[0];
                int nx = x + direction[1];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || visited[ny][nx]) {
                    continue;
                } else if (place[ny].charAt(nx) == 'X') {
                    continue;
                }

                visited[ny][nx] = true;
                dfs(i, place, ny, nx, move + 1);
                visited[ny][nx] = false;
            }
        }

    }
}
