package day13;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part2
{

    private static int publicInputSolution = 0;

    private static int hiddenInputSolution = 0;

    public static void run(List<String> input, boolean isHidden)
    {
        if (isHidden)
            setHiddenInputSolution(solve(input));
        else
            setPublicInputSolution(solve(input));
    }

    private static int solve(List<String> input)
    {
        int sum = 0;
        input.add("");
        List<String> pattren = new ArrayList<>();
        List<String> pattrenVertical = new ArrayList<>();
        for (String line : input)
        {
            if (line.isEmpty())
            {
                pattrenVertical = convertToListOfColumns(pattren);
                int currentLine = 0;

                // Check vertical

                int res = checkMirror(pattrenVertical);
                int res2 = checkMirror(pattren);
                //                if (res2>res){
                //                    res = 100*res2;
                //                }
                if (res > -1)
                {
                    //                                        System.out.println("FOUND VERTICAL " + (res));
                    sum += res;
                }
                else
                {
                    res = checkMirror(pattren);
                    sum += 100 * res;
                    //                                        System.out.println("FOUND HORIZONTAL " + (100* res));
                }
                //                System.out.println("FOUND HHHHH " + (checkMirror(pattrenVertical)));
                //                System.out.println("FOUND HHHHH " + (100* checkMirror(pattren)));

                pattren = new ArrayList<>();
                //                System.out.println("--------");
                continue;
            }

            pattren.add(line);

        }
        return sum;
    }

    private static int checkMirror(List<String> lines)
    {
        boolean isMirror = false;
        int ix = 0;
        int biggestIx = -1;
        int correctCounter = 0;
        boolean foundError = false;
        while (true)
        {

            // go from 0 to ix and  compare lines
            if (isMirror)
            {
                if (correctCounter > biggestIx && foundError)
                    biggestIx = ix;
            }
            correctCounter = 0;
            isMirror = false;
            foundError = false;
            if (ix > lines.size() - 2)
                break;
            int c = 1;
            int min = Math.min(ix + 1, lines.size() - ix - 1);
            for (int i = ix; i >= 0; i--)
            {
                if (ix + c > lines.size() - 1)
                    break;
                String l1 = lines.get(i);
                String l2 = lines.get(ix + c);
                //                System.out.println("First line: " + l1);
                //                System.out.println("Second line: " + l2);
                c++;
                long charDiff = 0;
                if (!l1.equals(l2))
                {
                    StringBuilder resultBuilder = new StringBuilder();

                    charDiff = IntStream.range(0, Math.min(l1.length(), l2.length()))
                            .filter(j -> l1.charAt(j) != l2.charAt(j))
                            .count();

                    if (charDiff == 1)
                    {
                        foundError = true;
                        IntStream.range(0, Math.min(l1.length(), l2.length()))
                                .forEach(h -> {
                                    char char1 = l1.charAt(h);
                                    char char2 = l2.charAt(h);

                                    if (char1 != char2)
                                    {
                                        // If characters are different, swap "#" with "." and vice versa
                                        resultBuilder.append(char1 == '.' ? '#' : '.');
                                    }
                                    else
                                    {
                                        // If characters are the same, keep the original character
                                        resultBuilder.append(char1);
                                    }
                                });
                        String res = resultBuilder.toString();
                        charDiff = 0;
                        //                    System.out.println(l1 + " Result after swapping differing characters: " + res);
                    }
                }

                if (charDiff > 0)
                {
                    break;
                }
                else
                {
                    correctCounter++;
                }
                if (correctCounter == min)
                {
                    isMirror = true;
                    break;
                }
            }

            ix++;
            //            System.out.println();
        }
        return biggestIx;
    }

    public static List<String> convertToListOfColumns(List<String> inputList)
    {
        int maxLength = inputList.stream().mapToInt(String::length).max().orElse(0);

        return IntStream.range(0, maxLength)
                .mapToObj(i -> inputList.stream()
                        .filter(s -> s.length() > i)
                        .map(s -> String.valueOf(s.charAt(i)))
                        .collect(Collectors.joining()))
                .collect(Collectors.toList());
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
