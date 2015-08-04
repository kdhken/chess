package chessGui.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import chessBoard.Board;
import chessGame.Game;
import chessGui.ChessView;
import chessPiece.Piece;

/**
 * Enables Actions for all the buttons in the MenuPanel
 * 1. start button should start the game with player's name
 * 2. restart button restart the game without changing the score
 * 3. forfeit button quit the game and change score
 * 4. undo button undo the last movement
 */

public class MenuListener
{
	private JTextField WnameInput = new JTextField();
	private JTextField BnameInput = new JTextField();
	private Object[] names = {
	"Black Player: ", BnameInput,
	"White Player: ", WnameInput,
	};
	
	private JTextField rowInput = new JTextField();
	private JTextField colInput = new JTextField();
	private JTextField pieceInput = new JTextField();
	private JTextField playerInput = new JTextField();
	private Object[] custom = {
	"1: White 0: Black", playerInput,
    "16: KnightQueen, 17: Protector", pieceInput,
	"row: ", rowInput,
	"column: ", colInput,
	};
	
	JFrame frame;
	private CommandList commands;
	boolean isStarted = false;
	String whiteName, blackName;
	int Bscore = 0, Wscore = 0;
	ChessView view;
	Board board;

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	/*Constructor*/
	public MenuListener(ChessView view, JFrame frame, CommandList commands, Board board)
	{
		this.board = board;
		this.frame = frame;
		this.view = view;
		this.commands = commands;
		startAction(view.getMenuPanel().getStartButton());
		restartAction(view.getMenuPanel().getRestartButton());
		blackForfeitAction(view.getMenuPanel().getBlackForfeitButton());
		whiteForfeitAction(view.getMenuPanel().getWhiteForfeitButton());
		undoAction(view.getMenuPanel().getUndoButton());
		customPieceAction(view.getMenuPanel().getCustomPieceButton());
	}
	
	private void startAction(JButton button)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int option = JOptionPane.showConfirmDialog(null, names, "Enter names for players", JOptionPane.OK_CANCEL_OPTION);
				if(option == JOptionPane.OK_OPTION)
				{
					whiteName = WnameInput.getText();
					blackName = BnameInput.getText();
					setNewGame(0,0);
					frame.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "Need player's name to start");
			}
		});
	}
	
	private void restartAction(JButton button)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(isStarted == false)
					JOptionPane.showMessageDialog(null, "Need to start game first");
				else
				{
					int option = JOptionPane.showConfirmDialog(null, "Do you want to restart Game???",
						    "Restart",
						    JOptionPane.YES_NO_OPTION);
					if(option == JOptionPane.OK_OPTION)
					{
						setNewGame(Bscore,Wscore);
						frame.setVisible(true);
					}
					else
						return;
				}
			}
		});
	}
	
	private void blackForfeitAction(JButton button)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(isStarted == false)
					JOptionPane.showMessageDialog(null, "Need to start game first");
				else
				{
					playerWon(1);
					JOptionPane.showMessageDialog(null, "White Player Won!");
				}
			}
		});
	}
	
	private void whiteForfeitAction(JButton button)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(isStarted == false)
					JOptionPane.showMessageDialog(null, "Need to start game first");
				else
				{
					playerWon(0);
					JOptionPane.showMessageDialog(null, "Black Player Won!");
				}
			}
		});
	}
	
	private void undoAction(JButton button)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Command command = commands.popCommand();
				if(command == null)
					JOptionPane.showMessageDialog(null, "There is nothing to Undo");
				else
					command.undo();
			}
		});
	}
	
	private void customPieceAction(JButton button)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int option = JOptionPane.showConfirmDialog(null, custom, "Enter custom piece option", JOptionPane.OK_CANCEL_OPTION);
				if(option == JOptionPane.OK_OPTION)
				{
					try {
						board.setCustomPiece(playerInput.getText(), pieceInput.getText(), rowInput.getText(), colInput.getText());
					}
					catch (Exception e1) {
						return;
					}
					view.updateBoardPanel(null);
					new SquareListener(view, board, commands);
					view.updateFrame();
				}
				else
					JOptionPane.showMessageDialog(null, "Need player's name to start");
			}
		});
	}
	
	private void setNewGame(int Bscore, int Wscore)
	{
		Game game = new Game();
		ChessView view = new ChessView(game.getBoard(), frame);
		ChessControl chessControl = new ChessControl(game.getBoard(), view, frame);
		chessControl.getMenuListener().setStarted(true);
		view.updateInfoPanel(whiteName, blackName,Bscore,Wscore);
		
		chessControl.getMenuListener().setBlackName(blackName);
		chessControl.getMenuListener().setWhiteName(whiteName);
		chessControl.getMenuListener().setBscore(Bscore);
		chessControl.getMenuListener().setWscore(Wscore);
	}
	
	public void playerWon(int player)
	{
		if(player == 1)
		{
			Wscore++;
			view.updateInfoPanel(whiteName, blackName,Bscore,Wscore);
		}
		else
		{
			Bscore++;
			view.updateInfoPanel(whiteName, blackName,Bscore,Wscore);
		}	
		frame.setVisible(true);
	}
	
	public void setBlackName(String blackName) {
		this.blackName = blackName;
	}

	public void setWhiteName(String whiteName) {
		this.whiteName = whiteName;
	}
	
	public void setBscore(int bscore) {
		Bscore = bscore;
	}

	public void setWscore(int wscore) {
		Wscore = wscore;
	}
}
