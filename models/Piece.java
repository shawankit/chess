package chess.models;

import java.util.List;

import chess.validators.MoveValidator;

public class Piece {
    private String name;
    private String color;
    private MoveValidator moveValidator;
    private String symbol;

    public Piece(String name, String color, MoveValidator moveValidator, String symbol) {
        this.name = name;
        this.color = color;
        this.moveValidator = moveValidator;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getSymbol() {
        return symbol;
    }

    public List<String> getValidMoves(String currentPosition, ChessGame game) {
        return moveValidator.getValidMoves(currentPosition, game);
    }
}
