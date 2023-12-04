package day4;

import java.util.*;

public class Part2 {

    private static double publicInputSolution = 0;

    private static double hiddenInputSolution = 0;

    public static void run(List<String> input, boolean isHidden) {
        if (isHidden)
            setHiddenInputSolution(solve(input));
        else
            setPublicInputSolution(solve(input));
    }

    private static double solve(List<String> input) {
        int sum = 0;
        HashMap<Integer, Integer> numMap = new HashMap<>();
        HashMap<Integer, Integer> toDo = new HashMap<>();

        for (String line : input) {
            Set<Integer> winningSet = new HashSet<>();
            Set<Integer> gottenSet = new HashSet<>();
            String[] split = line.split(":");
            String[] h = split[0].split(" ");
            int gameId = Integer.parseInt(h[h.length - 1]);


            String[] cards = split[1].split("\\|");
            String[] winingsOnes = cards[0].trim().split("\\s+");
            String[] gotten = cards[1].trim().split("\\s+");

            Arrays.stream(gotten)
                    .map(Integer::parseInt)
                    .forEach(gottenSet::add);

            Arrays.stream(winingsOnes)
                    .map(Integer::parseInt)
                    .forEach(winningSet::add);

            gottenSet.retainAll(winningSet);

            int numWins = gottenSet.size();

            numMap.putIfAbsent(gameId, 0);
            numMap.put(gameId, 1 + toDo.getOrDefault(gameId, 0));

            for (int i = gameId; i < numWins + gameId; i++) {
                toDo.putIfAbsent(i + 1, 0);
                toDo.put(i + 1, toDo.get(i + 1) + numMap.get(gameId));
            }
            sum += numMap.get(gameId);
        }
        return sum;
    }

    public static double getPublicInputSolution() {
        return publicInputSolution;
    }

    public static void setPublicInputSolution(double val) {
        publicInputSolution = val;
    }

    public static double getHiddenInputSolution() {
        return hiddenInputSolution;
    }

    public static void setHiddenInputSolution(double val) {
        hiddenInputSolution = val;
    }

}
