package aoc2015;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.HexFormat;

public class Day04 {

    public static void main(String [] args) throws Exception {
        String input = Files.readString(Paths.get("src/aoc2015/04.txt"));

        MessageDigest md = MessageDigest.getInstance("MD5");

        System.out.println("Part 1: " + getNumber(input, md, "00000"));
        System.out.println("Part 2: " + getNumber(input, md, "000000"));
    }

    private static long getNumber(String input, MessageDigest md, String zeroes) {
        long index = 0;

        while (true) {
            String key = String.format("%s%d", input, index++);
            byte[] digest = md.digest(key.getBytes());
            String hash = HexFormat.of().formatHex(digest);
            if (hash.startsWith(zeroes))
                return index - 1;
        }
    }
}
