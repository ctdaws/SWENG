public class Defaults {
  private Fonts fontDefault;
  private Colors colorDefault;

  public Defaults(Fonts fontDefault, Colors colorDefault) {
    this.fontDefault = fontDefault;
    this.colorDefault = colorDefault;
  }

  public void setDefaultFonts(Fonts fontDefault) { this.fontDefault = fontDefault; }

  public Fonts getDefaultFonts() { return this.fontDefault; }

  public void setDefaultColors(Colors colorDefault) { this.colorDefault = colorDefault; }

  public Colors getDefaultColors() { return this.colorDefault; }
}
