package com.filesystem.part2;

/**
 * @author yadhi
 *
 */
public interface CommandVisitor {

	public boolean visit(DelCommand delCommand);
	
	public long visit(SizeCommand sizeCommand);
	
	public String visit(LsCommand lsCommand);
	
	public boolean visit(ResizeCommand resizeCommand);
	
	public boolean visit(ExitCommand exitCommand);
}
