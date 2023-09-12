package chess.validators;

import java.util.ArrayList;
import java.util.List;

import chess.ChessApp;
import chess.models.Board;
import chess.models.ChessGame;

public class PawnMoveValidator implements MoveValidator{

    @Override
    public List<String> getValidMoves(String currentPosition, ChessGame game) {
        List<String> validMoves = new ArrayList<>();

        Board board = game.getBoard();
        int currentRow = currentPosition.charAt(1) - '1';
        int currentCol = currentPosition.charAt(0) - 'a';

        String pieceColor = board.getPiece(currentRow, currentCol).getColor();
        int direction = (pieceColor.equals(ChessApp.WHITE)) ? 1 : -1; // Determine pawn's forward direction

        // Check one square forward
        int forwardRow = currentRow + direction;
        if (board.isValidSquare(forwardRow, currentCol) && board.isSquareEmpty(forwardRow, currentCol)) {
            validMoves.add(board.convertToPosition(currentCol, forwardRow));
        }

        // Check two squares forward on the first move
        if ((pieceColor.equals(ChessApp.WHITE) && currentRow == 1) || (pieceColor.equals("black") && currentRow == 6)) {
            int doubleForwardRow = currentRow + 2 * direction;
            if (board.isValidSquare(doubleForwardRow, currentCol) && board.isSquareEmpty(doubleForwardRow, currentCol)) {
                validMoves.add(board.convertToPosition(currentCol, doubleForwardRow));
            }
        }

        // Check diagonal captures
        int[] captureCols = { currentCol - 1, currentCol + 1 };
        for (int captureCol : captureCols) {
            if (board.isValidSquare(forwardRow, captureCol) && !board.isSquareEmpty(forwardRow, captureCol)
                    && !board.getPiece(forwardRow, captureCol).getColor().equals(pieceColor)) {
                validMoves.add(board.convertToPosition(captureCol, forwardRow));
            }
        }

        return validMoves;
    }
    
    
}
