package chessPiece;

import chessBoard.Square;

/**
 * Queen can move either diagonally, horizontally, or vertically.
 * So Queen can move either as Bishop or Rook
 */
public class Queen extends Piece
{
	public Queen(int isWhite)
	{
		row = -1;
		col = -1;
		this.isWhite = isWhite;
		isAlive = false;
		pieceNumber = 2;
	}

	public boolean isValidMove(Square[][] grid, int newRow, int newCol)
	{
		if(Math.abs(row-newRow) == Math.abs(col-newCol))
			if(isBlocked(grid, newRow, newCol) == false)
				return true;

		if(Math.abs(row-newRow)>0 ^ Math.abs(col-newCol)>0)
			if(isBlocked(grid, newRow, newCol) == false)
				return true;

		return false;
	}
	
	protected boolean isBlocked(Square[][] grid, int newRow, int newCol)
	{
		if(isSameSpot(newRow, newCol) == true)
			return true;
		
		if(Math.abs(row-newRow) == Math.abs(col-newCol))
			return isBishopBlocked(grid, newRow, newCol);
		
		else
			return isRookBlocked(grid, newRow, newCol);
	}
}
