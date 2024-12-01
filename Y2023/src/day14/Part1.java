package day14;

import java.util.ArrayList;
import java.util.List;

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

        List<String> lines = rotateList(input);
        return score(lines);
    }

    private static int score(List<String> input) {
        int sum = 0;
        for (String line : input) {
            int scoreVal = line.length();
            int lineScore = 0;
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == 'O') {
                    lineScore += scoreVal;
                    scoreVal--;
                } else if (c == '#') {
                    scoreVal = (line.length() - i - 1);
                }
            }
            sum += lineScore;
        }
        return sum;
    }

    private static List<String> rotateList(List<String> input) {
        List<String> transposedList = new ArrayList<>();

        int numCols = input.get(0).length();

        for (int col = 0; col < numCols; col++) {
            StringBuilder columnBuilder = new StringBuilder();
            for (String s : input) {
                char charAtIndex = s.charAt(col);
                columnBuilder.append(charAtIndex);
            }
            transposedList.add(columnBuilder.toString());
        }

        return transposedList;
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
