package chessGui.Control;

/**
 * Command Interface that is used for undo
 *
 */
public interface Command
{
	public void execute();
	public void undo();
}
