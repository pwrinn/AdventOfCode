package aoc2015;

import com.google.gson.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12 {

    private static final Pattern PATTERN = Pattern.compile("-?\\d+");

    public static void main(String[] args) throws IOException {
        String json = Files.readString(Paths.get("src/aoc2015/12.txt"));

        System.out.println("Part 1: " + getSumOfAllNumbers(json));
        System.out.println("Part 2: " + getSumOfAllNonRedNumbers(json));
    }

    static int getSumOfAllNumbers(String json) {
        int result = 0;
        Matcher matcher = PATTERN.matcher(json);
        while (matcher.find()) {
            String number = matcher.group();
            result += Integer.parseInt(number);
        }
        return result;
    }

    static int getSumOfAllNonRedNumbers(String json) {
        JsonElement rootNode = JsonParser.parseString(json);
        return getSumOfAllNonRedNumbers(rootNode);
    }

    private static int getSumOfAllNonRedNumbers(JsonElement element) {
        int result = 0;

        if (element.isJsonArray()) {
            result += getSumOfNonRedNumbersInArray(element.getAsJsonArray());
        } else if (element.isJsonObject()) {
            result += getSumOfNonRedNumbersInObject(element.getAsJsonObject());
        } else if (element.isJsonPrimitive()) {
            JsonPrimitive jsonPrimitive = element.getAsJsonPrimitive();
            if (jsonPrimitive.isNumber()) {
                return jsonPrimitive.getAsInt();
            }
        }

        return result;
    }

    private static int getSumOfNonRedNumbersInArray(JsonArray jsonArray) {
        int result = 0;

        for (JsonElement arrayElement : jsonArray) {
            result += getSumOfAllNonRedNumbers(arrayElement);
        }

        return result;
    }

    private static int getSumOfNonRedNumbersInObject(JsonObject jsonObject) {
        int result = 0;

        Collection<JsonElement> values = jsonObject.asMap().values();
        for (JsonElement value : values) {
            if (value.isJsonPrimitive() && value.getAsString().equals("red")) {
                return 0;
            }

            result += getSumOfAllNonRedNumbers(value);
        }

        return result;
    }
}
