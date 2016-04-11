package swen221.assignment2.chessview.moves;

import swen221.assignment2.chessview.*;
import swen221.assignment2.chessview.pieces.*;

/**
 * This represents a pawn promotion.
 * @author djp
 *
 */
public class PawnPromotion implements MultiPieceMove {
	private Piece promotion;
	private SinglePieceMove move;

	public PawnPromotion(SinglePieceMove move, Piece promotion) {
		this.promotion = promotion;
		this.move = move;
	}

	public boolean isWhite() {
		return move.isWhite();
	}

	/**
	 * This checks if pawn promotion is valid.
	 * The piece has to be a pawn. Its new position has to be at the top row or bottom row.
	 * @param board - the current board
	 * @return true if the promotion is valid; false otherwise.
	 */
	public boolean isValid(Board board) {
		Piece promoted = move.piece;
		//the piece promoted has to be a pawn
		if(!(promoted instanceof Pawn)) return false;
		//the piece has to be promoted at the top row or bottom row
		if(move.newPosition.row() != 8 && move.newPosition.row() != 1) return false;

		return move.isValid(board);
	}

	public void apply(Board board) {
		board.move(move.oldPosition, move.newPosition);
		board.setPieceAt(move.newPosition, promotion);
		board.setPawnJumped(false);
	}

	public String toString() {
		return move.toString() + "=" + SinglePieceMove.pieceChar(promotion);
	}

	/**
	 * A PawnPromotion might be a check. Check it in the board.
	 * @param board - the current board.
	 * @return true if there is a check; false otherwise.
	 */
	@Override
	public boolean isChecking(Board board) {
		return board.isInCheck(!move.isWhite(), board);
	}
}
