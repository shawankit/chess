package chess.validators;

import java.util.List;

import chess.models.ChessGame;

public class RookMoveValidator implements MoveValidator {
    public List<String> getValidMoves(String currentPosition, ChessGame game) {
        int[][] directions = {
            {-1, 0}, {1, 0}, // Vertical (up, down)
            {0, -1}, {0, 1}  // Horizontal (left, right)
        };

        return MoveUtils.getValidMovesInDirection(currentPosition, game.getBoard(), directions, false);
    }
    
}
