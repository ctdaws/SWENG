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

	public String getFontFamily() { return this.fontFamily; }

	public int getSize() { return this.size; }

	public boolean getItalic() { return this.isItalic; }

	public boolean getBold() { return this.isBold; }

	public boolean getUnderlined() { return this.isUnderlined; }

}
