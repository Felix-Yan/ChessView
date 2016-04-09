package swen221.assignment2.chessview.moves;

import swen221.assignment2.chessview.*;
import swen221.assignment2.chessview.pieces.*;

/**
 * This represents a "check move". Note that, a check move can only be made up
 * from an underlying simple move; that is, we can't check a check move.
 *
 * @author djp
 *
 */
public class Check implements Move {
	private MultiPieceMove move;

	public Check(MultiPieceMove move) {
		this.move = move;
	}

	public MultiPieceMove move() {
		return move;
	}

	public boolean isWhite() {
		return move.isWhite();
	}

	public boolean isValid(Board board) {
		if(move.isValid(board)){
			//If the move is valid, construct a new board for calling isValidCheck.
			Board newBoard = new Board(board);
			this.apply(newBoard);
			if(this.isValidCheck(newBoard)){
				//let the original board remember that there will be a check after applying the move
				board.aboutToCheck(!isWhite());
				return true;
			}
		}
		return false;
	}

	/**
	 * Is this move checking the opponent's King?
	 * @param board - the current board.
	 * @return true if the move is a check; false otherwise.
	 */
	public boolean isValidCheck(Board board){
		return move.isChecking(board);
	}

	public void apply(Board board) {
		move.apply(board);
	}

	public String toString() {
		return move.toString() + "+";
	}
}
