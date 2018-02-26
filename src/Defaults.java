public class Defaults {
  private Fonts fontDefault;
  private Colors colorDefault;

  public Defaults(Fonts fontDefault, Colors colorDefault) {
    this.fontDefault = fontDefault;
    this.colorDefault = colorDefault;
  }

  public void setDefaultFonts(Fonts fontDefault) {
    this.fontDefault = fontDefault;
  }

  public void setDefaultColors(Colors colorDefault) {
    this.colorDefault = colorDefault;
  }
}
