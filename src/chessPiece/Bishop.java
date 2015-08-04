package chessPiece;

import chessBoard.Square;

/**
 * Bishop moves diagonally
 */
public class Bishop extends Piece
{
	public Bishop(int isWhite)
	{
		row = -1;
		col = -1;
		this.isWhite = isWhite;
		isAlive = false;
		pieceNumber = 5;
	}
	
	public boolean isValidMove(Square[][] grid, int newRow, int newCol)
	{
		if(Math.abs(row-newRow) == Math.abs(col-newCol))
			if(isBlocked(grid, newRow, newCol) == false)
				return true;
		
		return false;
	}
	
	protected boolean isBlocked(Square[][] grid, int newRow, int newCol)
	{
		if(isSameSpot(newRow, newCol) == true)
			return true;
		
		return isBishopBlocked(grid, newRow, newCol);
	}
}
