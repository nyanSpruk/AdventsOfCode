package day9;

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

    private static int solve(List<String> input) {
        int sum = 0;
        for (String historyStr : input) {
            List<List<Integer>> seqs = new ArrayList<>();
            List<Integer> numbers = Arrays.stream(historyStr.split(" ")).map(Integer::parseInt).toList();
            seqs.add(numbers);
            while (true) {
                List<Integer> prev = seqs.get(seqs.size() - 1);
                List<Integer> diffs = IntStream.range(1, prev.size())
                        .map(i -> prev.get(i) - prev.get(i - 1))
                        .boxed()
                        .toList();

                seqs.add(diffs);

                if (diffs.stream().allMatch(el -> el == 0))
                    break;
            }

            List<List<Integer>> resList = new ArrayList<>();

            List<Integer> prev = new ArrayList<>(seqs.get(seqs.size() - 1));
            prev.add(0);
            resList.add(prev);
            for (int i = seqs.size() - 2; i >= 0; i--) {
                List<Integer> l = new ArrayList<>(seqs.get(i));
                int newVal = l.get(l.size() - 1) + prev.get(prev.size() - 1);
                l.add(newVal);
                resList.add(l);
                prev = l;
            }

            var h = resList.get(resList.size() - 1);
            sum += h.get(h.size() - 1);
        }
        return sum;
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
