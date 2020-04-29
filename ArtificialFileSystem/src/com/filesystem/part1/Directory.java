package com.filesystem.part1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yadhi
 *
 */
public class Directory implements Component {

	private String name;
	private Directory parentDir;
	private List<Component> childrenList;
	private String dirPath;

	public Directory(String name, Directory parentDir) {
		this.name = name;
		this.parentDir = parentDir;
		this.childrenList = new ArrayList<Component>();
		this.dirPath = parentDir != null ? parentDir.getDirPath() : "";
	}

	public String getDirPath() {
		return dirPath + this.name + "/";
	}

	public void addChildren(Component children) {
		this.childrenList.add(children);
	}

	public void removeChildren(Component children) {
		this.childrenList.remove(children);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public DirOrFileComponent getType() {
		return DirOrFileComponent.DIR;
	}

	@Override
	public Directory getParentDir() {
		return this.parentDir;
	}

	@Override
	public long getSize() {
		long size = 0l;
		for (Component entity : childrenList) {
			size += entity.getSize();
		}
		return size;
	}

	@Override
	public Component getChildren(String name) {
		for (Component entity : childrenList) {
			if (entity.getName().equals(name)) {
				return entity;
			}
		}
		return null;
	}

	public List<Component> getChildrenList() {
		return this.childrenList;
	}

	@Override
	public String toString() {
		return name + "\t<" + getType() + ">";
	}

}
