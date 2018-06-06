import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.awt.font.TextAttribute;

/**
 * Media handler for text. Wrapper for a JavaFX TextFlow
 *
 * @author Samuel Broughton
 * @version 2.3
 */
public class PWSText extends PWSMedia<TextFlow> {
//    JavaFX TetFlow
    private TextFlow textFlow;
//    Text formatting
    private PWSFonts pwsFonts;
    private PWSColors pwsColors;

    /**
     * Implementation of abstract method inherited from PWSMedia
     * @return JavaFX TextFlow created by this class
     */
    @Override
    public TextFlow getPwsMedia() { return this.textFlow; }

    /**
     * Constructor for PWSText
     * @param id ID for identifing this object
     * @param pwsPosition required by PWSMedia, describes position and size of TextFlow
     * @param pwsTransitions required by PWSMedia, contains animtion timings
     * @param pwsFonts font formatting for plain text
     * @param pwsColors text colour for plain text
     */
    public PWSText(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions, PWSFonts pwsFonts, PWSColors pwsColors) {
//        Set variables inherited from PWSMedia
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
//        Create new TextFlow
        this.textFlow = new TextFlow();
//        Set top-left position of the textFlow
        this.textFlow.setLayoutX(pwsPosition.getX());
        this.textFlow.setLayoutY(pwsPosition.getY());
//        Set absolute width of TextFlow
        this.textFlow.setMinWidth(pwsPosition.getWidth());
        this.textFlow.setMaxWidth(pwsPosition.getWidth());
//        Set text alignment within the textFlow
        this.textFlow.setTextAlignment(pwsFonts.getAlign());
//        Store colours/font formatting
        this.pwsColors = pwsColors;
        this.pwsFonts = pwsFonts;
//        Generate animations from timing information
        this.setTransition(pwsTransitions);
    }

    /**
     * Generates timeline for showing.hiding text
     * @param pwsTransitions timing information for timeline
     */
    public void setTransition(PWSTransitions pwsTransitions) {
//        Create new timeline
        Timeline timeline = new Timeline();
//        Is the text set to be triggered manually
        if(pwsTransitions.isTriggered()) {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
//                Hide the text
                this.getPwsMedia().setVisible(false);
//                Stop the timeline from continuing
                this.getTimeline().stop();
            }));
        }
//        Text is automatic
        else {
            timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, "auto", (ActionEvent event) -> {
//                Hist the text
                this.getPwsMedia().setVisible(false);
            }));
        }
//        Set text to appear after 'start' number of seconds
        timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getStart(), "trigger", (ActionEvent event) -> {
            this.getPwsMedia().setVisible(true);
        }));
//        If the duration is set at a positive integer of seconds
        if(this.getPwsTransitions().getPwsDuration() >= 0) {
//            Hide the text after 'duration' number of seconds
            timeline.getKeyFrames().add(new KeyFrame(this.getPwsTransitions().getDuration(), "trigger", (ActionEvent event) -> {
                this.getPwsMedia().setVisible(false);
            }));
        }
//        Add timeline to media
        this.setTimeline(timeline);
    }

    /**
     * Getter for font formatting
     * @return font formatting in PWSFonts object
     */
    public PWSFonts getPwsFonts() { return this.pwsFonts; }

    /**
     * Getter for colours
     * @return colours in PWSColors object
     */
    public PWSColors getPwsColors() { return this.pwsColors; }

    /**
     * Method for adding Text to the TextFlow
     * @param textSnippet formatted textSnippet containing a JavaFX Text object
     */
    private void addSnippet(TextSnippet textSnippet) { this.textFlow.getChildren().add(textSnippet.getText()); }

    /**
     * Method for adding formatted text (colour, font) to the textFLow
     * @param textString text string to add
     * @param pwsColors colour formatting of the text
     * @param pwsFonts font formatting of the text
     */
    public void add(String textString, PWSColors pwsColors, PWSFonts pwsFonts) { this.addSnippet(new TextSnippet(textString, pwsColors, pwsFonts)); }

    /**
     * Method for adding formatted text (colour) to the textFlow
     * @param textString text string to add
     * @param pwsColors colour formatting of the text
     */
    public void add(String textString, PWSColors pwsColors) { this.addSnippet(new TextSnippet(textString, pwsColors, this.pwsFonts)); }

    /**
     * Method for adding formatted text (font) to the TextFlow
     * @param textString text string to add
     * @param pwsFonts font formatting of the text
     */
    public void add(String textString, PWSFonts pwsFonts) { this.addSnippet(new TextSnippet(textString, this.pwsColors, pwsFonts)); }

    /**
     * Method for adding plain text to the TextFlow, uses default formatting of the TextFlow
     * @param textString text string to add
     */
    public void add(String textString) { this.addSnippet(new TextSnippet(textString, this.pwsColors, this.pwsFonts)); }

    /**
     * Method for removing all text from the TextFlow
     */
    public void clear() { this.textFlow.getChildren().clear(); }

    /**
     * Internal class for creating JavaFX Text objects
     */
    public class TextSnippet {
//        JavaFX Text object
        private Text text;
//        Text formatting
        private PWSColors pwsColors;
        private PWSFonts pwsFonts;

        /**
         * Constructor for new TextSnippet
         * @param textString text string to add
         * @param pwsColors colour formatting
         * @param pwsFonts font formatting
         */
        TextSnippet(String textString, PWSColors pwsColors, PWSFonts pwsFonts) {
//            Create new text with given strng
            this.text = new Text(textString);
//            Store ormatting
            this.pwsColors = pwsColors;
            this.pwsFonts = pwsFonts;
//            Set underline
            this.text.setUnderline(this.pwsFonts.getPwsUnderline());
//            Set colour
            this.text.setFill(this.pwsColors.getColor());
//            Text position (normal, superscript, subscript)
            switch(this.pwsFonts.getLQPosition()) {
//                Superscript
                case "superscript":
//                    Create text with 1/2 size
                    this.text.setFont(Font.font(this.pwsFonts.getPwsFont(), this.pwsFonts.getBold(), this.pwsFonts.getItalic(), this.pwsFonts.getPwsTextsize()/2));
                    this.text.setTextOrigin(VPos.CENTER);
//                    Shift text position up
                    this.text.setTranslateY(this.pwsFonts.getPwsTextsize() * -0.5);
                    break;
//                    Subscript
                case "subscript":
//                    Create text with 1/2 size
                    this.text.setFont(Font.font(this.pwsFonts.getPwsFont(), this.pwsFonts.getBold(), this.pwsFonts.getItalic(), this.pwsFonts.getPwsTextsize()/2));
                    this.text.setTextOrigin(VPos.BASELINE);
//                    Shift text position down
                    this.text.setTranslateY(this.pwsFonts.getPwsTextsize() * 0.1);
                    break;
//                    Normal
                default:
                    this.text.setFont(Font.font(this.pwsFonts.getPwsFont(), this.pwsFonts.getBold(), this.pwsFonts.getItalic(), this.pwsFonts.getPwsTextsize()));
                    break;
            }
        }

        /**
         * Method for retrieving Text object from textSnippet
         * @return JavaFX Text object with formatting
         */
        public Text getText() { return this.text; }

        /**
         * Method for replacing the Text with a new one
         * @param text New text string to replace the old one
         */
        public void setText(String text) { this.text = new Text(text); }
    }
}
