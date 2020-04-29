package com.filesystem.part2;

import java.util.Observable;
import java.util.Observer;

/**
 * @author yadhi
 *
 */
public class ResizeCommand extends Observable implements Command {

	private String name;
	private long size;

	public ResizeCommand(String name, long size) {
		this.name = name;
		this.size = size;
	}

	public String getName() {
		return this.name;
	}

	public long getSize() {
		return this.size;
	}

	public void addObserver(Observer observer) {
		super.addObserver(observer);
	}

	@Override
	public Boolean accept(CommandVisitor visitor) {
		boolean b = visitor.visit(this);
		this.setChanged();
		this.notifyObservers();
		return b;
	}

}
