package Old;

import javafx.scene.text.*;
import javafx.scene.text.Font;

public class TextStyle {

	private String fontFamily;
	private int size;
	private boolean isItalic;
	private boolean isBold;
	private boolean isUnderlined;

	public TextStyle(String fontFamily, int size, boolean bold, boolean italic, boolean underlined) {
		Font.loadFont(this.getClass().getResource("fonts/BebasNeue-Regular.ttf").toExternalForm(), 20);
		this.fontFamily = fontFamily;
		this.size = size;
		this.isItalic = italic;
		this.isBold = bold;
		this.isUnderlined = underlined;
	}

	public TextStyle(TextStyle newStyle) {
		Font.loadFont(this.getClass().getResource("fonts/BebasNeue-Regular.ttf").toExternalForm(), 20);
		this.fontFamily = newStyle.getFontFamily();
		this.size = newStyle.getSize();
		this.isItalic = newStyle.isItalic();
		this.isBold = newStyle.isBold();
		this.isUnderlined = newStyle.getUnderlined();
	}

	public String getFontFamily() { return this.fontFamily; }
	public void setFontFamily(String font) { this.fontFamily = font; }

	public int getSize() { return this.size; }
	public void setSize(int size) {this.size = size; }

	public FontPosture getItalic() {
		if(this.isItalic) {
			return FontPosture.ITALIC;
		} else {
			return FontPosture.REGULAR;
		}
	}
	public boolean isItalic() { return this.isItalic; }
	public void setItalic(boolean italic) { this.isItalic = italic; }

	public FontWeight getBold() {
		if(this.isBold) {
			return FontWeight.BOLD;
		} else {
			return FontWeight.NORMAL;
		}
	}
	public boolean isBold() { return this.isBold; }
	public void setBold(boolean bold) { this.isBold = bold; }

	public boolean getUnderlined() { return this.isUnderlined; }
	public void setUnderlined(boolean underlined) { this.isUnderlined = underlined; }
}
