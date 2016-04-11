package swen221.assignment2.chessview.moves;

import swen221.assignment2.chessview.*;
import swen221.assignment2.chessview.pieces.*;

/**
 * This represents an "en passant move" --- http://en.wikipedia.org/wiki/En_passant.
 *
 * @author djp
 *
 */
public class EnPassant implements MultiPieceMove {
	private SinglePieceMove move;

	public EnPassant(SinglePieceMove move) {
		this.move = move;
	}

	/**
	 * This tells if the move is on the white side.
	 * @return true if the move is on the white side; false otherwise.
	 */
	public boolean isWhite() {
		return move.isWhite();
	}

	/**
	 * This tells if the enPassant move is valid.
	 * @param board - the current board.
	 * @return true if the move is valid; false otherwise.
	 */
	public boolean isValid(Board board) {
		//the last move has to be a two square pawn jump from the opponent
		if(board.getJumpPawnPos() == null) return false;

		//delegate the job to singlePieceMove
		return move.isValid(board);
	}

	/**
	 * Move the pawn taking to the new position.
	 * Remove the pawn taken.
	 */
	public void apply(Board board) {
		board.setPieceNull(board.getJumpPawnPos());
		board.move(move.oldPosition, move.newPosition);
		board.setPawnJumped(false);
	}

	public String toString() {
		return ((SinglePieceTake)move).toString()+"ep";
	}

	/**
	 * An en passnant might be a check. Check it in the board.
	 * @param board - the current board.
	 * @return true if there is a check; false otherwise.
	 */
	@Override
	public boolean isChecking(Board board) {
		return board.isInCheck(!move.isWhite(), board);
	}
}
