package day6;

import java.util.Arrays;
import java.util.List;

public class Part1 {

    private static int publicInputSolution = 0;

    private static int hiddenInputSolution = 0;

    public static void run(List<String> input, boolean isHidden) {
        if (isHidden)
            setHiddenInputSolution(solveQuadratic(input));
        else
            setPublicInputSolution(solveQuadratic(input));
    }

    private static int solveQuadratic(List<String> input) {
        List<Integer> times = Arrays.stream(input.get(0).split(":"))
                .skip(1)
                .flatMap(s -> Arrays.stream(s.split("\\s+")))
                .filter(s -> !s.trim().isEmpty())
                .map(Integer::parseInt)
                .toList();

        List<Integer> distances = Arrays.stream(input.get(1).split(":"))
                .skip(1)
                .flatMap(s -> Arrays.stream(s.split("\\s+")))
                .filter(s -> !s.trim().isEmpty())
                .map(Integer::parseInt)
                .toList();

        int res = 1;

        for (int i = 0; i < times.size(); i++) {
            int dist = distances.get(i);
            int num = times.get(i);
            // Go from number half until one reachers 0

            double sqrt = Math.sqrt(num * num - 4 * dist);
            double min = (num - sqrt) / 2.0;
            double max = (num + sqrt) / 2.0;

            int minHoldTime = (int) Math.floor(min + 1.0);
            int maxHoldTime = (int) Math.ceil(max - 1.0);
            res *= maxHoldTime - minHoldTime + 1;
        }

        return res;
    }

    private static int solve(List<String> input) {
        List<Integer> times = Arrays.stream(input.get(0).split(":"))
                .skip(1)
                .flatMap(s -> Arrays.stream(s.split("\\s+")))
                .filter(s -> !s.trim().isEmpty())
                .map(Integer::parseInt)
                .toList();

        List<Integer> distances = Arrays.stream(input.get(1).split(":"))
                .skip(1)
                .flatMap(s -> Arrays.stream(s.split("\\s+")))
                .filter(s -> !s.trim().isEmpty())
                .map(Integer::parseInt)
                .toList();

        int res = 1;

        for (int i = 0; i < times.size(); i++) {
            int counter = 0;
            int dist = distances.get(i);
            int num = times.get(i);
            // Go from number half until one reachers 0

            int x = num / 2;
            int y = num / 2 + num % 2;

            while (x * y > dist) {
                counter++;
                x--;
                y++;
            }

            int tempRes = counter * 2;

            if (num % 2 == 0)
                tempRes--;

            res *= tempRes;
        }

        return res;
    }

    public static int getPublicInputSolution() {
        return publicInputSolution;
    }

    public static void setPublicInputSolution(int val) {
        publicInputSolution = val;
    }

    public static int getHiddenInputSolution() {
        return hiddenInputSolution;
    }

    public static void setHiddenInputSolution(int val) {
        hiddenInputSolution = val;
    }

}
