package tests;

import java.util.List;

public class Part2 {

    private static int publicInputSolution = 0;

    private static int hiddenInputSolution = 0;

    public static void solve(List<String> input, boolean isHidden)
    {
        int solution = 0;
        //SOLVE here
        if (isHidden)
            setHiddenInputSolution(solution);
        else
            setPublicInputSolution(solution);
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
