package day10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Part2 {

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
        Coords curr = start;
        Coords prev = new Coords(-1, -1);
        HashSet<Coords> insideLoopSet = new HashSet<>();
        int firstMove = -1;
        int lastMove = -1;
        while (input.get(curr.y).charAt(curr.x) != 'S' || counter <= 0) {
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
            insideLoopSet.add(curr);
            if (curr.y > 0 && isValidChar(2, curr, input) && isValidChar(0, new Coords(curr.x, curr.y - 1), input) && areCoordsDiff(new Coords(curr.x, curr.y - 1), prev)) {
                prev = new Coords(curr);
                curr = new Coords(curr.x, curr.y - 1);
                if (firstMove == -1)
                    firstMove = 2;
                lastMove = 2;
            }
            // Check right
            else if (curr.x < input.get(0).length() - 1 && isValidChar(3, curr, input) && isValidChar(1, new Coords(curr.x + 1, curr.y), input) && areCoordsDiff(new Coords(curr.x + 1, curr.y), prev)) {
                prev = new Coords(curr);
                curr = new Coords(curr.x + 1, curr.y);
                if (firstMove == -1)
                    firstMove = 3;
                lastMove = 3;
            }
            // Check bottom
            else if (curr.y < input.size() - 1 && isValidChar(0, curr, input) && isValidChar(2, new Coords(curr.x, curr.y + 1), input) && areCoordsDiff(new Coords(curr.x, curr.y + 1), prev)) {
                prev = new Coords(curr);
                curr = new Coords(curr.x, curr.y + 1);
                if (firstMove == -1)
                    firstMove = 0;
                lastMove = 0;
            }
            // Check left
            else if (curr.x > 0 && isValidChar(1, curr, input) && isValidChar(3, new Coords(curr.x - 1, curr.y), input) && areCoordsDiff(new Coords(curr.x - 1, curr.y), prev)) {
                prev = new Coords(curr);
                curr = new Coords(curr.x - 1, curr.y);
                if (firstMove == -1)
                    firstMove = 1;
                lastMove = 1;
            }
            counter++;
        }

        // Loop through the lines char by char
        boolean inLoop = false;
        char replacement = ' ';
        if (firstMove == 3 && lastMove == 2)
            replacement = 'F';
        else if (firstMove == 2 && lastMove == 1)
            replacement = 'L';
        else if (firstMove == 2 && lastMove == 3)
            replacement = 'J';
        else if (firstMove == 0 && lastMove == 3)
            replacement = '7';
        else if (firstMove == 2 && lastMove == 2)
            replacement = '|';


        String s = input.get(start.y).replace('S', replacement);
//        System.out.println("REPLACEMENT: " + replacement);

        int ix = start.y;
        input.set(ix, s);

        int count = 0;
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                char c = input.get(y).charAt(x);
                if (insideLoopSet.contains(new Coords(x, y)) && (c == '|' || c == 'L' || c == 'J')) {
                    inLoop = !inLoop;
                    continue;
                }

                // Check if char at x, y is in hashset
                if (inLoop && !insideLoopSet.contains(new Coords(x, y))) {
//                    System.out.println("X: " + x + " Y: " + y);
                    count++;
                }
            }
        }

        return count;
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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Coords coord = (Coords) obj;
            return x == coord.x && y == coord.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
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
