package day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        HashMap<String, Long> map = new HashMap<>();
        long meja = 1000000000;
        for (long j = 1; j <= meja; j++) {
            for (int i = 0; i < 4; i++) {
                roll(input);
                input = rotate(input);
            }
            String key = String.join("", input); // Easiest way to use the key
            // map is to store already seen map. and to see when the cycle starts to repeat
            if (map.containsKey(key)) {
                long cycleSize = j - map.get(key);
                long numberOfCycles = (meja - j) / cycleSize;
                j += numberOfCycles * cycleSize;
            }
            map.put(key, j);
        }
        return score(input);
    }

    private static int score(List<String> input) {
        int res = 0;
        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < input.get(row).length(); col++) {
                if (input.get(row).charAt(col) == 'O')
                    res += input.size() - row;
            }
        }
        return res;
    }

    private static List<String> rotate(List<String> input) {
        List<String> transposedList = new ArrayList<>();

        int numCols = input.get(0).length();

        for (int col = 0; col < numCols; col++) {
            StringBuilder rowBuilder = new StringBuilder();
            for (String s : input) {
                char charAtIndex = s.charAt(col);
                rowBuilder.append(charAtIndex);
            }
            transposedList.add(rowBuilder.reverse().toString());
        }

        return transposedList;
    }

    private static void show(List<String> input) {
        input.forEach(System.out::println);
    }

    private static void roll(List<String> inp) {
        int colL = inp.get(0).length();
        for (int col = 0; col < colL; col++) {
            int rowL = inp.size();
            for (int row = 0; row < rowL; row++) {
                for (int r = 0; r < rowL; r++) {
                    if (inp.get(r).charAt(col) == 'O' && r > 0 && inp.get(r - 1).charAt(col) == '.') {
                        String currentRow = inp.get(r);
                        String previousRow = inp.get(r - 1);

                        String updatedCurrentRow = currentRow.substring(0, col) + '.' + currentRow.substring(col + 1);
                        String updatedPreviousRow = previousRow.substring(0, col) + 'O' + previousRow.substring(col + 1);

                        inp.set(r, updatedCurrentRow);
                        inp.set(r - 1, updatedPreviousRow);
                    }

                }
            }
        }
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
