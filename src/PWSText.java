import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PWSText extends PWSMedia<TextFlow> {

    private TextFlow textFlow;

    private PWSFonts pwsFonts;
    private PWSColors pwsColors;

    @Override
    public TextFlow getPwsMedia() { return this.textFlow; }

    public PWSText(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions, PWSFonts pwsFonts, PWSColors pwsColors) {
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
        this.textFlow = new TextFlow();
        this.textFlow.setLayoutX(pwsPosition.getX());
        this.textFlow.setLayoutY(pwsPosition.getY());
        this.textFlow.setMinWidth(pwsPosition.getWidth());
        this.textFlow.setMaxWidth(pwsPosition.getWidth());
        this.textFlow.setTextAlignment(pwsFonts.getAlign());
        this.pwsColors = pwsColors;
        this.pwsFonts = pwsFonts;
    }

    public PWSFonts getPwsFonts() { return this.pwsFonts; }

    public PWSColors getPwsColors() { return this.pwsColors; }

    private void addSnippet(TextSnippet textSnippet) { this.textFlow.getChildren().add(textSnippet.getText()); }

    public void add(String textString, PWSColors pwsColors, PWSFonts pwsFonts) { this.addSnippet(new TextSnippet(textString, pwsColors, pwsFonts)); }

    public void add(String textString, PWSColors pwsColors) { this.addSnippet(new TextSnippet(textString, pwsColors, this.pwsFonts)); }

    public void add(String textString, PWSFonts pwsFonts) { this.addSnippet(new TextSnippet(textString, this.pwsColors, pwsFonts)); }

    public void add(String textString) { this.addSnippet(new TextSnippet(textString, this.pwsColors, this.pwsFonts)); }

    public class TextSnippet {

        private Text text;
        private PWSColors pwsColors;
        private PWSFonts pwsFonts;

        public TextSnippet(String textString, PWSColors pwsColors, PWSFonts pwsFonts) {
            this.text = new Text(textString);
            this.pwsColors = pwsColors;
            this.pwsFonts = pwsFonts;

            this.text.setFont(Font.font(this.pwsFonts.getPwsFont(), this.pwsFonts.getBold(), this.pwsFonts.getItalic(), this.pwsFonts.getPwsTextsize()));
            this.text.setUnderline(this.pwsFonts.getPwsUnderline());
            this.text.setFill(this.pwsColors.getColor());

            switch(this.pwsFonts.getLQPosition()) {
                case "superscript":
                    this.text.setStyle("-fx-text-origin: top;");
//                    System.out.println("Setting superscript.");
                    break;
                case "subscript":
                    this.text.setStyle("-fx-text-origin: bottom;");
//                    System.out.println("Setting subscript.");
                    break;
            }
        }

        public Text getText() { return this.text; }
    }
}
