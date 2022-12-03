package devkosta.aoc.D01;

import devkosta.common.Problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Problem P01 = new Problem("D01");
        Problem P02 = new Problem("D01");

        P01.solve(Main::solvePartOne);
        P02.solve(Main::solvePartTwo);
    }

    public static int solvePartOne(String[] input) {
        int max = 0, curMax = 0;
        for (String line : input) {
            if (line.equals("")) {
                max = Math.max(max, curMax);
                curMax = 0;
            } else {
                curMax += Integer.parseInt(line);
            }
        }

        return max;
    }

    public static int solvePartTwo(String[] input) {
        ArrayList<Integer> snackTotals = new ArrayList<>();

        int max = 0, curMax = 0;
        for (String line : input) {
            if (line.equals("")) {
                snackTotals.add(curMax);
                curMax = 0;
            } else {
                curMax += Integer.parseInt(line);
            }
        }

        Collections.sort(snackTotals);

        List<Integer> topCarriers = snackTotals.subList(snackTotals.size() - 3, snackTotals.size());
        for (int snacks : topCarriers) {
            max += snacks;
        }

        return max;
    }
}
