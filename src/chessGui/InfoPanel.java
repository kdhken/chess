package chessGui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * On the main Panel, this class provides the info Panel
 * InfoPanel shows player's name and score
 */
public class InfoPanel extends JPanel
{
	private JPanel blackPlayerPanel;
	private JPanel whitePlayerPanel;
	private JLabel BplayerLabel;
	private JLabel WplayerLabel;
	
	/*Constructor*/
    public InfoPanel(String Wname, String Bname, int Bscore, int Wscore)
    {
    	blackPlayerPanel = new JPanel(new GridLayout(2,0));
        BplayerLabel = new JLabel(Bname, JLabel.CENTER);
        BplayerLabel.setFont(new Font("Serif", Font.BOLD, 40));
        blackPlayerPanel.add(BplayerLabel);  
        JLabel BscoreLabel = new JLabel(String.valueOf(Bscore),  JLabel.CENTER);
        BscoreLabel.setFont(new Font("Serif", Font.BOLD, 40));
        blackPlayerPanel.add(BscoreLabel);
        
        whitePlayerPanel = new JPanel(new GridLayout(2,0));
        WplayerLabel = new JLabel(Wname, JLabel.CENTER);
        WplayerLabel.setFont(new Font("Serif", Font.BOLD, 40));
        whitePlayerPanel.add(WplayerLabel);
        JLabel WscoreLabel = new JLabel(String.valueOf(Wscore),  JLabel.CENTER);
        WscoreLabel.setFont(new Font("Serif", Font.BOLD, 40));
        whitePlayerPanel.add(WscoreLabel);
        
        
        setLayout(new GridLayout(2,0));
        add(blackPlayerPanel);
        add(whitePlayerPanel);
        setPreferredSize(new Dimension(200, 400));
    }
}
