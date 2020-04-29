package com.filesystem.part1;

/**
 * @author yadhi
 *
 */
public interface Builder {

	//List of operations in FileSystem 
	public void cd(String dirName);

	public void mkdir(String dirName);

	public void create(String fileName, long size);

	public void del(String name);

	public void ls();
	
	public void ls(String name);

	public void size(String name);

	public void resize(String fileName, long size);

	public void exit();

	public FileSystem getFileSystemDisk();
}