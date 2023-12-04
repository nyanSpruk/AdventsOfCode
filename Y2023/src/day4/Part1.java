package day4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {

    private static double publicInputSolution = 0;

    private static double hiddenInputSolution = 0;

    public static void run(List<String> input, boolean isHidden) {
        if (isHidden)
            setHiddenInputSolution(solve(input));
        else
            setPublicInputSolution(solve(input));
    }

    private static double solve(List<String> input) {
        double sum = 0;
        for (String line : input) {
            Set<Integer> winningSet = new HashSet<>();
            Set<Integer> gottenSet = new HashSet<>();
            String[] split = line.split(":");

            String[] cards = split[1].split("\\|");
            String[] winingsOnes = cards[0].trim().split("\\s+");
            String[] gotten = cards[1].trim().split("\\s+");

            Arrays.stream(gotten)
                    .map(Integer::parseInt)
                    .forEach(gottenSet::add);

            Arrays.stream(winingsOnes)
                    .map(Integer::parseInt)
                    .forEach(winningSet::add);

            gottenSet.retainAll(winningSet);

            if (!gottenSet.isEmpty())
                sum += Math.pow(2, gottenSet.size() - 1);

        }
        return sum;
    }

    public static double getPublicInputSolution() {
        return publicInputSolution;
    }

    public static void setPublicInputSolution(double val) {
        publicInputSolution = val;
    }

    public static double getHiddenInputSolution() {
        return hiddenInputSolution;
    }

    public static void setHiddenInputSolution(double val) {
        hiddenInputSolution = val;
    }

}
