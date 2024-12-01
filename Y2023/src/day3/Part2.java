package day3;

import java.util.ArrayList;
import java.util.HashMap;
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
        HashMap<String, Integer> numbers = new HashMap<>();

        for (int row = 0; row < input.size(); row++) {
            String line = input.get(row);
            boolean isNum = false;
            String id = "";
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (Character.isDigit(c)) {
                    int number = Character.getNumericValue(c);
                    id = isNum ? id : row + "," + i;
                    numbers.put(id, isNum ? numbers.get(id) * 10 + number : number);
                    isNum = true;
                } else {
                    id = "";
                    isNum = false;
                }
            }
        }


        HashMap<String, List<Integer>> gears = new HashMap<>();
        // Loop through each number and then check around if there is a symbol
        for (String numCoords : numbers.keySet()) {
            int j = Integer.parseInt(numCoords.split(",")[0]);
            int x_int = Integer.parseInt(numCoords.split(",")[1]);
            int numberLen = numbers.get(numCoords).toString().length();

            int rows = input.size();
            int cols = input.get(0).length();
            boolean numFound = false;

            for (int i = x_int; i < x_int + numberLen && !numFound; i++) {
                for (int x = Math.max(0, i - 1); x < Math.min(rows, i + 2) && !numFound; x++) {
                    for (int y = Math.max(0, j - 1); y < Math.min(cols, j + 2) && !numFound; y++) {
                        if (!(x == i && y == j) && input.get(y).charAt(x) == '*') {
                            String id = y + "," + x;
                            gears.putIfAbsent(id, new ArrayList<>());
                            gears.get(id).add(numbers.get(numCoords));
                            numFound = true;
                        }
                    }
                }
            }
        }

        return gears.values().stream()
                .filter(x -> x.size() == 2)
                .mapToInt(l -> l.get(0) * l.get(1))
                .sum();
    }

    private static int oldSolve(List<String> input) {
        int sum = 0;
        HashMap<String, Integer> numbers = new HashMap<>();
        List<String> symbolsPos = new ArrayList<>();

        for (int row = 0; row < input.size(); row++) {
            String line = input.get(row);
            boolean isNum = false;
            String currentId = "";
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                    int number = line.charAt(i) - '0';
                    if (!isNum) {
                        isNum = true;
                        currentId = row + "," + i;
                        numbers.put(currentId, number);
                    } else {
                        numbers.put(currentId, numbers.get(currentId) * 10 + number);
                    }
                } else if (line.charAt(i) == '.') {
                    currentId = "";
                    isNum = false;
                } else {
                    symbolsPos.add(row + "," + i);
                    currentId = "";
                    isNum = false;
                }
            }
        }


        HashMap<String, List<Integer>> gears = new HashMap<>();
        // Loop through each number and then check around if there is a symbol
        for (String numCoords : numbers.keySet()) {
            int y = Integer.parseInt(numCoords.split(",")[0]);
            int x = Integer.parseInt(numCoords.split(",")[1]);
            int numberLen = numbers.get(numCoords).toString().length();


            // Check for borders, or number or dot. But if anything else then count the number and countinue
            for (int i = x; i < x + numberLen; i++) {
                if (i < 0 || i > input.get(0).length())
                    continue;

                String id = "";
                // Check up
                if (y - 1 >= 0 && input.get(y - 1).charAt(i) == '*') {
                    id = (y - 1) + "," + (i);
                    gears.putIfAbsent(id, new ArrayList<>());
                    gears.get(id).add(numbers.get(numCoords));

                    break;
                }
                // Check down
                if (y + 1 < input.size() && input.get(y + 1).charAt(i) == '*') {
                    id = (y + 1) + "," + (i);
                    gears.putIfAbsent(id, new ArrayList<>());
                    gears.get(id).add(numbers.get(numCoords));
                    break;
                }
                // Check left
                if (i - 1 >= 0 && input.get(y).charAt(i - 1) == '*') {
                    id = (y) + "," + (i - 1);
                    gears.putIfAbsent(id, new ArrayList<>());
                    gears.get(id).add(numbers.get(numCoords));
                    break;
                }
                // Check right
                if (i + 1 < input.get(y).length() && input.get(y).charAt(i + 1) == '*') {
                    id = (y) + "," + (i + 1);
                    gears.putIfAbsent(id, new ArrayList<>());
                    gears.get(id).add(numbers.get(numCoords));
                    break;
                }
                // Check diagonally up letf
                if (y - 1 >= 0 && i - 1 >= 0 && input.get(y - 1).charAt(i - 1) == '*') {
                    id = (y - 1) + "," + (i - 1);
                    gears.putIfAbsent(id, new ArrayList<>());
                    gears.get(id).add(numbers.get(numCoords));
                    break;
                }

                // Check diagonally up right
                if (y - 1 >= 0 && i + 1 < input.get(y).length() && input.get(y - 1).charAt(i + 1) == '*') {
                    id = (y - 1) + "," + (i + 1);
                    gears.putIfAbsent(id, new ArrayList<>());
                    gears.get(id).add(numbers.get(numCoords));
                    break;
                }

                // Check diagonally down letf
                if (y + 1 < input.size() && i - 1 >= 0 && input.get(y + 1).charAt(i - 1) == '*') {
                    id = (y + 1) + "," + (i - 1);
                    gears.putIfAbsent(id, new ArrayList<>());
                    gears.get(id).add(numbers.get(numCoords));
                    break;
                }

                // Check diagonally up right
                if (y + 1 < input.size() && i + 1 < input.get(y).length() && input.get(y + 1).charAt(i + 1) == '*') {
                    id = (y + 1) + "," + (i + 1);
                    gears.putIfAbsent(id, new ArrayList<>());
                    gears.get(id).add(numbers.get(numCoords));
                    break;
                }
            }

        }
        for (List<Integer> l : gears.values())
            if (l.size() == 2)
                sum += l.get(0) * l.get(1);

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
