package day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    private static long publicInputSolution = 0;

    private static long hiddenInputSolution = 0;

    public static void run(List<String> input, boolean isHidden) {
        if (isHidden)
            setHiddenInputSolution(solve(input));
        else
            setPublicInputSolution(solve(input));
    }


    private static long solve(List<String> ogInput) {
        List<String> input = new ArrayList<>(ogInput);

        List<Character> instructions = input.get(0).chars().mapToObj(c -> (char) c).toList();
        input.remove(0);
        input.remove(0);
        String pattern = "(\\w+) = \\((\\w+), (\\w+)\\)";
        Pattern regex = Pattern.compile(pattern);

        HashMap<String, Node> nodes = new HashMap<>();

        List<String> startingLines = new ArrayList<>();

        for (String line : input) {
            Matcher matcher = regex.matcher(line);
            if (matcher.matches()) {

                String value = matcher.group(1);
                String left = matcher.group(2);
                String right = matcher.group(3);


                Node n = new Node(value, left, right);
                nodes.putIfAbsent(value, n);

                if (value.charAt(2) == 'A')
                    startingLines.add(value);
            }
        }

        List<Long> finalMoves = new ArrayList<>();
        for (String start : startingLines) {
            Node curr = nodes.get(start);
            int ix = 0;
            int mod = instructions.size();
            int numMoves = 0;
            while (!(curr.value.charAt(2) == 'Z')) {
                int move = ix % mod;
                if (instructions.get(move).equals('R'))
                    curr = nodes.get(curr.right);
                else
                    curr = nodes.get(curr.left);

                numMoves++;
                ix++;
            }
            finalMoves.add((long) numMoves);
        }

        return finalMoves.stream()
                .reduce(finalMoves.get(0), Part2::lcm);
    }

    public static long gcd(long a, long b) {
        if (a < b)
            return gcd(b, a);
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return ((a * b) / gcd(a, b));
    }

    public static long getPublicInputSolution() {
        return publicInputSolution;
    }

    public static void setPublicInputSolution(long val) {
        publicInputSolution = val;
    }

    public static long getHiddenInputSolution() {
        return hiddenInputSolution;
    }

    public static void setHiddenInputSolution(long val) {
        hiddenInputSolution = val;
    }

    static class Node {
        String value;
        String left;
        String right;

        public Node(String val, String left, String right) {
            this.value = val;
            this.left = left;
            this.right = right;
        }
    }

}
