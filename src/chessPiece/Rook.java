package chessPiece;

import chessBoard.Square;

/**
 * Rook can only move linearly.  (Up, down) or (Left, Right)
 */
public class Rook extends Piece
{
	public Rook(int isWhite)
	{
		row = -1;
		col = -1;
		this.isWhite = isWhite;
		isAlive = false;
		pieceNumber = 3;
	}

	public boolean isValidMove(Square[][] grid, int newRow, int newCol)
	{
		if(Math.abs(row-newRow)>0 ^ Math.abs(col-newCol)>0)
			if(isBlocked(grid, newRow, newCol) == false)
				return true;
		
		return false;
	}
	
	protected boolean isBlocked(Square[][] grid, int newRow, int newCol)
	{
		if(isSameSpot(newRow, newCol) == true)
			return true;
		
		return isRookBlocked(grid, newRow, newCol);
	}
}
