package programmers.Lv2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class P86502 {

    public static void main(String[] args) {
        String[] grid = new String[]{"SL", "LR"};
        int[] solution = new Solution().solution(grid);
        for (int i : solution) {
            System.out.println(i);
        }
    }

    private static class Solution {

        public int[] solution(String[] grid) {
            List<Integer> answer = new ArrayList<>();
            From[] froms = new From[]{From.TOP, From.BOTTOM, From.LEFT, From.RIGHT};

            int rows = grid.length;
            int cols = grid[0].length();
            boolean[][][] visited = new boolean[rows][cols][4];

            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    for (int i = 0; i < 4; i++) {
                        int count = 0;
                        ArrayDeque<Node> dq = new ArrayDeque<>();
                        Node start = new Node(grid, y, x, froms[i]);
                        dq.push(start);

                        while (!dq.isEmpty()) {
                            Node pop = dq.pop();
                            count += 1;
                            visited[pop.y][pop.x][pop.from.idx] = true;

                            Node node = new Node(grid, pop.y, pop.x, pop.from);
                            changeLocation(grid, node, rows, cols);

                            if (node.equals(start)) {
                                answer.add(count);
                                break;
                            } else if (visited[node.y][node.x][node.from.idx]) {
                                break;
                            } else {
                                dq.push(node);
                            }
                        }
                    }
                }
            }

            return answer.stream().sorted(Comparator.naturalOrder()).mapToInt(v -> v).toArray();
        }

        private void changeLocation(String[] grid, Node node, int rows, int cols) {
            switch (node.name) {
                case 'S' -> {
                    if (node.from == From.TOP) {
                        node.y += 1;
                    } else if (node.from == From.BOTTOM) {
                        node.y -= 1;
                    } else if (node.from == From.LEFT) {
                        node.x += 1;
                    } else {
                        node.x -= 1;
                    }
                }
                case 'L' -> {
                    if (node.from == From.TOP) {
                        node.x += 1;
                        node.from = From.LEFT;
                    } else if (node.from == From.BOTTOM) {
                        node.x -= 1;
                        node.from = From.RIGHT;
                    } else if (node.from == From.LEFT) {
                        node.y -= 1;
                        node.from = From.BOTTOM;
                    } else {
                        node.y += 1;
                        node.from = From.TOP;
                    }
                }
                case 'R' -> {
                    if (node.from == From.TOP) {
                        node.x -= 1;
                        node.from = From.RIGHT;
                    } else if (node.from == From.BOTTOM) {
                        node.x += 1;
                        node.from = From.LEFT;
                    } else if (node.from == From.LEFT) {
                        node.y += 1;
                        node.from = From.TOP;
                    } else {
                        node.y -= 1;
                        node.from = From.BOTTOM;
                    }
                }
            }
            if (node.y < 0) {
                node.y = rows - 1;
            } else if (node.y >= rows) {
                node.y = 0;
            }
            if (node.x < 0) {
                node.x = cols - 1;
            } else if (node.x >= cols) {
                node.x = 0;
            }
            node.name = grid[node.y].charAt(node.x);
        }

        private enum From {
            TOP(0), BOTTOM(1), LEFT(2), RIGHT(3);
            final int idx;

            From(int idx) {
                this.idx = idx;
            }
        }

        private class Node {

            char name;
            int y;
            int x;
            From from;

            public Node(String[] grid, int y, int x, From from) {
                this.name = grid[y].charAt(x);
                this.y = y;
                this.x = x;
                this.from = from;
            }

            @Override
            public boolean equals(Object o) {
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                Node node = (Node) o;
                return name == node.name && y == node.y && x == node.x && from == node.from;
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, y, x, from);
            }
        }
    }
}
