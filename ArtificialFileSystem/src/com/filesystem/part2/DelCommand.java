package com.filesystem.part2;

/**
 * @author yadhi
 *
 */
public class DelCommand implements Command {

	private String path;

	public DelCommand(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}

	@Override
	public Boolean accept(CommandVisitor visitor) {
		return visitor.visit(this);
	}

}
