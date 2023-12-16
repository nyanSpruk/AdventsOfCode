package day16;

import java.util.*;

public class Part1 {

    private static int publicInputSolution = 0;

    private static int hiddenInputSolution = 0;

    public static void run(List<String> input, boolean isHidden) {
        if (isHidden)
            setHiddenInputSolution(solve(input));
        else
            setPublicInputSolution(solve(input));
    }

    private static int func(List<String> input) {
        List<String> energized = new ArrayList<>(input);

        // 0 up 1 right 2 down 3 left
        Coords start = new Coords(0, 0, 1);
        HashSet<Coords> seen = new HashSet<>();
        HashSet<Coords> seen2 = new HashSet<>();
        Stack<Coords> posToDo = new Stack<>();
        posToDo.add(start);

//        List<Coords> temp = new ArrayList<>(posToDo);

        while (true) {
//            posToDo = new ArrayList<>();
            List<Coords> newPos = new ArrayList<>();
            if (posToDo.isEmpty())
                break;

            for (Coords cord : posToDo) {
                if (cord.x < 0 || cord.x >= input.get(0).length() || cord.y < 0 || cord.y >= input.size())
                    continue;

                seen.add(cord);
                if (seen2.contains(cord))
                    continue;

                seen2.add(cord);

                // Cover the next move
                char c = input.get(cord.y).charAt(cord.x);
                int dir = cord.dir;

                String temps = energized.get(cord.y);
                temps = temps.substring(0, cord.x) + '#' + temps.substring(cord.x + 1);
                energized.set(cord.y, temps);
//                energized.forEach(System.out::println);
//                System.out.println();
                if (c == '.' || ((dir == 1 || dir == 3) && c == '-') || ((dir == 0 || dir == 2) && c == '|')) {
                    switch (dir) {
                        case 0 -> newPos.add(new Coords(cord.x, cord.y - 1, 0));
                        case 1 -> newPos.add(new Coords(cord.x + 1, cord.y, 1));
                        case 2 -> newPos.add(new Coords(cord.x, cord.y + 1, 2));
                        case 3 -> newPos.add(new Coords(cord.x - 1, cord.y, 3));
                    }
                } else if (c == '|' && (dir == 1 || dir == 3)) {
                    newPos.add(new Coords(cord.x, cord.y - 1, 0));
                    newPos.add(new Coords(cord.x, cord.y + 1, 2));
                } else if (c == '-' && (dir == 2 || dir == 0)) {
                    newPos.add(new Coords(cord.x + 1, cord.y, 1));
                    newPos.add(new Coords(cord.x - 1, cord.y, 3));
                } else if ((c == '/' && dir == 3) || (c == '\\' && dir == 1)) {
                    newPos.add(new Coords(cord.x, cord.y + 1, 2));
                } else if ((c == '/' && dir == 1) || (c == '\\' && dir == 3)) {
                    newPos.add(new Coords(cord.x, cord.y - 1, 0));
                } else if ((c == '/' && dir == 0) || (c == '\\' && dir == 2)) {
                    newPos.add(new Coords(cord.x + 1, cord.y, 1));
                } else if ((c == '/' && dir == 2) || (c == '\\' && dir == 0)) {
                    newPos.add(new Coords(cord.x - 1, cord.y, 3));
                }
            }

            posToDo.clear();
            posToDo.addAll(newPos);
        }
        return (int) energized.stream()
                .flatMapToInt(CharSequence::chars)
                .filter(ch -> ch == '#')
                .count();
    }

    private static int solve(List<String> input) {

        return func(input);
    }

    public static int getPublicInputSolution() {
        return publicInputSolution;
    }

//    private static int count = 0;
//    private static void move(List<String> input, List<String> energized, int x, int y, int dir, int prevDir)
//    {
//
//        if (x < 0 || x >= input.get(0).length() || y < 0 || y >= input.size())
//            return;
//
////                if (energized.get(y).charAt(x) == '#' && c == '.')
////                    return;
//        if (count == 5000)
//            return;
//        prev = new ArrayList<>(energized);
//        String temp = energized.get(y);
//        temp = temp.substring(0, x) + '#' + temp.substring(x+1);
//        energized.set(y, temp);
////        energized.forEach(System.out::println);
////        boolean listsAreEqual = IntStream.range(0, Math.min(energized.size(), prev.size()))
////                .allMatch(i -> energized.get(i).equals(prev.get(i)));
////
////        if (listsAreEqual)
////            return;
//
////        System.out.println(c);
////        System.out.println();
//        count++;
//        if (c == '.' || ((dir == 1 || dir == 3) && c== '-') || ((dir == 0 || dir == 2) && c== '|') )
//        {
//            switch (dir){
//                case 0 -> move(input, energized, x, y-1, dir, prevDir);
//                case 1 -> move(input, energized, x-1, y, dir, prevDir);
//                case 2 -> move(input, energized, x, y+1, dir, prevDir);
//                case 3 -> move(input, energized, x+1, y, dir, prevDir);
//            }
//        } else if (c == '|' && (dir == 1 || dir == 3))
//        {
//            move(input, energized, x, y+1, 2, prevDir);
//            count-=50;
//            move(input, energized, x, y-1, 0, prevDir);
//        } else if (c == '-' && (dir == 2 || dir == 0))
//        {
//            move(input, energized, x+1, y, 3, prevDir);
//            count-=50;
//            move(input, energized, x-1, y, 1, prevDir);
//        } else if ((c == '/' && dir == 3) || (c == '\\' && dir == 1))
//        {
//            move(input, energized, x, y-1, 0, prevDir);
//        }else if ((c == '/' && dir == 1) || (c == '\\' && dir == 3))
//        {
//            move(input, energized, x, y+1, 2, prevDir);
//        } else if ((c == '/' && dir == 0) || (c == '\\' && dir == 2))
//        {
//            move(input, energized, x+1, y, 3, prevDir);
//        }else if ((c == '/' && dir == 2) || (c == '\\' && dir == 0))
//        {
//            move(input, energized, x-1, y, 1, prevDir);
//        }
//    }

    public static void setPublicInputSolution(int val) {
        publicInputSolution = val;
    }

    public static int getHiddenInputSolution() {
        return hiddenInputSolution;
    }

    public static void setHiddenInputSolution(int val) {
        hiddenInputSolution = val;
    }

    static class Coords {
        int x;
        int y;
        int dir;

        public Coords(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Coords coord = (Coords) obj;
            return x == coord.x && y == coord.y && dir == coord.dir;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

}
