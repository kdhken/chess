package chessGui.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chessBoard.Board;
import chessGui.ChessView;
import chessPiece.Piece;

/**
 * Enables Actions for all the buttons in the BoardPanel
 * 1. Each square gets action for piece movement
 * 2. It colors the square which piece can move
 * 3. If piece can capture, color the square as orange
 */
public class SquareListener
{
	private int currentRow;
	private int currentCol;
	private boolean isClicked = false;
	private Board board;
	private ChessView view;
	private CommandList commands;
	private MenuListener menuListener;
	
	/*Constructor*/
	public SquareListener(ChessView view, Board board, CommandList commands, MenuListener menuListener)
	{
		this.menuListener = menuListener;
		this.view = view;
		this.board = board;
		this.commands = commands;
		squareAction();
	}
	
	public SquareListener(ChessView view, Board board, CommandList commands)
	{
		this.view = view;
		this.board = board;
		this.commands = commands;
		squareAction();
	}

	private void squareAction()
	{
		for(int row=0; row<8; row++)
			for(int col=0; col<8; col++)
			{
				final int rowFinal = row;
				final int colFinal = col;			
				view.getBoardPanel().getSquares(row, col).addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{			
						checkMove(rowFinal, colFinal);
					}
				});
			}
	}
	
	private void checkMove(int row, int col)
	{
		if(isClicked == true)
		{
			if(getPiece(row,col)==null)
				updateBoardPanelAndControl(row, col);
			else if(getPiece(currentRow,currentCol).getIsWhite() == getPiece(row,col).getIsWhite())
				updateMovableArea(row, col);
			else
				updateBoardPanelAndControl(row, col);
		}			
		else
		{
			if(getPiece(row,col)==null || board.getTurn() != getPiece(row,col).getIsWhite())
				return;
			else
			{
				updateMovableArea(row, col);
			}
		}
	}

	private void updateMovableArea(int row, int col)
	{
		view.updateBoardPanel(getPiece(row,col));
		SquareListener squareListener = new SquareListener(view, board, commands, menuListener);
		squareListener.setCurrentRow(row);
		squareListener.setCurrentCol(col);
		squareListener.setClicked(true);
		view.updateFrame();
	}
	
	private void updateBoardPanelAndControl(int row, int col)
	{
		int canMove = board.canMove(getPiece(currentRow,currentCol),row,col);
		if(canMove == 1 || canMove == 2)
		{
			MoveAction command = new MoveAction(view,board,currentRow,currentCol,row,col,commands, menuListener);
			commands.addCommand(command);
			command.execute();
		}
		else
		{
			view.updateBoardPanel(null);
			new SquareListener(view, board, commands, menuListener);
			view.updateFrame();
		}
		isClicked = false;
	}
	
	private Piece getPiece(int row, int col)
	{
		return board.getGrid(row, col).getPiece();
	}
	
	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public void setCurrentCol(int currentCol) {
		this.currentCol = currentCol;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	
}
