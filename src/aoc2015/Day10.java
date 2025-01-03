package aoc2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day10 {

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/aoc2015/10.txt"));

        System.out.println("Part 1: " + getLength(input, 40));
        System.out.println("Part 2: " + getLength(input, 50));
    }

    private static int getLength(String input, int times) {
        StringBuilder output = new StringBuilder(input);

        for (int i = 0; i < times; i++) {
            int count = 0;
            String[] numbers = output.toString().split("");
            int[] sequence = Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
            output.setLength(0);
            for (int j = 1; j < sequence.length; j++) {
                count++;
                if (sequence[j] == sequence[j - 1]) {
                    if (j == sequence.length - 1) {
                        output.append(count);
                        output.append(sequence[j]);
                    }
                } else {
                    output.append(count);
                    output.append(sequence[j - 1]);
                    if (j == sequence.length - 1) {
                        output.append(1);
                        output.append(sequence[j]);
                    }
                    count = 0;
                }
            }
        }

        return output.length();
    }
}