package chessGame;

import chessPiece.*;

/**
 * Each player gets initial 16 pieces.
 */
public class Player
{
	private Piece [] pieces;
	
	public Piece getPieces(int pieceNumber) {
		return pieces[pieceNumber];
	}

	/*Constructor*/
	public Player(int isWhite)
	{
		pieces = new Piece[18];
		pieces[0] = new King(isWhite);
		pieces[1] = new Queen(isWhite);
		pieces[2] = new Rook(isWhite);
		pieces[3] = new Rook(isWhite);
		pieces[4] = new Knight(isWhite);
		pieces[5] = new Knight(isWhite);
		pieces[6] = new Bishop(isWhite);
		pieces[7] = new Bishop(isWhite);
		for(int pawnI=8; pawnI<16; pawnI++)
			pieces[pawnI] = new Pawn(isWhite);
		pieces[16] = new KnightQueen(isWhite);
		pieces[17] = new Protector(isWhite);
	}

}
