package swen221.assignment2.chessview.pieces;

import java.util.Map;

import swen221.assignment2.chessview.*;

public class King extends PieceImpl implements Piece {

	public King(boolean isWhite) {
		super(isWhite);
	}

	public boolean isValidMove(Position oldPosition, Position newPosition,
			Piece isTaken, Board board) {
		int diffCol = Math.max(oldPosition.column(), newPosition.column())
				- Math.min(oldPosition.column(), newPosition.column());
		int diffRow = Math.max(oldPosition.row(), newPosition.row())
				- Math.min(oldPosition.row(), newPosition.row());
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);
		if(this.equals(p)
				&& (t == isTaken || (isTaken != null && isTaken.equals(t)))
				&& (diffCol == 1 || diffRow == 1) && diffCol <= 1
				&& diffRow <= 1){
			//update the position of the corresponding King on the board if it makes a valid move
			board.setKingPos(newPosition, isWhite);
			//if this is the first move of the king, update castlingValid
			Map<Position, Boolean> castlingValid = board.getCastlingValid();
			if(castlingValid.containsKey(oldPosition)){
				if(castlingValid.get(oldPosition)){
					board.updateCastlingValid(oldPosition);
				}
			}
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * This checks if the King can check another King in its current move.
	 * A King should never check another King. As it will be checked instead.
	 * @param board - the current board.
	 * @param currentPos - the current Position of this King.
	 * @return false always.
	 */
	@Override
	public boolean canCheck(Board board, Position currentPos){
		return false;
	}

	public String toString() {
		if(isWhite) {
			return "K";
		} else {
			return "k";
		}
	}
}
