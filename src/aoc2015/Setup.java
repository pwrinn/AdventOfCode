package aoc2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Setup {

    static List<String> getInput(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            return stream.collect(Collectors.toList());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
