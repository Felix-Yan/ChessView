package swen221.assignment2.chessview.moves;

import java.util.*;

import swen221.assignment2.chessview.*;
import swen221.assignment2.chessview.pieces.*;

public class SinglePieceMove implements MultiPieceMove {
	protected Piece piece;
	protected Position oldPosition;
	protected Position newPosition;

	public SinglePieceMove(Piece piece, Position oldPosition, Position newPosition) {
		this.piece = piece;
		this.oldPosition = oldPosition;
		this.newPosition = newPosition;
	}

	public Piece piece() {
		return piece;
	}

	public boolean isWhite() {
		return piece.isWhite();
	}

	public Position oldPosition() {
		return oldPosition;
	}

	public Position newPosition() {
		return newPosition;
	}

	public boolean isValid(Board board) {
		//the move has to be inside the board
		int newRow = newPosition.row();
		int newCol = newPosition.column();
		if(newRow < 1 || newRow > 8) return false;
		if(newCol < 1 || newCol > 8) return false;
		return piece.isValidMove(oldPosition, newPosition, null, board);
	}

	public void apply(Board b) {
		b.move(oldPosition,newPosition);
		b.setPawnJumped(false);//set the flag to be false to reset pawnJumpedPos after completing next move.
	}

	public String toString() {
		return pieceChar(piece) + oldPosition + "-" + newPosition;
	}

	protected static String pieceChar(Piece p) {
		if(p instanceof Pawn) {
			return "";
		} else if(p instanceof Knight) {
			return "N";
		} else if(p instanceof Bishop) {
			return "B";
		} else if(p instanceof Rook) {
			return "R";
		} else if(p instanceof Queen) {
			return "Q";
		} else {
			return "K";
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isChecking(Board board) {
		return piece.canCheck(board, newPosition);
	}
}
