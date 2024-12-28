package aoc2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Day03 {

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/aoc2015/03.txt"));

        System.out.println("Part 1: " + countVisited(input));
        System.out.println("Part 2: " + countVisitedWithRobotSanta(input));
    }

    private static int countVisited(String input) {
        Set<Position> visited = new HashSet<>();
        Position current = new Position(0, 0);
        visited.add(current);

        for (char c : input.toCharArray()) {
            current = getPosition(visited, current, c);
        }

        return visited.size();
    }

    private static int countVisitedWithRobotSanta(String input) {
        Set<Position> visited = new HashSet<>();
        Position santa = new Position(0, 0);
        Position roboSanta = new Position(0, 0);
        visited.add(santa);

        for (int i = 0; i < input.length(); i++) {
            Position current = (i % 2 == 0) ? santa : roboSanta;
            char c = input.charAt(i);

            current = getPosition(visited, current, c);

            if (i % 2 == 0) {
                santa = current;
            } else {
                roboSanta = current;
            }
        }

        return visited.size();
    }

    private static Position getPosition(Set<Position> visited, Position current, char c) {
        current = switch (c) {
            case '^' -> new Position(current.x, current.y + 1);
            case 'v' -> new Position(current.x, current.y - 1);
            case '>' -> new Position(current.x + 1, current.y);
            case '<' -> new Position(current.x - 1, current.y);
            default -> current;
        };

        visited.add(current);
        return current;
    }

    private record Position (int x, int y) {}
}
