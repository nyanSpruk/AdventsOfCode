package tests;

import java.util.List;

public class Part1 {

    private static int publicInputSolution = 0;

    private static int hiddenInputSolution = 0;

    public static void solve(List<String> input, boolean isHidden)
    {
        int solution = 0;
        //SOLVE here
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
        if (input.size() != 1)
            return -1;

        if (!input.get(0).equals("-2"))
            return -1;

        return 1;
    }

    private static int solvePublic(List<String> input)
    {
        if (input.size() != 1)
            return -1;

        if (!input.get(0).equals("-1"))
            return -1;

        return 1;
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
