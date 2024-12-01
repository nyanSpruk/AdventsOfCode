package day11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {

    private static long publicInputSolution = 0;

    private static long hiddenInputSolution = 0;

    public static void run(List<String> input, boolean isHidden, int size)
    {
        if (isHidden)
            setHiddenInputSolution(solve(input, size));
        else
            setPublicInputSolution(solve(input, size));
    }

    private static long solve(List<String> input, int size)
    {
        List<Integer> rows = new ArrayList<>();

        for (int i = 0; i < input.size(); i++)
            if (isAllDots(input.get(i)))
                rows.add(i);

        List<Integer> indexes = new ArrayList<>();
        for (int j = 0; j < input.get(0).length(); j++)
        {
            int finalJ = j;
            String column = input.stream()
                    .map(row -> String.valueOf(row.charAt(finalJ)))
                    .collect(Collectors.joining());

            if (isAllDots(column))
                indexes.add(j);
        }

        List<Point> points = new ArrayList<>();
        for (int r = 0; r < input.size(); r++)
        {
            String row = input.get(r);
            for (int c = 0; c < row.length(); c++)
            {
                if (row.charAt(c) == '#')
                {
                    int count = 0;
                    for (int rowI : rows)
                        if (rowI < r)
                            count += size - 1;

                    int count2 = 0;
                    for (int colI : indexes)
                        if (colI < c)
                            count2 += size - 1;

                    points.add(new Point(c + count2, r + count));
                }
            }
        }
        long sum = 0;

        for (int i = 0; i < points.size(); i++)
        {
            for (int j = i + 1; j < points.size(); j++)
            {
                Point point1 = points.get(i);
                Point point2 = points.get(j);
                int distance = calculateManhattanDistance(point1, point2);
                sum += distance;
                //                System.out.println("Shortest distance between #" + (i + 1) + " and #" + (j + 1) + ": " + (distance));
            }
        }
        return sum;
    }

    private static int calculateManhattanDistance(Point point1, Point point2)
    {
        return Math.abs(point1.x - point2.x) + Math.abs(point1.y - point2.y);
    }

    private static boolean isAllDots(String str)
    {
        return str.matches("\\.+");
    }

    public static long getPublicInputSolution()
    {
        return publicInputSolution;
    }

    public static void setPublicInputSolution(long val)
    {
        publicInputSolution = val;
    }

    public static long getHiddenInputSolution()
    {
        return hiddenInputSolution;
    }

    public static void setHiddenInputSolution(long val)
    {
        hiddenInputSolution = val;
    }

    static class Point
    {
        int x;
        int y;

        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

}
