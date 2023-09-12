package chess.validators;

import java.util.List;

import chess.models.ChessGame;

public class KnightMoveValidator implements MoveValidator {
    @Override
    public List<String> getValidMoves(String currentPosition,ChessGame game) {
        int[][] directions = {
            {-2, -1}, {-2, 1}, // Up
            {-1, -2}, {1, -2}, // Left
            {2, -1}, {2, 1},   // Down
            {-1, 2}, {1, 2}    // Right
        };

        return MoveUtils.getValidMovesInDirection(currentPosition, game.getBoard(), directions, true);
    }
}
