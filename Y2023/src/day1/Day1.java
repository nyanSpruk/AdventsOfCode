package day1;

import utils.FileReader;

import java.io.IOException;
import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        try {
            int day = 1;

            List<String> publicInput = FileReader.readLines("public", day);
            List<String> hiddenInput = FileReader.readLines("hidden", day);

            // Part 1
            Part1.solve(publicInput, false);
            System.out.println("Part 1 day " + day + " public result: " + Part1.getPublicInputSolution());

            Part1.solve(hiddenInput, true);
            System.out.println("Part 1 day " + day + " hidden result: " + Part1.getHiddenInputSolution());

            // Part 2
            Part2.solve(publicInput, false);
            System.out.println("Part 2 day " + day + " public result: " + Part2.getPublicInputSolution());

            Part2.solve(hiddenInput, true);
            System.out.println("Part 2 day " + day + " hidden result: " + Part2.getHiddenInputSolution());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
