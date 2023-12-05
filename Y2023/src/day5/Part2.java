package day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {

    private static long publicInputSolution = 0;

    private static long hiddenInputSolution = 0;

    public static void run(List<String> input, boolean isHidden) {
        if (isHidden)
            setHiddenInputSolution(solve(input));
        else
            setPublicInputSolution(solve(input));
    }

    private static long solve(List<String> ogInput) {
        // NOV NACIN:
        // Go through data
        // Izracunas vse ranges
        // Vzames cifro in jo posles cez vse
        // Na konc pogledas ce je manjsa od currentManjse
        List<String> input = new ArrayList<>(ogInput);

        long smallest = 1000000000L;

        // Get initial seeds
        String numbersStr = input.get(0).split(":")[1];
        List<Long> initialNumbers = Arrays.stream(numbersStr.split("\\s+"))
                .filter(s -> !s.trim().isEmpty())
                .map(Long::parseLong)
                .toList();

        input.remove(0);

        List<List<List<Long>>> maps = new ArrayList<>();

        List<List<Long>> temp = new ArrayList<>();
        for (String line : input) {
            if (line.isEmpty())
                continue;
            if (line.contains("map")) {
                if (!temp.isEmpty())
                    maps.add(temp);
                temp = new ArrayList<>();
            } else {
                List<Long> bounds = Arrays.stream(line.split("\\s+"))
                        .filter(s -> !s.trim().isEmpty())
                        .map(Long::parseLong)
                        .toList();

                // Need LowerBound, HigherBound, NumToAdd
                long upperBound = bounds.get(1);
                bounds = Arrays.asList(upperBound, upperBound + bounds.get(2), bounds.get(0) - upperBound);

                temp.add(bounds);
            }
        }
        maps.add(temp);

        // Go through the numbers
        for (int i = 0; i < initialNumbers.size(); i += 2) {
            for (long j = initialNumbers.get(i); j <= initialNumbers.get(i) + initialNumbers.get(i + 1); j++) {
                long num = j;
                for (List<List<Long>> map : maps) {
                    for (List<Long> bound : map) {
                        if (num >= bound.get(0) && num < bound.get(1)) {
                            num += bound.get(2);
                            break;
                        }
                    }
                }
                if (num < smallest)
                    smallest = num;
            }
        }

        return smallest;
    }

    public static long getPublicInputSolution() {
        return publicInputSolution;
    }

    public static void setPublicInputSolution(long val) {
        publicInputSolution = val;
    }

    public static long getHiddenInputSolution() {
        return hiddenInputSolution;
    }

    public static void setHiddenInputSolution(long val) {
        hiddenInputSolution = val;
    }

}
