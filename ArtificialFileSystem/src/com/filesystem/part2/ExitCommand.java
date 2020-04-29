package com.filesystem.part2;

/**
 * @author yadhi
 *
 */
public class ExitCommand implements Command {

	@Override
	public Boolean accept(CommandVisitor visitor) {
		return visitor.visit(this);
	}

}
