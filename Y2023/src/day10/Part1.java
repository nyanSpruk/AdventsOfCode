package day10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Part1 {

    private static int publicInputSolution = 0;

    private static int hiddenInputSolution = 0;

    public static void run(List<String> input, boolean isHidden) {
        if (isHidden)
            setHiddenInputSolution(solve(input));
        else
            setPublicInputSolution(solve(input));
    }

    static List<String> directions = new ArrayList<>();

    private static int solve(List<String> input) {
        Coords start = IntStream.range(0, input.size())
                .boxed()
                .flatMap(listIndex ->
                        IntStream.range(0, input.get(listIndex).length())
                                .filter(charPos -> input.get(listIndex).charAt(charPos) == 'S')
                                .mapToObj(charPos -> new Coords(charPos, listIndex))
                )
                .findFirst().orElse(null);

        directions.add("|7FS");
        directions.add("-7JS");
        directions.add("|LJS");
        directions.add("-FLS");

        int counter = 0;
        Coords prev = new Coords(-1, -1);
        while (input.get(start.y).charAt(start.x) != 'S' || counter <= 0) {
            // Go through all 4 sides and check
            // From the top the valid Chars are: | 7 F
            // From the right the valid chars are: - 7 J
            // From the bot the valid chars are: | L J
            // From the left the valid chars are: - F L

            // if prev | L J and going up allowed are: | 7 F
            // if prev - L F and going right are: - 7 J
            // if prev | 7 F and going down are: | L J
            // if prev - 7 J and going left are: - L F

            // Check top that bounds are ok, that char is valid and that we didnt come from there
            if (start.y > 0 && isValidChar(2, start, input) && isValidChar(0, new Coords(start.x, start.y - 1), input) && areCoordsDiff(new Coords(start.x, start.y - 1), prev)) {
                prev = new Coords(start);
                start.y -= 1;
            }
            // Check right
            else if (start.x < input.get(0).length() - 1 && isValidChar(3, start, input) && isValidChar(1, new Coords(start.x + 1, start.y), input) && areCoordsDiff(new Coords(start.x + 1, start.y), prev)) {
                prev = new Coords(start);
                start.x += 1;

            }
            // Check bottom
            else if (start.y < input.size() - 1 && isValidChar(0, start, input) && isValidChar(2, new Coords(start.x, start.y + 1), input) && areCoordsDiff(new Coords(start.x, start.y + 1), prev)) {
                prev = new Coords(start);
                start.y += 1;
            }
            // Check left
            else if (start.x > 0 && isValidChar(1, start, input) && isValidChar(3, new Coords(start.x - 1, start.y), input) && areCoordsDiff(new Coords(start.x - 1, start.y), prev)) {
                prev = new Coords(start);
                start.x -= 1;
            }
            counter++;
        }

        return counter / 2;
    }

    private static boolean areCoordsDiff(Coords c1, Coords c2) {
        return c1.x != c2.x || c1.y != c2.y;
    }

    private static boolean isValidChar(int dir, Coords coords, List<String> inp) {
        String c = String.valueOf(inp.get(coords.y).charAt(coords.x));
        return directions.get(dir).contains(c);
    }

    static class Coords {
        int x;
        int y;

        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coords(Coords c) {
            this.x = c.x;
            this.y = c.y;
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
