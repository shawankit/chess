package chess.models;

import chess.ChessApp;

public class ChessGame {
    private Player player1;
    private Player player2;
    private Board board;
    private Player currentPlayer;
    private Player winner;
    private boolean isFinished;

    public ChessGame(Player player1, Player player2, Board board) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        this.currentPlayer = player1;
        this.winner = null;
        this.isFinished = false;
    }

    public void start() {
        board.printChessBoard();
        while (!isFinished) {
            System.out.println(currentPlayer.getName() + "'s turn");
            System.out.println("Enter the position of the piece you want to move");
            String from = currentPlayer.getMoveFrom();
            System.out.println("Enter the position of the square you want to move to");
            String to = currentPlayer.getMoveTo();
            boolean isMoved = movePiece(from, to, currentPlayer);
            if (!isMoved) {
                continue;
            } else {
                if(isCurrentMoveMakeOpponentKingInCheck(to)){
                    getOpponenPlayer().setInCheck(true);
                }
            }
            board.printChessBoard();

            if (currentPlayer.isInCheck()) {
                System.out.println(currentPlayer.getName() + " is in check");
            }
            if (currentPlayer.isCheckmated()) {
                System.out.println(currentPlayer.getName() + " is checkmated");
                isFinished = true;
                winner = getOpponenPlayer();
                break;
            }

            switchCurrentPlayer();
        }
        System.out.println("Game over");
        System.out.println(winner.getName() + " won");
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponenPlayer() {
        if (currentPlayer == player1) {
            return player2;
        } else {
            return player1;
        }
    }

    public void switchCurrentPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public boolean isCurrentMoveMakeOpponentKingInCheck(String currentPiecePosition){
        String opponentKingPosition = getOpponenPlayer().getKingPosition();
    
        int destRow = currentPiecePosition.charAt(1) - '1';
        int destCol = currentPiecePosition.charAt(0) - 'a';

        Piece currentPiece = board.getPiece(destRow, destCol);

        // is current move make opponent king in check
        return currentPiece.getValidMoves(currentPiecePosition, this).contains(opponentKingPosition);
    }

    public boolean movePiece(String sourceSquare, String destinationSquare, Player currentPlayer){
        int sourceRow = sourceSquare.charAt(1) - '1';
        int sourceCol = sourceSquare.charAt(0) - 'a';
        int destRow = destinationSquare.charAt(1) - '1';
        int destCol = destinationSquare.charAt(0) - 'a';

        Piece currentPiece = board.getPiece(sourceRow, sourceCol);
        if(currentPiece == null){
            System.out.println("No piece at " + sourceSquare);
            return false;
        }

        if(currentPiece.getColor() != currentPlayer.getColor()){
            System.out.println("Cannot move opponent's piece");
            return false;
        }

        if (currentPiece != null && currentPiece.getValidMoves(sourceSquare, this).contains(destinationSquare)) {
            // Move the piece
            board.setPiece(destRow, destCol, currentPiece);
            board.setPiece(sourceRow, sourceCol, null);
            
            // Update king position
            if(currentPiece.getName().equals(ChessApp.KING)){
                if(destCol == 6){
                    // Kingside castling
                    board.setPiece(destRow, destCol - 1, board.getPiece(destRow, destCol + 1));
                    board.setPiece(destRow, destCol + 1, null);
                    currentPlayer.setLeftRookMoved(true);
                }
                else if(destCol == 2){
                    // Queenside castling
                    board.setPiece(destRow, destCol + 1, board.getPiece(destRow, destCol - 2));
                    board.setPiece(destRow, destCol - 2, null);
                    currentPlayer.setRightRookMoved(true);
                }
                currentPlayer.setKingPosition(destinationSquare);
                currentPlayer.setKingMoved(true);
            }

            // Update rook position
            if(currentPiece.getName().equals(ChessApp.ROOK)){
                if((sourceSquare.equals("a1") ||  sourceSquare.equals("a8")) && !currentPlayer.hasLeftRookMoved()){
                    currentPlayer.setLeftRookMoved(true);
                }
                else if((sourceSquare.equals("h1") ||  sourceSquare.equals("h8")) && !currentPlayer.hasRightRookMoved()){
                    currentPlayer.setRightRookMoved(true);
                }
            }

            System.out.println("Moved " + currentPiece.getName() + " from " + sourceSquare + " to " + destinationSquare);
            return true;
        } else {
            System.out.println("Invalid move for " + currentPiece.getName() + " from " + sourceSquare + " to " + destinationSquare);
            return false;
        }
    }
}
