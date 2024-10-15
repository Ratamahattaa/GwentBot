package pl.edu.us.medrala.szymon.aiagent.algorithm;

import pl.edu.us.medrala.szymon.aiagent.board.RoundNumber;

public final class StatusChecker {
    public static boolean shouldContinue(final int enemyPoints, final int myPoints, final boolean enemyPassed, final RoundNumber roundNumber) {
        if (roundNumber == RoundNumber.FIRST) {
            return enemyPoints >= myPoints || !enemyPassed;
        }

        return true;
    }

    private StatusChecker() {
    }
}
