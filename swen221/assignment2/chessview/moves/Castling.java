package swen221.assignment2.chessview.moves;

import swen221.assignment2.chessview.*;
import swen221.assignment2.chessview.pieces.*;

public class Castling implements MultiPieceMove {	
	private boolean kingSide;
	
	public Castling(boolean isWhite, boolean kingSide) {		
		this.kingSide = kingSide;
	}
	
	public boolean isWhite() {
		return false;
	}
	
	public void apply(Board board) {	
		// hmmm
	}
	
	public boolean isValid(Board board) {
		return true;		
	}		
	
	public String toString() {
		if(kingSide) {
			return "O-O";
		} else {
			return "O-O-O";
		}
	}
}
