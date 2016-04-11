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
			if(((dir == 1 && oldRow == 2) || (dir == -1 && oldRow == 7))
					&& (board.clearColumnExcept(oldPosition, newPosition, p,t))//need to check column clear
					&& t == null && this.equals(p)){
				board.setJumpPawnPos(newPosition);
				board.setPawnJumped(true);
				return true;
			}
		}
		//pawn takes one opponent's piece
		else if( (oldRow + dir) == newRow && ( (oldCol+1) == newCol || (oldCol-1 ) == newCol) ){
			//takes care of the enPassant case
			if(t == null){
				Position passedPawnPos = new Position(oldRow, newCol);
				//the pawn taken must have just moved two squares ahead
				if(!passedPawnPos.equals(board.getJumpPawnPos())){
					return false;
				}
				t = board.pieceAt(passedPawnPos);
			}
			return this.equals(p)
					&& (isTaken != null && isTaken.equals(t));//only move diagonally if t is not null
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

	/**
	 * This checks if the Pawn can make a valid check to the opponent's King.
	 * @param board - the current board.
	 * @param currentPos - the current position of this Pawn.
	 * @return true if the Pawn can make a valid check to the opponent's King; false otherwise.
	 */
	@Override
	public boolean canCheck(Board board, Position currentPos) {
		Position kingPos = board.getKingPos(!this.isWhite);
		return isValidMove(currentPos, kingPos, new King(!this.isWhite), board);
	}

	public boolean isValidEnPassant(){
		return false;
	}
}
