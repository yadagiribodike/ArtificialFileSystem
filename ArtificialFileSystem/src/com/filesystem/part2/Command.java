package com.filesystem.part2;

/**
 * @author yadhi
 *
 */
public interface Command {

	public Object accept(CommandVisitor visitor);
}
