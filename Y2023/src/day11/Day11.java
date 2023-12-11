package day11;

import java.io.IOException;
import java.util.List;

import utils.FileReader;

public class Day11 {
    public static void main(String[] args) {
        try {
            int day = 11;

            List<String> publicInput1 = FileReader.readPublic(day, 1);
            List<String> publicInput2 = FileReader.readPublic(day, 2);

            List<String> hiddenInput = FileReader.readHidden(day);

            // Part 1
            Part1.run(publicInput1, false, 2);
            System.out.println("Part 1 day " + day + " public result: " + Part1.getPublicInputSolution());

            Part1.run(hiddenInput, true, 2);
            System.out.println("Part 1 day " + day + " hidden result: " + Part1.getHiddenInputSolution());

            // Part 2
            Part1.run(publicInput2, false, 1000000);
            System.out.println("Part 2 day " + day + " public result: " + Part1.getPublicInputSolution());

            Part1.run(hiddenInput, true, 1000000);
            System.out.println("Part 2 day " + day + " hidden result: " + Part1.getHiddenInputSolution());

        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
