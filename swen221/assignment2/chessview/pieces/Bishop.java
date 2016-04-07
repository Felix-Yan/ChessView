package swen221.assignment2.chessview.pieces;

import swen221.assignment2.chessview.*;

public class Bishop extends PieceImpl implements Piece {
	public Bishop(boolean isWhite) {
		super(isWhite);
	}

	public boolean isValidMove(Position oldPosition, Position newPosition,
			Piece isTaken, Board board) {
		Piece p = board.pieceAt(oldPosition);
		Piece t = board.pieceAt(newPosition);
		return this.equals(p)
				//the taken piece claimed is the same as the piece at the newPosition
				&& (t == isTaken || (isTaken != null && isTaken.equals(t)))
				//TODO why checking column and row?
				&& (board.clearDiaganolExcept(oldPosition, newPosition, p, t)
						//|| board.clearColumnExcept(oldPosition, newPosition, p,t)
						//  || board.clearRowExcept(oldPosition,newPosition, p, t)
						);
	}

	public String toString() {
		if(isWhite) {
			return "B";
		} else {
			return "b";
		}
	}
}
