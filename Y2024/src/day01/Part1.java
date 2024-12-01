package day01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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
        int result = 0;
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        for (String line : input) {
            String[] numbers = line.replaceAll("[\\s+]", " ").split(" ");

            int first = Integer.parseInt(numbers[0]);
            int last = Integer.parseInt(numbers[numbers.length-1]);

//            System.out.println(first);
//            System.out.println(last);

            leftList.add(first);
            rightList.add(last);
        }

        Collections.sort(leftList);
        Collections.sort(rightList);

        result = IntStream.range(0, leftList.size())
                .map(i -> Math.abs(leftList.get(i) - rightList.get(i)))
                .sum();
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
