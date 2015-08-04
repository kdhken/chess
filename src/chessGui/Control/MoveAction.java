package chessGui.Control;

import chessBoard.Board;
import chessGui.ChessView;
import chessPiece.Piece;

/**
 * MoveAction is for handling piece movement
 * This class implements Command interface to manage undo
 */
public class MoveAction implements Command
{
	Board board;
	int currentRow, currentCol, newRow, newCol;
	ChessView view;
	CommandList commands;
	Piece piece;
	Piece originalPiece;
	GameEndListener gameEnd = new GameEndListener();
	MenuListener menuListener;
	
	/*Constructor*/
	public MoveAction(ChessView view, Board board, int currentRow, int currentCol,
			          int newRow, int newCol, CommandList commands, MenuListener menuListener)
	{
		this.view = view;
		this.board = board;
		this.currentRow =currentRow;
		this.currentCol =currentCol;
		this.newRow = newRow;
		this.newCol = newCol;
		this.commands = commands;
		this.menuListener = menuListener;
	}
	
	/**
	 * Execute the Command
	 * It moves the piece on the board
	 */
	public void execute()
	{
		piece = getPiece(currentRow, currentCol);
		originalPiece = getPiece(newRow,newCol);
		board.move(piece,newRow,newCol);
		view.updateBoardPanel(null);
		new SquareListener(view, board, commands, menuListener);
		view.updateFrame();
		
		checkForEnd();
		
	}

	private void checkForEnd() {
		int condition = board.isKingChecked(board.getTurn());
		if(condition == 1)
		{
			menuListener.playerWon(1);
			gameEnd.blackCheckMate();
		}
		else if(condition == 2) gameEnd.blackCheck();
		else if(condition == 3) 
		{
			menuListener.playerWon(1);
			gameEnd.whiteCheckMate();
		}
		else if(condition == 4) gameEnd.whiteCheck();
	}

	/**
	 * Undo the Command
	 * It undo the last movement on the board
	 */
	public void undo()
	{
		board.rewind(piece, newRow, newCol, currentRow, currentCol, originalPiece);
		checkPawnFirstMove();
		board.setTurn(board.getTurn()^1);
		view.updateBoardPanel(null);
		new SquareListener(view, board, commands, menuListener);
		view.updateFrame();	
	}

	private void checkPawnFirstMove()
	{
		if(piece.getPieceNumber()==6)
		{
			if(piece.getIsWhite()==1 && currentCol==6)
				piece.setIsFirst(1);
			else if(piece.getIsWhite()==0 && currentCol==1)
				piece.setIsFirst(1);
		}
		else
			return;
	}
	
	private Piece getPiece(int row, int col)
	{
		return board.getGrid(row, col).getPiece();
	}

}
