public class Defaults {
<<<<<<< HEAD
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
=======
  private TextStyle styleDefault;
  private Colors colorDefault;

  public Defaults(TextStyle styleDefault, Colors colorDefault) {
    this.styleDefault = styleDefault;
    this.colorDefault = colorDefault;
  }

  public void setDefaultStyle(TextStyle styleDefault) { this.styleDefault = styleDefault; }

  public TextStyle getDefaultStyle() { return this.styleDefault; }

  public void setDefaultColors(Colors colorDefault) { this.colorDefault = colorDefault; }

  public Colors getDefaultColors() { return this.colorDefault; }
>>>>>>> master
}
