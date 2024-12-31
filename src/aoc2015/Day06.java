package aoc2015;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day06 implements Setup {

    public static void main(String[] args) {
        List<String> lines = Setup.getInput("src/aoc2015/06.txt");
        System.out.println(run1(lines));
        System.out.println(run2(lines));
    }

    public static int run1(List<String> lines) {
        boolean[][] grid = new boolean[1000][1000];
        Pattern p = Pattern.compile("\\d+,\\d+");
        for (String instr : lines) {
            Matcher m = p.matcher(instr);
            int[] range1 = Arrays.stream(nextMatch(m).split(",")).mapToInt(Integer::parseInt).toArray();
            int[] range2 = Arrays.stream(nextMatch(m).split(",")).mapToInt(Integer::parseInt).toArray();
            updateGridPt1(grid, range1, range2, instr);
        }

        return countLights(grid);
    }

    public static int run2(List<String> lines) {
        int[][] grid = new int[1000][1000];
        Pattern p = Pattern.compile("\\d+,\\d+");
        for (String instr : lines) {
            Matcher m = p.matcher(instr);
            int[] range1 = Arrays.stream(nextMatch(m).split(",")).mapToInt(Integer::parseInt).toArray();
            int[] range2 = Arrays.stream(nextMatch(m).split(",")).mapToInt(Integer::parseInt).toArray();
            updateGridPt2(grid, range1, range2, instr);
        }

        return measureBrightness(grid);
    }

    private static void updateGridPt2(int[][] grid, int[] range1, int[] range2, String instruction) {
        int x0 = range1[0];
        int y0 = range1[1];
        int x1 = range2[0];
        int y1 = range2[1];

        for(int i = y0; i <= y1; i++) {
            for(int j = x0; j <= x1; j++) {
                int brightness = grid[i][j];
                if(instruction.contains("on")) {
                    grid[i][j] = brightness + 1;
                } else if(instruction.contains("off")) {
                    grid[i][j] = brightness == 0 ? brightness : brightness - 1;
                } else {
                    grid[i][j] = brightness + 2;
                }
            }
        }
    }

    private static int measureBrightness(int[][] grid) {
        return Arrays.stream(grid).flatMapToInt(arr -> Arrays.stream(arr)).sum();
    }


    private static int countLights(boolean[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void updateGridPt1(boolean[][] grid, int[] range1, int[] range2, String instruction) {
        int x0 = range1[0];
        int y0 = range1[1];
        int x1 = range2[0];
        int y1 = range2[1];

        for(int i = y0; i <= y1; i++) {
            for(int j = x0; j <= x1; j++) {
                if(instruction.contains("on")) {
                    grid[i][j] = true;
                } else if(instruction.contains("off")) {
                    grid[i][j] = false;
                } else {
                    grid[i][j] = !grid[i][j];
                }
            }
        }
    }

    private static String nextMatch(Matcher m) {
        m.find();
        return m.group();
    }

//    public static void main(String[] args) {
//        int[][] lights = new int[1000][1000];
//        List<String> input = Setup.getInput("src/aoc2015/06.txt");
//        int brightness = 0;
//
//        for (String line : input) {
//            String[] parts = line.split(" ");
//
//            String command = parts[0].equals("toggle") ? "toggle" : parts[1];
//            int x1 = Integer.parseInt(parts[parts.length - 3].split(",")[0]);
//            int y1 = Integer.parseInt(parts[parts.length - 3].split(",")[1]);
//            int x2 = Integer.parseInt(parts[parts.length - 1].split(",")[0]);
//            int y2 = Integer.parseInt(parts[parts.length - 1].split(",")[1]);
//
//            for (int i = x1; i <= x2; i++) {
//                for (int j = y1; j <= y2; j++) {
//                    switch (command) {
//                        case "on":
//                            lights[i][j]++;
//                            brightness++;
//                            break;
//                        case "off":
//                            lights[i][j]--;
//                            brightness--;
//                            break;
//                        case "toggle":
//                            lights[i][j] = 1 - lights[i][j];
//                            lights[i][j]+= 2;
//                            brightness += 2;
//                            break;
//                    }
//                }
//            }
//        }
//
//        int count = 0;
//        for (int i = 0; i < 1000; i++) {
//            for (int j = 0; j < 1000; j++) {
//                count += lights[i][j];
//            }
//        }
//
//        System.out.println("Lights on: " + count);
//        System.out.println("Brightness: " + brightness);
//    }
}
