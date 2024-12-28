package aoc2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day02 {

    public static void main(String[] args) {
        List<String> packages = getInput("src/aoc2015/02.txt");

        int totalPaper = 0;
        int ribbon = 0;
        for (String p : packages) {
            String[] dimensions = p.split("x");
            int l = Integer.parseInt(dimensions[0]);
            int w = Integer.parseInt(dimensions[1]);
            int h = Integer.parseInt(dimensions[2]);
            int side1 = l * w;
            int side2 = w * h;
            int side3 = h * l;
            int smallestSide = Math.min(side1, Math.min(side2, side3));

            // Surface area
            totalPaper += surfaceArea(l, w, h) + smallestSide;

            // Ribbon
            int perimeter = 2 * (Math.min(l + w, Math.min(w + h, h + l)));
            int volume = l * w * h;
            ribbon += perimeter + volume;

        }

        System.out.println("Part 1: " + totalPaper);
        System.out.println("Part 2: " + ribbon);
    }

    private static int surfaceArea(int l, int w, int h) {
        return 2 * ((l * w) + (w * h) + (l * h));
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
