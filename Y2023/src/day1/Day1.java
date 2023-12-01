package day1;

import utils.FileReader;

import java.io.IOException;
import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        try {
            int day = 1;

            List<String> publicInputPart1 = FileReader.readLines("public", day, 1);
            List<String> hiddenInputPart1 = FileReader.readLines("hidden", day, 1);
            List<String> publicInputPart2 = FileReader.readLines("public", day, 2);
            List<String> hiddenInputPart2 = FileReader.readLines("hidden", day, 2);
//            // Part 1
            Part1.solve(publicInputPart1, false);
            System.out.println("Part 1 day " + day + " public result: " + Part1.getPublicInputSolution());

            Part1.solve(hiddenInputPart1, true);
            System.out.println("Part 1 day " + day + " hidden result: " + Part1.getHiddenInputSolution());

            // Part 2
            Part2.run(publicInputPart2, false);
            System.out.println("Part 2 day " + day + " public result: " + Part2.getPublicInputSolution());

            Part2.run(hiddenInputPart2, true);
            System.out.println("Part 2 day " + day + " hidden result: " + Part2.getHiddenInputSolution());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
