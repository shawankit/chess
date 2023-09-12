package chess;

import java.util.HashMap;
import java.util.Map;

import chess.models.Board;
import chess.models.ChessGame;
import chess.models.Piece;
import chess.models.Player;
import chess.validators.BishopMoveValidator;
import chess.validators.KingMoveValidator;
import chess.validators.KnightMoveValidator;
import chess.validators.MoveValidator;
import chess.validators.PawnMoveValidator;
import chess.validators.QueenMoveValidator;
import chess.validators.RookMoveValidator;

public class ChessApp {
    public static final String ROOK = "rook";
    public static final String KNIGHT = "knight";
    public static final String BISHOP = "bishop";
    public static final String QUEEN = "queen";
    public static final String KING = "king";
    public static final String PAWN = "pawn";
    public static final String WHITE = "white";
    public static final String BLACK = "black";

    public static Map<String, MoveValidator> getMoveValidators() {
        Map<String, MoveValidator> moveValidators = new HashMap<>();
        moveValidators.put(ROOK, (MoveValidator)new RookMoveValidator());
        moveValidators.put(KNIGHT, (MoveValidator)new KnightMoveValidator());
        moveValidators.put(BISHOP, (MoveValidator)new BishopMoveValidator());
        moveValidators.put(QUEEN, (MoveValidator)new QueenMoveValidator());
        moveValidators.put(KING, (MoveValidator)new KingMoveValidator());
        moveValidators.put(PAWN, (MoveValidator)new PawnMoveValidator());
        return moveValidators;
    }

     public static Map<String, Piece[]> getAllPieces(String color, Map<String, MoveValidator> moveValidators) {
        var pieces = new HashMap<String, Piece[]>();
        
        Piece[] rooks = new Piece[2];
        for(int i = 0; i < 2; i++){
            rooks[i] = new Piece(ROOK, color,moveValidators.get(ROOK), color.equals(WHITE) ? "RW" : "RB");
        }
        pieces.put(ROOK, rooks);

        Piece[] knights = new Piece[2];
        for(int i = 0; i < 2; i++){
            knights[i] = new Piece(KNIGHT, color,moveValidators.get(KNIGHT), color.equals(WHITE) ? "NW" : "NB");
        }
        pieces.put(KNIGHT, knights);

        Piece[] bishops = new Piece[2];
        for(int i = 0; i < 2; i++){
            bishops[i] = new Piece(BISHOP, color,moveValidators.get(BISHOP), color.equals(WHITE) ? "BW" : "BB");
        }
        pieces.put(BISHOP, bishops);

        pieces.put(QUEEN, new Piece[] {
            new Piece(QUEEN, color,moveValidators.get(QUEEN), color.equals(WHITE) ? "QW" : "QB")
        });

        pieces.put(KING, new Piece[] {
            new Piece(KING, color,moveValidators.get(KING), color.equals(WHITE) ? "KW" : "KB")
        });

        Piece[] pawns = new Piece[8];
        for(int i = 0; i < 8; i++){
            pawns[i] = new Piece(PAWN, color,moveValidators.get(PAWN), color.equals(WHITE) ? "PW" : "PB");
        }
        pieces.put(PAWN, pawns);

        return pieces;
    }

    public static void main(String[] args) {
        System.out.println("Intializing Chess App");

        Map<String, MoveValidator> moveValidators = getMoveValidators();
        Map<String, Piece[]> whitePieces = getAllPieces(WHITE, moveValidators);
        Map<String, Piece[]> blackPieces = getAllPieces(BLACK, moveValidators);

        System.out.println("Chess App Initialized");

        Board board = new Board(whitePieces, blackPieces);

        System.out.println("Board Initialized");
        
        Player player1 = new Player("Player 1", WHITE);
        Player player2 = new Player("Player 2", BLACK);
        ChessGame game = new ChessGame(player1, player2, board);
        game.start();
    }
}
