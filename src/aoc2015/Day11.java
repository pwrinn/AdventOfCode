package aoc2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day11 {

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/aoc2015/11.txt"));
        String nextPassword = getNextPassword(input);
        System.out.println("Part 1: " + nextPassword);
        String nextPassword2 = getNextPassword(nextPassword);
        System.out.println("Part 2: " + nextPassword2);
    }

    private static String getNextPassword(String password) {
        do {
            password = incrementPassword(password);
        } while (!isValidPassword(password));
        return password;
    }

    private static String incrementPassword(String password) {
        char[] chars = password.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == 'z') {
                chars[i] = 'a';
            } else {
                chars[i]++;
                break;
            }
        }
        return new String(chars);
    }

    private static boolean isValidPassword(String password) {
        return hasIncreasingStraight(password) && !hasForbiddenChars(password) && hasTwoPairs(password);
    }

    private static boolean hasIncreasingStraight(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i + 1) == password.charAt(i) + 1 && password.charAt(i + 2) == password.charAt(i) + 2) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasForbiddenChars(String password) {
        return password.contains("i") || password.contains("o") || password.contains("l");
    }

    private static boolean hasTwoPairs(String password) {
        int pairs = 0;
        for (int i = 0; i < password.length() - 1; i++) {
            if (password.charAt(i) == password.charAt(i + 1)) {
                pairs++;
                i++;
            }
        }
        return pairs >= 2;
    }
}
