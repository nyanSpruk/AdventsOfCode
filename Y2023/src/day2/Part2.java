package day2;

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
        int sum = 0;

        for (String line : input) {
            int bigestBlue = 0;
            int biggestRed = 0;
            int biggestGreen = 0;

            // Split by :
            String[] split = line.split(":");

            String[] stringSets = split[1].split("[;,]");
            for (String set : stringSets) {
                set = set.substring(1);
                String color = set.split(" ")[1];
                int number = Integer.parseInt(set.split(" ")[0]);
                switch (color) {
                    case "blue":
                        if (number > bigestBlue) bigestBlue = number;
                        break;
                    case "red":
                        if (number > biggestRed) biggestRed = number;
                        break;
                    case "green":
                        if (number > biggestGreen) biggestGreen = number;
                        break;
                    default:
                        break;
                }
            }
            sum += biggestGreen * biggestRed * bigestBlue;
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
