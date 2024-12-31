package aoc2015;

import java.util.List;
import java.util.regex.Pattern;

public class Day05 implements Setup {

    public static void main(String[] args) {

        List<String> input = Setup.getInput("src/aoc2015/05.txt");

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
}
