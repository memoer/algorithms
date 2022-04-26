package programmers.Lv2;

import java.util.ArrayList;
import java.util.List;

public class P72412_2 {
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        for (int result : new Solution().solution(info, query)) System.out.print(result + ", ");
        System.out.println();
    }

    static class Solution {
        private Language[] languages;

        public int[] solution(String[] info, String[] query) {
            this.languages = new Language[3];
            for (String i : info) {
                insert(i.split(" "));
            }
            return new int[]{};
        }

        public void insert(String[] s) {
            getLanguages(languages, s[0]).get(s[1]).get(s[2]).get(s[3]).addScore(Integer.parseInt(s[4]));
        }

        public Language getLanguages(Language[] languages, String s) {
            switch (s) {
                case "java" -> {
                    if (this.languages[0] == null) {
                        this.languages[0] = new Language();
                    }
                    return this.languages[0];
                }
                case "python" -> {
                    if (this.languages[1] == null) {
                        this.languages[1] = new Language();
                    }
                    return this.languages[1];
                }
                case "cpp" -> {
                    if (this.languages[2] == null) {
                        this.languages[2] = new Language();
                    }
                    return this.languages[2];
                }
                default -> throw new UnsupportedOperationException();
            }
        }

        static class Language {
            Job back;
            Job front;

            public Job get(String s) {
                return switch (s) {
                    case "backend" -> this.getBack();
                    case "frontend" -> this.getFront();
                    default -> throw new UnsupportedOperationException();
                };
            }

            public Job getBack() {
                if (this.back == null) {
                    this.back = new Job();
                }
                return back;
            }

            public Job getFront() {
                if (this.front == null) {
                    this.front = new Job();
                }
                return front;
            }
        }

        static class Job {
            Carrer junior;
            Carrer senior;

            public Carrer get(String s) {
                return switch (s) {
                    case "junior" -> this.getJunior();
                    case "senior" -> this.getSenior();
                    default -> throw new UnsupportedOperationException();
                };
            }

            public Carrer getJunior() {
                if (this.junior == null) {
                    this.junior = new Carrer();
                }
                return junior;
            }

            public Carrer getSenior() {
                if (this.senior == null) {
                    this.senior = new Carrer();
                }
                return senior;
            }
        }

        static class Carrer {
            Food pizza;
            Food chicken;

            public Food get(String s) {
                return switch (s) {
                    case "pizza" -> this.getPizza();
                    case "chicken" -> this.getChicken();
                    default -> throw new IllegalStateException("Unexpected value: " + s);
                };
            }

            public Food getPizza() {
                if (this.pizza == null) {
                    this.pizza = new Food();
                }
                return pizza;
            }

            public Food getChicken() {
                if (this.chicken == null) {
                    this.chicken = new Food();
                }
                return chicken;
            }
        }

        static class Food {
            List<Integer> list;

            public void addScore(int score) {
                if (this.list == null) {
                    this.list = new ArrayList<>();
                }
                this.list.add(score);
            }
        }

    }
}
