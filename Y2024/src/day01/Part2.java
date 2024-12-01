package day01;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part2 {

    private static int publicInputSolution = 0;

    private static int hiddenInputSolution = 0;

    public static void run(List<String> input, boolean isHidden)
    {
        if (isHidden)
            setHiddenInputSolution(solve(input));
        else
            setPublicInputSolution(solve(input));
    }

    private static int solve(List<String> input) {
        int result = 0;
        HashMap<Integer, Integer> leftList = new HashMap<>();
        HashMap<Integer, Integer> rightList = new HashMap<>();

        for (String line : input) {
            String[] numbers = line.replaceAll("[\\s+]", " ").split(" ");

            int first = Integer.parseInt(numbers[0]);
            int last = Integer.parseInt(numbers[numbers.length-1]);

            if(leftList.containsKey(first))
            {
                leftList.put(first, leftList.get(first)+1);
            }else{
                leftList.put(first, 1);
            }

            if(rightList.containsKey(last))
            {
                rightList.put(last, rightList.get(last)+1);
            }else{
                rightList.put(last, 1);
            }

        }

        result = leftList.entrySet().stream()
                .filter(item -> rightList.containsKey(item.getKey()))
                .mapToInt(e -> e.getValue() * e.getKey() * rightList.get(e.getKey()))
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
