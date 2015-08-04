package chessBoard;
import chessPiece.*;

/**
 * 1. Each square of the board can have one piece.
 * 2. Each Player has attackZone for squares where they can attack.
 */
public class Square
{
	protected Piece piece;
	public int [] attackZone;
	
	public Piece getPiece() {
		return piece;
	}
	
	/*Constructor*/
	public Square(Piece piece)
	{
		this.piece = piece;
		attackZone = new int[2];
	}

}
