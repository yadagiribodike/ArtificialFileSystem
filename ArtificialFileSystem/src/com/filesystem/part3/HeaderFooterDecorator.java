package com.filesystem.part3;

/**
 * @author yadhi
 *
 */
public class HeaderFooterDecorator extends TextDisplayDecorator {
	
	private String headerText;
	private String footerText;

	public HeaderFooterDecorator(Display display, String headerText, String footerText) {
		super(display);
		this.headerText = headerText;
		this.footerText = footerText;
	}

	@Override
	public void display() {
		System.out.println(headerText);
		textDisplay.display();
		System.out.println(footerText);
	}

}
