public class Fonts {

	private String fontFamily;
	private int size;
	private boolean isItalic;
	private boolean isBold;
	private boolean isUnderlined;

	public Fonts(String fontFamily, int size, boolean italic, boolean bold, boolean underlined) {
		this.fontFamily = fontFamily;
		this.size = size;
		this.isItalic = italic;
		this.isBold = bold;
		this.isUnderlined = underlined;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	public String getFontFamily() {
		return this.fontFamily;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return this.size;
	}

	public void setItalic(boolean italic) {
		this.isItalic = italic;
	}

	public boolean getItalic() {
		return this.isItalic;
	}

	public void setBold(boolean bold) {
		this.isBold = bold;
	}

	public boolean getBold() {
		return this.isBold;
	}

	public void setUnderlined(boolean underlined) {
		this.isUnderlined = underlined;
	}

	public boolean getUnderlined() {
		return this.isUnderlined;
	}

}
