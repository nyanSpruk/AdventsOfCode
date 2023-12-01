package day_x;

import utils.FileReader;

import java.io.IOException;
import java.util.List;

public class DayTemplate {
    public static void main(String[] args) {
        try {
            int day = 0;

            List<String> publicInput = FileReader.readPublic(day, 0);

            List<String> hiddenInput = FileReader.readHidden(day);

            // Part 1
            Part1.run(publicInput, false);
            System.out.println("Part 1 day " + day + " public result: " + Part1.getPublicInputSolution());

            Part1.run(hiddenInput, true);
            System.out.println("Part 1 day " + day + " hidden result: " + Part1.getHiddenInputSolution());

            // Part 2
            Part2.run(publicInput, false);
            System.out.println("Part 2 day " + day + " public result: " + Part2.getPublicInputSolution());

            Part2.run(hiddenInput, true);
            System.out.println("Part 2 day " + day + " hidden result: " + Part2.getHiddenInputSolution());

        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
