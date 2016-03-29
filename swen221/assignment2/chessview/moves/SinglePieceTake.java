package swen221.assignment2.chessview.moves;

import swen221.assignment2.chessview.*;
import swen221.assignment2.chessview.pieces.Piece;

public class SinglePieceTake extends SinglePieceMove {
	private Piece isTaken;
	
	public SinglePieceTake(Piece piece, Piece isTaken, Position oldPosition, Position newPosition) {
		super(piece,oldPosition,newPosition);
		this.isTaken = isTaken;
	}
	
	public boolean isValid(Board board) {
		return piece.isValidMove(oldPosition, newPosition, isTaken, board);
	}
	
	public String toString() {
		return pieceChar(piece) + oldPosition + "x" + pieceChar(isTaken) + newPosition; 
	}	
}
