public class FLFonts {
	private String fontFamily;
	private int size;
	private boolean isItalic;
	private boolean isBold;
	private boolean isUnderlined;
	
	public setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
	
	public getFontFamily() {
		return this.fontFamily;
	}
	
	public setSize(int size) {
		this.size = size;
	}
	
	public getSize() {
		return this.size;
	}
	
	public setItalic(boolean italic) {
		this.isItalic = italic;
	}
	
	public getItalic() {
		return this.isItalic;
	}
	
	public setBold(boolean bold) {
		this.isBold = bold;
	}
	
	public getBold() {
		return this.isBold;
	}
	
	public setUnderlined(boolean underlined) {
		this.isUnderlined = underlined;
	}
	
	public getUnderlined() {
		return this.isUnderlined;
	}
}
