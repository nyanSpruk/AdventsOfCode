package day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class Part2 {

    private static int publicInputSolution = 0;

    private static int hiddenInputSolution = 0;
    private static HashMap<Triple, Integer> memo = new HashMap<>();

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

        if (isValid)
            System.out.println(springs);

        return isValid;
    }

    private static int rek(String springs, List<Integer> sizes, int ix, int wordIx, int size) {
        Triple key = new Triple(size, ix, wordIx);
        if (memo.containsKey(key))
            return memo.get(key);
        if (ix == springs.length())
            return isArrValid(springs, sizes) ? 1 : 0;

        // Replace ? with possible answers
        int sum = 0;
        if (springs.charAt(ix) == '?') {
            int opt1 = rek(springs.substring(0, ix) + "#" + springs.substring(ix + 1), sizes, ix + 1, wordIx, size + 1);
            int opt2 = rek(springs.substring(0, ix) + "." + springs.substring(ix + 1), sizes, ix + 1, wordIx + 1, 0);
            sum = opt2 + opt1;
        } else {
            sum = rek(springs, sizes, ix + 1, wordIx, 0);
        }
        memo.put(key, sum);
        return sum;
    }

    private static int solve(List<String> input) {
        int res = 0;
        for (String line : input) {
            String springs = line.split(" ")[0];
            List<Integer> sizes = Arrays.stream(line.split(" ")[1]
                            .split(","))
                    .map(Integer::parseInt)
                    .toList();

            StringBuilder newSprings = new StringBuilder(springs);
            List<Integer> newSizes = new ArrayList<>(sizes);
            for (int i = 0; i < 4; i++) {
                if (springs.charAt(0) == '?' && springs.charAt(springs.length() - 1) == '#') {
                    newSprings.append("#").append(springs);
                } else {
                    newSprings.append(springs);
                }
                newSizes.addAll(sizes);
            }

            String newString = newSprings.toString();

            int h = rek(newString, sizes, 0, 0, 0);

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

    private static class Triple {
        int size;
        int ix;
        int jx;

        public Triple(int size, int ix, int jx) {
            this.size = size;
            this.ix = ix;
            this.jx = jx;
        }
    }

}
