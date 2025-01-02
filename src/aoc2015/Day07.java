package aoc2015;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 implements Setup {

    public static void main(String[] args) {
        List<String> instructions = Setup.getInput("src/aoc2015/07.txt");
        System.out.println("Part 1: " + part1(instructions));
        System.out.println("Part 2: " + part2(instructions));
    }

    public static int part1(List<String> instructions) {
        return getMapping(instructions).get("a");
    }

    public static int part2(List<String> instructions) {
        int indexWB = indexOfWireB(instructions);
        int aPt1 = part1(instructions);

        instructions.set(indexWB,
                aPt1 + " -> " + instructions.get(indexWB).substring(instructions.get(indexWB).indexOf('>') + 2));

        return getMapping(instructions).get("a");
    }

    private static Map<String,Integer> getMapping(List<String> instructions) {
        Map<String, Integer> wireMapping = new HashMap<>();
        // pattern to match left hand arguments
        Pattern p = Pattern.compile("[a-z]+|\\d+");

        for (String instr : instructions) {
            String targetWire = instr.substring(instr.indexOf('>') + 2);

            if (!wireMapping.containsKey(targetWire)) {
                calculateSignal(targetWire, instr.substring(0, instr.indexOf('-') - 1), instructions, wireMapping, p);
            }
        }
        return wireMapping;
    }
    private static int indexOfWireB(List<String> instructions) {
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i).substring(instructions.get(i).indexOf('>') + 2).equals("b")) {
                return i;
            }
        }
        return -1;
    }

    // recursively calculates all signals
    private static void calculateSignal(String targetWire, String instr, List<String> instructions,
                                        Map<String, Integer> wireMapping, Pattern paramPattern) {

        // already calculated
        if (wireMapping.containsKey(targetWire)) {
            return;
        }

        // get the left-hand parameter(s)
        Matcher m = paramPattern.matcher(instr);
        List<String> params = params(m);

        // base case
        if (params.size() == 1 && params.get(0).matches("\\d+")) {
            wireMapping.put(targetWire, Integer.parseInt(params.getFirst()));
            return;
        }

        for (String param : params) {
            // parameter is a wire
            if (!param.matches("\\d+")) {
                String target = instructions.stream().filter(s -> s.substring(s.indexOf('>') + 2).equals(param))
                        .findFirst().orElse(null);
                if (!wireMapping.containsKey(param)) {
                    assert target != null;
                    calculateSignal(param, target.substring(0, target.indexOf('-') - 1), instructions, wireMapping,
                            paramPattern);
                }
                // parameter is an integer literal
            } else {
                wireMapping.put(param, Integer.parseInt(param));
            }
        }

        // calculate signal for this wire
        if (instr.contains("AND")) {
            wireMapping.put(targetWire, wireMapping.get(params.get(0)) & wireMapping.get(params.get(1)));
        } else if (instr.contains("OR")) {
            wireMapping.put(targetWire, wireMapping.get(params.get(0)) | wireMapping.get(params.get(1)));
        } else if (instr.contains("NOT")) {
            wireMapping.put(targetWire, ~wireMapping.get(params.getFirst()) & 0xFFFF);
        } else if (instr.contains("LSHI")) {
            wireMapping.put(targetWire, wireMapping.get(params.get(0)) << wireMapping.get(params.get(1)));
        } else if (instr.contains("RSHI")) {
            wireMapping.put(targetWire, wireMapping.get(params.get(0)) >> wireMapping.get(params.get(1)));
        } else {
            wireMapping.put(targetWire, wireMapping.get(params.getFirst()));
        }
    }

    // returns the left-hand parameters of the instruction
    private static List<String> params(Matcher m) {
        List<String> params = new ArrayList<>();
        while (m.find()) {
            params.add(m.group());
        }
        return params;
    }
}
