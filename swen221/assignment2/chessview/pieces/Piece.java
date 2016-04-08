package swen221.assignment2.chessview.pieces;

//import java.util.Arrays;

import swen221.assignment2.chessview.*;


public interface Piece {
	/**
	 * Determine whether this piece is white or black.
	 * @return
	 */
	public boolean isWhite();

	/**
	 * Check whether or not a given move on a given board is valid. For takes,
	 * the piece being taken must be supplied.
	 *
	 * @param oldPosition
	 *            --- position of this piece before move.
	 * @param newPosition
	 *            --- position of this piece after move.
	 * @param isTaken
	 *            --- piece being taken, or null if no piece taken.
	 * @param board
	 *            --- board on which the validity of this move is being checked.
	 * @return
	 */
	public boolean isValidMove(Position oldPosition,
			Position newPosition, Piece isTaken, Board board);

	/**
	 * This checks if the piece can check the opponent's King in its current move.
	 * @param board  - the current board.
	 * @param currentPos - the current position of the piece.
	 * @return true if the piece can check the opponent's King in its current move; false otherwise.
	 */
	public boolean canCheck(Board board, Position currentPos);
}
