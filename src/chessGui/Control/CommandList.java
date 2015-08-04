package chessGui.Control;

import java.util.ArrayList;
import java.util.List;

/**
 * CommandList contains all the command that is done by game
 * CommandList has two method
 * 1. addCommand: add command to CommandList
 * 2. popCommand: pop command from CommandList
 */
public class CommandList
{
	private List<Command> commands;

	/*Constructor*/
	public CommandList()
	{
		commands = new ArrayList<Command>();
	}
	
	public void addCommand(Command command)
	{
		commands.add(command);
	}
	
	public Command popCommand()
	{
		if(commands.size()==0)
			return null;
		Command rtrCommand = commands.get(commands.size()-1);
		commands.remove(commands.size()-1);
		return rtrCommand;
	}
}
