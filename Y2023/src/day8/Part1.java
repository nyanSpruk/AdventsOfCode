package day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static int publicInputSolution = 0;

    private static int hiddenInputSolution = 0;

    public static void run(List<String> input, boolean isHidden)
    {
        if (isHidden)
            setHiddenInputSolution(solve(input));
        else
            setPublicInputSolution(solve(input));
    }


    private static int solve(List<String> ogInput) {
        List<String> input = new ArrayList<>(ogInput);

        List<Character> instructions = input.get(0).chars().mapToObj(c -> (char) c).toList();
        input.remove(0);
        input.remove(0);
        String pattern = "(\\w+) = \\((\\w+), (\\w+)\\)";
        Pattern regex = Pattern.compile(pattern);

        HashMap<String, Node> nodes = new HashMap<>();

        for (String line : input) {
            Matcher matcher = regex.matcher(line);
            if (matcher.matches()) {

                String value = matcher.group(1);
                String left = matcher.group(2);
                String right = matcher.group(3);

                Node n = new Node(value, left, right);
                nodes.putIfAbsent(value, n);
            }
        }

        Node curr = nodes.get("AAA");
        int ix = 0;
        int mod = instructions.size();
        int numMoves = 0;
        while (!curr.value.equals("ZZZ")) {
            int move = ix % mod;
            if (instructions.get(move).equals('R'))
                curr = nodes.get(curr.right);
            else
                curr = nodes.get(curr.left);
            numMoves++;
            ix++;
        }
        return numMoves;
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
