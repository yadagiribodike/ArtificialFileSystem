package com.filesystem.part2;

public class SizeCommand implements Command {

	private String name;

	public SizeCommand(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public Long accept(CommandVisitor visitor) {
		return visitor.visit(this);
	}

}
