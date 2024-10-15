package pl.edu.us.medrala.szymon.aiagent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.us.medrala.szymon.aiagent.algorithm.StatusChecker;
import pl.edu.us.medrala.szymon.aiagent.board.RoundNumber;

@SpringBootTest
class AiAgentApplicationTests {

    @Test
    void shouldContinueWhenEnemyPassedIsTrueFirst() {
        boolean result = StatusChecker.shouldContinue(10, 5, true, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }

    @Test
    void shouldContinueWhenEnemyPassedIsTrueSecond() {
        boolean result = StatusChecker.shouldContinue(5, 5, true, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }

    @Test
    void shouldContinueWhenEnemyPassedIsTrueThird() {
        boolean result = StatusChecker.shouldContinue(3, 5, true, RoundNumber.FIRST);
        Assertions.assertEquals(false, result);
    }

    @Test
    void shouldContinueWhenEnemyPassedIsTrueFourth() {
        boolean result = StatusChecker.shouldContinue(5, 10, true, RoundNumber.FIRST);
        Assertions.assertEquals(false, result);
    }

    @Test
    void shouldContinueWhenEnemyPassedIsTrueFifth() {
        boolean result = StatusChecker.shouldContinue(5, 3, true, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }

    @Test
    void shouldContinueWhenEnemyPassedIsTrueSixth() {
        boolean result = StatusChecker.shouldContinue(0, 0, true, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }

    @Test
    void shouldContinueWhenEnemyPassedIsFalseFirst() {
        boolean result = StatusChecker.shouldContinue(10, 5, false, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }
    @Test
    void shouldContinueWhenEnemyPassedIsFalseSecond() {
        boolean result = StatusChecker.shouldContinue(5, 5, false, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }
    @Test
    void shouldContinueWhenEnemyPassedIsFalseThird() {
        boolean result = StatusChecker.shouldContinue(3, 5, false, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }
    @Test
    void shouldContinueWhenEnemyPassedIsFalseFourth() {
        boolean result = StatusChecker.shouldContinue(5, 10, false, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }
    @Test
    void shouldContinueWhenEnemyPassedIsFalseFifth() {
        boolean result = StatusChecker.shouldContinue(5, 3, false, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }
    @Test
    void shouldContinueWhenEnemyPassedIsFalseSixth() {
        boolean result = StatusChecker.shouldContinue(0, 0, false, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }
    @Test
    void shouldContinueEdgeCaseFirst() {
        boolean result = StatusChecker.shouldContinue(-1, 0, true, RoundNumber.FIRST);
        Assertions.assertEquals(false, result);
    }
    @Test
        void shouldContinueEdgeCaseSecond() {
        boolean result = StatusChecker.shouldContinue(0, -1, true, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }
    @Test
        void shouldContinueEdgeCaseThird() {
        boolean result = StatusChecker.shouldContinue(Integer.MAX_VALUE, Integer.MAX_VALUE, true, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }
    @Test
    void shouldContinueEdgeCaseFourth() {
        boolean result = StatusChecker.shouldContinue(-1, 0, false, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }
    @Test
    void shouldContinueEdgeCaseFifth() {
        boolean result = StatusChecker.shouldContinue(0, -1, false, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }
    @Test
    void shouldContinueEdgeCaseSisth() {
        boolean result = StatusChecker.shouldContinue(Integer.MAX_VALUE, Integer.MAX_VALUE, false, RoundNumber.FIRST);
        Assertions.assertEquals(true, result);
    }
    // pogoda
    //tight bond
    // horn
}
