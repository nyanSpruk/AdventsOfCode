package day7;

import java.util.*;
import java.util.stream.IntStream;


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

        // 5 * same char 6 points
        // 4 * same char 5 points
        // 3 * same char + 2 * same char 4 points

        // 3 * same char 3 points
        // 2 * same char + 2 * same char 2 points
        // 2 * same char 1 point
        // biggest char chech the biggest card
        List<Integer> values = new ArrayList<>();
        List<Triple> l = new ArrayList<>();
        for (String line : input) {
            HashMap<Character, Integer> cardOccurances = new HashMap<>();
            String[] split = line.split(" ");
            String cards = split[0];

            int value = Integer.parseInt(split[1]);

            HashMap<Character, Integer> finalCardOccurances1 = cardOccurances;
            cards.chars()
                    .mapToObj(c -> (char) c)
                    .forEach(character ->
                            finalCardOccurances1.merge(character, 1, Integer::sum)
                    );

            char mostFrequentChar = 'J';
            int maxCount = 0;
            for (Map.Entry<Character, Integer> entry : cardOccurances.entrySet()) {
                char currentChar = entry.getKey();
                int currentCount = entry.getValue();
                if (currentChar != 'J' && currentCount > maxCount) {
                    mostFrequentChar = currentChar;
                    maxCount = currentCount;
                }
            }

            // Replace all occurrences of 'J' with the most frequent character
            String resultString = cards.replace('J', mostFrequentChar);
            cardOccurances = new HashMap<>();
            HashMap<Character, Integer> finalCardOccurances = cardOccurances;
            resultString.chars()
                    .mapToObj(c -> (char) c)
                    .forEach(character ->
                            finalCardOccurances.merge(character, 1, Integer::sum)
                    );

            Triple res = points(cardOccurances, value, cards);
            l.add(res);
        }

        TripleComparator tripleComparator = new TripleComparator();
        l.sort(tripleComparator);

        Collections.reverse(l);

        long res = IntStream.range(0, l.size())
                .mapToLong(i -> (i + 1) * l.get(i).bid)
                .sum();

        return (int) res;
    }

    private static Triple points(HashMap<Character, Integer> map, int bid, String cards) {
        if (map.size() == 1) // we got 5 of same
            return new Triple(bid, cards, 6);
        if (map.size() == 2) // we might have 3 2 or 4 1
        {
            int biggest = map.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(0);
            if (biggest == 4)
                return new Triple(bid, cards, 5);

            if (biggest == 3)
                return new Triple(bid, cards, 4);

        }
        int biggest = map.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(0);
        if (biggest == 3)
            return new Triple(bid, cards, 3);
        if (map.size() == 3 && biggest == 2)
            return new Triple(bid, cards, 2);
        if (biggest == 2)
            return new Triple(bid, cards, 1);

        return new Triple(bid, cards, 0);
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

    static class Triple {
        int bid;
        int score;
        String cards;

        Triple(int bid, String cards, int score) {
            this.bid = bid;
            this.score = score;
            this.cards = cards;
        }
    }

    public static class TripleComparator implements Comparator<Triple> {
        List<Character> charList = List.of('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A');

        @Override
        public int compare(Triple triple1, Triple triple2) {
            int scoreComparison = Integer.compare(triple2.score, triple1.score);

            if (scoreComparison != 0)
                return scoreComparison;

            return compareCards(triple1.cards, triple2.cards);
        }

        private int compareCards(String cards1, String cards2) {
            for (int i = 0; i < Math.min(cards1.length(), cards2.length()); i++) {
                char char1 = cards1.charAt(i);
                char char2 = cards2.charAt(i);

                int index1 = charList.indexOf(char1);
                int index2 = charList.indexOf(char2);

                int charComparison = Integer.compare(index2, index1);

                if (charComparison != 0)
                    return charComparison;
            }

            return Integer.compare(cards2.length(), cards1.length());
        }
    }

}
