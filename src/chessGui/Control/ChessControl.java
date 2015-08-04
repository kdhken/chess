package chessGui.Control;

import javax.swing.JFrame;

import chessBoard.Board;
import chessGui.ChessView;

/**
 * For MVC architecture, this class is for the Control.
 * This class manages events for button clicks.
 * It initially provides ActionListner for every button.
 */
public class ChessControl
{	
	MenuListener menuListener;
	
	/*Constructor*/
	public ChessControl(Board board, ChessView view, JFrame frame)
	{	
		CommandList commands = new CommandList();
		menuListener = new MenuListener(view, frame, commands, board);
		new SquareListener(view, board, commands, menuListener);
	}
	
	public MenuListener getMenuListener() {
		return menuListener;
	}
}
