package aoc2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day01 {

    public static void main(String[] args) throws IOException {

        String input = Files.readString(Paths.get("src/aoc2015/01.txt"));

        System.out.println("Part 1: " + floors(input));
        System.out.println("Part 2: " + basement(input));
    }

    private static int floors(String input) {
        int floor = 0;

        for (char c : input.toCharArray()) {
            if (c == '(') floor++;
            else if (c == ')') floor--;
        }

        return floor;
    }

    private static int basement(String input) {
        int floor = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') floor++;
            else if (input.charAt(i) == ')') floor--;
            if (floor == -1) return i + 1;
        }

        return floor;
    }
}