package com.filesystem.part1;

import java.util.ArrayList;
import java.util.List;

import com.filesystem.part2.Command;
import com.filesystem.part2.CommandVisitor;
import com.filesystem.part2.CommandVisitorImpl;
import com.filesystem.part2.ExitCommand;
import com.filesystem.part2.LsCommand;
import com.filesystem.part2.ProxyDelCommand;
import com.filesystem.part2.ResizeCommand;
import com.filesystem.part2.SizeCommand;
import com.filesystem.part3.DirectoryTreeDisplay;
import com.filesystem.part3.TreeDisplay;

/**
 * @author yadhi
 *
 */
public class ConcreteBuilder implements Builder {

	private FileSystem fileSystemDisk;
	private CommandVisitor visitor;
	private List<Command> proxyDelCommands;
	private TreeDisplay display;

	public ConcreteBuilder() {
		fileSystemDisk = FileSystem.getFileSystemDisk();
		visitor = new CommandVisitorImpl();
		proxyDelCommands = new ArrayList<Command>();			//arraylist to store the details about the deleted components
		display = new DirectoryTreeDisplay();
	}

	@Override
	public void cd(String dirName) {
		fileSystemDisk.explore(dirName);
	}

	@Override
	public void mkdir(String dirName) {
		fileSystemDisk.addDir(dirName);
	}

	@Override
	public void create(String fileName, long size) {
		fileSystemDisk.addFile(fileName, size);
	}

	@Override
	public void del(String name) {
		Component entity = fileSystemDisk.findEntity(name);
		if(entity != null) {
			String path = entity.getType() == DirOrFileComponent.DIR ? ((Directory) entity).getDirPath() : ((File) entity).getFilePath();
			Command proxyDelCommand = new ProxyDelCommand(path);
			proxyDelCommand.accept(visitor);
			proxyDelCommands.add(proxyDelCommand);    //instead of deleting the file storing the details of the components to delete when exit command is given as input
		}
	}

	@Override
	public void ls() {
		Command lsCommand = new LsCommand();
		String info = (String) lsCommand.accept(visitor);
		System.out.println(info.trim());
	}

	@Override
	public void ls(String name) {
		Command lsCommand = new LsCommand(name);
		String info = (String) lsCommand.accept(visitor);
		System.out.println(info.trim());
	}

	@Override
	public void size(String name) {
		Command sizeCommand = new SizeCommand(name);
		long size = (long) sizeCommand.accept(visitor);
		System.out.println(size);
	}

	@Override
	public void resize(String fileName, long size) {
		ResizeCommand resizeCommand = new ResizeCommand(fileName, size);
		resizeCommand.addObserver(display);
		resizeCommand.accept(visitor);
	}

	@Override
	public void exit() {
		for (Command proxyDelCommand : proxyDelCommands) {
			proxyDelCommand.accept(visitor);
		}
		proxyDelCommands.clear();
		Command exitCommand = new ExitCommand();
		exitCommand.accept(visitor);
	}

	@Override
	public FileSystem getFileSystemDisk() {
		return fileSystemDisk;
	}

}
