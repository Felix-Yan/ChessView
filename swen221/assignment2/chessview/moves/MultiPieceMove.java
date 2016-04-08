package swen221.assignment2.chessview.moves;

import java.util.*;

import swen221.assignment2.chessview.*;
import swen221.assignment2.chessview.pieces.Piece;

/**
 * A MultiPieceMove represents a simple move operation involving one or more
 * pieces.
 *
 * @author djp
 *
 */
public interface MultiPieceMove extends Move {
	/**
	 * Is this move checking the opponent's King?
	 * @param board - the current board.
	 * @return true if the move is a check; false otherwise.
	 */
	public boolean isChecking(Board board);
}
