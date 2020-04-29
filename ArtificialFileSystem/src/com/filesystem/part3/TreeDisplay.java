package com.filesystem.part3;

import java.util.List;
import java.util.Observer;

import com.filesystem.part1.Component;

/**
 * @author yadhi
 *
 */
public interface TreeDisplay extends Observer {

	public List<Component> getChildren(Component entity);

	public String createGraphicNode(Component entity);

	public void display();

	public void buildTree(Component entity);
}
