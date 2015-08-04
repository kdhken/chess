package chessPiece;

import chessBoard.Square;

/**
 * Knight can move in L shape. It can also jump
 */
public class Knight extends Piece
{
	public Knight(int isWhite)
	{
		row = -1;
		col = -1;
		this.isWhite = isWhite;
		isAlive = false;
		pieceNumber = 4;
	}
	
	public boolean isValidMove(Square[][] grid, int newRow, int newCol)
	{
		if( (Math.abs(row-newRow)==2 && Math.abs(col-newCol)==1) ||
		    (Math.abs(row-newRow)==1 && Math.abs(col-newCol)==2)   )
		{
			if(isBlocked(grid, newRow, newCol) == true);
				return true;
		}

		return false;
	}
	
	protected boolean isBlocked(Square[][] grid, int newRow, int newCol)
	{
		return isSameSpot(newRow, newCol);
	}
}
