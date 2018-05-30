import javafx.scene.control.Button;

public class LQButton extends LQMedia<Button>{

    private Button button;
    private String path;

    @Override
    public Button getLQMedia() { return this.button; }

    public LQButton(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions) {
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
        this.button = new Button();
        this.button.setLayoutX(pwsPosition.getX());
        this.button.setLayoutY(pwsPosition.getY());
        this.button.setStyle("-fx-min-width: " + pwsPosition.getWidth() + "px; " + "-fx-min-height: " + pwsPosition.getHeight() + "px; ");
    }

    public LQButton(String id, PWSPosition pwsPosition, PWSTransitions pwsTransitions, String path) {
        this.setId(id);
        this.setPwsPosition(pwsPosition);
        this.setPwsTransitions(pwsTransitions);
        this.button = new Button();
        this.button.setLayoutX(pwsPosition.getX());
        this.button.setLayoutY(pwsPosition.getY());
        this.button.setStyle("-fx-min-width: " + pwsPosition.getWidth() + "px; " + "-fx-min-height: " + pwsPosition.getHeight() + "px; " + "-fx-border: none; " + "-fx-background-color: transparent; " + "-fx-background-image: url('" + path + "'); " + "-fx-background-size: " + pwsPosition.getWidth() + "px " + pwsPosition.getHeight() + "px;");
        this.path = path;
    }

    public void add(String buttonText) { this.button.setText(buttonText); }

    public Button getLQButton() { return this.button; }

    public String toString() {
        return "LQButton:\nid = " + this.getId() + "\nbackground image = " + this.path + "\n" + this.getPwsPosition();
    }
}
