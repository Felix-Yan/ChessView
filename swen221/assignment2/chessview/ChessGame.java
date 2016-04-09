package swen221.assignment2.chessview;

import java.util.*;

import swen221.assignment2.chessview.moves.*;
import swen221.assignment2.chessview.pieces.*;

import java.io.*;

/**
 * This class represents a game of chess, which is essentially just a list moves
 * that make up the game.
 *
 * @author djp
 *
 */
public class ChessGame {
	private ArrayList<Round> rounds;

	public ChessGame(String sheet) throws IOException {
		this(new StringReader(sheet));
	}

	/**
	 * Construct a ChessGame object from a given game sheet, where each round
	 * occurs on a new line.
	 *
	 * @param gameSheet
	 */
	public ChessGame(Reader input) throws IOException {
		rounds = new ArrayList<Round>();

		BufferedReader reader = new BufferedReader(input);

		// First, read in the commands
		String line;
		while((line = reader.readLine()) != null) {//this will read the string line by line. Thus delimited by "\n".
			if(line.equals("")) { continue; } // skip blank lines
			int pos = line.indexOf(' ');
			if(pos == -1) { pos = line.length(); }//By Felix: this means no space. Only white move exists.
			Move white = moveFromString(line.substring(0,pos),true);
			Move black = null;
			if(pos != line.length()) {//black move exists. pos is at the ' '.
				black = moveFromString(line.substring(pos+1),false);
			}
			rounds.add(new Round(white,black));
		}
	}

	public List<Round> rounds() {
		return rounds;
	}

	/**
	 * This method computes the list of boards which make up the game. If an
	 * invalid move, or board is encountered then a RuntimeException is thrown.
	 *
	 * @return
	 */
	public List<Board> boards() {
		ArrayList<Board> boards = new ArrayList<Board>();
		Board b = new Board();//the board at the start of the game before anyone moves
		boards.add(b);
		boolean lastTime = false;
		for(Round r : rounds) {
			if (lastTime) { return boards; }
			b = new Board(b);
			if(!b.apply(r.white())) { return boards; }
			boards.add(b);
			if(r.black() != null) {
				b = new Board(b);
				if(!b.apply(r.black())) { return boards; }
				boards.add(b);
			} else {//if black does not move any more, the last white move must be the last move
				lastTime = true;
			}
		}
		return boards;
	}

	/**
	 * Construct a move object from a given string.
	 *
	 * @param str
	 * @return
	 */
	private static Move moveFromString(String str, boolean isWhite) {
		Piece piece;
		int index = 0;
		char lookahead = str.charAt(index);

		switch(lookahead) {
			case 'N':
				piece = new Knight(isWhite);
				index++;
				break;
			case 'B':
				piece = new Bishop(isWhite);
				index++;
				break;
			case 'R':
				piece = new Rook(isWhite);
				index++;
				break;
			case 'K':
				piece = new King(isWhite);
				index++;
				break;
			case 'Q':
				piece = new Queen(isWhite);
				index++;
				break;
			case 'O':
				if(str.equals("O-O")) {
					return new Castling(isWhite,true);
				} else if(str.equals("O-O-O")) {
					return new Castling(isWhite,false);
				} else {
					throw new IllegalArgumentException("invalid sheet");
				}
			default:
				piece = new Pawn(isWhite);
		}

		Position start = positionFromString(str.substring(index,index+2));//By Felix: eg. e2
		char moveType = str.charAt(index+2);//By Felix: moveType is either 'x' or '-'
		Piece target = null;
		index = index + 3;//By Felix: now the index is at the start of the black move

		if(moveType == 'x') {//By felix: piece taking move
			lookahead = str.charAt(index);
			switch(lookahead) {
				case 'N':
					target = new Knight(!isWhite);
					index++;
					break;
				case 'B':
					target = new Bishop(!isWhite);
					index++;
					break;
				case 'R':
					target = new Rook(!isWhite);
					index++;
					break;
				case 'K':
					target = new King(!isWhite);
					index++;
					break;
				case 'Q':
					target = new Queen(!isWhite);
					index++;
					break;
				default:
					target = new Pawn(!isWhite);
			}
		} else if(moveType != '-') {
			throw new IllegalArgumentException("invalid sheet");
		}

		Position end = positionFromString(str.substring(index,index+2));
		index = index + 2;//now index is at the end of this move string.

		Move move;

		if(target != null) {
			move = new SinglePieceTake(piece,target,start,end);
		} else {
			move = new SinglePieceMove(piece,start,end);
		}

		//en passant has ep as suffix
		if((index+1) < str.length() && str.charAt(index) == 'e' && str.charAt(index+1) == 'p') {
			move = new EnPassant((SinglePieceMove) move);//TODO
			index+=2;
		} else if((index+1) < str.length() && str.charAt(index)=='=') {
			lookahead = str.charAt(index+1);
			Piece promotion;
			switch(lookahead) {
				case 'N':
					promotion = new Knight(isWhite);
					break;
				case 'B':
					promotion = new Bishop(isWhite);
					break;
				case 'R':
					promotion = new Rook(isWhite);
					break;
				case 'K':
					promotion = new King(isWhite);
					break;
				case 'Q':
					promotion = new Queen(isWhite);
					break;
				default:
					throw new IllegalArgumentException("invalid sheet");
			}
			move = new PawnPromotion((SinglePieceMove) move,promotion);//TODO
			index+=2;
		}

		if(index < str.length() && str.charAt(index) == '+') {
			move = new Check((MultiPieceMove) move);
			index++;
		} else {
			move = new NonCheck((MultiPieceMove) move);
		}

		if(index != str.length()) {
			System.out.println("index: "+index);//debug
			System.out.println("String is: "+str);//debug
			System.out.println("String length is: "+str.length());
			throw new IllegalArgumentException("invalid sheet");
		}

		return move;
	}

	/**
	 * parsing a string of length 2 to be a position.
	 * @param pos
	 * @return
	 */
	private static Position positionFromString(String pos) {
		if(pos.length() != 2) {
			throw new IllegalArgumentException("invalid position: " + pos);
		}
		int col = (pos.charAt(0) - 'a') + 1;
		int row = Integer.parseInt(pos.substring(1,2));
		return new Position(row,col);
	}
}
