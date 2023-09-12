package chess.validators;

import java.util.List;

import chess.models.ChessGame;

public interface MoveValidator {
    List<String> getValidMoves(String currentPosition, ChessGame game);
}
