package chessPiece;

import chessBoard.Square;

/**
 * Abstract class for all the pieces in the chess
 * Main method is just isValidMove method
 */
public abstract class Piece
{
	protected int pieceNumber;
	protected int isWhite;
	protected int isFirst;//this is just for Pawn
	public int row;
	public int col;
	public boolean isAlive;
	
	public int getPieceNumber() {
		return pieceNumber;
	}
	public int getIsWhite() {
		return isWhite;
	}
	public int getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(int isFirst) {
		this.isFirst = isFirst;
	}
	
	/**
	 * 
	 * @param grid: grid of the board to get location of all pieces
	 * @param newRow: new row coordinate to move
	 * @param newCol: new column coordinate to move
	 * @return: returns true when it is valid to move to new square.  Otherwise, false
	 */
	public abstract boolean isValidMove(Square[][] grid, int newRow, int newCol);
	
	/**
	 * Helper function for isValidMove method
	 * 1. Check for piece blocking on the way
	 * 2. Check for bishop movement
	 * 3. Check for rook movement
	 */
	protected abstract boolean isBlocked(Square[][] grid, int newRow, int newCol);
	protected boolean isSameSpot(int newRow, int newCol)
	{
		if(newRow == row && newCol == col)
			return true;
		
		return false;
	}
	
	protected boolean isBishopBlocked(Square[][] grid, int newRow, int newCol)
	{
		int rowDirection = newRow - row;
		int colDirection = newCol - col;
		
		if(rowDirection>0 && colDirection>0)
		{
			for(int rowI=row+1, colI=col+1; rowI<newRow || colI<newCol; rowI++, colI++)
				if(grid[rowI][colI].getPiece() != null)
					return true;
		}
		
		else if(rowDirection>0 && colDirection<0)
		{
			for(int rowI=row+1, colI=col-1; rowI<newRow || colI>newCol; rowI++, colI--)
				if(grid[rowI][colI].getPiece() != null)
					return true;
		}
		
		else if(rowDirection<0 && colDirection<0)
		{
			for(int rowI=row-1, colI=col-1; rowI>newRow || colI>newCol; rowI--, colI--)
				if(grid[rowI][colI].getPiece() != null)
					return true;
		}
	
		else if(rowDirection<0 && colDirection>0)
		{
			for(int rowI=row-1, colI=col+1; rowI>newRow || colI<newCol; rowI--, colI++)
				if(grid[rowI][colI].getPiece() != null)
					return true;
		}

		return false;
	}
	
	protected boolean isRookBlocked(Square[][] grid, int newRow, int newCol)
	{
		int rowDirection = newRow - row;
		int colDirection = newCol - col;
		
		if(rowDirection>0)
		{
			for(int rowI=row+1; rowI<newRow; rowI++)
				if(grid[rowI][col].getPiece() != null)
					return true;
		}
		
		else if(rowDirection<0)
		{
			for(int rowI=row-1; rowI>newRow; rowI--)
				if(grid[rowI][col].getPiece() != null)
					return true;
		}
		
		else if(colDirection>0)
		{
			for(int colI=col+1; colI<newCol; colI++)
				if(grid[row][colI].getPiece() != null)
					return true;
		}
		
		else if(colDirection<0)
		{
			for(int colI=col-1; colI>newCol; colI--)
				if(grid[row][colI].getPiece() != null)
					return true;
		}
		
		return false;
	}
}
