package chessBoard;

import chessPiece.*;
import chessGame.*;

/**
 * 1. It has two player.
 * 2. It keeps track of all the pieces with Piece array called grid.
 * 3. It keeps track of the turn
 * 4. It has constructor which allow you to create board with size you provide. (Normally, always 8x8)
 * 5. It has Move method that allows you to move the piece.
 * 6. It has isKingChecked method that checks game ending condition
 * 7. (0,0) is upper left and (7,7)is down right
 */
public class Board
{
	private Player [] players;
	private int turn;
	private Square[][] grid;
	private int gridRow;
	private int gridCol;
	
	public int getTurn() {
		return turn;
	}
	public Square getGrid(int row, int col) {
		return grid[row][col];
	}
	public int getGridRow() {
		return gridRow;
	}
	public int getGridCol() {
		return gridCol;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	/*Constructor*/
	public Board(int numRows, int numCols, Player Wplayer, Player Bplayer)
	{
		grid = new Square[numRows][numCols];
		for(int row=0; row<numRows; row++)
			for(int col=0; col<numCols; col++)
				grid[row][col] = new Square(null);
		gridRow = numRows;
		gridCol = numCols;
		turn = 1;//White player goes first
		players = new Player[2];
		players[1] = Wplayer;
		players[0] = Bplayer;
	}
	
	/**
	 * Sets the piece on the board(This method is for unit test)
	 * @param piece: piece you want to set
	 * @param newRow: row value you want to set
	 * @param newCol: column value you want to set
	 * @return true if piece is successfully set.  Otherwise, false
	 */
	public boolean setPiece(Piece piece, int row, int col)
	{
		if(isInBound(row,col)==false)
			return false;

		if(grid[row][col].piece==null)
		{
			piece.isAlive = true;
			movePiece(piece, row, col);
			return true;
		}
		
		else
			return false;
	}
	public void setCustomPiece(String playerS, String pieceTypeS, String rowS, String colS) throws Exception
	{
		int player = Integer.parseInt(playerS);
		int pieceType = Integer.parseInt(pieceTypeS);
		int row = Integer.parseInt(rowS);
		int col = Integer.parseInt(colS);
		
		if(grid[row][col].piece!=null)
		{
			grid[row][col].piece.isAlive = false;
			grid[row][col].piece.row = -1;
			grid[row][col].piece.col = -1;
			grid[row][col].piece = null;
		}
		setPiece(players[player].getPieces(pieceType),row,col);
	}
	
	/**
	 * This method just check whether the piece can move or not.
	 * @param piece: piece you want to move
	 * @param newRow: new row value to move
	 * @param newCol: new column value to move
	 * @return
	 * 0. If the piece cannot move
	 * 1. If the piece can move
	 * 2. If the piece can capture 
	 */
	public int canMove(Piece piece, int newRow, int newCol)
	{
		if(isInBound(newRow,newCol) == false)
			return 0;
		if(piece.getIsWhite() != turn)
			return 0;
		if(piece.isValidMove(grid, newRow, newCol) == false)
			return 0;
		if(grid[newRow][newCol].piece == null)
		{
			if(isKingSafe(piece, newRow, newCol) == true)
				return 1;
			else
				return 0;
		}
		else
		{
			if(piece.getIsWhite() == grid[newRow][newCol].piece.getIsWhite())
				return 0;
			if(isKingSafe(piece, newRow, newCol) == true)
				return 2;
			else
				return 0;
		}
	}
	
	/**
	 * Before move the piece, check for invalid things
	 * 1. Check bound
	 * 2. Check turn
	 * 3. Check whether player is checked
	 * 4. Check for piece valid move
	 * @param piece: piece you want to move
	 * @param newRow: new row value to move
	 * @param newCol: new column value to move
	 * @return true if piece successful moved.  Otherwise, false 
	 */
	public boolean move(Piece piece, int newRow, int newCol)
	{
		if(isInBound(newRow,newCol) == false)
			return false;
		if(piece.getIsWhite() != turn)
			return false;
		if(piece.isValidMove(grid, newRow, newCol) == false)
			return false;
		
		if(grid[newRow][newCol].piece == null)
		{
			if(isKingSafe(piece, newRow, newCol) == true)
			{
				movePiece(piece, newRow, newCol);
				pawnFirstMove(piece);
				turn = turn^1;
				return true;
			}
			else
				return false;
		}
		else
		{
			if(piece.getIsWhite() == grid[newRow][newCol].piece.getIsWhite())
				return false;
			if(isKingSafe(piece, newRow, newCol) == true)
			{
				capture(piece, newRow, newCol);
				pawnFirstMove(piece);
				turn = turn^1;
				return true;
			}
			else
				return false;
		}
	}
	private void pawnFirstMove(Piece piece) {
		if(piece.getPieceNumber()==6 && piece.getIsFirst()==1)
			piece.setIsFirst(0);
	}
	
	/**
	 * Helper functions for Move method
	 * 1. Check the bound
	 * 2. Actual method that moves the piece
	 * 3. Update the dangerous zone
	 * 3. Check whether the move makes king checked. This is invalid move
	 * 3. Check for Game ending conditions
	 */
	private boolean isInBound(int newRow, int newCol)
	{
		if(0 <= newRow && newRow < gridRow &&
		   0 <= newCol && newCol < gridCol)
			return true;
		else
			return false;
	}
	
	private void movePiece(Piece piece, int newRow, int newCol)
	{
		if(piece.row != -1 && piece.col != -1)
			grid[piece.row][piece.col].piece = null;		
		grid[newRow][newCol].piece = piece;
		piece.row = newRow;
		piece.col = newCol;
		setDangerousZone();
	}
	
	private void capture(Piece piece, int newRow, int newCol) {
		grid[newRow][newCol].piece.isAlive = false;
		grid[newRow][newCol].piece.row = -1;
		grid[newRow][newCol].piece.col = -1;
		movePiece(piece, newRow, newCol);
	}	
	
	private void setDangerousZone()
	{
		for(int row=0; row<gridRow; row++)
			for(int col=0; col<gridCol; col++)
			{
				grid[row][col].attackZone[1] = 0;
				grid[row][col].attackZone[0] = 0;
				for(int pieceI=0; pieceI<18; pieceI++)
				{
					if(checkAttackZone(1, row, col, pieceI))
						grid[row][col].attackZone[1] = 1;
				
					if(checkAttackZone(0, row, col, pieceI))
						grid[row][col].attackZone[0] = 1;
				}
			}
	}
	private boolean checkAttackZone(int player, int row, int col, int pieceI)
	{
		return players[player].getPieces(pieceI).isAlive == true &&
			   players[player].getPieces(pieceI).isValidMove(grid, row, col)==true;
	}
	
	private boolean isKingSafe(Piece piece, int newRow, int newCol)
	{
		int currentRow = piece.row;
		int currentCol = piece.col;
		Piece originalPiece = grid[newRow][newCol].piece;
		if(originalPiece==null)
			movePiece(piece, newRow, newCol);
		else
			capture(piece, newRow, newCol);
		
		setDangerousZone();		
		if(grid[getKingRow(turn)][getKingCol(turn)].attackZone[turn^1] == 1)
		{
			rewind(piece, newRow, newCol, currentRow, currentCol, originalPiece);
			return false;
		}	
		rewind(piece, newRow, newCol, currentRow, currentCol, originalPiece);
		return true;
	}

	public void rewind(Piece piece, int newRow, int newCol, int currentRow, int currentCol, Piece originalPiece)
	{
		movePiece(piece, currentRow, currentCol);
		grid[newRow][newCol].piece=originalPiece;
		
		if(originalPiece!=null)
		{
			originalPiece.isAlive = true;
			originalPiece.row = newRow;
			originalPiece.col = newCol;
		}
		setDangerousZone();
	}
	
	/**
	 * Then nested for loop check every square that King can move
	 * This loop check CheckMate,Check at the same time.
	 * @param player: player to be checked for condition
	 * @return
	 * 0. Nothing
	 * 1. Black Player CheckMate
	 * 2. Black Player Checked.
	 * 3. White Player CheckMate
	 * 4. White Player Checked.
	 */
	public int isKingChecked(int player)
	{
		int checkMate = 1;
		int check = 0;
		for(int kingRow=-1; kingRow<2; kingRow++)
		{
			for(int kingCol=-1; kingCol<2; kingCol++) 
			{
				if(isInBound(getKingRow(player)+kingRow, getKingCol(player)+kingCol)==false)
					continue;
				else if(grid[getKingRow(player)+kingRow][getKingCol(player)+kingCol].attackZone[player^1] == 1)
				{
					if(kingRow==0 && kingCol==0)
						check = 1;
				}
				else if(grid[getKingRow(player)+kingRow][getKingCol(player)+kingCol].piece == null)
					checkMate = 0;
				else if(grid[getKingRow(player)+kingRow][getKingCol(player)+kingCol].piece.getIsWhite()!=player)
					checkMate = 0;
			}
		}//end of two nested for loop
		if(player == 0)
		{
			if(checkMate == 1 && check == 1)
				return isCheckMate(player);
			else if(check == 1)
				return 2;
		}
		else
		{
			if(checkMate == 1 && check == 1)
				return isCheckMate(player) + 2;
			else if(check == 1)
				return 4;
		}
		return 0;
	}

	private int getKingRow(int player)
	{
		return players[player].getPieces(0).row;
	}
	
	private int getKingCol(int player)
	{
		return players[player].getPieces(0).col;
	}

	private int isCheckMate(int player)
	{
		for(int row=0; row<gridRow; row++)
			for(int col=0; col<gridCol; col++)
				if(grid[row][col].attackZone[player] == 1)
					if(canProtectKing(player, row, col))
						return 2;//check
		return 1;//checkMate
	}
	
	private boolean canProtectKing(int player, int row, int col)
	{
		for(int pieceI=1; pieceI<18; pieceI++)
		{
			if(checkAttackZone(player, row, col, pieceI))
			{
				if(grid[row][col].getPiece() == null)
				{
					if(isKingSafe(players[player].getPieces(pieceI), row, col))
						return true;
				}
				else if(grid[row][col].getPiece().getIsWhite() != player)
				{
					if(isKingSafe(players[player].getPieces(pieceI), row, col))
						return true;
				}
			}
		}
		return false;
	}
	
}
