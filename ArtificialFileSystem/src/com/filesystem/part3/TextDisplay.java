package com.filesystem.part3;

/**
 * @author yadhi
 *
 */
public class TextDisplay implements Display {

	private String text;

	public TextDisplay(String text) {
		this.text = text;
	}

	@Override
	public void display() {
		System.out.println(text);
	}

}
