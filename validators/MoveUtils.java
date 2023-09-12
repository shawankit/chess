package chess.validators;

import java.util.ArrayList;
import java.util.List;

import chess.models.Board;

public class MoveUtils {
    public static List<String> getValidMovesInDirection(
            String currentPosition, Board board, int[][] directions, boolean isOneStep) {
        List<String> validMoves = new ArrayList<>();

        int currentRow = currentPosition.charAt(1) - '1';
        int currentCol = currentPosition.charAt(0) - 'a';

        String pieceColor = board.getPiece(currentRow, currentCol).getColor();
        for (int[] direction : directions) {
            int row = currentRow + direction[0];
            int col = currentCol + direction[1];
            while (board.isValidSquare(row, col)) {
                if (board.isSquareEmpty(row, col)) {
                    validMoves.add(board.convertToPosition(col, row));
                } else {
                    if (!board.getPiece(row, col).getColor().equals(pieceColor)) {
                        validMoves.add(board.convertToPosition(col, row));
                    }
                    break;
                }
                if (isOneStep) {
                    break;
                }
                row += direction[0];
                col += direction[1];
            }
        }

        return validMoves;
    }
}
