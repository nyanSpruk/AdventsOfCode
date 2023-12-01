package tests;

import utils.FileReader;

import java.io.IOException;
import java.util.List;

public class DayTest {
    public static void main(String[] args) {
        try {
            int day = 0;

            List<String> publicInput = FileReader.readLines("public", day, 1);
            List<String> hiddenInput = FileReader.readLines("hidden", day, 1);

            // Part 1
            Part1.solve(publicInput, false);
            System.out.println("Part 1 day " + day + " public result: " + Part1.getPublicInputSolution());
            assertEqual(1, Part1.getPublicInputSolution(), "General public test");

            Part1.solve(hiddenInput, true);
            System.out.println("Part 1 day " + day + " hidden result: " + Part1.getHiddenInputSolution());
            assertEqual(1, Part1.getHiddenInputSolution(), "General hidden test");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void assertEqual(int expected, int actual, String testName) {
        if (expected == actual) {
            System.out.println(testName + " passed.");
        } else {
            System.err.println(testName + " failed. Expected: " + expected + ", Actual: " + actual);
        }
    }
}
