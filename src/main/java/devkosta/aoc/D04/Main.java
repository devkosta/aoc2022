package devkosta.aoc.D04;

import devkosta.common.Problem;

public class Main {
    public static void main(String[] args) {
        Problem P01 = new Problem("D04");
        Problem P02 = new Problem("D04");

        P01.solve(Main::solvePartOne);
        P02.solve(Main::solvePartTwo);
    }

    public static boolean overlaps(String sOne, String sTwo, String sThr, String sFou, boolean full) {
        int sOneInt = Integer.parseInt(sOne), sTwoInt = Integer.parseInt(sTwo);
        int sThrInt = Integer.parseInt(sThr), sFouInt = Integer.parseInt(sFou);

        boolean condOne = sOneInt <= sThrInt && sTwoInt >= (full ? sFouInt : sThrInt);
        boolean condTwo = sThrInt <= sOneInt && sFouInt >= (full ? sTwoInt : sOneInt);

        return condOne || condTwo;
    }

    public static int solvePartOne(String[] input) {
        int totalSum = 0;

        for (String pairs : input) {
            String[] sections = pairs.split(",");
            String[] sectionOne = sections[0].split("-");
            String[] sectionTwo = sections[1].split("-");

            boolean overlapCondition = overlaps(sectionOne[0], sectionOne[1], sectionTwo[0], sectionTwo[1], true);

            if (overlapCondition) totalSum += 1;
        }

        return totalSum;
    }

    public static int solvePartTwo(String[] input) {
        int totalSum = 0;

        for (String pairs : input) {
            String[] sections = pairs.split(",");
            String[] sectionOne = sections[0].split("-");
            String[] sectionTwo = sections[1].split("-");

            boolean overlapCondition = overlaps(sectionOne[0], sectionOne[1], sectionTwo[0], sectionTwo[1], false);

            if (overlapCondition) totalSum += 1;
        }

        return totalSum;
    }
}
