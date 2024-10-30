package programmers.DFSnBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dfs43164Recur {

    public static void main(String[] args) {
        String[][][] testCase = {
            {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}},
            {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}},
            {{"ICN", "AAA"}, {"ICN", "AAA"}, {"ICN", "AAA"}, {"AAA", "ICN"}, {"AAA", "ICN"}},
            {{"ICN", "BOO"}, {"ICN", "COO"}, {"COO", "DOO"}, {"DOO", "COO"}, {"BOO", "DOO"}, {"DOO", "BOO"},
                {"BOO", "ICN"}, {"COO", "BOO"},},
            {{"ICN", "A"}, {"ICN", "A"}, {"A", "ICN"},}, {{"ICN", "COO"}, {"ICN", "BOO"}, {"COO", "ICN"}},
            {{"ICN", "SFO"}, {"SFO", "ICN"}, {"ICN", "SFO"}, {"SFO", "QRE"},},};
        for (String[][] ticketList : testCase) {
            for (String s : new Solution().solution(ticketList)) {
                System.out.print(s + ", ");
            }
            System.out.println();
        }

    }

    static class Solution {

        static class Airport {

            public String name;
            public boolean visited;

            public Airport(String name) {
                this.name = name;
                this.visited = false;
            }

            public boolean isVisited() {
                return this.visited;
            }

            public void setVisited(boolean visited) {
                this.visited = visited;
            }
        }

        private final Map<String, List<Airport>> map= new HashMap<>();
        private boolean allStop = false;
        private String[] result;
        private int resultLength;

        public String[] solution(String[][] ticketList) {
            initialize(ticketList);
            String START = "ICN";
            dfs(START, 0, new String[this.resultLength]);
            return this.result;
        }

        private void initialize(String[][] ticketList) {
            this.resultLength = ticketList.length + 1;
            this.result = new String[this.resultLength];
            for (String[] ticket : ticketList) {
                String start = ticket[0];
                String end = ticket[1];
                if (!this.map.containsKey(start)) {
                    this.map.put(start, new ArrayList<>());
                }
                this.map.get(start).add(new Airport(end));
            }
            for (String key : map.keySet()) {
                map.get(key).sort(Comparator.comparing(pre -> pre.name));
            }
        }

        private void dfs(String curAirportName, int idx, String[] passedByAirport) {
            passedByAirport[idx] = curAirportName;
            if (idx + 1 == this.resultLength) {
                this.allStop = true;
                this.result = Arrays.copyOfRange(passedByAirport, 0, this.resultLength);
                return;
            } else if (!this.map.containsKey(curAirportName)) {
                return;
            }

            List<Airport> airportList = map.get(curAirportName);
            for (Airport airport : airportList) {
                if (this.allStop) {
                    break;
                }
                if (airport.isVisited()) {
                    continue;
                }
                airport.setVisited(true);
                dfs(airport.name, idx + 1, passedByAirport);
                airport.setVisited(false);
                passedByAirport[idx + 1] = null;
            }
        }
    }
}
