package chessGui.Control;

import javax.swing.JOptionPane;


/**
 * Contains pop up menu for the Game ending conditions
 *
 */
public class GameEndListener
{	
	public void whiteCheckMate()
	{
		JOptionPane.showMessageDialog(null, "Check Mate! Black Player won");
	}
	
	public void blackCheckMate()
	{
		JOptionPane.showMessageDialog(null, "Check Mate! White Player won");
	}
	
	public void whiteCheck()
	{
		JOptionPane.showMessageDialog(null, "White Player checked!");
	}
	
	public void blackCheck()
	{
		JOptionPane.showMessageDialog(null, "Black Player checked!");
	}
	
	public void staleMate()
	{
		JOptionPane.showMessageDialog(null, "Stale Mate! Draw!");
	}
}
