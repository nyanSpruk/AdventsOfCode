package day6;

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

    private static int solveQuadratic(List<String> input) {
        long time = Arrays.stream(input.get(0).split(":"))
                .skip(1)
                .map(s -> s.replaceAll("\\s+", ""))
                .filter(s -> !s.trim().isEmpty())
                .map(Long::parseLong)
                .findFirst()
                .orElseThrow();


        long distance = Arrays.stream(input.get(1).split(":"))
                .skip(1)
                .map(s -> s.replaceAll("\\s+", ""))
                .filter(s -> !s.trim().isEmpty())
                .map(Long::parseLong)
                .findFirst()
                .orElseThrow();

        int res = 1;

        double sqrt = Math.sqrt(time * time - 4 * distance);
        double min = (time - sqrt) / 2.0;
        double max = (time + sqrt) / 2.0;

        int minHoldTime = (int) Math.floor(min + 1.0);
        int maxHoldTime = (int) Math.ceil(max - 1.0);
        res *= maxHoldTime - minHoldTime + 1;


        return res;
    }

    private static long solve(List<String> input) {
        long time = Arrays.stream(input.get(0).split(":"))
                .skip(1)
                .map(s -> s.replaceAll("\\s+", ""))
                .filter(s -> !s.trim().isEmpty())
                .map(Long::parseLong)
                .findFirst()
                .orElseThrow();


        long distance = Arrays.stream(input.get(1).split(":"))
                .skip(1)
                .map(s -> s.replaceAll("\\s+", ""))
                .filter(s -> !s.trim().isEmpty())
                .map(Long::parseLong)
                .findFirst()
                .orElseThrow();

        long res = 0;

        long x = time / 2;
        long y = time / 2 + time % 2;

        while (x * y > distance) {
            res++;
            x--;
            y++;
        }

        long tempRes = res * 2;

        if (time % 2 == 0) {
            tempRes--;
        }


        return tempRes;
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
