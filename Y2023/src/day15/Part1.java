package day15;

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
        String h = input.get(0).trim();
        List<String> parts = Arrays.stream(h.split(",")).toList();
        int res = 0;
        for (String part : parts)
        {
            int temp = 0;
            for (char c : part.toCharArray())
            {
                temp += c;
                temp *= 17;
                temp %= 256;
            }
            res += temp;
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
