package aoc2015;

import java.util.List;

public class Day08 implements Setup{

    public static void main(String[] args) {
        List<String> input = Setup.getInput("src/aoc2015/08.txt");

        System.out.println("Part 1: " + part1(input));
        System.out.println("Part 2: " + part2(input));
    }

    private static int part1(List<String> input) {
        int codeChars = 0;
        int memoryChars = 0;

        for (String line : input) {
            codeChars += line.length();
            memoryChars += countMemoryChars(line);
        }

        return codeChars - memoryChars;
    }

    private static int part2(List<String> input) {
        int codeChars = 0;
        int encodedChars = 0;

        for (String line : input) {
            codeChars += line.length();
            encodedChars += countEncodedChars(line);
        }

        return encodedChars - codeChars;
    }

    private static int countMemoryChars(String line) {
        int count = 0;
        for (int i = 1; i < line.length() - 1; i++) {
            char c = line.charAt(i);
            if (c == '\\') {
                if (line.charAt(i + 1) == 'x') {
                    i += 3;
                } else {
                    i++;
                }
            }
            count++;
        }
        return count;
    }

    private static int countEncodedChars(String line) {
        int count = 2; // for the surrounding quotes
        for (char c : line.toCharArray()) {
            if (c == '\\' || c == '"') {
                count += 2;
            } else {
                count++;
            }
        }
        return count;
    }
}
