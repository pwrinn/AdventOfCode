package aoc2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day05 {

    public static void main(String[] args) throws IOException {

        List<String> input = getInput("src/aoc2015/05.txt");

        Pattern p1_p1 = Pattern.compile("ab|cd|pq|xy");
        Pattern p1_p2 = Pattern.compile("(.*[aeiou]){3}");
        Pattern p1_p3 = Pattern.compile("(.)\\1");
        Pattern p2_p1 = Pattern.compile("(..).*\\1");
        Pattern p2_p2 = Pattern.compile("(.).\\1");

        int p1_count = 0;
        int p2_count = 0;

        for (String line : input) {
            if (!p1_p1.matcher(line).find() &&
                    p1_p2.matcher(line).find() &&
                    p1_p3.matcher(line).find()) {
                p1_count++;
            }

            if (p2_p1.matcher(line).find() &&
                    p2_p2.matcher(line).find()) {
                p2_count++;
            }
        }

        System.out.println("Part 1: " + p1_count);
        System.out.println("Part 2: " + p2_count);
    }

    private static List<String> getInput(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            return stream.collect(Collectors.toList());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
