package day1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {

    private static int publicInputSolution = 0;

    private static int hiddenInputSolution = 0;

    public static void solve(List<String> input, boolean isHidden)
    {
        int solution = 0;

        if (isHidden)
        {
            solution = solveHidden(input);
            setHiddenInputSolution(solution);
        }
        else
        {
            solution = solvePublic(input);
            setPublicInputSolution(solution);
        }
    }

    private static int solveHidden(List<String> input)
    {

        int result = 0;
        for (String line : input) {
            String[] numbers = line.replaceAll("[^0-9]", " ").split("\\s");
            List<String> newNum =  new ArrayList<>(Arrays.asList(numbers));
            newNum.removeAll(Arrays.asList("", null));

            int sum = 0;
            sum = 10 * Integer.parseInt(newNum.get(0)) + Integer.parseInt(newNum.get(newNum.size() -1));
            result += sum;

        }
        return result;

    }

    private static int solvePublic(List<String> input)
    {
        int result = 0;
        for (String line : input) {
            String[] numbers = line.replaceAll("[^0-9]", " ").split("\\s");
            List<String> newNum =  new ArrayList<>(Arrays.asList(numbers));
            newNum.removeAll(Arrays.asList("", null));

            int sum = 0;
            sum = 10 * Integer.parseInt(newNum.get(0)) + Integer.parseInt(newNum.get(newNum.size() -1));
            result += sum;

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
