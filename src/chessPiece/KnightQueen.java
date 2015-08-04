package chessPiece;

import chessBoard.Square;

/**
 * KnightQueen can move either diagonally, horizontally, or vertically.
 * So KnightQueen can move either as Bishop or Rook but it can "jump" 
 */
public class KnightQueen extends Piece
{
	public KnightQueen(int isWhite)
	{
		row = -1;
		col = -1;
		this.isWhite = isWhite;
		isAlive = false;
		pieceNumber = 7;
	}

	public boolean isValidMove(Square[][] grid, int newRow, int newCol)
	{
		if(Math.abs(row-newRow) == Math.abs(col-newCol))
		{
			if(isBlocked(grid, newRow, newCol) == false)
				return true;
		}
		if(Math.abs(row-newRow)>0 ^ Math.abs(col-newCol)>0)
		{
			if(isBlocked(grid, newRow, newCol) == false)
				return true;
		}
		return false;
	}

	protected boolean isBlocked(Square[][] grid, int newRow, int newCol)
	{
		if(isSameSpot(newRow, newCol) == true)
			return true;
		else
			return false;
	}
}
