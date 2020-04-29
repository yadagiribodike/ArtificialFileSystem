package com.filesystem.part3;

/**
 * @author yadhi
 *
 */
public abstract class TextDisplayDecorator implements Display {

	protected Display textDisplay;

	public TextDisplayDecorator(Display textDisplay) {
		this.textDisplay = textDisplay;
	}
}
