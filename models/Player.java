package chess.models;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import chess.ChessApp;

public class Player {
    private String name;
    private String color;
    private String kingPosition;
    private boolean isInCheck;
    private boolean isCheckmated;
    private boolean isStalemate;
    private boolean hasKingMoved;
    private boolean hasLeftRookMoved;
    private boolean hasRightRookMoved;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.kingPosition = color.equals(ChessApp.WHITE) ? "e1" : "e8";
        this.isInCheck = false;
        this.isCheckmated = false;
        this.isStalemate = false;
        this.hasKingMoved = false;
        this.hasLeftRookMoved = false;
        this.hasRightRookMoved = false;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getKingPosition() {
        return kingPosition;
    }

    public void setKingPosition(String kingPosition) {
        this.kingPosition = kingPosition;
    }

    public boolean isInCheck() {
        return isInCheck;
    }

    public void setInCheck(boolean isInCheck) {
        this.isInCheck = isInCheck;
    }

    public boolean isCheckmated() {
        return isCheckmated;
    }

    public void setCheckmated(boolean isCheckmated) {
        this.isCheckmated = isCheckmated;
    }

    public boolean isStalemate() {
        return isStalemate;
    }

    public void setStalemate(boolean isStalemate) {
        this.isStalemate = isStalemate;
    }

    public boolean hasKingMoved() {
        return hasKingMoved;
    }

    public void setKingMoved(boolean hasKingMoved) {
        this.hasKingMoved = hasKingMoved;
    }

    public boolean hasLeftRookMoved() {
        return hasLeftRookMoved;
    }

    public void setLeftRookMoved(boolean hasLeftRookMoved) {
        this.hasLeftRookMoved = hasLeftRookMoved;
    }

    public boolean hasRightRookMoved() {
        return hasRightRookMoved;
    }

    public void setRightRookMoved(boolean hasRightRookMoved) {
        this.hasRightRookMoved = hasRightRookMoved;
    }


    public String getMoveFrom() {
        // get move from user input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String from = null;
        try {
            from = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return from;
    }

    public String getMoveTo() {
        // get move from user input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String to = null;
        try {
            to = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return to;
    }
}
