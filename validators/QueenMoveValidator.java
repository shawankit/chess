package chess.validators;

import java.util.List;

import chess.models.ChessGame;

public class QueenMoveValidator implements MoveValidator {
    @Override
    public List<String> getValidMoves(String currentPosition, ChessGame game) {
        int[][] directions = {
            {-1, 0}, {1, 0}, // Vertical (up, down)
            {0, -1}, {0, 1}, // Horizontal (left, right)
            {-1, -1}, {-1, 1}, // Diagonal (up and left, up and right)
            {1, -1}, {1, 1}    // Diagonal (down and left, down and right)
        };

        return MoveUtils.getValidMovesInDirection(currentPosition, game.getBoard(), directions, false);
    }
    
}
