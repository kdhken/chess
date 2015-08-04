package chessGui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import chessBoard.Board;

/**
 * On the main Panel, this class provides the menu Panel
 */
public class MenuPanel extends JPanel
{
	JButton startButton;
	JButton restartButton;
	JButton blackForfeitButton;
	JButton whiteForfeitButton;
	JButton undoButton;
	JButton customPieceButton;
	JLabel turnLabel;
	
	/*Constructor*/
	public MenuPanel()
	{
		setLayout(new GridLayout(0,1));
		JToolBar menu = new JToolBar();
		menu.setFloatable(false);
		startButton = new JButton("Start");
		restartButton = new JButton("Restart");
		blackForfeitButton = new JButton("Black Forfeit");
		whiteForfeitButton = new JButton("White Forfeit");
		undoButton = new JButton("Undo");
		customPieceButton = new JButton("Custom Piece");
		menu.add(startButton);
		menu.add(restartButton);
		menu.add(blackForfeitButton);
		menu.add(whiteForfeitButton);
		menu.addSeparator();
		menu.add(customPieceButton);
		menu.addSeparator();
		menu.add(undoButton);
		
		
		add(menu);
	}
	
	public JButton getStartButton() {
		return startButton;
	}

	public JButton getRestartButton() {
		return restartButton;
	}

	public JButton getBlackForfeitButton() {
		return blackForfeitButton;
	}
	
	public JButton getWhiteForfeitButton() {
		return whiteForfeitButton;
	}
	
	public JButton getUndoButton() {
		return undoButton;
	}
	
	public JButton getCustomPieceButton() {
		return customPieceButton;
	}

}
