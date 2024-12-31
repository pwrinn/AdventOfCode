package aoc2015;

import java.util.List;

public class Day02 implements Setup {

    public static void main(String[] args) {
        List<String> packages = Setup.getInput("src/aoc2015/02.txt");

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
}
