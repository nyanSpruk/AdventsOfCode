package day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Part2
{

    private static int publicInputSolution = 0;

    private static int hiddenInputSolution = 0;

    private static HashMap<String, Integer> memo = new HashMap<>();

    public static void run(List<String> input, boolean isHidden)
    {
        if (isHidden)
            setHiddenInputSolution(solve(input));
        else
            setPublicInputSolution(solve(input));
    }

    private static boolean isBigEnough(String str, int size)
    {
        for (int i = 0; i < size; i++)
            if (str.charAt(i) != '?' && str.charAt(i) != '#')
                return false;
        return true;
    }

    private static int rek(String springs, List<Integer> sizes, int ix)
    {
        String key = springs + " " + sizes;
        if (memo.containsKey(key))
            return memo.get(key);
        if (sizes.isEmpty() && springs.isEmpty())
        {
            memo.put(key, 1);
            return 1;
        }

        if (!sizes.isEmpty() && springs.length() < sizes.get(0))
        {
            memo.put(key, 0);
            return 0;
        }
        int sum = 0;
        // Check if current # ? combo is at least of size same as the condition
        if (!sizes.isEmpty())
        {
            if (isBigEnough(springs.substring(0, sizes.get(0)), sizes.get(0)))
            {
                if (springs.length() == sizes.get(0))
                {
                    int size = sizes.get(0);
                    List<Integer> newSizes = sizes.subList(1, sizes.size());
                    sum += rek(springs.substring(size), newSizes, ix);
                }
                else if (springs.charAt(sizes.get(0)) != '#')
                {
                    int size = sizes.get(0);
                    List<Integer> newSizes = sizes.subList(1, sizes.size());
                    sum += rek(springs.substring(size + 1), newSizes, ix);
                }
            }

        }

        if (springs.charAt(0) != '#')
            sum += rek(springs.substring(1), sizes, ix);

        memo.put(key, sum);

        return sum;
    }

    private static int solve(List<String> input) {
        int res = 0;
        for (String line : input) {
            String springs = line.split(" ")[0];
            List<Integer> sizes = Arrays.stream(line.split(" ")[1]
                            .split(","))
                    .map(Integer::parseInt)
                    .toList();

            StringBuilder newSprings = new StringBuilder(springs);
            List<Integer> newSizes = new ArrayList<>(sizes);
            for (int i = 0; i < 4; i++) {
                //                if (springs.charAt(0) == '?' && springs.charAt(springs.length() - 1) == '#') {
                newSprings.append("?").append(springs);
                //                } else {
                //                    newSprings.append(springs);
                //                }
                newSizes.addAll(sizes);
            }

            String newString = newSprings.toString();

            int h = rek(newString, newSizes, 0);

            System.out.println(newString + " ; " + h);
            res += h;
        }

        return res;
    }

    public static int getPublicInputSolution()
    {
        return publicInputSolution;
    }

    public static void setPublicInputSolution(int val)
    {
        publicInputSolution = val;
    }

    public static int getHiddenInputSolution()
    {
        return hiddenInputSolution;
    }

    public static void setHiddenInputSolution(int val)
    {
        hiddenInputSolution = val;
    }

}
