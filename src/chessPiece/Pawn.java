package chessPiece;

import chessBoard.Square;

/**
 * Pawn can move only forward.
 * It can move diagonally only on capturing
 */
public class Pawn extends Piece
{
	public Pawn(int isWhite)
	{
		row = -1;
		col = -1;
		this.isWhite = isWhite;
		isAlive = false;
		pieceNumber = 6;
		isFirst = 1;
	}

	/**
	 * 1. Pawn can move diagonally forward only when it is capturing
	 * 2. Not capturing
	 *  basic: Pawn cannot move horizontally.  It cannot move vertically backward
	 *  (1) Pawn can move 2+ in column at first move
	 *  (2) Pawn can move only 1+ in column after first move.
	 * */
	public boolean isValidMove(Square[][] grid, int newRow, int newCol)
	{
		if(grid[newRow][newCol].getPiece()!=null && Math.abs(row-newRow)==1)
			return canCapture(grid, newRow, newCol);
		
		if(Math.abs(row-newRow)!=0)
			return false;
		if(isWhite==0 && (newCol-col)<0)
			return false;
		if(isWhite==1 && (col-newCol)<0)
			return false;

		if(isFirst == 1)
			return canPawnMove(grid, newRow, newCol, 2);	
		else if(isFirst != 1)
			return canPawnMove(grid, newRow, newCol, 1);
	
		return false;
	}

	private boolean canCapture(Square[][] grid, int newRow, int newCol)
	{
		if(grid[newRow][newCol].getPiece().isWhite != isWhite)
		{
			if(isWhite==0 && (newCol-col)==1)
				return true;
			if(isWhite==1 && (col-newCol)==1)
				return true;
		}
		return false;
	}

	private boolean canPawnMove(Square[][] grid, int newRow, int newCol, int move)
	{
		if(isWhite==0 && (newCol-col)<=move)
			if(isBlocked(grid, newRow, newCol) == false)
				return true;
		if(isWhite==1 && (col-newCol)<=move)
			if(isBlocked(grid, newRow, newCol) == false)
				return true;
		return false;
	}
	
	protected boolean isBlocked(Square[][] grid, int newRow, int newCol)
	{
		if(isSameSpot(newRow, newCol) == true)
			return true;	
		if(grid[newRow][newCol].getPiece() != null)
			return true;
		if(this.isFirst==1)
		{
			if(isWhite==0 && grid[row][col+1].getPiece() != null)
				return true;
			if(isWhite==1 && grid[row][col-1].getPiece() != null)
				return true;
		}		
		return false;
	}
}
