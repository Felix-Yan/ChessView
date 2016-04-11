package swen221.assignment2.chessview;

import java.util.HashMap;
import java.util.Map;

import swen221.assignment2.chessview.moves.Move;
import swen221.assignment2.chessview.pieces.*;

public class Board {
	private Piece[][] pieces; // this is the underlying data structure for a board.
	private Position whiteKingPos;
	private Position blackKingPos;
	private boolean whiteChecked;//Is the white king checked?
	private boolean blackChecked;//Is the black king checked?
	private boolean aboutToCheckWhite;
	private boolean aboutToCheckBlack;
	//the position of the pawn that has just moved two squares.
	//used for validity check of enPassant.
	private Position jumpPawnPos;
	//is pawn just jumped? Used for resetting jumpPawnPos.
	private boolean pawnJumped;

	/*
	 * The map holds the positions of kings and rooks. The corresponding boolean value tells us if the castling
	 *  is valid or not.
	 */
	private Map<Position, Boolean> castlingValid = new HashMap<Position, Boolean>();




	/**
	 * Construct an initial board.
	 */
	public Board() {
		pieces = new Piece[9][9];

		for(int i=1;i<=8;++i) {
			pieces[2][i] = new Pawn(true);
			pieces[7][i] = new Pawn(false);
		}

		// rooks
		pieces[1][1] = new Rook(true);
		pieces[1][8] = new Rook(true);
		pieces[8][1] = new Rook(false);
		pieces[8][8] = new Rook(false);

		// knights
		pieces[1][2] = new Knight(true);
		pieces[1][7] = new Knight(true);
		pieces[8][2] = new Knight(false);
		pieces[8][7] = new Knight(false);

		// bishops
		pieces[1][3] = new Bishop(true);
		pieces[1][6] = new Bishop(true);
		pieces[8][3] = new Bishop(false);
		pieces[8][6] = new Bishop(false);

		// king + queen
		pieces[1][4] = new Queen(true);
		pieces[1][5] = new King(true);
		pieces[8][4] = new Queen(false);
		pieces[8][5] = new King(false);

		whiteKingPos = new Position(1,5);
		blackKingPos = new Position(8,5);

		initializeCastlingValid();
	}


	/**
	 * Construct a board which is identical to another board.
	 *
	 * @param board
	 */
	public Board(Board board) {
		this.pieces = new Piece[9][9];
		for(int row=1;row<=8;++row) {
			for(int col=1;col<=8;++col) {
				this.pieces[row][col] = board.pieces[row][col];
			}
		}
		//Pass the King positions to the new identical board as well
		//Also pass the checked booleans
		this.blackKingPos = board.getKingPos(false);
		this.whiteKingPos = board.getKingPos(true);
		if(board.whiteChecked) this.setChecked(true);
		if(board.blackChecked) this.setChecked(false);
		//Pass the castlingValid map
		this.castlingValid = board.getCastlingValid();
		//Pass the fields for enPassant
		this.pawnJumped = board.pawnJumped;
		this.jumpPawnPos = board.jumpPawnPos;
	}

	/**
	 * This initialize the castlingValid Map by putting positions of kings and rooks with true boolean.
	 */
	public void initializeCastlingValid(){
		//add king positions. They are valid for castling as they haven't moved.
		castlingValid.put(new Position(1,5), true);
		castlingValid.put(new Position(8,5), true);

		//add rook positions.
		castlingValid.put(new Position(1,1), true);
		castlingValid.put(new Position(1,8), true);
		castlingValid.put(new Position(8,1), true);
		castlingValid.put(new Position(8,8), true);
	}

	/**
	 * Apply a given move to this board, returning true is successful, otherwise
	 * false.
	 *
	 * @param move
	 * @return
	 */
	public boolean apply(Move move) {
		/*
		 * singlePieceMove/Take.isValid() -> piece.isValidMove()
		 * Should check no king is checked as well.
		 * singlePieceMove.apply() -> board.move()
		 */
		if(move.isValid(this)) {
			if(!isChecked()){
				move.apply(this);
				if(aboutToCheckWhite){
					this.whiteChecked = true;
					aboutToCheckWhite = false;
				}
				else if(aboutToCheckBlack){
					this.blackChecked = true;
					aboutToCheckBlack = false;
				}

				//this is to make sure there are no check moves disguised as noncheck
				if( (isInCheck(true,this) || isInCheck(false, this)) && !isChecked() ){
					return false;
				}
				//checks if there should be a promotion
				return !lackingPromotion(this);
			}else{
				//if a king is in check, the current move has to solve this problem. Otherwise invalid.
				Board newBoard = new Board(this);
				move.apply(newBoard);
				if(!isInCheck(move.isWhite(),newBoard)){
					move.apply(this);
					removeChecked();
					//checks if there should be a promotion
					return !lackingPromotion(this);
				}else{
					return false;
				}
			}
		} else {
			return false;
		}
	}

	/**
	 * Move a piece from one position to another.
	 *
	 * @param oldPosition
	 * @param newPosition
	 */
	public void move(Position oldPosition, Position newPosition) {
		Piece p = pieces[oldPosition.row()][oldPosition.column()];
		//this will do the take action as it will overwrite the taken piece
		pieces[newPosition.row()][newPosition.column()] = p;
		pieces[oldPosition.row()][oldPosition.column()] = null;

		if(!pawnJumped && jumpPawnPos != null){
			jumpPawnPos = null;//reset the jumpPawnPos to null if pawn jump was not the opponent's last move.
		}
	}

	public void setPieceAt(Position pos, Piece piece) {
		pieces[pos.row()][pos.column()] = piece;
	}

	public Piece pieceAt(Position pos) {
		return pieces[pos.row()][pos.column()];
	}

	public String toString() {
		String r = "";
		for(int row=8;row!=0;row--) {
			r += row + "|";
			for(int col=1;col<=8;col++) {
				Piece p = pieces[row][col];
				if(p != null) {
					r += p + "|";
				} else {
					r += "_|";
				}
			}
			r += "\n";
		}
		return r + "  a b c d e f g h";
	}

	/**
	 * This checks that there is not a pawn waiting for promotion.
	 * @param board - the current board
	 * @return true if there is promotion lacking; false otherwise.
	 */
	public boolean lackingPromotion(Board board){
		for(int col=1; col <= 8; ++col){
			int row = 1;
			Position pos = new Position(row, col);
			Piece p = board.pieceAt(pos);
			if(p instanceof Pawn){
				if(!p.isWhite()) return true;
			}
		}
		for(int col=1; col <= 8; ++col){
			int row = 8;
			Position pos = new Position(row, col);
			Piece p = board.pieceAt(pos);
			if(p instanceof Pawn){
				if(p.isWhite()) return true;
			}
		}
		return false;
	}

	/**
	 * This method determines whether or not one side is in check.
	 *
	 * @param isWhite
	 *            --- true means check whether white is in check; otherwise,
	 *            check black.
	 * @return
	 */
	public boolean isInCheck(boolean isWhite, Board board) {
		//First, find my king
		//now it is more efficient and clear with my code
		Position kingPos = board.getKingPos(isWhite);
		King king = (King)(board.pieceAt(kingPos));

		// Second, check opposition pieces to see whether they can take
		// my king or not.  If one can, we're in check!
		for (int row = 1; row <= 8; ++row) {
			for (int col = 1; col <= 8; ++col) {
				Position pos = new Position(row, col);
				Piece p = board.pieceAt(pos);
				// If this is an opposition piece, and it can take my king,
				// then we're definitely in check.
				if (p != null && p.isWhite() != isWhite
						&& p.isValidMove(pos, kingPos, king, board)) {
					// p can take opposition king, so we're in check.
					return true;
				}
			}
		}

		// couldn't find any piece in check.
		return false;
	}

	/**
	 * The following method checks whether the given diaganol is completely
	 * clear, except for a given set of pieces. Observe that this doesn't
	 * guarantee a given diaganol move is valid, since this method does not
	 * ensure anything about the relative positions of the given pieces.
	 *
	 * @param startPosition - start of diaganol
	 * @param endPosition - end of diaganol
	 * @param exceptions - the list of pieces allowed on the diaganol
	 * @return
	 */
	//this checks no other pieces will block the diagonal move
	public boolean clearDiaganolExcept(Position startPosition,
			Position endPosition, Piece... exceptions) {
		int startCol = startPosition.column();
		int endCol = endPosition.column();
		int startRow = startPosition.row();
		int endRow = endPosition.row();
		int diffCol = Math.max(startCol,endCol) - Math.min(startCol,endCol);
		int diffRow = Math.max(startRow,endRow) - Math.min(startRow,endRow);

		if(diffCol != diffRow
				//&& diffCol == 0 //I think this is unnecessary
				) {
			return false;
		}


		int row = startRow;
		int col = startCol;
		while(row != endRow && col != endCol) {
			Piece p = pieces[row][col];
			if(p != null && !contains(p,exceptions)) {
				return false;
			}
			col = col <= endCol ? col + 1 : col - 1;
			row = row <= endRow ? row + 1 : row - 1;
		}

		return true;
	}

	/**
	 * The following method checks whether the given column is completely
	 * clear, except for a given set of pieces. Observe that this doesn't
	 * guarantee a given column move is valid, since this method does not
	 * ensure anything about the relative positions of the given pieces.
	 *
	 * @param startPosition - start of column
	 * @param endPosition - end of column
	 * @param exceptions - the list of pieces allowed on the column
	 * @return
	 */
	public boolean clearColumnExcept(Position startPosition,
			Position endPosition, Piece... exceptions) {
		int minCol = Math.min(startPosition.column(), endPosition.column());
		int maxCol = Math.max(startPosition.column(), endPosition.column());
		int minRow = Math.min(startPosition.row(), endPosition.row());
		int maxRow = Math.max(startPosition.row(), endPosition.row());
		int diffCol = maxCol - minCol;
		int diffRow = maxRow - minRow;

		if(diffCol != 0 || diffRow == 0) {//move should be on the same column.
			return false;
		}

		int row = minRow;
		while(row <= maxRow) {
			Piece p = pieces[row][minCol];
			if(p != null && !contains(p,exceptions)) {
				return false;
			}
			row++;
		}

		return true;
	}

	/**
	 * The following method checks whether the given row is completely
	 * clear, except for a given set of pieces. Observe that this doesn't
	 * guarantee a given row move is valid, since this method does not
	 * ensure anything about the relative positions of the given pieces.
	 *
	 * @param startPosition - start of row
	 * @param endPosition - end of row
	 * @param exceptions - the list of pieces allowed on the row
	 * @return
	 */
	public boolean clearRowExcept(Position startPosition,
			Position endPosition, Piece... exceptions) {
		int minCol = Math.min(startPosition.column(), endPosition.column());
		int maxCol = Math.max(startPosition.column(), endPosition.column());
		int minRow = Math.min(startPosition.row(), endPosition.row());
		int maxRow = Math.max(startPosition.row(), endPosition.row());
		int diffCol = maxCol - minCol;
		int diffRow = maxRow - minRow;

		if(diffRow != 0 || diffCol == 0) {//move should be on the same row
			return false;
		}

		int col = minCol;
		while(col <= maxCol) {
			Piece p = pieces[minRow][col];
			if(p != null && !contains(p,exceptions)) {
				return false;
			}
			col++;
		}

		return true;
	}

	// Helper method for the clear?????Except methods above.
	private static boolean contains(Piece p1, Piece... pieces) {
		for(Piece p2 : pieces) {
			if(p1 == p2) {
				return true;
			}
		}

		return false;
	}


	/**
	 * This sets if the king is going to be checked after this move.
	 * @param isWhite - true if the white king is going to be checked; false if the black king is going to be checked.
	 */
	public void aboutToCheck(boolean isWhite){
		if(isWhite){
			this.aboutToCheckWhite = true;
		}else{
			this.aboutToCheckBlack = true;
		}
	}

	/**
	 * This sets if the king is checked at the moment.
	 * @param isWhite - true if the white king is going to be checked; false if the black king is going to be checked.
	 */
	public void setChecked(boolean isWhite){
		if(isWhite){
			this.whiteChecked = true;
		}else{
			this.blackChecked = true;
		}
	}

	/**
	 * This sets both whiteChecked and blackChecked to be false.
	 */
	public void removeChecked(){
		this.whiteChecked = false;
		this.blackChecked = false;
	}

	/**
	 * This checks if a king is checked at the moment.
	 * @return true if either the white king or the black king is checked; false otherwise.
	 */
	public boolean isChecked(){
		if(whiteChecked || blackChecked){
			return true;
		}
		return false;
	}

	/**
	 * This gets the position of the King based on the isWhite boolean.
	 * @param isWhite - this tells the color of the King to be returned.
	 * @return whiteKingPos if isWhite is true; blackKingPos otherwise.
	 */
	public Position getKingPos(boolean isWhite){
		if(isWhite){
			return whiteKingPos;
		}else{
			return blackKingPos;
		}
	}

	/**
	 * This sets the position of the King based on the isWhite boolean. Set the white King if isWhite is true; set
	 * the black King otherwise.
	 * @param newPosition - the new position that the King should be set to.
	 * @param isWhite - true if the King is white; false otherwise.
	 */
	public void setKingPos(Position newPosition, boolean isWhite){
		if(isWhite){
			this.whiteKingPos = newPosition;
		}else{
			this.blackKingPos = newPosition;
		}
	}

	/**
	 * This updates the castlingValid map if any of the king or rook has moved.
	 * Make the corresponding boolean value false.
	 * @param oldPosition - the old position of the piece moved
	 */
	public void updateCastlingValid(Position oldPosition){
		castlingValid.put(oldPosition, false);
	}


	/**
	 * Getter for castlingValid.
	 * @return the castlingValid
	 */
	public Map<Position, Boolean> getCastlingValid() {
		return this.castlingValid;
	}


	/**
	 * Getter for jumpPawnPos.
	 * @return the jumpPawnPos
	 */
	public Position getJumpPawnPos() {
		return jumpPawnPos;
	}


	/**
	 * Setter for jumpPawnPos;
	 * @param jumpPawnPos the jumpPawnPos to set
	 */
	public void setJumpPawnPos(Position jumpPawnPos) {
		this.jumpPawnPos = jumpPawnPos;
	}


	/**
	 * Getter for pawnJumped.
	 * @return the pawnJumped
	 */
	public boolean isPawnJumped() {
		return pawnJumped;
	}


	/**
	 * Setter for pawnJumped.
	 * @param pawnJumped the pawnJumped to set
	 */
	public void setPawnJumped(boolean pawnJumped) {
		this.pawnJumped = pawnJumped;
	}

	/**
	 * This sets the piece at the given position to be null.
	 * @param pos
	 */
	public void setPieceNull(Position pos){
		pieces[pos.row()][pos.column()] = null;
	}


}
