package aoc2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Day01 {

    public static void main(String[] args) {
        List<Integer> fileInput1 = new ArrayList<>();
        List<Integer> fileInput2 = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/aoc2024/01.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(" ");

                String firstColumn = columns[0];
                String secondColumn = columns[3];

                fileInput1.add(Integer.parseInt(firstColumn));
                fileInput2.add(Integer.parseInt(secondColumn));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer[] arr1 = new Integer[fileInput1.size()];
        arr1 = fileInput1.toArray(arr1);
        Integer[] arr2 = new Integer[fileInput2.size()];
        arr2 = fileInput2.toArray(arr2);

        System.out.println("Part 1: " + sum(arr1, arr2));
        System.out.println("Part 2: " + similarity(arr1, arr2));
    }

    static Integer sum(Integer[] a, Integer[] b) {
        int sum = 0;
        Arrays.sort(a);
        Arrays.sort(b);

        if (a.length == b.length) {
            for (int i = 0; i < a.length; i++) {
                sum += Math.abs(a[i] - b[i]);
            }
        }
        return sum;
    }

    static Integer similarity(Integer[] a, Integer[] b) {
        int similarity = 0;
        int sum = 0;

        for (Integer integer : a) {
            for (Integer value : b) {
                if (Objects.equals(integer, value)) {
                    sum += 1;
                }
            }
            similarity += integer * sum;
            sum = 0;
        }

        return similarity;
    }
}
