package com.filesystem.part2;

import com.filesystem.part1.FileSystem;

/**
 * @author yadhi
 *
 */
public class CommandVisitorImpl implements CommandVisitor {

	private FileSystem fileSystemDisk;

	public CommandVisitorImpl() {
		fileSystemDisk = FileSystem.getFileSystemDisk();
	}

	@Override
	public boolean visit(DelCommand delCommand) {
		//return fileSystemDisk.removeEntity(delCommand.getName());
		return fileSystemDisk.removeEntityByPath(delCommand.getPath());
	}

	@Override
	public long visit(SizeCommand sizeCommand) {
		return fileSystemDisk.getSize(sizeCommand.getName());
	}

	@Override
	public String visit(LsCommand lsCommand) {
		if (lsCommand.getName() == null) {
			return fileSystemDisk.getCurrentDirInfo();
		} else {
			return fileSystemDisk.getInfo(lsCommand.getName());
		}
	}

	@Override
	public boolean visit(ResizeCommand resizeCommand) {
		return fileSystemDisk.resize(resizeCommand.getName(), resizeCommand.getSize());
	}

	@Override
	public boolean visit(ExitCommand exitCommand) {
		fileSystemDisk.exit();
		return true;
	}

}
