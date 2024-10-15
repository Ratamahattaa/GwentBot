package pl.edu.us.medrala.szymon.aiagent.api;

public record UpdateBoardQuery(int enemyAllPoints, int enemySpyPoints, boolean isFrost, boolean isRain, boolean isFog, boolean enemyPassed) {

}
