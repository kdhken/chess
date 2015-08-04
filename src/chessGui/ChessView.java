package chessGui;

import java.awt.*;
import javax.swing.*;
import chessBoard.Board;
import chessPiece.Piece;

/**
 * For MVC architecture, this class is for the View
 * So it creates Frame and then get Panel from BoardPanel
 */
public class ChessView
{
	JFrame frame;
	JPanel mainPanel;
	BoardPanel boardPanel;
	InfoPanel infoPanel;
	MenuPanel menuPanel;
	Board board;
	
	/*Constructor*/
	public ChessView(Board board, JFrame frame)
	{
		this.board = board;
		this.frame = frame;
		mainPanel = new JPanel(new BorderLayout());
		
        boardPanel = new BoardPanel(board, null);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        
        String Wname = "White";
    	String Bname = "Black";
        infoPanel = new InfoPanel(Wname, Bname, 0, 0);
        mainPanel.add(infoPanel, BorderLayout.EAST);
        
        menuPanel = new MenuPanel();
        mainPanel.add(menuPanel, BorderLayout.PAGE_START);
        
        frame.setContentPane(mainPanel);  
	}
	
	public void updateBoardPanel(Piece piece)
	{
		BoardPanel newBoardPanel = new BoardPanel(board, piece);
		mainPanel.remove(boardPanel);
		boardPanel = newBoardPanel;
        mainPanel.add(boardPanel, BorderLayout.CENTER);
	}
	
	public void updateInfoPanel(String Wname, String Bname, int Bscore, int Wscore)
	{
		InfoPanel newInfoPanel = new InfoPanel(Wname, Bname, Bscore, Wscore);
		mainPanel.remove(infoPanel);
		infoPanel = newInfoPanel;
        mainPanel.add(infoPanel, BorderLayout.EAST);
	}
	
	public void updateMenuPanel()
	{
		MenuPanel newMenuPanel = new MenuPanel();
		mainPanel.remove(menuPanel);
		menuPanel = newMenuPanel;
        mainPanel.add(menuPanel, BorderLayout.PAGE_START);
	}
	
	public void updateFrame()
	{
		frame.setVisible(true);
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	public InfoPanel getInfoPanel(){
		return infoPanel;
	}
	
	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	public MenuPanel getMenuPanel() {
		return menuPanel;
	}
}