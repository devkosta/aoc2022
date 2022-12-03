package devkosta.aoc.D02;

import devkosta.common.Problem;

public class Main {
    enum Shape {ROCK, PAPER, SCISSORS}

    enum Outcome {DRAW, WIN, LOSS}

    public static void main(String[] args) {
        Problem P01 = new Problem("D02");
        Problem P02 = new Problem("D02");

        P01.solve(Main::solvePartOne);
        P02.solve(Main::solvePartTwo);
    }

    public record Game(Character moveA, Character moveB) {
        private Shape getShape(Character c) {
            return switch (c) {
                case 'A', 'X' -> Shape.ROCK;
                case 'B', 'Y' -> Shape.PAPER;
                case 'C', 'Z' -> Shape.SCISSORS;
                default -> throw new RuntimeException(String.valueOf(c));
            };
        }

        private Outcome getDesiredOutcome(Character c) {
            return switch (c) {
                case 'X' -> Outcome.LOSS;
                case 'Y' -> Outcome.DRAW;
                case 'Z' -> Outcome.WIN;
                default -> throw new RuntimeException(String.valueOf(c));
            };
        }

        private Shape selectMoveFromDesiredOutcome(Shape move, Outcome desired) {
            return Shape.values()[(move.ordinal() + desired.ordinal()) % Shape.values().length];
        }

        private Outcome calculateOutcome(Shape moveA, Shape moveB) {
            if (moveA == moveB) {
                return Outcome.DRAW;
            } else if (moveA.ordinal() == ((moveB.ordinal() + 1) % Shape.values().length)) {
                return Outcome.LOSS;
            } else {
                return Outcome.WIN;
            }
        }

        private int getOutcomeScore(Outcome outcome) {
            int baseScore = 0;

            return switch (outcome) {
                case LOSS -> baseScore;
                case DRAW -> baseScore + 3;
                case WIN -> baseScore + 6;
            };
        }

        private int getOverallScoreP1() {
            Shape a = getShape(moveA);
            Shape b = getShape(moveB);

            return getOutcomeScore(calculateOutcome(a, b)) + b.ordinal() + 1;
        }

        private int getOverallScoreP2() {
            Shape a = getShape(moveA);
            Outcome desiredOutcome = getDesiredOutcome(moveB);

            return selectMoveFromDesiredOutcome(a, desiredOutcome).ordinal() + getOutcomeScore(desiredOutcome) + 1;
        }
    }

    public static int solvePartOne(String[] input) {
        int totalScore = 0;
        for (String x : input) {
            Game game = new Game(x.charAt(0), x.charAt(2));
            totalScore += game.getOverallScoreP1();
        }

        return totalScore;
    }

    public static int solvePartTwo(String[] input) {
        int totalScore = 0;
        for (String x : input) {
            Game game = new Game(x.charAt(0), x.charAt(2));
            totalScore += game.getOverallScoreP2();
        }

        return totalScore;
    }
}
