package com.filesystem.part1;

/**
 * @author yadhi
 *
 */
public class File implements Component {

	private String name;
	private Directory parentDir;
	private long size;
	private String filePath;

	public File(String name, Directory parentDir, long size) {
		this.name = name;
		this.parentDir = parentDir;
		this.size = size;
		this.filePath = parentDir.getDirPath();
	}

	public String getFilePath() {
		return filePath + this.name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public DirOrFileComponent getType() {
		return DirOrFileComponent.FILE;
	}

	@Override
	public Directory getParentDir() {
		return this.parentDir;
	}

	@Override
	public long getSize() {
		return this.size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Override
	public Component getChildren(String name) {
		// File do not have children
		return null;
	}

	@Override
	public String toString() {
		return name + "\t" + size + "\t<" + getType() + ">";
	}
}
