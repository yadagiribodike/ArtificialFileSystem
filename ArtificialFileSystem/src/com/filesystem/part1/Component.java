package com.filesystem.part1;

/**
 * @author yadhi
 *
 */
public interface Component {

	public String getName();

	public DirOrFileComponent getType();

	public Directory getParentDir();

	public long getSize();

	public Component getChildren(String name);

}