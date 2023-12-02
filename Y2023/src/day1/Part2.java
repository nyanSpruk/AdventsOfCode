package day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    private static int publicInputSolution = 0;

    private static int hiddenInputSolution = 0;

    private static final Map<String, Integer> numberMap =
            Map.of("one", 1, "two", 2, "three", 3, "four", 4, "five", 5,
            "six", 6, "seven", 7, "eight", 8, "nine", 9);

    public static void run(List<String> input, boolean isHidden)
    {
        if (isHidden)
            setHiddenInputSolution(solve(input));
        else
            setPublicInputSolution(solve(input));
    }

    private static int solve(List<String> input)
    {
        int result = 0;
        for (String line : input)
        {
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < line.length(); i++)
            {
                char c = line.charAt(i);
                if ( '1' <= c && c <= '9')
                    nums.add(c - '0');
                else
                {
                    String subS = line.substring(i);
                    Pattern pattern = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine)", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(subS);

                    if (matcher.find()) {
                        String match = matcher.group();
                        int index = subS.indexOf(match);
                        if (match.charAt(0) == subS.charAt(0) && index ==0)
                        {
                            nums.add(numberMap.get(match));
                        }
                    }
                }
            }
            result += nums.get(0) * 10 + nums.get(nums.size()-1);
        }
        return result;
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
