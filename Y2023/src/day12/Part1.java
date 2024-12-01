package day12;

import java.util.ArrayList;
import java.util.Arrays;
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

    private static boolean isArrValid(String springs, List<Integer> sizes) {
        int springSize = 0;
        List<Integer> blocks = new ArrayList<>();
//        if(springs.equals(".###.##.#.##"))
//            System.out.println("h");
        for (char c : springs.toCharArray()) {
            if (c == '#')
                springSize++;
            else if (c == '.') {
                if (springSize > 0)
                    blocks.add(springSize);
                springSize = 0;
            }
        }

        if (springSize > 0)
            blocks.add(springSize);

        if (sizes.size() != blocks.size())
            return false;

        boolean isValid = IntStream.range(0, Math.min(sizes.size(), blocks.size()))
                .mapToObj(i -> sizes.get(i).equals(blocks.get(i)))
                .allMatch(Boolean::booleanValue);

//        if(isValid)
//            System.out.println(springs);

        return isValid;
    }

    private static int rek(String springs, List<Integer> sizes, int ix) {
        if (ix == springs.length())
            return isArrValid(springs, sizes) ? 1 : 0;

        // Replace ? with possible answers
        if (springs.charAt(ix) == '?') {
            int opt1 = rek(springs.substring(0, ix) + "#" + springs.substring(ix + 1), sizes, ix + 1);
            int opt2 = rek(springs.substring(0, ix) + "." + springs.substring(ix + 1), sizes, ix + 1);
            return opt1 + opt2;
        } else
            return rek(springs, sizes, ix + 1);
    }

    private static int solve(List<String> input) {
        int res = 0;
        for (String line : input) {
            String springs = line.split(" ")[0];
            List<Integer> sizes = Arrays.stream(line.split(" ")[1]
                            .split(","))
                    .map(Integer::parseInt)
                    .toList();

            int h = rek(springs, sizes, 0);
//            System.out.println(springs + " ; " + h);
            res += h;
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
