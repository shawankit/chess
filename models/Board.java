package chess.models;

import java.util.Map;

import chess.ChessApp;

public class Board {
    private Piece[][] board;
    
    public Board(Map<String, Piece[]> whitePieces, Map<String, Piece[]> blackPieces) {
        this.board = new Piece[8][8];
        this.setPieces(whitePieces, ChessApp.WHITE);
        this.setPieces(blackPieces, ChessApp.BLACK);
    }

    private void setPieces(Map<String, Piece[]> pieceMap, String color) {
        int row = color.equals(ChessApp.WHITE) ? 0 : 7;
        for (Map.Entry<String, Piece[]> entry : pieceMap.entrySet()) {
            String type = entry.getKey();
            Piece[] pieces = entry.getValue();
            if(type.equals(ChessApp.PAWN)) {
                for (Piece piece : pieces) {
                    for (int col = 0; col < 8; col++) {
                        board[ color.equals(ChessApp.WHITE) ? 1 : 6][col] = piece;
                    }
                }
            } 
            else if(type.equals(ChessApp.ROOK)) {
                board[row][0] = pieces[0];
                board[row][7] = pieces[1];
            }
                
            else if(type.equals(ChessApp.KNIGHT)) {
                board[row][1] = pieces[0];
                board[row][6] = pieces[1];
            }
            else if(type.equals(ChessApp.BISHOP)) {
                board[row][2] = pieces[0];
                board[row][5] = pieces[1];
            }
            else if(type.equals(ChessApp.QUEEN)) {
                board[row][3] = pieces[0];
            }
            else if(type.equals(ChessApp.KING)) {
                board[row][4] = pieces[0];
            }
        }
    }

    public boolean isValidSquare(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public boolean isSquareEmpty(int row, int col) {
        return board[row][col] == null;
    }

    public String convertToPosition(int col, int row) {
        return String.valueOf((char) (col + 'a')) + (row + 1);
    }

    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    public void printChessBoard() {
        System.out.println("  a  b  c  d  e  f  g  h");
        for (int row = 0; row < 8; row++) {
            System.out.print((row + 1) + "|");
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece == null) {
                    System.out.print("  |");
                } else {
                    System.out.print(piece.getSymbol() + "|");
                }
            }
            System.out.println(" " + (row + 1));
        }
        System.out.println("  a  b  c  d  e  f  g  h");
    }

     

    
}
