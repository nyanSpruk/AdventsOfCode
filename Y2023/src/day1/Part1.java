package day1;

import java.util.List;

public class Part1 {

    private static int publicInputSolution = 0;

    private static int hiddenInputSolution = 0;

    public static void run(List<String> input, boolean isHidden)
    {
        if (isHidden)
            setHiddenInputSolution(solve(input));
        else
            setPublicInputSolution(solve(input));
    }

    private static int solve(List<String> input)
    {
        int result = 0;
        for (String line : input) {
            String numbers = line.replaceAll("[^0-9]", "");

            int first = Integer.parseInt(String.valueOf(numbers.charAt(0)));
            int last = Integer.parseInt(String.valueOf(numbers.charAt(numbers.length()-1)));

            result += 10 * first + last;

        }
        return result;

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
