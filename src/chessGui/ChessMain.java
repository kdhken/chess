package chessGui;

import javax.swing.JFrame;

import chessBoard.Board;
import chessGame.Player;
import chessGui.Control.ChessControl;

/**
 * Combines all the MVC and displays the GUI
 */
public class ChessMain
{	
	public static void main(String s[])
    {	
		JFrame frame = new JFrame("Ryan's Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900,800);
	    frame.setLocationRelativeTo(null);
		
	    Player Wplayer = new Player(1);
	    Player Bplayer = new Player(0);
	    Board board = new Board(8,8,Wplayer,Bplayer);
		ChessView startView = new ChessView(board, frame);
		new ChessControl(board, startView, frame);
		
		frame.setMinimumSize(frame.getSize());
		frame.setVisible(true);

    }

}
