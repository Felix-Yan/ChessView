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
	
	public PawnPromotion(SinglePieceMove move, Piece promotion) {						
	}
	
	public boolean isWhite() {
		return false;
	}
	
	public boolean isValid(Board board) {				
		return false;
	}
	
	public void apply(Board board) {
	}
	
	public String toString() {
		return super.toString() + "=" + SinglePieceMove.pieceChar(promotion);
	}
}
