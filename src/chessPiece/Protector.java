package chessPiece;

import chessBoard.Square;

/**
 * Protector can move as Rook but it must have only one piece between the current square and the new square
 */
public class Protector extends Piece
{
	public Protector(int isWhite)
	{
		row = -1;
		col = -1;
		this.isWhite = isWhite;
		isAlive = false;
		pieceNumber = 8;
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

		int rowDirection = newRow - row;
		int colDirection = newCol - col;
		int pieceCount=0;
		
		if(rowDirection>0)
		{
			for(int rowI=row+1; rowI<newRow; rowI++)
				if(grid[rowI][col].getPiece() != null)
					pieceCount++;
		}
		
		else if(rowDirection<0)
		{
			for(int rowI=row-1; rowI>newRow; rowI--)
				if(grid[rowI][col].getPiece() != null)
					pieceCount++;
		}
		
		else if(colDirection>0)
		{
			for(int colI=col+1; colI<newCol; colI++)
				if(grid[row][colI].getPiece() != null)
					pieceCount++;
		}
		
		else if(colDirection<0)
		{
			for(int colI=col-1; colI>newCol; colI--)
				if(grid[row][colI].getPiece() != null)
					pieceCount++;
		}
		
		if(pieceCount==1)
			return false;
		else
			return true;
	}
}
