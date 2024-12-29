package programmers.Lv1;

import java.util.HashMap;
import java.util.Map;

public class P118666_2 {

    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};
        String solution = new Solution().solution(survey, choices);
        System.out.printf(solution);
    }

    private static class Solution {

        public String solution(String[] survey, int[] choices) {
            Map<Character, Integer> map = new HashMap<>();
            Character[][] types = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
            int[] score = {0, 3, 2, 1, 0, 1, 2, 3};

            for (Character[] type : types) {
                map.put(type[0], 0);
                map.put(type[1], 0);
            }

            int len = choices.length;
            for (int i = 0; i < len; i++) {
                String type = survey[i];
                int choice = choices[i];
                if (choice < 4) {
                    map.put(type.charAt(0), map.get(type.charAt(0)) + score[choice]);
                } else {
                    map.put(type.charAt(1), map.get(type.charAt(1)) + score[choice]);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (Character[] type : types) {
                Character c = map.get(type[0]) < map.get(type[1]) ? type[1] : type[0];
                sb.append(c);
            }
            return sb.toString();
        }

    }
}
