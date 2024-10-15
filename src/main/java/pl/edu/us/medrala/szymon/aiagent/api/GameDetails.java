package pl.edu.us.medrala.szymon.aiagent.api;

public record GameDetails(int gameId, int yourBoardId, int enemyBoardId, int handId, int discardPileId, int deckId) {
}
