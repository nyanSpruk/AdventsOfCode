package day2;

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
        int sum = 0;

        for (String line : input) {
            // Split by :
            String[] split = line.split(":");
            int gameId = Integer.parseInt(split[0].split(" ")[1]);

            String[] stringSets = split[1].split("[;,]");
            boolean saveNum = true;
            for (String set : stringSets) {
                set = set.substring(1);
                String color = set.split(" ")[1];
                int number = Integer.parseInt(set.split(" ")[0]);
                switch (color) {
                    case "blue":
                        if (number > 14) saveNum = false;
                        break;
                    case "red":
                        if (number > 12) saveNum = false;
                        break;
                    case "green":
                        if (number > 13) saveNum = false;
                        break;
                    default:
                        break;
                }
            }
            sum += saveNum ? gameId : 0;
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
