package chess.validators;

import java.util.ArrayList;
import java.util.List;
import chess.models.Board;
import chess.models.ChessGame;
import chess.models.Player;

public class KingMoveValidator implements MoveValidator {
    @Override
    public List<String> getValidMoves(String currentPosition, ChessGame game) {
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1}, // Diagonal (up and left, up, up and right)
            {0, -1}, {0, 1},           // Horizontal (left, right)
            {1, -1}, {1, 0}, {1, 1}    // Diagonal (down and left, down, down and right)
        };

        List<String> validMoves = MoveUtils.getValidMovesInDirection(currentPosition, game.getBoard(), directions, true);

        validMoves.addAll(getValidCastlingMoves(currentPosition, game));
        return validMoves;
    }
    
    private List<String> getValidCastlingMoves(String currentPosition, ChessGame game) {
        List<String> validCastlingMoves = new ArrayList<>();
        Player currentPlayer = game.getCurrentPlayer();

        if ( !currentPlayer.hasKingMoved() 
            && !currentPlayer.hasLeftRookMoved() 
            && isSquaresEmptyForKingsideCastling(currentPosition, game)
            && !isSquaresUnderAttackForKingsideCastling(currentPosition, game)
        ) {
            validCastlingMoves.add("g" + currentPosition.charAt(1));
        }
        if(!currentPlayer.hasKingMoved() 
            && !currentPlayer.hasRightRookMoved() 
            && isSquaresEmptyForQueensideCastling(currentPosition, game)
            && !isSquaresUnderAttackForQueensideCastling(currentPosition, game)
        ) {
            validCastlingMoves.add("c" + currentPosition.charAt(1));
        }

        return validCastlingMoves;
    }

    private boolean isSquaresEmptyForKingsideCastling(String currentPosition, ChessGame game) {
        Board board = game.getBoard();
        int row = currentPosition.charAt(1) - '1';
        for (int col = 5; col <= 6; col++) {
            if (!board.isSquareEmpty(row, col)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSquaresEmptyForQueensideCastling(String currentPosition, ChessGame game) {
        Board board = game.getBoard();
        int row = currentPosition.charAt(1) - '1';
        for (int col = 1; col <= 3; col++) {
            if (!board.isSquareEmpty(row, col)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSquaresUnderAttackForKingsideCastling(String currentPosition, ChessGame game) {
        // Check if the squares the king passes through for kingside castling are under attack
        // Implement this logic to check if the king passes through any squares that are attacked by opponent pieces
        // Return true if any of those squares are under attack, indicating that the king cannot castle kingside
        return false;
    }

    private boolean isSquaresUnderAttackForQueensideCastling(String currentPosition, ChessGame game) {
        // Check if the squares the king passes through for queenside castling are under attack
        // Implement this logic to check if the king passes through any squares that are attacked by opponent pieces
        // Return true if any of those squares are under attack, indicating that the king cannot castle queenside
        return false;
    }
}
