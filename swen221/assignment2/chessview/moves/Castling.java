package swen221.assignment2.chessview.moves;

import swen221.assignment2.chessview.*;
import swen221.assignment2.chessview.pieces.*;

public class Castling implements MultiPieceMove {
	private boolean kingSide;
	private boolean isWhite;

	private Piece king = null;
	private Piece rook = null;
	private Position kingPos = null;
	private Position rookPos = null;

	public Castling(boolean isWhite, boolean kingSide) {
		this.kingSide = kingSide;
		this.isWhite = isWhite;
	}

	/**
	 * A getter for isWhite field.
	 * @return true if the move by the white side; false otherwise.
	 */
	public boolean isWhite() {
		return this.isWhite;
	}

	/**
	 * This applies the change of Castling to the board.
	 * @param board - the current board
	 */
	public void apply(Board board) {
		if(kingSide){
			board.move(kingPos, new Position(kingPos.row(), kingPos.column()+2));
			board.move(rookPos, new Position(rookPos.row(), rookPos.column()-2));
		}else{
			board.move(kingPos, new Position(kingPos.row(), kingPos.column()-2));
			board.move(rookPos, new Position(rookPos.row(), rookPos.column()+3));
		}
	}

	/**
	 * This checks if the Castling is valid
	 * @return true if valid; false otherwise
	 */
	public boolean isValid(Board board) {
		//Castling cannot be performed if the king is in check
		if(board.isInCheck(isWhite, board)) return false;

		if(isWhite){
			kingPos = new Position(1,5);
			king = board.pieceAt(kingPos);
			if(kingSide){
				rookPos = new Position(1,8);
				rook = board.pieceAt(rookPos);
			}else {
				rookPos = new Position(1,1);
				rook = board.pieceAt(rookPos);
			}
		}else{
			kingPos = new Position(8,5);
			king = board.pieceAt(kingPos);
			if(kingSide){
				rookPos = new Position(8,8);
				rook = board.pieceAt(rookPos);
			}else{
				rookPos = new Position(8,1);
				rook = board.pieceAt(rookPos);
			}
		}
		//Castling cannot be performed if the king has moved
		if(board.isKingMoved()) return false;

		//Castling cannot be performed if the rook has moved
		if(board.isRookMoved()) return false;

		//Castling cannot be performed if there are other pieces between the king and the rook
		if(!board.clearRowExcept(kingPos, rookPos, king, rook)) return false;


		return true;
	}

	public String toString() {
		if(kingSide) {
			return "O-O";
		} else {
			return "O-O-O";
		}
	}

	/**
	 * A castling is not a checking.
	 * @param board - the current board.
	 * @return false always.
	 */
	@Override
	public boolean isChecking(Board board) {
		return false;
	}
}
