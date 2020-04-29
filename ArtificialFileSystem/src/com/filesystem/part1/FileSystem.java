package com.filesystem.part1;

/**
 * @author yadhi
 *
 */
public final class FileSystem {

	private static FileSystem fileSystemDisk;

	private final Directory rootDir;
	private Directory currentDir;

	public static FileSystem getFileSystemDisk() {
		if (fileSystemDisk == null) {
			synchronized (FileSystem.class) {
				fileSystemDisk = new FileSystem();
			}
		}
		return fileSystemDisk;
	}

	private FileSystem() {
		rootDir = new Directory("root", null);
		currentDir = rootDir;
	}
	
	public Directory getRootDir() {
		return this.rootDir;
	}

	public String pwd() {
		return "[" + currentDir.getDirPath() + "]$ ";
	}

	public boolean explore(String name) {
		if (name.equals("..")) {
			if (currentDir == rootDir) {
				return false;
			} else {
				currentDir = currentDir.getParentDir();
				return true;
			}
		} else {
			Component entity = currentDir.getChildren(name);
			if (entity != null && entity.getType() == DirOrFileComponent.DIR) {
				currentDir = (Directory) entity;
				return true;
			}
			return false;
		}
	}

	public boolean addDir(String name) {
		Component entity = currentDir.getChildren(name);
		if (entity == null || entity.getType() != DirOrFileComponent.FILE) {
			Directory dir = new Directory(name, currentDir);
			currentDir.addChildren(dir);
			return true;
		}
		return false;
	}

	public boolean addFile(String name, long size) {
		Component entity = currentDir.getChildren(name);
		if (entity == null || entity.getType() != DirOrFileComponent.DIR) {
			File file = new File(name, currentDir, size);
			currentDir.addChildren(file);
			return true;
		}
		return false;
	}

	public boolean removeEntity(String name) {
		Component entity = currentDir.getChildren(name);
		if (entity != null) {
			currentDir.removeChildren(entity);
			return true;
		}
		return false;
	}
	
	public boolean removeEntityByPath(String path) {
		Directory tempDir = this.rootDir;
		String[] paths = path.split("/");
		for(int i=1; i<paths.length-1; i++) {
			tempDir = (Directory) tempDir.getChildren(paths[i]);
		}
		tempDir.removeChildren(tempDir.getChildren(paths[paths.length-1]));
		return true;
	}
	
	public Component findEntity(String name) {
		return currentDir.getChildren(name);
	}

	public String getCurrentDirInfo() {
		String list = "";
		for (Component entity : currentDir.getChildrenList()) {
			list += entity.toString() + "\n";
		}
		return list;
	}

	public String getInfo(String name) {
		Component entity = currentDir.getChildren(name);
		if (entity == null) {
			return "\"" + name + "\" does not exists.";
		}
		if (entity.getType() == DirOrFileComponent.FILE) {
			return entity.toString();
		} else {
			String list = "";
			for (Component e : ((Directory) entity).getChildrenList()) {
				list += e.toString() + "\n";
			}
			return list;
		}
	}

	public long getSize(String name) {
		Component entity = currentDir.getChildren(name);
		return entity.getSize();
	}

	public boolean resize(String fileName, long size) {
		Component entity = currentDir.getChildren(fileName);
		if (entity == null || entity.getType() == DirOrFileComponent.DIR) {
			return false;							//returns false if entity is a directory
		} else {
			((File) entity).setSize(size);			//resize the file
			return true;
		}
	}
	
	public void exit() {
		currentDir = rootDir;
	}
}
