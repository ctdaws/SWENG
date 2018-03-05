public class Defaults {
  private TextStyle fontDefault;
  private Colors colorDefault;

  public Defaults(TextStyle fontDefault, Colors colorDefault) {
    this.fontDefault = fontDefault;
    this.colorDefault = colorDefault;
  }

  public void setDefaultFonts(TextStyle fontDefault) { this.fontDefault = fontDefault; }

  public TextStyle getDefaultFonts() { return this.fontDefault; }

  public void setDefaultColors(Colors colorDefault) { this.colorDefault = colorDefault; }

  public Colors getDefaultColors() { return this.colorDefault; }
}
