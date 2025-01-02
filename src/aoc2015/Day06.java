package aoc2015;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day06 implements Setup {

    private static final Pattern PATTERN = Pattern.compile("\\d+,\\d+");

    public static void main(String[] args) {
        List<String> lines = Setup.getInput("src/aoc2015/06.txt");
        boolean[][] grid1 = new boolean[1000][1000];
        int[][] grid2 = new int[1000][1000];
        System.out.println("Part 1: " + part1(lines, grid1));
        System.out.println("Part 2: " + part2(lines, grid2));
    }

    private static int part1(List<String> lines, boolean[][] grid) {

        for (String instr : lines) {
            Matcher m = PATTERN.matcher(instr);
            int[] range1 = Arrays.stream(nextMatch(m).split(",")).mapToInt(Integer::parseInt).toArray();
            int[] range2 = Arrays.stream(nextMatch(m).split(",")).mapToInt(Integer::parseInt).toArray();
            updateGridPt1(grid, range1, range2, instr);
        }

        return countLights(grid);
    }

    private static void updateGridPt1(boolean[][] grid, int[] range1, int[] range2, String instruction) {
        int x0 = range1[0];
        int y0 = range1[1];
        int x1 = range2[0];
        int y1 = range2[1];

        for (int i = y0; i <= y1; i++) {
            for (int j = x0; j <= x1; j++) {
                if (instruction.contains("on")) {
                    grid[i][j] = true;
                } else if (instruction.contains("off")) {
                    grid[i][j] = false;
                } else {
                    grid[i][j] = !grid[i][j];
                }
            }
        }
    }

    private static int countLights(boolean[][] grid) {
        int count = 0;
        for (boolean[] booleans : grid) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int part2(List<String> lines, int[][] grid) {

        for (String instr : lines) {
            Matcher m = PATTERN.matcher(instr);
            int[] range1 = Arrays.stream(nextMatch(m).split(",")).mapToInt(Integer::parseInt).toArray();
            int[] range2 = Arrays.stream(nextMatch(m).split(",")).mapToInt(Integer::parseInt).toArray();
            updateGridPt2(grid, range1, range2, instr);
        }

        return Arrays.stream(grid).flatMapToInt(Arrays::stream).sum();
    }

    private static void updateGridPt2(int[][] grid, int[] range1, int[] range2, String instruction) {
        int x0 = range1[0];
        int y0 = range1[1];
        int x1 = range2[0];
        int y1 = range2[1];

        for (int i = y0; i <= y1; i++) {
            for (int j = x0; j <= x1; j++) {
                int brightness = grid[i][j];
                if (instruction.contains("on")) {
                    grid[i][j] = brightness + 1;
                } else if(instruction.contains("off")) {
                    grid[i][j] = brightness == 0 ? brightness : brightness - 1;
                } else {
                    grid[i][j] = brightness + 2;
                }
            }
        }
    }

    private static String nextMatch(Matcher m) {
        m.find();
        return m.group();
    }
}
