package day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Part2 {

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
            List<List<Long>> seqs = new ArrayList<>();
            List<Long> numbers = Arrays.stream(historyStr.split(" ")).map(Long::parseLong).toList();

            seqs.add(numbers);
            while (true) {
                List<Long> prev = seqs.get(seqs.size() - 1);
                List<Long> diffs = IntStream.range(1, prev.size())
                        .mapToLong(i -> prev.get(i) - prev.get(i - 1))
                        .boxed()
                        .toList();
                seqs.add(diffs);

                if (diffs.stream().allMatch(el -> el == 0))
                    break;
            }

            List<List<Long>> resList = new ArrayList<>();

            List<Long> prev = new ArrayList<>(seqs.get(seqs.size() - 1));
            prev.add(0L);
            resList.add(prev);
            for (int i = seqs.size() - 2; i >= 0; i--) {
                List<Long> l = new ArrayList<>(seqs.get(i));
                long newVal = l.get(0) - prev.get(0);
                l.add(0, newVal);
                resList.add(l);
                prev = l;
            }

            sum += resList.get(resList.size() - 1).get(0);
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
