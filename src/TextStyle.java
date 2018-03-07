import javafx.scene.text.*;

public class TextStyle {

	private String fontFamily;
	private int size;
	private boolean isItalic;
	private boolean isBold;
	private boolean isUnderlined;

	public TextStyle(String fontFamily, int size, boolean bold, boolean italic, boolean underlined) {
		this.fontFamily = fontFamily;
		this.size = size;
		this.isItalic = italic;
		this.isBold = bold;
		this.isUnderlined = underlined;
	}

	public String getFontFamily() { return this.fontFamily; }

	public int getSize() { return this.size; }

	public FontPosture getItalic() {
		if(this.isItalic) {
			return FontPosture.ITALIC;
		} else {
			return FontPosture.REGULAR;
		}
	}

	public FontWeight getBold() {
		if(this.isBold) {
			return FontWeight.BOLD;
		} else {
			return FontWeight.NORMAL;
		}
	}

	public boolean getUnderlined() { return this.isUnderlined; }

}
