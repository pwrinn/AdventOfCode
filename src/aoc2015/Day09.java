package aoc2015;

import java.util.*;

public class Day09 implements Setup {

    public static void main(String[] args) {
        List<String> lines = Setup.getInput("src/aoc2015/09.txt");
        Map<String, Map<String, Integer>> distances = new HashMap<>();
        Set<String> cities = new HashSet<>();

        for (String line : lines) {
            String[] parts = line.split(" ");
            String city1 = parts[0];
            String city2 = parts[2];
            int distance = Integer.parseInt(parts[4]);

            cities.add(city1);
            cities.add(city2);

            distances.computeIfAbsent(city1, k -> new HashMap<>()).put(city2, distance);
            distances.computeIfAbsent(city2, k -> new HashMap<>()).put(city1, distance);
        }

        List<String> cityList = new ArrayList<>(cities);

        int shortestDistance = Integer.MAX_VALUE;
        int longestDistance = Integer.MIN_VALUE;

        List<List<String>> permutations = generatePermutations(cityList);
        for (List<String> permutation : permutations) {
            int totalDistance = 0;
            for (int i = 0; i < permutation.size() - 1; i++) {
                String cityA = permutation.get(i);
                String cityB = permutation.get(i + 1);
                totalDistance += distances.get(cityA).get(cityB);
            }

            shortestDistance = Math.min(shortestDistance, totalDistance);
            longestDistance = Math.max(longestDistance, totalDistance);
        }

        System.out.println("Part 1: " + shortestDistance);
        System.out.println("Part 2: " + longestDistance);
    }

    private static List<List<String>> generatePermutations(List<String> cities) {
        List<List<String>> result = new ArrayList<>();
        if (cities.isEmpty()) {
            result.add(new ArrayList<>());
            return result;
        }

        for (int i = 0; i < cities.size(); i++) {
            String first = cities.get(i);
            List<String> rest = new ArrayList<>(cities);
            rest.remove(i);
            for (List<String> permutation : generatePermutations(rest)) {
                List<String> newPermutation = new ArrayList<>();
                newPermutation.add(first);
                newPermutation.addAll(permutation);
                result.add(newPermutation);
            }
        }

        return result;
    }
}
