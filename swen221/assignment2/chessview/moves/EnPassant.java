package swen221.assignment2.chessview.moves;

import swen221.assignment2.chessview.*;
import swen221.assignment2.chessview.pieces.*;

/**
 * This represents an "en passant move" --- http://en.wikipedia.org/wiki/En_passant.
 * 
 * @author djp
 * 
 */
public class EnPassant implements Move {
	public EnPassant(SinglePieceMove move) {				
	}
	
	public boolean isWhite() {
		return false;
	}
	
	public boolean isValid(Board board) {		
		return true;
	}
	
	public void apply(Board board) {		
	}
	
	public String toString() {
		return "ep";
	}
}
