public class FLDefaults {
  private FLFonts fontDefault;
  private FLColors colorDefault;

  public FLDefaults(FLFonts fontDefault, FLColors colorDefault) {
    this.fontDefault = fontDefault;
    this.colorDefault = colorDefault;
  }

  public void setDefaultFonts(FLFonts fontDefault) {
    this.fontDefault = fontDefault;
  }

  public void setDefaultColors(FLColors colorDefault) {
    this.colorDefault = colorDefault;
  }
}
