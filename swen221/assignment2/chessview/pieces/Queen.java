package swen221.assignment2.chessview.pieces;

import swen221.assignment2.chessview.*;

public class Queen extends PieceImpl implements Piece {
	public Queen(boolean isWhite) {
		super(isWhite);
	}

	public boolean isValidMove(Position oldPosition, Position newPosition,
			Piece isTaken, Board board) {
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);
		return this.equals(p)
				&& (t == isTaken || (isTaken != null && isTaken.equals(t)))
				&& (board.clearColumnExcept(oldPosition, newPosition, p,t)
						|| board.clearRowExcept(oldPosition,newPosition, p, t)
							|| board.clearDiaganolExcept(oldPosition, newPosition, p, t)
						);
	}

	public String toString() {
		if(isWhite) {
			return "Q";
		} else {
			return "q";
		}
	}

	/**
	 * This checks if the Queen can make a valid check to the opponent's King.
	 * @param board - the current board.
	 * @param currentPos - the current position of this Queen.
	 * @return true if the Queen can make a valid check to the opponent's King; false otherwise.
	 */
	@Override
	public boolean canCheck(Board board, Position currentPos) {
		Position kingPos = board.getKingPos(!this.isWhite);
		return isValidMove(currentPos, kingPos, new King(!this.isWhite), board);
	}
}
