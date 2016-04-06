package swen221.assignment2.chessview.pieces;

import swen221.assignment2.chessview.*;

public class Pawn extends PieceImpl implements Piece {
	public Pawn(boolean isWhite) {
		super(isWhite);
	}

	public boolean isValidMove(Position oldPosition, Position newPosition,
			Piece isTaken, Board board) {
		int dir = isWhite ? 1 : -1;
		int oldRow = oldPosition.row();
		int oldCol = oldPosition.column();
		int newRow = newPosition.row();
		int newCol = newPosition.column();

		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);

		// this logic should be more complex than for other pieces, since there
		// is a difference between a take and non-take move for pawns.

		//move one square ahead
		if ((oldRow + dir) == newRow && oldCol == newCol) {
			return this.equals(p) && t == null;
		}
		//pawn can move two squares for the first move
		else if ((oldRow + dir + dir) == newRow && oldCol == newCol) {
			return ((dir == 1 && oldRow == 2) || (dir == -1 && oldRow == 7))
					&& t == null && this.equals(p);
		}
		//pawn takes one opponent's piece normally
		else if( (oldRow + dir) == newRow && ((oldCol+1) == newCol || (oldCol-1) == newCol) ){
			return this.equals(p)
					&& (t == isTaken || (isTaken != null && isTaken.equals(t)));
		}
		return false;
	}

	public String toString() {
		if(isWhite) {
			return "P";
		} else {
			return "p";
		}
	}
}
