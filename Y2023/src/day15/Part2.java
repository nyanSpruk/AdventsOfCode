package day15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String h = input.get(0).trim();
        List<String> parts = Arrays.stream(h.split(",")).toList();
        int res = 0;
        HashMap<Integer, List<Tuple>> map = new HashMap<>();
        for (String part : parts)
        {
            char op = part.charAt(part.length() - 2);
            int lens = -1;
            String data;
            if (op == '=')
            {
                lens = Integer.parseInt(String.valueOf(part.charAt(part.length() - 1)));
                data = part.substring(0, part.length() - 2);
            }
            else
            {
                data = part.substring(0, part.length() - 1);
            }
            int temp = 0;
            for (char c : data.toCharArray())
            {
                temp += c;
                temp *= 17;
                temp %= 256;
            }

            if (op == '=')
            {
                boolean contains = false;
                Tuple n = new Tuple(data, lens);
                if (!map.containsKey(temp))
                {
                    map.put(temp, new ArrayList<>());
                    map.get(temp).add(n);
                    continue;
                }

                for (int i = 0; i < map.get(temp).size(); i++)
                {
                    Tuple tup = map.get(temp).get(i);
                    if (tup.str.equals(data))
                    {
                        map.get(temp).set(i, n);
                        contains = true;
                        break;
                    }
                }
                if (!contains)
                {
                    map.get(temp).add(n);
                }
            }
            else
            {
                if (map.containsKey(temp))
                {
                    for (int i = 0; i < map.get(temp).size(); i++)
                    {
                        Tuple tup = map.get(temp).get(i);
                        if (tup.str.equals(data))
                        {
                            map.get(temp).remove(i);
                            break;
                        }
                    }
                }
            }
        }

        for (Map.Entry<Integer, List<Tuple>> e : map.entrySet())
        {
            int val = e.getKey() + 1;
            List<Tuple> t = e.getValue();
            for (int i = 0; i < t.size(); i++)
                res += val * (i + 1) * t.get(i).val;
        }

        return res;
    }

    public static class Tuple
    {
        String str;
        int val;

        public Tuple(String str, int val)
        {
            this.str = str;
            this.val = val;
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
