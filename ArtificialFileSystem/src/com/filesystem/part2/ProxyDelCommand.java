package com.filesystem.part2;

/**
 * @author yadhi
 *
 */
public class ProxyDelCommand implements Command {

	private String path;
	private DelCommand delCommand;

	public ProxyDelCommand(String path) {
		this.path = path;
	}

	@Override
	public Boolean accept(CommandVisitor visitor) {
		if (delCommand == null) {
			delCommand = new DelCommand(path);			//if no delete was executed before exit, creates a new DelCommand
			return false;
		} else {
			return visitor.visit(delCommand);			//if 
		}
	}

}