package com.filesystem.part2;

/**
 * @author yadhi
 *
 */
public class LsCommand implements Command {

	private String name;

	public LsCommand() {
	}
	
	public LsCommand(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String accept(CommandVisitor visitor) {
		return visitor.visit(this);
	}

}
