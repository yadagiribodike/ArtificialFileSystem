package com.filesystem.part3;

import java.util.List;
import java.util.Observable;

import com.filesystem.part1.Component;
import com.filesystem.part1.DirOrFileComponent;
import com.filesystem.part1.Directory;
import com.filesystem.part1.File;
import com.filesystem.part1.FileSystem;

/**
 * @author yadhi
 *
 */
public class DirectoryTreeDisplay implements TreeDisplay {

	private static final String HEADER_TEXT = "|================================================|\n|+++++++++++++++ Java File System +++++++++++++++|\n|================================================|";
	private static final String FOOTER_TEXT = "|================================================|";

	private FileSystem fileSystemDisk;
	private String hierarchy;

	public DirectoryTreeDisplay() {
		fileSystemDisk = FileSystem.getFileSystemDisk();
	}

	@Override
	public List<Component> getChildren(Component node) {
		if (node.getType() == DirOrFileComponent.FILE) {
			return null;
		} else {
			return ((Directory) node).getChildrenList();
		}
	}

	@Override
	public String createGraphicNode(Component node) {
		if (node.getType() == DirOrFileComponent.FILE) {
			return "|" + ((File) node).getFilePath() + "\t" + node.getSize()+"(size)";
		} else {
			return "|" + ((Directory) node).getDirPath() + "\t" + node.getSize()+"(size)";
		}
	}

	public void addGraphicNode(String stringNode) {
		hierarchy += stringNode + "\n";
	}

	@Override
	public void display() {
		hierarchy = "";
		buildTree(fileSystemDisk.getRootDir());
		Display display = new HeaderFooterDecorator(new TextDisplay(hierarchy.trim()), HEADER_TEXT, FOOTER_TEXT);
		display.display();
	}

	@Override
	public void buildTree(Component node) {
		List<Component> children = getChildren(node);
		if (children != null) {
			for (Component child : children) {
				addGraphicNode(createGraphicNode(child));
				buildTree(child);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		this.display();
	}

}
