package chessPiece;

import chessBoard.Square;

/**
 * King can move at most 1 square apart
 */
public class King extends Piece
{	
	public King(int isWhite)
	{
		row = -1;
		col = -1;
		this.isWhite = isWhite;
		isAlive = false;
		pieceNumber = 1;
	}
	
	public boolean isValidMove(Square[][] grid, int newRow, int newCol)
	{
		if(Math.abs(row-newRow)<=1 && Math.abs(col-newCol)<=1)
			if(isBlocked(grid, newRow, newCol) == false)
				return true;
	
		return false;
	}
	
	protected boolean isBlocked(Square[][] grid, int newRow, int newCol)
	{
		return isSameSpot(newRow, newCol);
	}
}
