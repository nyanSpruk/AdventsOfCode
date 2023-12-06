package day6;

import java.util.Arrays;
import java.util.List;

public class Part1 {

    private static int publicInputSolution = 0;

    private static int hiddenInputSolution = 0;

    public static void run(List<String> input, boolean isHidden) {
        if (isHidden)
            setHiddenInputSolution(solve(input));
        else
            setPublicInputSolution(solve(input));
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
//                System.out.println("X " + x + " Y " + y + "m: " + (x*y) + " dist " + dist);
                counter++;
                x--;
                y++;
            }

            int tempRes = counter * 2;

            if (num % 2 == 0) {
                tempRes--;
            }

//            System.out.println("COUNT " + tempRes);
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
