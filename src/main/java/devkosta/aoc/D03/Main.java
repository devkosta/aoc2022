package devkosta.aoc.D03;

import devkosta.common.Problem;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Character.isUpperCase;

public class Main {
    public static void main(String[] args) {
        Problem P01 = new Problem("D03");
        Problem P02 = new Problem("D03");

        P01.solve(Main::solvePartOne);
        P02.solve(Main::solvePartTwo);
    }

    public static int commonPriority(List<String> rucksacks) {
        // Common character in rucksacks
        char c = rucksacks.stream().reduce(Main::commonChars).get().charAt(0);

        return c - 'a' + (isUpperCase(c) ? 58 : 0) + 1;
    }

    static String commonChars(String a, String b) {
        return a.chars().filter(i1 -> b.chars().anyMatch(i2 -> i1 == i2)).distinct().mapToObj(Character::toString).collect(Collectors.joining());
    }

    public static int solvePartOne(String[] input) {
        int totalSum = 0;
        for (String rucksack : input) {
            int n = rucksack.length(), mid = n / 2;

            String rucksackOne = rucksack.substring(0, mid);
            String rucksackTwo = rucksack.substring(mid, n);

            totalSum += commonPriority(List.of(rucksackOne, rucksackTwo));
        }

        return totalSum;
    }

    public static int solvePartTwo(String[] input) {
        int totalSum = 0;
        for (int i = 0; i < input.length; i += 3) {
            String rucksackOne = input[i];
            String rucksackTwo = input[i + 1];
            String rucksackThr = input[i + 2];

            String newRucksack = commonChars(rucksackOne, rucksackTwo);

            totalSum += commonPriority(List.of(newRucksack, rucksackThr));
        }

        return totalSum;
    }
}
